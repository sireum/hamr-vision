package org.sireum.hamr.vision.treetable

import org.sireum.Random.Gen64
import org.sireum.Random.Impl.Xoshiro256
import org.sireum._
import org.sireum.hamr.vision.value.RandomLib
import org.sireum.test.TestSuite

import java.awt.BorderLayout
import javax.swing.{JFrame, JScrollPane}

class ExampleTest extends TestSuite {

  val isCI: B = Os.env("GITLAB_CI").nonEmpty || Os.env("GITHUB_ACTIONS").nonEmpty || Os.env("BUILD_ID").nonEmpty

  "Live Feed" in {
    if (!isCI && Os.env("__CFBundleIdentifier") == Some(string"com.jetbrains.intellij.ce")) {
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

      val (model, elems): (DemoTreeTableModelSC, ISZ[compSC]) = {
        val input = Array[InputSC](
          new InputSC(Array[Object]("In 0", "", "This is an input port")),
          new InputSC(Array[Object]("In 1", "", "I am another port")))
        val output = Array[OutputSC](
          new OutputSC(Array[Object]("Out 0", "", "This is an Output")),
          new OutputSC(Array[Object]("Out 1", "", "Haha robot talk")))
        val component = new compSC(new InputsSC(input), new OutputsSC(output), "Component 0")
        val input2 = Array[InputSC](
          new InputSC(Array[Object]("In 0", "", "More inputs!")),
          new InputSC(Array[Object]("In 1", "", "The bite of '87?")))
        val output2 = Array[OutputSC](
          new OutputSC(Array[Object]("Out 0", "", "Bendy?")),
          new OutputSC(Array[Object]("Out 1", "", "Pizza orders")))
        val component2 = new compSC(new InputsSC(input2), new OutputsSC(output2), "Component 1")

        (new DemoTreeTableModelSC(Array[compSC](component, component2)), ISZ(component, component2))
      }

      val tt = new JTreeTableSC(model)
      val jf = new JFrame()
      val jsp = new JScrollPane(tt)
      jf.add(jsp, BorderLayout.CENTER)
      jf.pack()
      jf.setVisible(true)

      while (true) {
        for (j <- 0 to 1) {
          val component = elems(j)

          val in1 = nextString().native
          val in2 = nextString().native

          tt.updatePort(component.getIn.inputs, s"In 0", in1, "")
          tt.updatePort(component.getIn.inputs, s"In 1", in2, "")

          // let component think
          Thread.sleep(500)

          val out1 = nextString().native
          val out2 = nextString().native

          tt.updatePort(component.getOut.outputs, s"Out 0", out1, "")
          tt.updatePort(component.getOut.outputs, s"Out 1", out2, "")

          // wait before switching to the other component
          Thread.sleep(2000)
        }
      }
    }
  }
}
