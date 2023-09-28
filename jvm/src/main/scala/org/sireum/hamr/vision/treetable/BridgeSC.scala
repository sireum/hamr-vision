package org.sireum.hamr.vision.treetable

import java.util

class BridgeSC(val name: String) {
  val category = new util.ArrayList[CategorySC]

  override def toString: String = { return name }
}
