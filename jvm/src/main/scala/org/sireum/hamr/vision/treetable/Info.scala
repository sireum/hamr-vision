package org.sireum.hamr.vision.treetable

import org.sireum.hamr.vision.value.StringValue

import java.awt._
import java.awt.font.{LineBreakMeasurer, TextAttribute}
import java.text._
import javax.swing._
import javax.swing.table.{DefaultTableModel, TableCellRenderer}

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
  table.getColumnModel.getColumn(2).setCellRenderer(new MyRenderer)

  private class MyRenderer extends JTextArea with TableCellRenderer {
    setOpaque(true)
    setLineWrap(true)
    setWrapStyleWord(true)

    private def countLines(textArea: JTextArea): Int = {
      val text = new AttributedString(textArea.getText)
      text.addAttribute(TextAttribute.FONT, textArea.getFont)
      val frc = textArea.getFontMetrics(textArea.getFont).getFontRenderContext
      val charIt: AttributedCharacterIterator = text.getIterator
      val lineMeasurer = new LineBreakMeasurer(charIt, frc)
      val formatWidth = textArea.getSize.width.asInstanceOf[Float]
      lineMeasurer.setPosition(charIt.getBeginIndex)
      var noLines = 0
      while (lineMeasurer.getPosition < charIt.getEndIndex) {
        lineMeasurer.nextLayout(formatWidth)
        noLines += 1
      }
      noLines
    }
    override def getTableCellRendererComponent(table: JTable, value: Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component = {
      this.setText(if (value == null) ""
      else value.toString)
      val text = value.toString
      if(!text.isEmpty) {
        val area = new JTextArea(text)
        val size = countLines(area)
        if(size < 12) table.setRowHeight(15)
        else table.setRowHeight(countLines(area))
      }
      this
    }
  }

  val jsp = new JScrollPane(table)
  jf.add(jsp, BorderLayout.CENTER)
  jf.pack()
  jf.setVisible(true)
}
