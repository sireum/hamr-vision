// #Sireum
// @formatter:off

// This file is auto-generated from Value.scala

package org.sireum.hamr.vision.value

import org.sireum._
import org.sireum.Json.Printer._
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

object JSON {

  object Printer {

    @pure def printValue(o: Value): ST = {
      o match {
        case o: ZValue => return printZValue(o)
        case o: RValue => return printRValue(o)
        case o: StringValue => return printStringValue(o)
        case o: ArrayValue => return printArrayValue(o)
        case o: Pair => return printPair(o)
        case o: RecordValue => return printRecordValue(o)
        case o: MapValue => return printMapValue(o)
      }
    }

    @pure def printPrimitiveValue(o: PrimitiveValue): ST = {
      o match {
        case o: ZValue => return printZValue(o)
        case o: RValue => return printRValue(o)
        case o: StringValue => return printStringValue(o)
      }
    }

    @pure def printZValue(o: ZValue): ST = {
      return printObject(ISZ(
        ("type", st""""ZValue""""),
        ("value", printZ(o.value))
      ))
    }

    @pure def printRValue(o: RValue): ST = {
      return printObject(ISZ(
        ("type", st""""RValue""""),
        ("value", printR(o.value))
      ))
    }

    @pure def printStringValue(o: StringValue): ST = {
      return printObject(ISZ(
        ("type", st""""StringValue""""),
        ("value", printString(o.value))
      ))
    }

    @pure def printCollectionValue(o: CollectionValue): ST = {
      o match {
        case o: ArrayValue => return printArrayValue(o)
        case o: Pair => return printPair(o)
        case o: RecordValue => return printRecordValue(o)
        case o: MapValue => return printMapValue(o)
      }
    }

    @pure def printArrayValue(o: ArrayValue): ST = {
      return printObject(ISZ(
        ("type", st""""ArrayValue""""),
        ("elements", printISZ(F, o.elements, printValue _))
      ))
    }

    @pure def printPair(o: Pair): ST = {
      return printObject(ISZ(
        ("type", st""""Pair""""),
        ("p1", printValue(o.p1)),
        ("p2", printValue(o.p2))
      ))
    }

    @pure def printRecordValue(o: RecordValue): ST = {
      return printObject(ISZ(
        ("type", st""""RecordValue""""),
        ("fields", printISZ(F, o.fields, printPair _))
      ))
    }

    @pure def printMapValue(o: MapValue): ST = {
      return printObject(ISZ(
        ("type", st""""MapValue""""),
        ("entries", printISZ(F, o.entries, printPair _))
      ))
    }

  }

  @record class Parser(val input: String) {
    val parser: Json.Parser = Json.Parser.create(input)

    def errorOpt: Option[Json.ErrorMsg] = {
      return parser.errorOpt
    }

    def parseValue(): Value = {
      val t = parser.parseObjectTypes(ISZ("ZValue", "RValue", "StringValue", "ArrayValue", "Pair", "RecordValue", "MapValue"))
      t.native match {
        case "ZValue" => val r = parseZValueT(T); return r
        case "RValue" => val r = parseRValueT(T); return r
        case "StringValue" => val r = parseStringValueT(T); return r
        case "ArrayValue" => val r = parseArrayValueT(T); return r
        case "Pair" => val r = parsePairT(T); return r
        case "RecordValue" => val r = parseRecordValueT(T); return r
        case "MapValue" => val r = parseMapValueT(T); return r
        case _ => val r = parseMapValueT(T); return r
      }
    }

    def parsePrimitiveValue(): PrimitiveValue = {
      val t = parser.parseObjectTypes(ISZ("ZValue", "RValue", "StringValue"))
      t.native match {
        case "ZValue" => val r = parseZValueT(T); return r
        case "RValue" => val r = parseRValueT(T); return r
        case "StringValue" => val r = parseStringValueT(T); return r
        case _ => val r = parseStringValueT(T); return r
      }
    }

    def parseZValue(): ZValue = {
      val r = parseZValueT(F)
      return r
    }

    def parseZValueT(typeParsed: B): ZValue = {
      if (!typeParsed) {
        parser.parseObjectType("ZValue")
      }
      parser.parseObjectKey("value")
      val value = parser.parseZ()
      parser.parseObjectNext()
      return ZValue(value)
    }

    def parseRValue(): RValue = {
      val r = parseRValueT(F)
      return r
    }

    def parseRValueT(typeParsed: B): RValue = {
      if (!typeParsed) {
        parser.parseObjectType("RValue")
      }
      parser.parseObjectKey("value")
      val value = parser.parseR()
      parser.parseObjectNext()
      return RValue(value)
    }

    def parseStringValue(): StringValue = {
      val r = parseStringValueT(F)
      return r
    }

    def parseStringValueT(typeParsed: B): StringValue = {
      if (!typeParsed) {
        parser.parseObjectType("StringValue")
      }
      parser.parseObjectKey("value")
      val value = parser.parseString()
      parser.parseObjectNext()
      return StringValue(value)
    }

    def parseCollectionValue(): CollectionValue = {
      val t = parser.parseObjectTypes(ISZ("ArrayValue", "Pair", "RecordValue", "MapValue"))
      t.native match {
        case "ArrayValue" => val r = parseArrayValueT(T); return r
        case "Pair" => val r = parsePairT(T); return r
        case "RecordValue" => val r = parseRecordValueT(T); return r
        case "MapValue" => val r = parseMapValueT(T); return r
        case _ => val r = parseMapValueT(T); return r
      }
    }

    def parseArrayValue(): ArrayValue = {
      val r = parseArrayValueT(F)
      return r
    }

    def parseArrayValueT(typeParsed: B): ArrayValue = {
      if (!typeParsed) {
        parser.parseObjectType("ArrayValue")
      }
      parser.parseObjectKey("elements")
      val elements = parser.parseISZ(parseValue _)
      parser.parseObjectNext()
      return ArrayValue(elements)
    }

    def parsePair(): Pair = {
      val r = parsePairT(F)
      return r
    }

    def parsePairT(typeParsed: B): Pair = {
      if (!typeParsed) {
        parser.parseObjectType("Pair")
      }
      parser.parseObjectKey("p1")
      val p1 = parseValue()
      parser.parseObjectNext()
      parser.parseObjectKey("p2")
      val p2 = parseValue()
      parser.parseObjectNext()
      return Pair(p1, p2)
    }

    def parseRecordValue(): RecordValue = {
      val r = parseRecordValueT(F)
      return r
    }

    def parseRecordValueT(typeParsed: B): RecordValue = {
      if (!typeParsed) {
        parser.parseObjectType("RecordValue")
      }
      parser.parseObjectKey("fields")
      val fields = parser.parseISZ(parsePair _)
      parser.parseObjectNext()
      return RecordValue(fields)
    }

    def parseMapValue(): MapValue = {
      val r = parseMapValueT(F)
      return r
    }

    def parseMapValueT(typeParsed: B): MapValue = {
      if (!typeParsed) {
        parser.parseObjectType("MapValue")
      }
      parser.parseObjectKey("entries")
      val entries = parser.parseISZ(parsePair _)
      parser.parseObjectNext()
      return MapValue(entries)
    }

    def eof(): B = {
      val r = parser.eof()
      return r
    }

  }

  def to[T](s: String, f: Parser => T): Either[T, Json.ErrorMsg] = {
    val parser = Parser(s)
    val r = f(parser)
    parser.eof()
    parser.errorOpt match {
      case Some(e) => return Either.Right(e)
      case _ => return Either.Left(r)
    }
  }

  def fromValue(o: Value, isCompact: B): String = {
    val st = Printer.printValue(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toValue(s: String): Either[Value, Json.ErrorMsg] = {
    def fValue(parser: Parser): Value = {
      val r = parser.parseValue()
      return r
    }
    val r = to(s, fValue _)
    return r
  }

  def fromPrimitiveValue(o: PrimitiveValue, isCompact: B): String = {
    val st = Printer.printPrimitiveValue(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toPrimitiveValue(s: String): Either[PrimitiveValue, Json.ErrorMsg] = {
    def fPrimitiveValue(parser: Parser): PrimitiveValue = {
      val r = parser.parsePrimitiveValue()
      return r
    }
    val r = to(s, fPrimitiveValue _)
    return r
  }

  def fromZValue(o: ZValue, isCompact: B): String = {
    val st = Printer.printZValue(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toZValue(s: String): Either[ZValue, Json.ErrorMsg] = {
    def fZValue(parser: Parser): ZValue = {
      val r = parser.parseZValue()
      return r
    }
    val r = to(s, fZValue _)
    return r
  }

  def fromRValue(o: RValue, isCompact: B): String = {
    val st = Printer.printRValue(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toRValue(s: String): Either[RValue, Json.ErrorMsg] = {
    def fRValue(parser: Parser): RValue = {
      val r = parser.parseRValue()
      return r
    }
    val r = to(s, fRValue _)
    return r
  }

  def fromStringValue(o: StringValue, isCompact: B): String = {
    val st = Printer.printStringValue(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toStringValue(s: String): Either[StringValue, Json.ErrorMsg] = {
    def fStringValue(parser: Parser): StringValue = {
      val r = parser.parseStringValue()
      return r
    }
    val r = to(s, fStringValue _)
    return r
  }

  def fromCollectionValue(o: CollectionValue, isCompact: B): String = {
    val st = Printer.printCollectionValue(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toCollectionValue(s: String): Either[CollectionValue, Json.ErrorMsg] = {
    def fCollectionValue(parser: Parser): CollectionValue = {
      val r = parser.parseCollectionValue()
      return r
    }
    val r = to(s, fCollectionValue _)
    return r
  }

  def fromArrayValue(o: ArrayValue, isCompact: B): String = {
    val st = Printer.printArrayValue(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toArrayValue(s: String): Either[ArrayValue, Json.ErrorMsg] = {
    def fArrayValue(parser: Parser): ArrayValue = {
      val r = parser.parseArrayValue()
      return r
    }
    val r = to(s, fArrayValue _)
    return r
  }

  def fromPair(o: Pair, isCompact: B): String = {
    val st = Printer.printPair(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toPair(s: String): Either[Pair, Json.ErrorMsg] = {
    def fPair(parser: Parser): Pair = {
      val r = parser.parsePair()
      return r
    }
    val r = to(s, fPair _)
    return r
  }

  def fromRecordValue(o: RecordValue, isCompact: B): String = {
    val st = Printer.printRecordValue(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toRecordValue(s: String): Either[RecordValue, Json.ErrorMsg] = {
    def fRecordValue(parser: Parser): RecordValue = {
      val r = parser.parseRecordValue()
      return r
    }
    val r = to(s, fRecordValue _)
    return r
  }

  def fromMapValue(o: MapValue, isCompact: B): String = {
    val st = Printer.printMapValue(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toMapValue(s: String): Either[MapValue, Json.ErrorMsg] = {
    def fMapValue(parser: Parser): MapValue = {
      val r = parser.parseMapValue()
      return r
    }
    val r = to(s, fMapValue _)
    return r
  }

}