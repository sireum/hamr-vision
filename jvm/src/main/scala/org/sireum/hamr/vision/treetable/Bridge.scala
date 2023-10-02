package org.sireum.hamr.vision.treetable

import java.util

class Bridge(val name: String) {
  val category = new util.ArrayList[JCategory]

  override def toString: String = { return name }
}
