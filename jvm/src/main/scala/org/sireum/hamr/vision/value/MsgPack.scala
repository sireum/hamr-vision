// #Sireum
// @formatter:off

// This file is auto-generated from Value.scala

package org.sireum.hamr.vision.value

import org.sireum._
import org.sireum.hamr.vision.value.Value
import org.sireum.hamr.vision.value.PrimitiveValue
import org.sireum.hamr.vision.value.ZValue
import org.sireum.hamr.vision.value.RValue
import org.sireum.hamr.vision.value.StringValue
import org.sireum.hamr.vision.value.CollectionValue
import org.sireum.hamr.vision.value.ArrayValue
import org.sireum.hamr.vision.value.Pair
import org.sireum.hamr.vision.value.RecordValue
import org.sireum.hamr.vision.value.MapValue

object MsgPack {

  object Constants {

    val ZValue: Z = -32

    val RValue: Z = -31

    val StringValue: Z = -30

    val ArrayValue: Z = -29

    val Pair: Z = -28

    val RecordValue: Z = -27

    val MapValue: Z = -26

  }

  object Writer {

    @record class Default(val writer: MessagePack.Writer.Impl) extends Writer

  }

  @msig trait Writer {

    def writer: MessagePack.Writer

    def writeValue(o: Value): Unit = {
      o match {
        case o: ZValue => writeZValue(o)
        case o: RValue => writeRValue(o)
        case o: StringValue => writeStringValue(o)
        case o: ArrayValue => writeArrayValue(o)
        case o: Pair => writePair(o)
        case o: RecordValue => writeRecordValue(o)
        case o: MapValue => writeMapValue(o)
      }
    }

    def writePrimitiveValue(o: PrimitiveValue): Unit = {
      o match {
        case o: ZValue => writeZValue(o)
        case o: RValue => writeRValue(o)
        case o: StringValue => writeStringValue(o)
      }
    }

    def writeZValue(o: ZValue): Unit = {
      writer.writeZ(Constants.ZValue)
      writer.writeZ(o.value)
    }

    def writeRValue(o: RValue): Unit = {
      writer.writeZ(Constants.RValue)
      writer.writeR(o.value)
    }

    def writeStringValue(o: StringValue): Unit = {
      writer.writeZ(Constants.StringValue)
      writer.writeString(o.value)
    }

    def writeCollectionValue(o: CollectionValue): Unit = {
      o match {
        case o: ArrayValue => writeArrayValue(o)
        case o: Pair => writePair(o)
        case o: RecordValue => writeRecordValue(o)
        case o: MapValue => writeMapValue(o)
      }
    }

    def writeArrayValue(o: ArrayValue): Unit = {
      writer.writeZ(Constants.ArrayValue)
      writer.writeISZ(o.elements, writeValue _)
    }

    def writePair(o: Pair): Unit = {
      writer.writeZ(Constants.Pair)
      writeValue(o.p1)
      writeValue(o.p2)
    }

    def writeRecordValue(o: RecordValue): Unit = {
      writer.writeZ(Constants.RecordValue)
      writer.writeISZ(o.fields, writePair _)
    }

    def writeMapValue(o: MapValue): Unit = {
      writer.writeZ(Constants.MapValue)
      writer.writeISZ(o.entries, writePair _)
    }

    def result: ISZ[U8] = {
      return writer.result
    }

  }

  object Reader {

    @record class Default(val reader: MessagePack.Reader.Impl) extends Reader {
      def errorOpt: Option[MessagePack.ErrorMsg] = {
        return reader.errorOpt
      }
    }

  }

  @msig trait Reader {

    def reader: MessagePack.Reader

    def readValue(): Value = {
      val i = reader.curr
      val t = reader.readZ()
      t match {
        case Constants.ZValue => val r = readZValueT(T); return r
        case Constants.RValue => val r = readRValueT(T); return r
        case Constants.StringValue => val r = readStringValueT(T); return r
        case Constants.ArrayValue => val r = readArrayValueT(T); return r
        case Constants.Pair => val r = readPairT(T); return r
        case Constants.RecordValue => val r = readRecordValueT(T); return r
        case Constants.MapValue => val r = readMapValueT(T); return r
        case _ =>
          reader.error(i, s"$t is not a valid type of Value.")
          val r = readMapValueT(T)
          return r
      }
    }

    def readPrimitiveValue(): PrimitiveValue = {
      val i = reader.curr
      val t = reader.readZ()
      t match {
        case Constants.ZValue => val r = readZValueT(T); return r
        case Constants.RValue => val r = readRValueT(T); return r
        case Constants.StringValue => val r = readStringValueT(T); return r
        case _ =>
          reader.error(i, s"$t is not a valid type of PrimitiveValue.")
          val r = readStringValueT(T)
          return r
      }
    }

    def readZValue(): ZValue = {
      val r = readZValueT(F)
      return r
    }

    def readZValueT(typeParsed: B): ZValue = {
      if (!typeParsed) {
        reader.expectZ(Constants.ZValue)
      }
      val value = reader.readZ()
      return ZValue(value)
    }

    def readRValue(): RValue = {
      val r = readRValueT(F)
      return r
    }

    def readRValueT(typeParsed: B): RValue = {
      if (!typeParsed) {
        reader.expectZ(Constants.RValue)
      }
      val value = reader.readR()
      return RValue(value)
    }

    def readStringValue(): StringValue = {
      val r = readStringValueT(F)
      return r
    }

    def readStringValueT(typeParsed: B): StringValue = {
      if (!typeParsed) {
        reader.expectZ(Constants.StringValue)
      }
      val value = reader.readString()
      return StringValue(value)
    }

    def readCollectionValue(): CollectionValue = {
      val i = reader.curr
      val t = reader.readZ()
      t match {
        case Constants.ArrayValue => val r = readArrayValueT(T); return r
        case Constants.Pair => val r = readPairT(T); return r
        case Constants.RecordValue => val r = readRecordValueT(T); return r
        case Constants.MapValue => val r = readMapValueT(T); return r
        case _ =>
          reader.error(i, s"$t is not a valid type of CollectionValue.")
          val r = readMapValueT(T)
          return r
      }
    }

    def readArrayValue(): ArrayValue = {
      val r = readArrayValueT(F)
      return r
    }

    def readArrayValueT(typeParsed: B): ArrayValue = {
      if (!typeParsed) {
        reader.expectZ(Constants.ArrayValue)
      }
      val elements = reader.readISZ(readValue _)
      return ArrayValue(elements)
    }

    def readPair(): Pair = {
      val r = readPairT(F)
      return r
    }

    def readPairT(typeParsed: B): Pair = {
      if (!typeParsed) {
        reader.expectZ(Constants.Pair)
      }
      val p1 = readValue()
      val p2 = readValue()
      return Pair(p1, p2)
    }

    def readRecordValue(): RecordValue = {
      val r = readRecordValueT(F)
      return r
    }

    def readRecordValueT(typeParsed: B): RecordValue = {
      if (!typeParsed) {
        reader.expectZ(Constants.RecordValue)
      }
      val fields = reader.readISZ(readPair _)
      return RecordValue(fields)
    }

    def readMapValue(): MapValue = {
      val r = readMapValueT(F)
      return r
    }

    def readMapValueT(typeParsed: B): MapValue = {
      if (!typeParsed) {
        reader.expectZ(Constants.MapValue)
      }
      val entries = reader.readISZ(readPair _)
      return MapValue(entries)
    }

  }

  def to[T](data: ISZ[U8], f: Reader => T): Either[T, MessagePack.ErrorMsg] = {
    val rd = Reader.Default(MessagePack.reader(data))
    rd.reader.init()
    val r = f(rd)
    rd.errorOpt match {
      case Some(e) => return Either.Right(e)
      case _ => return Either.Left(r)
    }
  }

  def fromValue(o: Value, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeValue(o)
    return w.result
  }

  def toValue(data: ISZ[U8]): Either[Value, MessagePack.ErrorMsg] = {
    def fValue(reader: Reader): Value = {
      val r = reader.readValue()
      return r
    }
    val r = to(data, fValue _)
    return r
  }

  def fromPrimitiveValue(o: PrimitiveValue, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writePrimitiveValue(o)
    return w.result
  }

  def toPrimitiveValue(data: ISZ[U8]): Either[PrimitiveValue, MessagePack.ErrorMsg] = {
    def fPrimitiveValue(reader: Reader): PrimitiveValue = {
      val r = reader.readPrimitiveValue()
      return r
    }
    val r = to(data, fPrimitiveValue _)
    return r
  }

  def fromZValue(o: ZValue, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeZValue(o)
    return w.result
  }

  def toZValue(data: ISZ[U8]): Either[ZValue, MessagePack.ErrorMsg] = {
    def fZValue(reader: Reader): ZValue = {
      val r = reader.readZValue()
      return r
    }
    val r = to(data, fZValue _)
    return r
  }

  def fromRValue(o: RValue, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeRValue(o)
    return w.result
  }

  def toRValue(data: ISZ[U8]): Either[RValue, MessagePack.ErrorMsg] = {
    def fRValue(reader: Reader): RValue = {
      val r = reader.readRValue()
      return r
    }
    val r = to(data, fRValue _)
    return r
  }

  def fromStringValue(o: StringValue, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeStringValue(o)
    return w.result
  }

  def toStringValue(data: ISZ[U8]): Either[StringValue, MessagePack.ErrorMsg] = {
    def fStringValue(reader: Reader): StringValue = {
      val r = reader.readStringValue()
      return r
    }
    val r = to(data, fStringValue _)
    return r
  }

  def fromCollectionValue(o: CollectionValue, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeCollectionValue(o)
    return w.result
  }

  def toCollectionValue(data: ISZ[U8]): Either[CollectionValue, MessagePack.ErrorMsg] = {
    def fCollectionValue(reader: Reader): CollectionValue = {
      val r = reader.readCollectionValue()
      return r
    }
    val r = to(data, fCollectionValue _)
    return r
  }

  def fromArrayValue(o: ArrayValue, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeArrayValue(o)
    return w.result
  }

  def toArrayValue(data: ISZ[U8]): Either[ArrayValue, MessagePack.ErrorMsg] = {
    def fArrayValue(reader: Reader): ArrayValue = {
      val r = reader.readArrayValue()
      return r
    }
    val r = to(data, fArrayValue _)
    return r
  }

  def fromPair(o: Pair, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writePair(o)
    return w.result
  }

  def toPair(data: ISZ[U8]): Either[Pair, MessagePack.ErrorMsg] = {
    def fPair(reader: Reader): Pair = {
      val r = reader.readPair()
      return r
    }
    val r = to(data, fPair _)
    return r
  }

  def fromRecordValue(o: RecordValue, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeRecordValue(o)
    return w.result
  }

  def toRecordValue(data: ISZ[U8]): Either[RecordValue, MessagePack.ErrorMsg] = {
    def fRecordValue(reader: Reader): RecordValue = {
      val r = reader.readRecordValue()
      return r
    }
    val r = to(data, fRecordValue _)
    return r
  }

  def fromMapValue(o: MapValue, pooling: B): ISZ[U8] = {
    val w = Writer.Default(MessagePack.writer(pooling))
    w.writeMapValue(o)
    return w.result
  }

  def toMapValue(data: ISZ[U8]): Either[MapValue, MessagePack.ErrorMsg] = {
    def fMapValue(reader: Reader): MapValue = {
      val r = reader.readMapValue()
      return r
    }
    val r = to(data, fMapValue _)
    return r
  }

}