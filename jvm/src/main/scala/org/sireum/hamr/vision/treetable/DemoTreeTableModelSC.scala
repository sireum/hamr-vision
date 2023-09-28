package org.sireum.hamr.vision.treetable

import javax.swing.tree.TreePath
import org.sireum.hamr.vision.value._

import scala.collection.mutable

object DemoTreeTableModelSC {
  private val root = "System"
  protected var cNames: Array[String] = Array("Port", "Description", "Value")
  protected var cTypes: Array[Class[_]] = Array(classOf[TreeTableModelSC], classOf[String], classOf[String])
}

class DemoTreeTableModelSC (var list: mutable.HashMap[Int, BridgeSC]) extends AbstractTreeTableModelSC(DemoTreeTableModelSC.root) {
  def getPort(node: AnyRef, place: Int): AnyRef = {
    if (node.isInstanceOf[PortSC]) return node.asInstanceOf[PortSC].getColumn(place) match {
      case StringValue(v) => v.native
      case _ => "This isn't handled yet"
    }
    null
  }

  override def getBridges: mutable.HashMap[Int, BridgeSC] = { list }
  override def getColumnCount = 3

  override def getColumnClass(column: Int): Class[_] = {
    //return column == 0 ? TreeTableModel.class : Object.class;
    if (column < 0 || column >= DemoTreeTableModelSC.cTypes.length) return classOf[AnyRef]
    DemoTreeTableModelSC.cTypes(column)
  }

  override def getColumnName(column: Int): String = DemoTreeTableModelSC.cNames(column)

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
    if (parent eq "System") return list(index)
    if (parent.isInstanceOf[BridgeSC]) {
      val bridge = parent.asInstanceOf[BridgeSC]
      return bridge.category.get(index)
    }
    if (parent.isInstanceOf[CategorySC]) {
      val category = parent.asInstanceOf[CategorySC]
      val array = category.children.keySet.toArray
      return category.children(array(index))
    }
    ""
  }

  override def getChildCount(parent: AnyRef): Int = {
    //return parent.equals(root) ? 3 : 0;
    var parentNew = parent
    if (parentNew.isInstanceOf[TreePath]) parentNew = parent.asInstanceOf[TreePath].getLastPathComponent
    if (parentNew eq "System") return list.size
    if (parent.isInstanceOf[BridgeSC]) {
      val bridge = parent.asInstanceOf[BridgeSC]
      return bridge.category.size()
    }
    if (parent.isInstanceOf[CategorySC]) {
      val category = parent.asInstanceOf[CategorySC]
      return category.children.size
    }
    0
  }
}
