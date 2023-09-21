package org.sireum.hamr.vision.treetable

import java.awt.{event, _}
import javax.swing._
import org.sireum.ISZ
import org.sireum.hamr.vision.value._
import java.awt.event.ActionListener


class TreeTableDemoSC extends JFrame with ActionListener {
  this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE)
  this.setSize(500, 350)
  this.setLayout(new FlowLayout)
  this.setTitle("TreeTable Demo")

  val menuBar = new JMenuBar
  val optionsMenu = new JMenu("Options")
  val colorMenu = new JMenuItem("Color Toggle")
  val colorChoice = new JMenuItem("Color Choice")
  optionsMenu.add(colorMenu)
  optionsMenu.add(colorChoice)
  menuBar.add(optionsMenu)

  val ex: ISZ[compSC] = new Ex2SC().build
  val treeTable = new JTreeTableSC(new DemoTreeTableModelSC(ex))
  treeTable.updatePort(ex(0).getIn, StringValue("I am a port"), StringValue("I UPDATED!"), StringValue("2024"))
  treeTable.updatePort(ex(1).getOut, StringValue("Pizzas"), StringValue("I also got UPDATED!"), StringValue("53"))
  this.setJMenuBar(menuBar)
  this.add(new JScrollPane(treeTable))
  this.setVisible(true)

  override def actionPerformed(e: event.ActionEvent): Unit = {
    if (e.getSource == colorMenu) {
      if (treeTable.getColorToggle) treeTable.setColorToggle(false)
      else treeTable.setColorToggle(true)
    }
    if(e.getSource == colorChoice){
      treeTable.setColorChoice(JColorChooser.showDialog(null, "Pick a color", Color.yellow))
    }
  }
}
