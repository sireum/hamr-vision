package org.sireum.hamr.vision.treetable

import java.awt._
import javax.swing._
import org.sireum.ISZ
import org.sireum.hamr.vision.value._


class TreeTableDemoSC extends JFrame {
  this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE)
  this.setSize(500, 350)
  this.setLayout(new FlowLayout)
  this.setTitle("TreeTable Demo")
  val ex: ISZ[compSC] = new Ex2SC().build
  val treeTable = new JTreeTableSC(new DemoTreeTableModelSC(ex))
  treeTable.updatePort(ex(0).getIn, StringValue("I am a port"), StringValue("2024"), StringValue("I UPDATED!"))
  treeTable.updatePort(ex(1).getOut, StringValue("Pizzas"), StringValue("53"), StringValue("I also got UPDATED!"))
  this.add(new JScrollPane(treeTable))
  this.setVisible(true)
}
