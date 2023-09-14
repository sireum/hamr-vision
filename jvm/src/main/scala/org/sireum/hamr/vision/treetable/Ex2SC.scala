package org.sireum.hamr.vision.treetable

class Ex2SC {
  def build: Array[compSC] = {
    // ======= Hardcoded Values (info for the "Model" -- the data to be displayed =======
    val input = Array[InputSC](new InputSC(Array[Object]("I am a port", "1022", "This is an input port")), new InputSC(Array[Object]("Port number 2", "127", "I am another port")), new InputSC(Array[Object]("This is an input", "4102", "Inputs are cool")))
    val output = Array[OutputSC](new OutputSC(Array[Object]("Outputs ARE COOL", "101", "This is an Output")), new OutputSC(Array[Object]("Beep Boop Line", "3092", "Haha robot talk")), new OutputSC(Array[Object]("I get called", "2138", "I love outputs")))
    val component = new compSC(new InputsSC(input), new OutputsSC(output), "Component")
    val input2 = Array[InputSC](new InputSC(Array[Object]("I am a port", "1470", "More inputs!")), new InputSC(Array[Object]("Accident", "1987", "The bite of '87?")))
    val output2 = Array[OutputSC](new OutputSC(Array[Object]("Ink", "1929", "Bendy?")), new OutputSC(Array[Object]("Pizzas", "103", "Pizza orders")))
    val component2 = new compSC(new InputsSC(input2), new OutputsSC(output2), "Component 2")
    Array[compSC](component, component2)
  }
}
