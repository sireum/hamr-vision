::/*#! 2> /dev/null                                   #
@ 2>/dev/null # 2>nul & echo off & goto BOF           #
if [ -z "${SIREUM_HOME}" ]; then                      #
  echo "Please set SIREUM_HOME env var"               #
  exit -1                                             #
fi                                                    #
exec "${SIREUM_HOME}/bin/sireum" slang run "$0" "$@"  #
:BOF
setlocal
if not defined SIREUM_HOME (
  echo Please set SIREUM_HOME env var
  exit /B -1
)
"%SIREUM_HOME%\bin\sireum.bat" slang run "%0" %*
exit /B %errorlevel%
::!#*/
// #Sireum

import org.sireum._
import org.sireum.project.ProjectUtil._
import org.sireum.project.{Project, ProjectUtil}
import org.sireum.project.PublishInfo.Developer

val vision = "hamr-vision"

val homeDir = Os.slashDir.up.canon

val catLiang: Developer = Developer(id = "catlisantos", name = "Catalina Liang")

val library = "library"

var visionJvm = moduleJvmPub(
  id = vision,
  baseDir = homeDir,
  jvmDeps = id(library),
  jvmIvyDeps = ISZ(),
  pubOpt = pub(
    desc = "HAMR Vision",
    url = "github.com/sireum/hamr-vision",
    licenses = bsd2,
    devs = ISZ(catLiang)
  )
)

val project = Project.empty + visionJvm

projectCli(Os.cliArgs, project)
