package org.sireum.hamr.vision.treetable

import org.sireum.ISZ
import org.sireum.hamr.vision.value._

class Ex2SC {
  def build: ISZ[compSC] = {
    // ======= Hardcoded Values (info for the "Model" -- the data to be displayed =======
    val input = ISZ[InputSC](new InputSC(ISZ[Value](StringValue("I am a port"), StringValue("1022"), StringValue("This is an input port"))), new InputSC(ISZ[Value](StringValue("Port number 2"), StringValue("127"), StringValue("I am another port"))), new InputSC(ISZ[Value](StringValue("This is an input"), StringValue("4102"), StringValue("Inputs are cool"))))
    val output = ISZ[OutputSC](new OutputSC(ISZ[Value](StringValue("Outputs ARE COOL"), StringValue("101"), StringValue("This is an Output"))), new OutputSC(ISZ[Value](StringValue("Beep Boop Line"), StringValue("3092"), StringValue("Haha robot talk"))), new OutputSC(ISZ[Value](StringValue("I get called"), StringValue("2138"), StringValue("I love outputs"))))
    val component = new compSC(new InputsSC(input), new OutputsSC(output), "Component")
    val input2 = ISZ[InputSC](new InputSC(ISZ[Value](StringValue("I am a port"), StringValue("1470"), StringValue("More inputs!"))), new InputSC(ISZ[Value](StringValue("Accident"), StringValue("1987"), StringValue("The bite of '87?"))))
    val output2 = ISZ[OutputSC](new OutputSC(ISZ[Value](StringValue("Ink"), StringValue("1929"), StringValue("Bendy?"))), new OutputSC(ISZ[Value](StringValue("Pizzas"), StringValue("103"), StringValue("Pizza orders"))))
    val component2 = new compSC(new InputsSC(input2), new OutputsSC(output2), "Component 2")
    ISZ[compSC](component, component2)
  }
}
