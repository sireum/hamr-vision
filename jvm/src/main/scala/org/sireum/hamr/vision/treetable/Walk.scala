package org.sireum.hamr.vision.treetable

import org.sireum.{ISZ, String}

import scala.collection.mutable

class Walk {
  var map = mutable.HashMap[String, JPort]()

  def construct(entries: ISZ[Entry], columns: Int): ISZ[JEntry] = {

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
        if(r.values.size == columns){
          val rec = new JPort(r.rowId.toString, r.values)
          map(rec.rowID) = rec
          return map(rec.rowID)
        } else {
          System.out.println("Error: Length Out Of Bounds at Row: " + r.rowId)
          System.exit(0)
          return null
        }
      }
    }

    for (i <- 0 until entries.size.toInt) {
      JEntries = JEntries :+ walk(entries(i))
    }
    return JEntries
  }

  def getMap: mutable.HashMap[String, JPort] = { return map }
}
