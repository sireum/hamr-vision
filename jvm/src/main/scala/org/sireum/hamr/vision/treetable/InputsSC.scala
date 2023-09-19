package org.sireum.hamr.vision.treetable

import org.sireum.ISZ

trait InputsOutputs

class InputsSC(var inputs: ISZ[InputSC]) extends InputsOutputs {
  def getInputs: ISZ[InputSC] = { return inputs; }
  override def toString: String = { return "Input"; }
}
