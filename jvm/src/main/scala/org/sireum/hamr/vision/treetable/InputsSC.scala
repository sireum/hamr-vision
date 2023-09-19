package org.sireum.hamr.vision.treetable

import org.sireum.ISZ

class InputsSC(var inputs: ISZ[InputSC]){
  def getInputs: ISZ[InputSC] = { return inputs; }
  override def toString: String = { return "Input"; }
}
