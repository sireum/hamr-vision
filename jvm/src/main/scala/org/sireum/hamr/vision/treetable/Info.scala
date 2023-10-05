package org.sireum.hamr.vision.treetable

import org.sireum.hamr.vision.value.{StringValue}

import java.awt._
import javax.swing._
import javax.swing.table.DefaultTableModel

class Info(port: JPort) {
  val jf = new JFrame()
  jf.setTitle("Information")
  jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE)

  val data = port.column
  val model = new DefaultTableModel()

  val columnName: Array[String] = Array("Port", "Description", "Value")
  for(i <- 0 until columnName.size){
    val string = data(i) match {
      case StringValue(v) => v.native
      case _ => "This isn't handled yet"
    }
    val array: Array[Object] = Array(string)
    model.addColumn(columnName(i), array)
  }

  val table = new JTable(model){
    override def isCellEditable(row: Int, column: Int): Boolean = false
  }
  table.getTableHeader.setReorderingAllowed(false)

  val jsp = new JScrollPane(table)
  jsp.setSize(300, 75)
  jf.add(jsp, BorderLayout.CENTER)
  jf.setSize(300, 75)
  //jf.pack()
  jf.setVisible(true)
}
