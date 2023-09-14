package org.sireum.hamr.vision.treetable

class InputSC(var column: Array[Object]) {
  var updated: Boolean = false;

  def getColumn: Array[Object] = { return column; }

  def setColumn(columnN: Array[Object]): Unit = { column = columnN; }

  def getUpdated: Boolean = { return updated; }

  def setUpdated(updatedN: Boolean): Unit = { updated = updatedN; }
  override def toString: String = { return column(0).toString; }
}
