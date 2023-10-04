::/*#! 2> /dev/null                                                                                         #
@ 2>/dev/null # 2>nul & echo off & goto BOF                                                                 #
export SIREUM_HOME=$(cd -P $(dirname "$0")/.. && pwd -P)                                                    #
if [ ! -z ${SIREUM_PROVIDED_SCALA++} ]; then                                                                #
  SIREUM_PROVIDED_JAVA=true                                                                                 #
fi                                                                                                          #
"${SIREUM_HOME}/bin/init.sh" || exit $?                                                                     #
if [ -n "$COMSPEC" -a -x "$COMSPEC" ]; then                                                                 #
  export SIREUM_HOME=$(cygpath -C OEM -w -a ${SIREUM_HOME})                                                 #
  if [ -z ${SIREUM_PROVIDED_JAVA++} ]; then                                                                 #
    export PATH="${SIREUM_HOME}/bin/win/java":"${SIREUM_HOME}/bin/win/z3":"$PATH"                           #
    export PATH="$(cygpath -C OEM -w -a ${JAVA_HOME}/bin)":"$(cygpath -C OEM -w -a ${Z3_HOME}/bin)":"$PATH" #
  fi                                                                                                        #
elif [ "$(uname)" = "Darwin" ]; then                                                                        #
  if [ -z ${SIREUM_PROVIDED_JAVA++} ]; then                                                                 #
    export PATH="${SIREUM_HOME}/bin/mac/java/bin":"${SIREUM_HOME}/bin/mac/z3/bin":"$PATH"                   #
  fi                                                                                                        #
elif [ "$(expr substr $(uname -s) 1 5)" = "Linux" ]; then                                                   #
  if [ -z ${SIREUM_PROVIDED_JAVA++} ]; then                                                                 #
    if [ "$(uname -m)" = "aarch64" ]; then                                                                  #
      export PATH="${SIREUM_HOME}/bin/linux/arm/java/bin":"$PATH"                                           #
    else                                                                                                    #
      export PATH="${SIREUM_HOME}/bin/linux/java/bin":"${SIREUM_HOME}/bin/linux/z3/bin":"$PATH"             #
    fi                                                                                                      #
  fi                                                                                                        #
fi                                                                                                          #
if [ -f "$0.com" ] && [ "$0.com" -nt "$0" ]; then                                                           #
  exec "$0.com" "$@"                                                                                        #
else                                                                                                        #
  rm -fR "$0.com"                                                                                           #
  exec "${SIREUM_HOME}/bin/sireum" slang run -n "$0" "$@"                                                   #
fi                                                                                                          #
:BOF
setlocal
set SIREUM_HOME=%~dp0../
call "%~dp0init.bat" || exit /B %errorlevel%
if defined SIREUM_PROVIDED_SCALA set SIREUM_PROVIDED_JAVA=true
if not defined SIREUM_PROVIDED_JAVA set PATH=%~dp0win\java\bin;%~dp0win\z3\bin;%PATH%
set NEWER=False
if exist %~dpnx0.com for /f %%i in ('powershell -noprofile -executionpolicy bypass -command "(Get-Item %~dpnx0.com).LastWriteTime -gt (Get-Item %~dpnx0).LastWriteTime"') do @set NEWER=%%i
if "%NEWER%" == "True" goto native
del "%~dpnx0.com" > nul 2>&1
"%~dp0sireum.bat" slang run -n "%0" %*
exit /B %errorlevel%
:native
%~dpnx0.com %*
exit /B %errorlevel%
::!#*/
// #Sireum

import org.sireum._

val homeBin: Os.Path = Os.slashDir
val home: Os.Path = homeBin.up
val sireum: Os.Path = homeBin / (if (Os.isWin) "sireum.bat" else "sireum")

val proyekName: String = "sireum-proyek"

def usage(): Unit = {
  println(
    st"""HAMR Vision /build
        |Usage: ( tipe | test | compile | m2 |
        |         ive  | setup |
        |         regen-tools)
        |""".render)
}

def clone(repo: String, proj: String, location: Option[String]): B = {
  val loc: Os.Path = location match {
    case Some(l) => home / l
    case _ => home / proj
  }
  val ret: B = if (!loc.exists) {
    val args = ISZ[String]("git", "clone", "--recurse", s"${repo}/$proj") ++ (if (location.nonEmpty) ISZ(location.get) else ISZ[String]())
    Os.proc(args).at(home).echo.console.timeout(10000).run().ok
  } else {
    Os.proc(ISZ("git", "pull")).at(loc).echo.console.run().ok
  }
  return ret
}

def cloneProjects(): Unit = {
  ISZ[String]("runtime").foreach((p: String) => {
    clone("https://github.com/sireum", p, None()); println()
  })
}

def tipe(): Unit = {
  println("Slang type checking ...")
  Os.proc(ISZ(sireum.string, "proyek", "tipe", "--par", "--strict-aliasing", home.string)).
    at(home).console.runCheck()
  println()
}

def compile(): Unit = {
  tipe()

  println("Compiling ...")
  proc"$sireum proyek compile -n $proyekName --par --sha3 .".at(home).console.runCheck()
  println()
}

def test(): Unit = {

  tipe()

  println(s"Testing ...")

  proc"$sireum proyek test -n $proyekName --par --sha3 . org.sireum.hamr.vision".at(home).console.runCheck()
  println()
}

def regenTools(): Unit = {
  val valueRoot = home / "jvm" / "src" / "main" / "scala" / "org" / "sireum" / "hamr" / "vision" / "value"
  val dataFiles = valueRoot / "Value.scala"
  val vpackage = "org.sireum.hamr.vision.value"

  proc"$sireum tools sergen -p $vpackage -m json,msgpack -o $valueRoot $dataFiles".at(home).echo.console.runCheck()

  //proc"$sireum tools slangcheck generator -p $vpackage -o $valueRoot $dataFiles".at(home).echo.console.runCheck()

  proc"$sireum proyek slangcheck -p $vpackage -o $valueRoot $home $dataFiles".at(home).echo.console.runCheck()
}

def m2(): Os.Path = {
  tipe()

  val repository = Os.home / ".m2" / "repository"
  val visionRepo = repository / "org" / "sireum" / "hamr-vision"
  visionRepo.removeAll()
  proc"$sireum proyek publish -n $proyekName --target jvm --par --sha3 --ignore-runtime --m2 ${repository.up.canon} . org.sireum.kekinian".at(home).console.run()

  return visionRepo
}

def setup(): Unit = {
  cloneProjects()
}

def isCI(): B = {
  return Os.env("GITLAB_CI").nonEmpty || Os.env("GITHUB_ACTIONS").nonEmpty || Os.env("BUILD_ID").nonEmpty
}

if (Os.cliArgs.isEmpty) {
  usage()
  Os.exit(0)
}

for (i <- 0 until Os.cliArgs.size) {
  Os.cliArgs(i) match {
    case string"compile" =>
      setup()
      compile()
    case string"test" =>
      setup()
      test()
    case string"tipe" =>
      setup()
      tipe()
    case string"ive" =>
      setup()
      proc"$sireum proyek ive .".at(home).echo.console.runCheck()
    case string"m2" =>
      setup()
      m2()
    case string"regen-tools" =>
      regenTools()
    case string"setup" =>
      setup()
    case string"-h" => usage()
    case string"--help" => usage()
    case cmd =>
      usage()
      eprintln(s"Unrecognized command: $cmd")
      Os.exit(-1)
  }
}
