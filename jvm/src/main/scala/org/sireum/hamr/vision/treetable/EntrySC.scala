// #Sireum

package org.sireum.hamr.vision.treetable

import org.sireum._
import org.sireum.hamr.vision.value.Value

@datatype trait Entry

@datatype class Category(val displayName: String,
                         val children: ISZ[Entry]) extends Entry

@datatype class Row(val rowId: String,
                    val values: ISZ[Value]) extends Entry