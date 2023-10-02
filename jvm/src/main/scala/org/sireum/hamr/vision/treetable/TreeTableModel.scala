package org.sireum.hamr.vision.treetable

import javax.swing.tree.TreeModel
import scala.collection.mutable

abstract class TreeTableModel extends TreeModel{
  def getColumnCount: Int
  def getColumnName(column:Int): String
  def getColumnClass(column: Int): Class[_]
  def getValueAt(node: AnyRef, column: Int): AnyRef
  def isCellEditable(node: AnyRef, column: Int): Boolean
  def setValueAt(aValue: AnyRef, node: AnyRef, column: Int): Unit
}
