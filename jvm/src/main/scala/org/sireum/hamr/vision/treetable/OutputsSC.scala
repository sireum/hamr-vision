package org.sireum.hamr.vision.treetable

import org.sireum.ISZ

class OutputsSC(var outputs: ISZ[OutputSC]) {
  def getOutputs: ISZ[OutputSC] = { return outputs; }
  override def toString: String = { return "Output"; }
}
