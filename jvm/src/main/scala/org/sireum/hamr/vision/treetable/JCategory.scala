package org.sireum.hamr.vision.treetable

import scala.collection.mutable

class JCategory(val name: String) {
  val children = new mutable.HashMap[Int, Port]

  override def toString: String = { return name }
}
