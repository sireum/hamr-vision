package org.sireum.hamr.vision.treetable

import org.sireum.{ISZ, MSZ, Z}
import org.sireum.hamr.vision.value.{StringValue, Value}

trait JEntry

class JPort(val rowID: String, var column: ISZ[Value]) extends JEntry {
  // invariants: size of the following sequences are always the same
  // as the size of column
  val updatedCells: MSZ[Boolean] = MSZ.create(column.size, false)

  def getColumn: ISZ[Value] = { return column; }
  def setValueAt(rowId: Z, contents: Value): Unit = {
    column = column(rowId ~> contents)

    if (!updatedCells(rowId)) {
      updatedCells(rowId) = true
    }
    /// if recordType and expandedCells(rowId) is false, then expand
    //  else collapse
  }

  override def toString: String = {
    return (column(0) match {
      case StringValue(v) => v.native
      case _ => "This isn't handled yet"
    })
  }
}

class JCategory(val name: String, val children: ISZ[JEntry]) extends JEntry {
  override def toString: String = { return name }
}

