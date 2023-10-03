package org.sireum.hamr.vision.treetable

import org.sireum.{ISZ, String}

import scala.collection.mutable

class Walk {
  var map = mutable.HashMap[String, JPort]()

  def construct(entries: ISZ[Entry]): ISZ[JEntry] = {

    var JEntries: ISZ[JEntry] = ISZ()

    def walk(entry: Entry): JEntry = {
      entry match {
        case c: Category =>
          var children: ISZ[JEntry] = ISZ()
          for (ee <- c.children) {
            children = children :+ walk(ee)
          }
          val cat = new JCategory(c.displayName.toString, children)
          return cat
        case r: Row =>
          val rec = new JPort(r.rowId.toString, r.values)
          map(rec.rowID) = rec
          return map(rec.rowID)
      }
    }

    for (i <- 0 until entries.size.toInt) {
      JEntries = JEntries :+ walk(entries(i))
    }
    return JEntries
  }

  def getMap: mutable.HashMap[String, JPort] = { return map }
}
