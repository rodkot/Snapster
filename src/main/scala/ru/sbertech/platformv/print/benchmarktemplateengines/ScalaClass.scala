package ru.sbertech.platformv.print.benchmarktemplateengines

class ScalaClass(val name: String){

  def greet(): String = {
    s"Hello from Scala, $name!"
  }
}