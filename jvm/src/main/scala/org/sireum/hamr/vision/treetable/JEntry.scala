package org.sireum.hamr.vision.treetable

import org.sireum.ISZ
import org.sireum.hamr.vision.value.{StringValue, Value}

trait JEntry

class JPort(val rowID: String, var column: ISZ[Value]) extends JEntry {
  var updated: Boolean = false;

  def getColumn: ISZ[Value] = { return column; }
  def setValue(value: Value): Unit = {column = ISZ[Value](column(0), column(1), value)}
  def getUpdated: Boolean = { return updated; }
  def setUpdated(updatedN: Boolean): Unit = { updated = updatedN; }
  override def toString: String = {
    return (column(0) match {
      case StringValue(v) => v.native
      case _ => "This isn't handled yet"
    })
  }
}

class JJCategory(val name: String, val children: ISZ[JEntry]) extends JEntry {
  override def toString: String = { return name }
}

