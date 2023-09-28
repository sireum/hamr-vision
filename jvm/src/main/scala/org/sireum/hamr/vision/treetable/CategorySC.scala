package org.sireum.hamr.vision.treetable

import scala.collection.mutable

class CategorySC(val name: String) {
  val children = new mutable.HashMap[Int, PortSC]

  override def toString: String = { return name }
}
