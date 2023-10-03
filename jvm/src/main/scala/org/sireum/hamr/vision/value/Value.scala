// #Sireum

package org.sireum.hamr.vision.value

import org.sireum._

@datatype trait Value


@datatype trait PrimitiveValue extends Value

@datatype class ZValue(value: Z) extends PrimitiveValue

@datatype class RValue(value: R) extends PrimitiveValue

@datatype class StringValue(value: String) extends PrimitiveValue


@datatype trait CollectionValue extends Value

@datatype class ArrayValue(elements: ISZ[Value]) extends CollectionValue

@datatype class Pair(val p1: Value, val p2: Value) extends CollectionValue

// Sergen requires named types for fields so can't do ISZ[(String, Value)]
@datatype class RecordValue(fields: ISZ[Pair]) extends CollectionValue {
  @spec def FieldIdentifiersMustBeStrings = Invariant (
    All{ i: Z => (i <= 0 & i < fields.size) -->: ( fields(i).p1.isInstanceOf[PrimitiveValue] )}
  )
}

object RecordValue {
  @strictpure def D_Inv_RecordValue(value: RecordValue): B =
    ops.ISZOps(value.fields).forall(p => p.p1.isInstanceOf[PrimitiveValue])
}

@datatype class MapValue(entries: ISZ[Pair]) extends CollectionValue