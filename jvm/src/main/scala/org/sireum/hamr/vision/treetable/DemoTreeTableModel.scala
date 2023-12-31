package org.sireum.hamr.vision.treetable

import org.sireum.{ISZ}

import javax.swing.tree.TreePath
import org.sireum.hamr.vision.value._

import scala.collection.mutable

object DemoTreeTableModel {
  private val root = "System"
}

class DemoTreeTableModel(var list: ISZ[Entry], var colNames: ISZ[String]) extends AbstractTreeTableModel(DemoTreeTableModel.root) {

  val w = new Walk
  val JList = w.construct(list, getColumnCount)
  def getMap: mutable.HashMap[org.sireum.String, JPort] = { return w.getMap }

  def getPort(node: AnyRef, place: Int): AnyRef = {
    if (node.isInstanceOf[JPort]) return node.asInstanceOf[JPort].getColumn(place) match {
      case StringValue(v) => v.native
      //case RecordValue(r) =>
      case _ => "This isn't handled yet"
    }
    null
  }

  override def getColumnCount = colNames.size.toInt

  override def getColumnClass(column: Int): Class[_] = {
    //return column == 0 ? TreeTableModel.class : Object.class;
    if(column == 0) classOf[TreeTableModel]
    else classOf[String]
  }

  override def getColumnName(column: Int): String = colNames(column)

  override def getValueAt(node: AnyRef, column: Int): AnyRef = {
    //return node.toString();
    try column match {
      case 0 =>
        return node
      case 1 =>
        return getPort(node, 1)
      case 2 =>
        return getPort(node, 2)
    }
    catch {
      case se: SecurityException =>
    }
    null
  }

  override def getChild(parent: AnyRef, index: Int): AnyRef = {
    //return "Child " + index;
    if (parent eq "System") return JList(index)
    if (parent.isInstanceOf[JCategory]) {
      val category = parent.asInstanceOf[JCategory]
      val array = category.children
      return array(index)
    }
    ""
  }

  override def getChildCount(parent: AnyRef): Int = {
    //return parent.equals(root) ? 3 : 0;
    var parentNew = parent
    if (parentNew.isInstanceOf[TreePath]) parentNew = parent.asInstanceOf[TreePath].getLastPathComponent
    if (parentNew eq "System") return JList.size.toInt
    if (parent.isInstanceOf[JCategory]) {
      val category = parent.asInstanceOf[JCategory]
      return category.children.size.toInt
    }
    0
  }
}
