package org.sireum.hamr.vision.treetable

import org.sireum.ISZ
import org.sireum.hamr.vision.value._

class OutputSC(var column: ISZ[Value]) {
  var updated: Boolean = false;

  def getColumn: ISZ[Value] = { return column; }

  def setColumn(columnN: ISZ[Value]): Unit = { column = columnN; }

  def getUpdated: Boolean = { return updated; }

  def setUpdated(updatedN: Boolean): Unit = { updated = updatedN; }
  override def toString: String = {
    return (column(0) match {
      case StringValue(v) => v.native
      case _ => "This isn't handled yet"
    }) }
}
