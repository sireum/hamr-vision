// #Sireum

package org.sireum.hamr.vision.value

import org.sireum._
import org.sireum.Random.Gen64

/*
GENERATED FROM

Value.scala

*/

@datatype class Config_Z(low: Option[Z], high: Option[Z], attempts: Z, verbose: B, filter: Z => B) {}

@datatype class Config_B(attempts: Z, verbose: B, filter: B => B) {}

@datatype class Config_C(low: Option[C], high: Option[C], attempts: Z, verbose: B, filter: C => B) {}

@datatype class Config_R(low: Option[R], high: Option[R], attempts: Z, verbose: B, filter: R => B) {}

@datatype class Config_F32(low: Option[F32], high: Option[F32], attempts: Z, verbose: B, filter: F32 => B) {}

@datatype class Config_F64(low: Option[F64], high: Option[F64], attempts: Z, verbose: B, filter: F64 => B) {}

@datatype class Config_S8(low: Option[S8], high: Option[S8], attempts: Z, verbose: B, filter: S8 => B) {}

@datatype class Config_S16(low: Option[S16], high: Option[S16], attempts: Z, verbose: B, filter: S16 => B) {}

@datatype class Config_S32(low: Option[S32], high: Option[S32], attempts: Z, verbose: B, filter: S32 => B) {}

@datatype class Config_S64(low: Option[S64], high: Option[S64], attempts: Z, verbose: B, filter: S64 => B) {}

@datatype class Config_U8(low: Option[U8], high: Option[U8], attempts: Z, verbose: B, filter: U8 => B) {}

@datatype class Config_U16(low: Option[U16], high: Option[U16], attempts: Z, verbose: B, filter: U16 => B) {}

@datatype class Config_U32(low: Option[U32], high: Option[U32], attempts: Z, verbose: B, filter: U32 => B) {}

@datatype class Config_U64(low: Option[U64], high: Option[U64], attempts: Z, verbose: B, filter: U64 => B) {}

@datatype class Config_Value(attempts: Z, verbose: B, additiveTypeFiltering: B, typeFilter: ISZ[Value_DataTypeId.Type], filter: Value => B) {}

@datatype class Config_PrimitiveValue(attempts: Z, verbose: B, additiveTypeFiltering: B, typeFilter: ISZ[PrimitiveValue_DataTypeId.Type], filter: PrimitiveValue => B) {}

@datatype class Config_ZValue(attempts: Z, verbose: B, filter: ZValue => B) {}

@datatype class Config_RValue(attempts: Z, verbose: B, filter: RValue => B) {}

@datatype class Config_StringValue(attempts: Z, verbose: B, filter: StringValue => B) {}

@datatype class Config_CollectionValue(attempts: Z, verbose: B, additiveTypeFiltering: B, typeFilter: ISZ[CollectionValue_DataTypeId.Type], filter: CollectionValue => B) {}

@datatype class Config_ArrayValue(attempts: Z, verbose: B, filter: ArrayValue => B) {}

@datatype class Config_Pair(attempts: Z, verbose: B, filter: Pair => B) {}

@datatype class Config_RecordValue(attempts: Z, verbose: B, filter: RecordValue => B) {}

@datatype class Config_MapValue(attempts: Z, verbose: B, filter: MapValue => B) {}


