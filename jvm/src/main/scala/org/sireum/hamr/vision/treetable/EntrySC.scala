// #Sireum

package org.sireum.hamr.vision.treetable

import org.sireum._
import org.sireum.hamr.vision.value.Value

@datatype trait Entry

@datatype class Category(val displayName: String,
                         val children: ISZ[Entry]) extends Entry

@datatype class Row(val rowId: String,
                    val values: ISZ[Value]) extends Entry


// assume in a different file
@record class TreeTable(val entries: ISZ[Entry],
                        val columnNames: ISZ[String]) {
  //var lookupTable: Map[String, a row in the JTable]
  type JEntry = Z

  def construct(): Unit = {

    var jcategories: ISZ[String] = ISZ()

    def walk(entry: Entry): JEntry = {
      entry match {
        case c: Category =>
          var children: ISZ[JEntry] = ISZ()
          for (ee <- c.children) {
            children = children :+ walk(ee)
          }
          val ret: JEntry = z"1" // new JEntry(c.displayName, children)
          return ret
        case r: Row =>
          //portsc = ????
          //map = map + (r.rowId ~> portsc)
          return z"0"
      }
    }

    var jEntries: ISZ[JEntry] = ISZ()
    for (e <- entries) {
      jEntries = jEntries :+ walk(e)
    }
  }

  def update(rowId: String, value: ISZ[Option[Value]]): B = {
    // pseudo-code
    // use rowId to find the row in the jtable to update

    // for (value <- values if (value.nonEmpty) {
    //   update the ith cell with value
    //   trigger a color change if option is enabled
    // }

    return T // assuming success

    // if rowId not found, throw exception, report error etc
  }
}