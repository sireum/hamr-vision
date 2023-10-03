// #Sireum

package org.sireum.hamr.vision.value

import org.sireum._
import org.sireum.Random.Gen64

/*
GENERATED FROM

Value.scala

*/

@msig trait RandomLibI {
  def gen: org.sireum.Random.Gen

  def get_numElement: Z
  def set_numElement(s: Z): Unit

  // ========  Z ==========
    def get_Config_Z: Config_Z
    def set_Config_Z(config: Config_Z): RandomLib

    def nextZ(): Z = {
      val conf = get_Config_Z

      var r: Z = if (conf.low.isEmpty) {
          if (conf.high.isEmpty)
            gen.nextZ()
          else
            gen.nextZBetween(S64.Min.toZ, conf.high.get)
        } else {
          if (conf.high.isEmpty)
            gen.nextZBetween(conf.low.get, S64.Max.toZ)
          else
            gen.nextZBetween(conf.low.get, conf.high.get)
        }

      if(get_Config_Z.attempts >= 0) {
       for (i <- 0 to get_Config_Z.attempts) {
         if (get_Config_Z.filter(r)) {
           return r
         }
         if (get_Config_Z.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextZ()
           else
              gen.nextZBetween(S64.Min.toZ, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextZBetween(conf.low.get, S64.Max.toZ)
            else
             gen.nextZBetween(conf.low.get, conf.high.get)
         }
       }
      } else {
       while(T) {
         if (get_Config_Z.filter(r)) {
           return r
         }
         if (get_Config_Z.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextZ()
           else
              gen.nextZBetween(S64.Min.toZ, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextZBetween(conf.low.get, S64.Max.toZ)
            else
             gen.nextZBetween(conf.low.get, conf.high.get)
         }
       }
      }
      assert(F, "Requirements too strict to generate")
      halt("Requirements too strict to generate")
    }

  // ========  B ==========}
    def get_Config_B: Config_B
    def set_Config_B(config: Config_B): RandomLib

    def nextB(): B = {
      var r = gen.nextB()
      if(get_Config_B.attempts >= 0) {
       for (i <- 0 to get_Config_B.attempts) {
         if (get_Config_B.filter(r)) {
           return r
         }
         if (get_Config_B.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = gen.nextB()
       }
      } else {
       while(T) {
         if (get_Config_B.filter(r)) {
           return r
         }
         if (get_Config_B.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = gen.nextB()
       }
      }
      assert(F, "Requirements too strict to generate")
      halt("Requirements too strict to generate")
    }

  // ========  C ==========
    def get_Config_C: Config_C
    def set_Config_C(config: Config_C): RandomLib

    def nextC(): C = {
      val conf = get_Config_C

      var r: C = if (conf.low.isEmpty) {
          if (conf.high.isEmpty)
            gen.nextC()
          else
            gen.nextCBetween(C.fromZ(0), conf.high.get)
        } else {
          if (conf.high.isEmpty)
            gen.nextCBetween(conf.low.get, C.fromZ(1114111))
          else
            gen.nextCBetween(conf.low.get, conf.high.get)
        }

      if(get_Config_C.attempts >= 0) {
       for (i <- 0 to get_Config_C.attempts) {
         if (get_Config_C.filter(r)) {
           return r
         }
         if (get_Config_C.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextC()
           else
              gen.nextCBetween(C.fromZ(0), conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextCBetween(conf.low.get, C.fromZ(1114111))
            else
             gen.nextCBetween(conf.low.get, conf.high.get)
         }
       }
      } else {
       while(T) {
         if (get_Config_C.filter(r)) {
           return r
         }
         if (get_Config_C.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextC()
           else
              gen.nextCBetween(C.fromZ(0), conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextCBetween(conf.low.get, C.fromZ(1114111))
            else
             gen.nextCBetween(conf.low.get, conf.high.get)
         }
       }
      }
      assert(F, "Requirements too strict to generate")
      halt("Requirements too strict to generate")
    }

  // ========  R ==========
    def get_Config_R: Config_R
    def set_Config_R(config: Config_R): RandomLib

    def nextR(): R = {
      val conf = get_Config_R

      var r: R = if (conf.low.isEmpty) {
          if (conf.high.isEmpty)
            gen.nextR()
          else
            gen.nextRBetween(r"-1.7976931348623157E308", conf.high.get)
        } else {
          if (conf.high.isEmpty)
            gen.nextRBetween(conf.low.get, r"1.7976931348623157E308")
          else
            gen.nextRBetween(conf.low.get, conf.high.get)
        }

      if(get_Config_R.attempts >= 0) {
       for (i <- 0 to get_Config_R.attempts) {
         if (get_Config_R.filter(r)) {
           return r
         }
         if (get_Config_R.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextR()
           else
              gen.nextRBetween(r"-1.7976931348623157E308", conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextRBetween(conf.low.get, r"1.7976931348623157E308")
            else
             gen.nextRBetween(conf.low.get, conf.high.get)
         }
       }
      } else {
       while(T) {
         if (get_Config_R.filter(r)) {
           return r
         }
         if (get_Config_R.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextR()
           else
              gen.nextRBetween(r"-1.7976931348623157E308", conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextRBetween(conf.low.get, r"1.7976931348623157E308")
            else
             gen.nextRBetween(conf.low.get, conf.high.get)
         }
       }
      }
      assert(F, "Requirements too strict to generate")
      halt("Requirements too strict to generate")
    }

  // ========  F32 ==========
    def get_Config_F32: Config_F32
    def set_Config_F32(config: Config_F32): RandomLib

    def nextF32(): F32 = {
      val conf = get_Config_F32

      var r: F32 = if (conf.low.isEmpty) {
          if (conf.high.isEmpty)
            gen.nextF32()
          else
            gen.nextF32Between(f32"-3.40282347E38f", conf.high.get)
        } else {
          if (conf.high.isEmpty)
            gen.nextF32Between(conf.low.get, f32"3.4028235E38f")
          else
            gen.nextF32Between(conf.low.get, conf.high.get)
        }

      if(get_Config_F32.attempts >= 0) {
       for (i <- 0 to get_Config_F32.attempts) {
         if (get_Config_F32.filter(r)) {
           return r
         }
         if (get_Config_F32.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextF32()
           else
              gen.nextF32Between(f32"-3.40282347E38f", conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextF32Between(conf.low.get, f32"3.4028235E38f")
            else
             gen.nextF32Between(conf.low.get, conf.high.get)
         }
       }
      } else {
       while(T) {
         if (get_Config_F32.filter(r)) {
           return r
         }
         if (get_Config_F32.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextF32()
           else
              gen.nextF32Between(f32"-3.40282347E38f", conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextF32Between(conf.low.get, f32"3.4028235E38f")
            else
             gen.nextF32Between(conf.low.get, conf.high.get)
         }
       }
      }
      assert(F, "Requirements too strict to generate")
      halt("Requirements too strict to generate")
    }

  // ========  F64 ==========
    def get_Config_F64: Config_F64
    def set_Config_F64(config: Config_F64): RandomLib

    def nextF64(): F64 = {
      val conf = get_Config_F64

      var r: F64 = if (conf.low.isEmpty) {
          if (conf.high.isEmpty)
            gen.nextF64()
          else
            gen.nextF64Between(f64"-1.7976931348623157E308f", conf.high.get)
        } else {
          if (conf.high.isEmpty)
            gen.nextF64Between(conf.low.get, f64"1.7976931348623157E308f")
          else
            gen.nextF64Between(conf.low.get, conf.high.get)
        }

      if(get_Config_F64.attempts >= 0) {
       for (i <- 0 to get_Config_F64.attempts) {
         if (get_Config_F64.filter(r)) {
           return r
         }
         if (get_Config_F64.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextF64()
           else
              gen.nextF64Between(f64"-1.7976931348623157E308f", conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextF64Between(conf.low.get, f64"1.7976931348623157E308f")
            else
             gen.nextF64Between(conf.low.get, conf.high.get)
         }
       }
      } else {
       while(T) {
         if (get_Config_F64.filter(r)) {
           return r
         }
         if (get_Config_F64.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextF64()
           else
              gen.nextF64Between(f64"-1.7976931348623157E308f", conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextF64Between(conf.low.get, f64"1.7976931348623157E308f")
            else
             gen.nextF64Between(conf.low.get, conf.high.get)
         }
       }
      }
      assert(F, "Requirements too strict to generate")
      halt("Requirements too strict to generate")
    }

  // ========  S8 ==========
    def get_Config_S8: Config_S8
    def set_Config_S8(config: Config_S8): RandomLib

    def nextS8(): S8 = {
      val conf = get_Config_S8

      var r: S8 = if (conf.low.isEmpty) {
          if (conf.high.isEmpty)
            gen.nextS8()
          else
            gen.nextS8Between(S8.Min, conf.high.get)
        } else {
          if (conf.high.isEmpty)
            gen.nextS8Between(conf.low.get, S8.Max)
          else
            gen.nextS8Between(conf.low.get, conf.high.get)
        }

      if(get_Config_S8.attempts >= 0) {
       for (i <- 0 to get_Config_S8.attempts) {
         if (get_Config_S8.filter(r)) {
           return r
         }
         if (get_Config_S8.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextS8()
           else
              gen.nextS8Between(S8.Min, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextS8Between(conf.low.get, S8.Max)
            else
             gen.nextS8Between(conf.low.get, conf.high.get)
         }
       }
      } else {
       while(T) {
         if (get_Config_S8.filter(r)) {
           return r
         }
         if (get_Config_S8.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextS8()
           else
              gen.nextS8Between(S8.Min, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextS8Between(conf.low.get, S8.Max)
            else
             gen.nextS8Between(conf.low.get, conf.high.get)
         }
       }
      }
      assert(F, "Requirements too strict to generate")
      halt("Requirements too strict to generate")
    }

  // ========  S16 ==========
    def get_Config_S16: Config_S16
    def set_Config_S16(config: Config_S16): RandomLib

    def nextS16(): S16 = {
      val conf = get_Config_S16

      var r: S16 = if (conf.low.isEmpty) {
          if (conf.high.isEmpty)
            gen.nextS16()
          else
            gen.nextS16Between(S16.Min, conf.high.get)
        } else {
          if (conf.high.isEmpty)
            gen.nextS16Between(conf.low.get, S16.Max)
          else
            gen.nextS16Between(conf.low.get, conf.high.get)
        }

      if(get_Config_S16.attempts >= 0) {
       for (i <- 0 to get_Config_S16.attempts) {
         if (get_Config_S16.filter(r)) {
           return r
         }
         if (get_Config_S16.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextS16()
           else
              gen.nextS16Between(S16.Min, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextS16Between(conf.low.get, S16.Max)
            else
             gen.nextS16Between(conf.low.get, conf.high.get)
         }
       }
      } else {
       while(T) {
         if (get_Config_S16.filter(r)) {
           return r
         }
         if (get_Config_S16.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextS16()
           else
              gen.nextS16Between(S16.Min, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextS16Between(conf.low.get, S16.Max)
            else
             gen.nextS16Between(conf.low.get, conf.high.get)
         }
       }
      }
      assert(F, "Requirements too strict to generate")
      halt("Requirements too strict to generate")
    }

  // ========  S32 ==========
    def get_Config_S32: Config_S32
    def set_Config_S32(config: Config_S32): RandomLib

    def nextS32(): S32 = {
      val conf = get_Config_S32

      var r: S32 = if (conf.low.isEmpty) {
          if (conf.high.isEmpty)
            gen.nextS32()
          else
            gen.nextS32Between(S32.Min, conf.high.get)
        } else {
          if (conf.high.isEmpty)
            gen.nextS32Between(conf.low.get, S32.Max)
          else
            gen.nextS32Between(conf.low.get, conf.high.get)
        }

      if(get_Config_S32.attempts >= 0) {
       for (i <- 0 to get_Config_S32.attempts) {
         if (get_Config_S32.filter(r)) {
           return r
         }
         if (get_Config_S32.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextS32()
           else
              gen.nextS32Between(S32.Min, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextS32Between(conf.low.get, S32.Max)
            else
             gen.nextS32Between(conf.low.get, conf.high.get)
         }
       }
      } else {
       while(T) {
         if (get_Config_S32.filter(r)) {
           return r
         }
         if (get_Config_S32.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextS32()
           else
              gen.nextS32Between(S32.Min, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextS32Between(conf.low.get, S32.Max)
            else
             gen.nextS32Between(conf.low.get, conf.high.get)
         }
       }
      }
      assert(F, "Requirements too strict to generate")
      halt("Requirements too strict to generate")
    }

  // ========  S64 ==========
    def get_Config_S64: Config_S64
    def set_Config_S64(config: Config_S64): RandomLib

    def nextS64(): S64 = {
      val conf = get_Config_S64

      var r: S64 = if (conf.low.isEmpty) {
          if (conf.high.isEmpty)
            gen.nextS64()
          else
            gen.nextS64Between(S64.Min, conf.high.get)
        } else {
          if (conf.high.isEmpty)
            gen.nextS64Between(conf.low.get, S64.Max)
          else
            gen.nextS64Between(conf.low.get, conf.high.get)
        }

      if(get_Config_S64.attempts >= 0) {
       for (i <- 0 to get_Config_S64.attempts) {
         if (get_Config_S64.filter(r)) {
           return r
         }
         if (get_Config_S64.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextS64()
           else
              gen.nextS64Between(S64.Min, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextS64Between(conf.low.get, S64.Max)
            else
             gen.nextS64Between(conf.low.get, conf.high.get)
         }
       }
      } else {
       while(T) {
         if (get_Config_S64.filter(r)) {
           return r
         }
         if (get_Config_S64.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextS64()
           else
              gen.nextS64Between(S64.Min, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextS64Between(conf.low.get, S64.Max)
            else
             gen.nextS64Between(conf.low.get, conf.high.get)
         }
       }
      }
      assert(F, "Requirements too strict to generate")
      halt("Requirements too strict to generate")
    }

  // ========  U8 ==========
    def get_Config_U8: Config_U8
    def set_Config_U8(config: Config_U8): RandomLib

    def nextU8(): U8 = {
      val conf = get_Config_U8

      var r: U8 = if (conf.low.isEmpty) {
          if (conf.high.isEmpty)
            gen.nextU8()
          else
            gen.nextU8Between(U8.Min, conf.high.get)
        } else {
          if (conf.high.isEmpty)
            gen.nextU8Between(conf.low.get, U8.Max)
          else
            gen.nextU8Between(conf.low.get, conf.high.get)
        }

      if(get_Config_U8.attempts >= 0) {
       for (i <- 0 to get_Config_U8.attempts) {
         if (get_Config_U8.filter(r)) {
           return r
         }
         if (get_Config_U8.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextU8()
           else
              gen.nextU8Between(U8.Min, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextU8Between(conf.low.get, U8.Max)
            else
             gen.nextU8Between(conf.low.get, conf.high.get)
         }
       }
      } else {
       while(T) {
         if (get_Config_U8.filter(r)) {
           return r
         }
         if (get_Config_U8.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextU8()
           else
              gen.nextU8Between(U8.Min, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextU8Between(conf.low.get, U8.Max)
            else
             gen.nextU8Between(conf.low.get, conf.high.get)
         }
       }
      }
      assert(F, "Requirements too strict to generate")
      halt("Requirements too strict to generate")
    }

  // ========  U16 ==========
    def get_Config_U16: Config_U16
    def set_Config_U16(config: Config_U16): RandomLib

    def nextU16(): U16 = {
      val conf = get_Config_U16

      var r: U16 = if (conf.low.isEmpty) {
          if (conf.high.isEmpty)
            gen.nextU16()
          else
            gen.nextU16Between(U16.Min, conf.high.get)
        } else {
          if (conf.high.isEmpty)
            gen.nextU16Between(conf.low.get, U16.Max)
          else
            gen.nextU16Between(conf.low.get, conf.high.get)
        }

      if(get_Config_U16.attempts >= 0) {
       for (i <- 0 to get_Config_U16.attempts) {
         if (get_Config_U16.filter(r)) {
           return r
         }
         if (get_Config_U16.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextU16()
           else
              gen.nextU16Between(U16.Min, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextU16Between(conf.low.get, U16.Max)
            else
             gen.nextU16Between(conf.low.get, conf.high.get)
         }
       }
      } else {
       while(T) {
         if (get_Config_U16.filter(r)) {
           return r
         }
         if (get_Config_U16.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextU16()
           else
              gen.nextU16Between(U16.Min, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextU16Between(conf.low.get, U16.Max)
            else
             gen.nextU16Between(conf.low.get, conf.high.get)
         }
       }
      }
      assert(F, "Requirements too strict to generate")
      halt("Requirements too strict to generate")
    }

  // ========  U32 ==========
    def get_Config_U32: Config_U32
    def set_Config_U32(config: Config_U32): RandomLib

    def nextU32(): U32 = {
      val conf = get_Config_U32

      var r: U32 = if (conf.low.isEmpty) {
          if (conf.high.isEmpty)
            gen.nextU32()
          else
            gen.nextU32Between(U32.Min, conf.high.get)
        } else {
          if (conf.high.isEmpty)
            gen.nextU32Between(conf.low.get, U32.Max)
          else
            gen.nextU32Between(conf.low.get, conf.high.get)
        }

      if(get_Config_U32.attempts >= 0) {
       for (i <- 0 to get_Config_U32.attempts) {
         if (get_Config_U32.filter(r)) {
           return r
         }
         if (get_Config_U32.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextU32()
           else
              gen.nextU32Between(U32.Min, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextU32Between(conf.low.get, U32.Max)
            else
             gen.nextU32Between(conf.low.get, conf.high.get)
         }
       }
      } else {
       while(T) {
         if (get_Config_U32.filter(r)) {
           return r
         }
         if (get_Config_U32.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextU32()
           else
              gen.nextU32Between(U32.Min, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextU32Between(conf.low.get, U32.Max)
            else
             gen.nextU32Between(conf.low.get, conf.high.get)
         }
       }
      }
      assert(F, "Requirements too strict to generate")
      halt("Requirements too strict to generate")
    }

  // ========  U64 ==========
    def get_Config_U64: Config_U64
    def set_Config_U64(config: Config_U64): RandomLib

    def nextU64(): U64 = {
      val conf = get_Config_U64

      var r: U64 = if (conf.low.isEmpty) {
          if (conf.high.isEmpty)
            gen.nextU64()
          else
            gen.nextU64Between(U64.Min, conf.high.get)
        } else {
          if (conf.high.isEmpty)
            gen.nextU64Between(conf.low.get, U64.Max)
          else
            gen.nextU64Between(conf.low.get, conf.high.get)
        }

      if(get_Config_U64.attempts >= 0) {
       for (i <- 0 to get_Config_U64.attempts) {
         if (get_Config_U64.filter(r)) {
           return r
         }
         if (get_Config_U64.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextU64()
           else
              gen.nextU64Between(U64.Min, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextU64Between(conf.low.get, U64.Max)
            else
             gen.nextU64Between(conf.low.get, conf.high.get)
         }
       }
      } else {
       while(T) {
         if (get_Config_U64.filter(r)) {
           return r
         }
         if (get_Config_U64.verbose) {
           println(s"Retrying for failing value: $r")
         }
         r = if (conf.low.isEmpty) {
           if (conf.high.isEmpty)
             gen.nextU64()
           else
              gen.nextU64Between(U64.Min, conf.high.get)
          } else {
            if (conf.high.isEmpty)
              gen.nextU64Between(conf.low.get, U64.Max)
            else
             gen.nextU64Between(conf.low.get, conf.high.get)
         }
       }
      }
      assert(F, "Requirements too strict to generate")
      halt("Requirements too strict to generate")
    }

  def nextString(): String = {
    val length: Z = gen.nextZBetween(0, get_numElement)
    var str: String = ""
    for(r <- 0 until length){
      str = s"${str}${gen.nextC().string}"
    }

    return str
  }

  // ============= Value ===================

  def get_Config_Value: Config_Value
  def set_Config_Value(config: Config_Value): RandomLib

  def nextValue(): Value = {
    var callEnum: ISZ[Value_DataTypeId.Type] = ISZ(Value_DataTypeId.sireumhamrvisionvalueArrayValue_Id, Value_DataTypeId.sireumhamrvisionvalueMapValue_Id, Value_DataTypeId.sireumhamrvisionvaluePair_Id, Value_DataTypeId.sireumhamrvisionvalueRValue_Id, Value_DataTypeId.sireumhamrvisionvalueRecordValue_Id, Value_DataTypeId.sireumhamrvisionvalueStringValue_Id, Value_DataTypeId.sireumhamrvisionvalueZValue_Id)

    if(get_Config_Value.additiveTypeFiltering) {
       callEnum = get_Config_Value.typeFilter
    } else {
       for(h <- get_Config_Value.typeFilter) {
         callEnum = ops.ISZOps(callEnum).filter(f => h =!= f)
       }
    }

    var c = callEnum(gen.nextZBetween(0, callEnum.size-1))

    var v: Value = c match {
      case Value_DataTypeId.sireumhamrvisionvalueArrayValue_Id => (nextArrayValue _).apply()
      case Value_DataTypeId.sireumhamrvisionvalueMapValue_Id => (nextMapValue _).apply()
      case Value_DataTypeId.sireumhamrvisionvaluePair_Id => (nextPair _).apply()
      case Value_DataTypeId.sireumhamrvisionvalueRValue_Id => (nextRValue _).apply()
      case Value_DataTypeId.sireumhamrvisionvalueRecordValue_Id => (nextRecordValue _).apply()
      case Value_DataTypeId.sireumhamrvisionvalueStringValue_Id => (nextStringValue _).apply()
      case Value_DataTypeId.sireumhamrvisionvalueZValue_Id => (nextZValue _).apply()
      case _ => halt("Invalid Child")
    }


    if(get_Config_Value.attempts >= 0) {
     for(i <- 0 to get_Config_Value.attempts) {
       if(get_Config_Value.filter(v)) {
        return v
       }
       if (get_Config_Value.verbose) {
         println(s"Retrying for failing value: $v")
       }
       c = callEnum(gen.nextZBetween(0, callEnum.size-1))

       v = c match {
         case Value_DataTypeId.sireumhamrvisionvalueArrayValue_Id => (nextArrayValue _).apply()
         case Value_DataTypeId.sireumhamrvisionvalueMapValue_Id => (nextMapValue _).apply()
         case Value_DataTypeId.sireumhamrvisionvaluePair_Id => (nextPair _).apply()
         case Value_DataTypeId.sireumhamrvisionvalueRValue_Id => (nextRValue _).apply()
         case Value_DataTypeId.sireumhamrvisionvalueRecordValue_Id => (nextRecordValue _).apply()
         case Value_DataTypeId.sireumhamrvisionvalueStringValue_Id => (nextStringValue _).apply()
         case Value_DataTypeId.sireumhamrvisionvalueZValue_Id => (nextZValue _).apply()
         case _ => halt("Invalid Child")
       }
     }
    } else {
     while(T) {
       if(get_Config_Value.filter(v)) {
         return v
       }
       if (get_Config_Value.verbose) {
         println(s"Retrying for failing value: $v")
       }
       c = callEnum(gen.nextZBetween(0, callEnum.size-1))

       v = c match {
         case Value_DataTypeId.sireumhamrvisionvalueArrayValue_Id => (nextArrayValue _).apply()
         case Value_DataTypeId.sireumhamrvisionvalueMapValue_Id => (nextMapValue _).apply()
         case Value_DataTypeId.sireumhamrvisionvaluePair_Id => (nextPair _).apply()
         case Value_DataTypeId.sireumhamrvisionvalueRValue_Id => (nextRValue _).apply()
         case Value_DataTypeId.sireumhamrvisionvalueRecordValue_Id => (nextRecordValue _).apply()
         case Value_DataTypeId.sireumhamrvisionvalueStringValue_Id => (nextStringValue _).apply()
         case Value_DataTypeId.sireumhamrvisionvalueZValue_Id => (nextZValue _).apply()
         case _ => halt("Invalid Child")
       }
     }
    }
    assert(F, "Requirements too strict to generate")
    halt("Requirements too strict to generate")
  }

  // ============= PrimitiveValue ===================

  def get_Config_PrimitiveValue: Config_PrimitiveValue
  def set_Config_PrimitiveValue(config: Config_PrimitiveValue): RandomLib

  def nextPrimitiveValue(): PrimitiveValue = {
    var callEnum: ISZ[PrimitiveValue_DataTypeId.Type] = ISZ(PrimitiveValue_DataTypeId.sireumhamrvisionvalueRValue_Id, PrimitiveValue_DataTypeId.sireumhamrvisionvalueStringValue_Id, PrimitiveValue_DataTypeId.sireumhamrvisionvalueZValue_Id)

    if(get_Config_PrimitiveValue.additiveTypeFiltering) {
       callEnum = get_Config_PrimitiveValue.typeFilter
    } else {
       for(h <- get_Config_PrimitiveValue.typeFilter) {
         callEnum = ops.ISZOps(callEnum).filter(f => h =!= f)
       }
    }

    var c = callEnum(gen.nextZBetween(0, callEnum.size-1))

    var v: PrimitiveValue = c match {
      case PrimitiveValue_DataTypeId.sireumhamrvisionvalueRValue_Id => (nextRValue _).apply()
      case PrimitiveValue_DataTypeId.sireumhamrvisionvalueStringValue_Id => (nextStringValue _).apply()
      case PrimitiveValue_DataTypeId.sireumhamrvisionvalueZValue_Id => (nextZValue _).apply()
      case _ => halt("Invalid Child")
    }


    if(get_Config_PrimitiveValue.attempts >= 0) {
     for(i <- 0 to get_Config_PrimitiveValue.attempts) {
       if(get_Config_PrimitiveValue.filter(v)) {
        return v
       }
       if (get_Config_PrimitiveValue.verbose) {
         println(s"Retrying for failing value: $v")
       }
       c = callEnum(gen.nextZBetween(0, callEnum.size-1))

       v = c match {
         case PrimitiveValue_DataTypeId.sireumhamrvisionvalueRValue_Id => (nextRValue _).apply()
         case PrimitiveValue_DataTypeId.sireumhamrvisionvalueStringValue_Id => (nextStringValue _).apply()
         case PrimitiveValue_DataTypeId.sireumhamrvisionvalueZValue_Id => (nextZValue _).apply()
         case _ => halt("Invalid Child")
       }
     }
    } else {
     while(T) {
       if(get_Config_PrimitiveValue.filter(v)) {
         return v
       }
       if (get_Config_PrimitiveValue.verbose) {
         println(s"Retrying for failing value: $v")
       }
       c = callEnum(gen.nextZBetween(0, callEnum.size-1))

       v = c match {
         case PrimitiveValue_DataTypeId.sireumhamrvisionvalueRValue_Id => (nextRValue _).apply()
         case PrimitiveValue_DataTypeId.sireumhamrvisionvalueStringValue_Id => (nextStringValue _).apply()
         case PrimitiveValue_DataTypeId.sireumhamrvisionvalueZValue_Id => (nextZValue _).apply()
         case _ => halt("Invalid Child")
       }
     }
    }
    assert(F, "Requirements too strict to generate")
    halt("Requirements too strict to generate")
  }

  // ============= ZValue ===================

  def get_Config_ZValue: Config_ZValue
  def set_Config_ZValue(config: Config_ZValue): RandomLib

  def nextZValue(): ZValue = {
    var value: Z = nextZ()

    var v: ZValue = ZValue(value)

    if(get_Config_ZValue.attempts >= 0) {
     for(i <- 0 to get_Config_ZValue.attempts) {
        if(get_Config_ZValue.filter(v)) {
          return v
        }
        if (get_Config_ZValue.verbose) {
          println(s"Retrying for failing value: $v")
        }
        value = nextZ()
        v = ZValue(value)
     }
    } else {
     while(T) {
       if(get_Config_ZValue.filter(v)) {
         return v
       }
       if (get_Config_ZValue.verbose) {
         println(s"Retrying for failing value: $v")
       }
       value = nextZ()
       v = ZValue(value)
     }
    }

    assert(F, "Requirements too strict to generate")
    halt("Requirements too strict to generate")
  }

  // ============= RValue ===================

  def get_Config_RValue: Config_RValue
  def set_Config_RValue(config: Config_RValue): RandomLib

  def nextRValue(): RValue = {
    var value: R = nextR()

    var v: RValue = RValue(value)

    if(get_Config_RValue.attempts >= 0) {
     for(i <- 0 to get_Config_RValue.attempts) {
        if(get_Config_RValue.filter(v)) {
          return v
        }
        if (get_Config_RValue.verbose) {
          println(s"Retrying for failing value: $v")
        }
        value = nextR()
        v = RValue(value)
     }
    } else {
     while(T) {
       if(get_Config_RValue.filter(v)) {
         return v
       }
       if (get_Config_RValue.verbose) {
         println(s"Retrying for failing value: $v")
       }
       value = nextR()
       v = RValue(value)
     }
    }

    assert(F, "Requirements too strict to generate")
    halt("Requirements too strict to generate")
  }

  // ============= StringValue ===================

  def get_Config_StringValue: Config_StringValue
  def set_Config_StringValue(config: Config_StringValue): RandomLib

  def nextStringValue(): StringValue = {
    var value: String = nextString()

    var v: StringValue = StringValue(value)

    if(get_Config_StringValue.attempts >= 0) {
     for(i <- 0 to get_Config_StringValue.attempts) {
        if(get_Config_StringValue.filter(v)) {
          return v
        }
        if (get_Config_StringValue.verbose) {
          println(s"Retrying for failing value: $v")
        }
        value = nextString()
        v = StringValue(value)
     }
    } else {
     while(T) {
       if(get_Config_StringValue.filter(v)) {
         return v
       }
       if (get_Config_StringValue.verbose) {
         println(s"Retrying for failing value: $v")
       }
       value = nextString()
       v = StringValue(value)
     }
    }

    assert(F, "Requirements too strict to generate")
    halt("Requirements too strict to generate")
  }

  // ============= CollectionValue ===================

  def get_Config_CollectionValue: Config_CollectionValue
  def set_Config_CollectionValue(config: Config_CollectionValue): RandomLib

  def nextCollectionValue(): CollectionValue = {
    var callEnum: ISZ[CollectionValue_DataTypeId.Type] = ISZ(CollectionValue_DataTypeId.sireumhamrvisionvalueArrayValue_Id, CollectionValue_DataTypeId.sireumhamrvisionvalueMapValue_Id, CollectionValue_DataTypeId.sireumhamrvisionvaluePair_Id, CollectionValue_DataTypeId.sireumhamrvisionvalueRecordValue_Id)

    if(get_Config_CollectionValue.additiveTypeFiltering) {
       callEnum = get_Config_CollectionValue.typeFilter
    } else {
       for(h <- get_Config_CollectionValue.typeFilter) {
         callEnum = ops.ISZOps(callEnum).filter(f => h =!= f)
       }
    }

    var c = callEnum(gen.nextZBetween(0, callEnum.size-1))

    var v: CollectionValue = c match {
      case CollectionValue_DataTypeId.sireumhamrvisionvalueArrayValue_Id => (nextArrayValue _).apply()
      case CollectionValue_DataTypeId.sireumhamrvisionvalueMapValue_Id => (nextMapValue _).apply()
      case CollectionValue_DataTypeId.sireumhamrvisionvaluePair_Id => (nextPair _).apply()
      case CollectionValue_DataTypeId.sireumhamrvisionvalueRecordValue_Id => (nextRecordValue _).apply()
      case _ => halt("Invalid Child")
    }


    if(get_Config_CollectionValue.attempts >= 0) {
     for(i <- 0 to get_Config_CollectionValue.attempts) {
       if(get_Config_CollectionValue.filter(v)) {
        return v
       }
       if (get_Config_CollectionValue.verbose) {
         println(s"Retrying for failing value: $v")
       }
       c = callEnum(gen.nextZBetween(0, callEnum.size-1))

       v = c match {
         case CollectionValue_DataTypeId.sireumhamrvisionvalueArrayValue_Id => (nextArrayValue _).apply()
         case CollectionValue_DataTypeId.sireumhamrvisionvalueMapValue_Id => (nextMapValue _).apply()
         case CollectionValue_DataTypeId.sireumhamrvisionvaluePair_Id => (nextPair _).apply()
         case CollectionValue_DataTypeId.sireumhamrvisionvalueRecordValue_Id => (nextRecordValue _).apply()
         case _ => halt("Invalid Child")
       }
     }
    } else {
     while(T) {
       if(get_Config_CollectionValue.filter(v)) {
         return v
       }
       if (get_Config_CollectionValue.verbose) {
         println(s"Retrying for failing value: $v")
       }
       c = callEnum(gen.nextZBetween(0, callEnum.size-1))

       v = c match {
         case CollectionValue_DataTypeId.sireumhamrvisionvalueArrayValue_Id => (nextArrayValue _).apply()
         case CollectionValue_DataTypeId.sireumhamrvisionvalueMapValue_Id => (nextMapValue _).apply()
         case CollectionValue_DataTypeId.sireumhamrvisionvaluePair_Id => (nextPair _).apply()
         case CollectionValue_DataTypeId.sireumhamrvisionvalueRecordValue_Id => (nextRecordValue _).apply()
         case _ => halt("Invalid Child")
       }
     }
    }
    assert(F, "Requirements too strict to generate")
    halt("Requirements too strict to generate")
  }

  // ============= ArrayValue ===================

  def get_Config_ArrayValue: Config_ArrayValue
  def set_Config_ArrayValue(config: Config_ArrayValue): RandomLib

  def nextArrayValue(): ArrayValue = {
    var elements: ISZ[Value] = nextISZValue()

    var v: ArrayValue = ArrayValue(elements)

    if(get_Config_ArrayValue.attempts >= 0) {
     for(i <- 0 to get_Config_ArrayValue.attempts) {
        if(get_Config_ArrayValue.filter(v)) {
          return v
        }
        if (get_Config_ArrayValue.verbose) {
          println(s"Retrying for failing value: $v")
        }
        elements = nextISZValue()
        v = ArrayValue(elements)
     }
    } else {
     while(T) {
       if(get_Config_ArrayValue.filter(v)) {
         return v
       }
       if (get_Config_ArrayValue.verbose) {
         println(s"Retrying for failing value: $v")
       }
       elements = nextISZValue()
       v = ArrayValue(elements)
     }
    }

    assert(F, "Requirements too strict to generate")
    halt("Requirements too strict to generate")
  }

  // ============= Pair ===================

  def get_Config_Pair: Config_Pair
  def set_Config_Pair(config: Config_Pair): RandomLib

  def nextPair(): Pair = {
    var p1: Value = nextValue()
    var p2: Value = nextValue()

    var v: Pair = Pair(p1, p2)

    if(get_Config_Pair.attempts >= 0) {
     for(i <- 0 to get_Config_Pair.attempts) {
        if(get_Config_Pair.filter(v)) {
          return v
        }
        if (get_Config_Pair.verbose) {
          println(s"Retrying for failing value: $v")
        }
        p1 = nextValue()
        p2 = nextValue()
        v = Pair(p1, p2)
     }
    } else {
     while(T) {
       if(get_Config_Pair.filter(v)) {
         return v
       }
       if (get_Config_Pair.verbose) {
         println(s"Retrying for failing value: $v")
       }
       p1 = nextValue()
       p2 = nextValue()
       v = Pair(p1, p2)
     }
    }

    assert(F, "Requirements too strict to generate")
    halt("Requirements too strict to generate")
  }

  // ============= RecordValue ===================

  def get_Config_RecordValue: Config_RecordValue
  def set_Config_RecordValue(config: Config_RecordValue): RandomLib

  def nextRecordValue(): RecordValue = {
    var fields: ISZ[Pair] = nextISZPair()

    var v: RecordValue = RecordValue(fields)

    if(get_Config_RecordValue.attempts >= 0) {
     for(i <- 0 to get_Config_RecordValue.attempts) {
        if(get_Config_RecordValue.filter(v)) {
          return v
        }
        if (get_Config_RecordValue.verbose) {
          println(s"Retrying for failing value: $v")
        }
        fields = nextISZPair()
        v = RecordValue(fields)
     }
    } else {
     while(T) {
       if(get_Config_RecordValue.filter(v)) {
         return v
       }
       if (get_Config_RecordValue.verbose) {
         println(s"Retrying for failing value: $v")
       }
       fields = nextISZPair()
       v = RecordValue(fields)
     }
    }

    assert(F, "Requirements too strict to generate")
    halt("Requirements too strict to generate")
  }

  // ============= MapValue ===================

  def get_Config_MapValue: Config_MapValue
  def set_Config_MapValue(config: Config_MapValue): RandomLib

  def nextMapValue(): MapValue = {
    var entries: ISZ[Pair] = nextISZPair()

    var v: MapValue = MapValue(entries)

    if(get_Config_MapValue.attempts >= 0) {
     for(i <- 0 to get_Config_MapValue.attempts) {
        if(get_Config_MapValue.filter(v)) {
          return v
        }
        if (get_Config_MapValue.verbose) {
          println(s"Retrying for failing value: $v")
        }
        entries = nextISZPair()
        v = MapValue(entries)
     }
    } else {
     while(T) {
       if(get_Config_MapValue.filter(v)) {
         return v
       }
       if (get_Config_MapValue.verbose) {
         println(s"Retrying for failing value: $v")
       }
       entries = nextISZPair()
       v = MapValue(entries)
     }
    }

    assert(F, "Requirements too strict to generate")
    halt("Requirements too strict to generate")
  }

  //=================== ISZ[Value] =====================

  def nextISZValue(): ISZ[Value] = {
    val length: Z = gen.nextZBetween(0, get_numElement)
    var temp: ISZ[Value] = ISZ()
    for (r <- 0 until length) {
      temp = temp :+ nextValue()
    }

    return temp
  }

  //=================== ISZ[Pair] =====================

  def nextISZPair(): ISZ[Pair] = {
    val length: Z = gen.nextZBetween(0, get_numElement)
    var temp: ISZ[Pair] = ISZ()
    for (r <- 0 until length) {
      temp = temp :+ nextPair()
    }

    return temp
  }
}

@record class RandomLib(val gen: org.sireum.Random.Gen) extends RandomLibI {

  var numElem: Z = 50

  var _verbose: B = F
  def verbose: RandomLib = {
    _verbose = !_verbose
    return this
  }

  def get_numElement: Z = {return numElem}

  def set_numElement(s: Z): Unit ={
    numElem = s
  }

  // ============= Z ===================
  def alwaysTrue_Z(v: Z): B = {return T}

  var config_Z: Config_Z = Config_Z(None(), None(), 100, _verbose, alwaysTrue_Z _)
  def get_Config_Z: Config_Z = {return config_Z}

  def set_Config_Z(config: Config_Z): RandomLib ={
    config_Z = config
    return this
  }

  // ============= B ===================
  def alwaysTrue_B(v: B): B = {return T}

  var config_B: Config_B = Config_B(100, _verbose, alwaysTrue_B _)
  def get_Config_B: Config_B = {return config_B}

  def set_Config_B(config: Config_B): RandomLib ={
    config_B = config
    return this
  }

  // ============= C ===================
  def alwaysTrue_C(v: C): B = {return T}

  var config_C: Config_C = Config_C(None(), None(), 100, _verbose, alwaysTrue_C _)
  def get_Config_C: Config_C = {return config_C}

  def set_Config_C(config: Config_C): RandomLib ={
    config_C = config
    return this
  }

  // ============= R ===================
  def alwaysTrue_R(v: R): B = {return T}

  var config_R: Config_R = Config_R(None(), None(), 100, _verbose, alwaysTrue_R _)
  def get_Config_R: Config_R = {return config_R}

  def set_Config_R(config: Config_R): RandomLib ={
    config_R = config
    return this
  }

  // ============= F32 ===================
  def alwaysTrue_F32(v: F32): B = {return T}

  var config_F32: Config_F32 = Config_F32(None(), None(), 100, _verbose, alwaysTrue_F32 _)
  def get_Config_F32: Config_F32 = {return config_F32}

  def set_Config_F32(config: Config_F32): RandomLib ={
    config_F32 = config
    return this
  }

  // ============= F64 ===================
  def alwaysTrue_F64(v: F64): B = {return T}

  var config_F64: Config_F64 = Config_F64(None(), None(), 100, _verbose, alwaysTrue_F64 _)
  def get_Config_F64: Config_F64 = {return config_F64}

  def set_Config_F64(config: Config_F64): RandomLib ={
    config_F64 = config
    return this
  }

  // ============= S8 ===================
  def alwaysTrue_S8(v: S8): B = {return T}

  var config_S8: Config_S8 = Config_S8(None(), None(), 100, _verbose, alwaysTrue_S8 _)
  def get_Config_S8: Config_S8 = {return config_S8}

  def set_Config_S8(config: Config_S8): RandomLib ={
    config_S8 = config
    return this
  }

  // ============= S16 ===================
  def alwaysTrue_S16(v: S16): B = {return T}

  var config_S16: Config_S16 = Config_S16(None(), None(), 100, _verbose, alwaysTrue_S16 _)
  def get_Config_S16: Config_S16 = {return config_S16}

  def set_Config_S16(config: Config_S16): RandomLib ={
    config_S16 = config
    return this
  }

  // ============= S32 ===================
  def alwaysTrue_S32(v: S32): B = {return T}

  var config_S32: Config_S32 = Config_S32(None(), None(), 100, _verbose, alwaysTrue_S32 _)
  def get_Config_S32: Config_S32 = {return config_S32}

  def set_Config_S32(config: Config_S32): RandomLib ={
    config_S32 = config
    return this
  }

  // ============= S64 ===================
  def alwaysTrue_S64(v: S64): B = {return T}

  var config_S64: Config_S64 = Config_S64(None(), None(), 100, _verbose, alwaysTrue_S64 _)
  def get_Config_S64: Config_S64 = {return config_S64}

  def set_Config_S64(config: Config_S64): RandomLib ={
    config_S64 = config
    return this
  }

  // ============= U8 ===================
  def alwaysTrue_U8(v: U8): B = {return T}

  var config_U8: Config_U8 = Config_U8(None(), None(), 100, _verbose, alwaysTrue_U8 _)
  def get_Config_U8: Config_U8 = {return config_U8}

  def set_Config_U8(config: Config_U8): RandomLib ={
    config_U8 = config
    return this
  }

  // ============= U16 ===================
  def alwaysTrue_U16(v: U16): B = {return T}

  var config_U16: Config_U16 = Config_U16(None(), None(), 100, _verbose, alwaysTrue_U16 _)
  def get_Config_U16: Config_U16 = {return config_U16}

  def set_Config_U16(config: Config_U16): RandomLib ={
    config_U16 = config
    return this
  }

  // ============= U32 ===================
  def alwaysTrue_U32(v: U32): B = {return T}

  var config_U32: Config_U32 = Config_U32(None(), None(), 100, _verbose, alwaysTrue_U32 _)
  def get_Config_U32: Config_U32 = {return config_U32}

  def set_Config_U32(config: Config_U32): RandomLib ={
    config_U32 = config
    return this
  }

  // ============= U64 ===================
  def alwaysTrue_U64(v: U64): B = {return T}

  var config_U64: Config_U64 = Config_U64(None(), None(), 100, _verbose, alwaysTrue_U64 _)
  def get_Config_U64: Config_U64 = {return config_U64}

  def set_Config_U64(config: Config_U64): RandomLib ={
    config_U64 = config
    return this
  }

  // ============= Value ===================
  def alwaysTrue_Value(v: Value): B = {return T}

  var config_Value: Config_Value = Config_Value(100, _verbose, F, ISZ(), alwaysTrue_Value _)

  def get_Config_Value: Config_Value = {return config_Value}

  def set_Config_Value(config: Config_Value): RandomLib ={
    config_Value = config
    return this
  }

  // ============= PrimitiveValue ===================
  def alwaysTrue_PrimitiveValue(v: PrimitiveValue): B = {return T}

  var config_PrimitiveValue: Config_PrimitiveValue = Config_PrimitiveValue(100, _verbose, F, ISZ(), alwaysTrue_PrimitiveValue _)

  def get_Config_PrimitiveValue: Config_PrimitiveValue = {return config_PrimitiveValue}

  def set_Config_PrimitiveValue(config: Config_PrimitiveValue): RandomLib ={
    config_PrimitiveValue = config
    return this
  }

  // ============= ZValue ===================
  def alwaysTrue_ZValue(v: ZValue): B = {return T}

  var config_ZValue: Config_ZValue = Config_ZValue(100, _verbose, alwaysTrue_ZValue _)

  def get_Config_ZValue: Config_ZValue = {return config_ZValue}

  def set_Config_ZValue(config: Config_ZValue): RandomLib ={
    config_ZValue = config
    return this
  }

  // ============= RValue ===================
  def alwaysTrue_RValue(v: RValue): B = {return T}

  var config_RValue: Config_RValue = Config_RValue(100, _verbose, alwaysTrue_RValue _)

  def get_Config_RValue: Config_RValue = {return config_RValue}

  def set_Config_RValue(config: Config_RValue): RandomLib ={
    config_RValue = config
    return this
  }

  // ============= StringValue ===================
  def alwaysTrue_StringValue(v: StringValue): B = {return T}

  var config_StringValue: Config_StringValue = Config_StringValue(100, _verbose, alwaysTrue_StringValue _)

  def get_Config_StringValue: Config_StringValue = {return config_StringValue}

  def set_Config_StringValue(config: Config_StringValue): RandomLib ={
    config_StringValue = config
    return this
  }

  // ============= CollectionValue ===================
  def alwaysTrue_CollectionValue(v: CollectionValue): B = {return T}

  var config_CollectionValue: Config_CollectionValue = Config_CollectionValue(100, _verbose, F, ISZ(), alwaysTrue_CollectionValue _)

  def get_Config_CollectionValue: Config_CollectionValue = {return config_CollectionValue}

  def set_Config_CollectionValue(config: Config_CollectionValue): RandomLib ={
    config_CollectionValue = config
    return this
  }

  // ============= ArrayValue ===================
  def alwaysTrue_ArrayValue(v: ArrayValue): B = {return T}

  var config_ArrayValue: Config_ArrayValue = Config_ArrayValue(100, _verbose, alwaysTrue_ArrayValue _)

  def get_Config_ArrayValue: Config_ArrayValue = {return config_ArrayValue}

  def set_Config_ArrayValue(config: Config_ArrayValue): RandomLib ={
    config_ArrayValue = config
    return this
  }

  // ============= Pair ===================
  def alwaysTrue_Pair(v: Pair): B = {return T}

  var config_Pair: Config_Pair = Config_Pair(100, _verbose, alwaysTrue_Pair _)

  def get_Config_Pair: Config_Pair = {return config_Pair}

  def set_Config_Pair(config: Config_Pair): RandomLib ={
    config_Pair = config
    return this
  }

  // ============= RecordValue ===================
  def alwaysTrue_RecordValue(v: RecordValue): B = {return T}

  var config_RecordValue: Config_RecordValue = Config_RecordValue(100, _verbose, org.sireum.hamr.vision.value.RecordValue.D_Inv_RecordValue _)

  def get_Config_RecordValue: Config_RecordValue = {return config_RecordValue}

  def set_Config_RecordValue(config: Config_RecordValue): RandomLib ={
    config_RecordValue = config
    return this
  }

  // ============= MapValue ===================
  def alwaysTrue_MapValue(v: MapValue): B = {return T}

  var config_MapValue: Config_MapValue = Config_MapValue(100, _verbose, alwaysTrue_MapValue _)

  def get_Config_MapValue: Config_MapValue = {return config_MapValue}

  def set_Config_MapValue(config: Config_MapValue): RandomLib ={
    config_MapValue = config
    return this
  }
}

