package org.sireum.hamr.vision.treetable

import org.sireum.Random.Gen64
import org.sireum.Random.Impl.Xoshiro256
import org.sireum._
import org.sireum.hamr.vision.value._
import org.sireum.hamr.vision.value.{RandomLib, Value}
import org.sireum.test.TestSuite

import java.awt._
import java.awt.event._
import javax.swing._
import scala.collection.mutable

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

      val entries: ISZ[JEntry] = ISZ(
        new JCategory(
          "Component 0",
          ISZ(
            new JCategory(
              "Input",
              ISZ(
                new JPort("110", ISZ(StringValue("In 0"), StringValue("This is an input port"), StringValue(""))),
                new JPort("111", ISZ(StringValue("In 1"), StringValue("I am another port"), StringValue("")))
              )
            ),
            new JCategory(
              "Output",
              ISZ(
                new JPort("112", ISZ(StringValue("Out 0"), StringValue("This is an Output"), StringValue(""))),
                new JPort("113", ISZ(StringValue("Out 1"), StringValue("Haha robot talk"), StringValue("")))
              )
            )
          )
        ),
        new JCategory(
          "Component 1",
          ISZ(
            new JCategory(
              "Input 1",
              ISZ(
                new JPort("120", ISZ(StringValue("In 0"), StringValue("More inputs!"), StringValue(""))),
                new JPort("121", ISZ(StringValue("In 1"), StringValue("The bite of '87?"), StringValue("")))
              )
            ),
            new JCategory(
              "Output 1",
              ISZ(
                new JPort("122", ISZ(StringValue("Out 0"), StringValue("Bendy?"), StringValue(""))),
                new JPort("123", ISZ(StringValue("Out 1"), StringValue("Pizza orders"), StringValue("")))
              )
            )
          )
        )
      )

      val model = new DemoTreeTableModel(entries)

      val menuBar = new JMenuBar
      val optionsMenu = new JMenu("Options")
      val colorMenu = new JMenuItem("Color Toggle")
      val colorChoice = new JMenuItem("Color Choice")
      optionsMenu.add(colorMenu)
      optionsMenu.add(colorChoice)
      menuBar.add(optionsMenu)

      val tt = new JTreeTable(model)
      for (i <- 0 until entries.size.toInt) {
        tt.walk(entries(i))
      }
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
        val in1 = StringValue(nextString().native)
        val in2 = StringValue(nextString().native)

        tt.updatePort("110", in1)
        tt.updatePort("111", in2)

        // let component think
        Thread.sleep(500)

        val out1 = StringValue(nextString().native)
        val out2 = StringValue(nextString().native)

        tt.updatePort("120", out1)
        tt.updatePort("121", out2)

        Thread.sleep(500)

        val in3 = StringValue(nextString().native)
        val in4 = StringValue(nextString().native)

        tt.updatePort("112", in3)
        tt.updatePort("113", in4)

        // let component think
        Thread.sleep(500)

        val out3 = StringValue(nextString().native)
        val out4 = StringValue(nextString().native)

        tt.updatePort("122", out3)
        tt.updatePort("123", out4)

        // wait before switching to the other component
        Thread.sleep(2000)
      }
    }
  }
}