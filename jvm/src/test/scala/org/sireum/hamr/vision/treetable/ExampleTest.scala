package org.sireum.hamr.vision.treetable

import org.sireum.Random.Gen64
import org.sireum.Random.Impl.Xoshiro256
import org.sireum._
import org.sireum.hamr.vision.value._
import org.sireum.hamr.vision.value.{RandomLib}
import org.sireum.test.TestSuite

import java.awt._
import java.awt.event._
import javax.swing._

class ExampleTest extends TestSuite {

  val isCI: B = Os.env("GITLAB_CI").nonEmpty || Os.env("GITHUB_ACTIONS").nonEmpty || Os.env("BUILD_ID").nonEmpty

  "Live Feed" in {
    if (!isCI) {//Os.env("__CFBundleIdentifier") == Some(string"com.jetbrains.intellij.ce")) {
      val seedGen: Gen64 = Random.Gen64Impl(Xoshiro256.create)

      val freshRandomLib: RandomLib = {
        val r = RandomLib(Random.Gen64Impl(Xoshiro256.createSeed(seedGen.genU64())))

        // TODO: should slangcheck reject Value's children who introduce recursion?
        //       issue is their next methods will likely lead to stack overflows

        // TODO: shouldn't there be a config for strings so that we can set
        //       the max number of characters to generate?
        r.set_numElement(10)

        // TODO: seems like nextC could have a from/between.  This issue with filters
        //       is small bounds leads to failures to gen a random c that satisfies
        //       the bound
        r.set_Config_C(r.get_Config_C()(filter = c => c.value >= 32 && c.value <= 55639))
      }

      def nextString(): String = {
        while (true) {
          try {
            return freshRandomLib.nextString()
          } catch {
            case e: Throwable =>
          }
        }
        halt("Infeasible")
      }

      val entries: ISZ[Entry] = ISZ(
        Category(
          "Component 0",
          ISZ(
            Category(
              "Input",
              ISZ(
                Row("110", ISZ(StringValue("In 0"), StringValue("This is an input port"), StringValue("Should he write it down? That was the question running through his mind. He couldn't believe what had just happened and he knew nobody else would believe him as well. Even if he documented what had happened by writing it down, he still didn't believe anyone would still believe it. So the question remained. Was it be worth it to actually write it down?"))),
                Row("111", ISZ(StringValue("In 1"), StringValue("I am another port"), StringValue("")))
              )
            ),
            Category(
              "Output",
              ISZ(
                Row("112", ISZ(StringValue("Out 0"), StringValue("This is an Output"), StringValue(""))),
                Row("113", ISZ(StringValue("Out 1"), StringValue("Haha robot talk"), StringValue("")))
              )
            )
          )
        ),
        Category(
          "Component 1",
          ISZ(
            Category(
              "Input 1",
              ISZ(
                Row("120", ISZ(StringValue("In 0"), StringValue("More inputs!"), StringValue(""))),
                Row("121", ISZ(StringValue("In 1"), StringValue("The bite of '87?"), StringValue("")))
              )
            ),
            Category(
              "Output 1",
              ISZ(
                Row("122", ISZ(StringValue("Out 0"), StringValue("Bendy?"), StringValue(""))),
                Row("123", ISZ(StringValue("Out 1"), StringValue("Pizza orders"), StringValue("")))
              )
            )
          )
        )
      )

      val menuBar = new JMenuBar
      val optionsMenu = new JMenu("Options")
      val colorMenu = new JMenuItem("Color Toggle")
      val colorChoice = new JMenuItem("Color Choice")
      optionsMenu.add(colorMenu)
      optionsMenu.add(colorChoice)
      menuBar.add(optionsMenu)

      val w = new Walk
      val model = new DemoTreeTableModel(w.construct(entries))
      val tt = new JTreeTable(model, w.getMap)

      val jf = new JFrame()
      jf.setTitle("Example Test")
      jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
      val jsp = new JScrollPane(tt)
      jf.add(jsp, BorderLayout.CENTER)
      jf.setJMenuBar(menuBar)
      jf.pack()
      jf.setVisible(true)

      colorMenu.addActionListener((e: ActionEvent) => {
        if (tt.getColorToggle) tt.setColorToggle(false)
        else tt.setColorToggle(true)
      })
      colorChoice.addActionListener((e: ActionEvent) => {
        tt.setColorChoice(JColorChooser.showDialog(null, "Pick a color", Color.yellow))
      })

      while (true) {
        val in1 = ISZ[Option[Value]](None(), None(), Some(StringValue(nextString().native)))
        val in2 = ISZ[Option[Value]](None(), None(), Some(StringValue(nextString().native)))

        //tt.update("110", in1)
        tt.update("111", in2)

        // let component think
        Thread.sleep(500)

        val out1 = ISZ[Option[Value]](None(), None(), Some(StringValue(nextString().native)))
        val out2 = ISZ[Option[Value]](None(), None(), Some(StringValue(nextString().native)))

        tt.update("120", out1)
        tt.update("121", out2)

        Thread.sleep(500)

        val in3 = ISZ[Option[Value]](None(), None(), Some(StringValue(nextString().native)))
        val in4 = ISZ[Option[Value]](None(), None(), Some(StringValue(nextString().native)))

        tt.update("112", in3)
        tt.update("113", in4)

        // let component think
        Thread.sleep(500)

        val out3 = ISZ[Option[Value]](None(), None(), Some(StringValue(nextString().native)))
        val out4 = ISZ[Option[Value]](None(), None(), Some(StringValue(nextString().native)))

        tt.update("122", out3)
        tt.update("123", out4)

        // wait before switching to the other component
        Thread.sleep(2000)
      }
    }
  }
}