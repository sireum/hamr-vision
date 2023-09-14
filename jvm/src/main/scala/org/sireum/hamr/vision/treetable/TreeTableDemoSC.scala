package org.sireum.hamr.vision.treetable

import java.awt._
import javax.swing._


class TreeTableDemoSC extends JFrame {
  this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE)
  this.setSize(500, 350)
  this.setLayout(new FlowLayout)
  this.setTitle("TreeTable Demo")
  val ex: Array[compSC] = new Ex2SC().build
  val treeTable = new JTreeTableSC(new DemoTreeTableModelSC(ex))
  treeTable.updatePort(ex(0).getIn.getInputs, "I am a port", "2024", "I UPDATED!")
  treeTable.updatePort(ex(1).getOut.getOutputs, "Pizzas", "53", "I also got UPDATED!")
  this.add(new JScrollPane(treeTable))
  this.setVisible(true)
}
