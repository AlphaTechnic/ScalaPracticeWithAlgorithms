class SomeClass {
  def printFilename() = {
    println(SomeClass.HiddenFilename)
  }
}

object SomeClass {
  private val HiddenFilename = "/tmp/foo.bar"

  def apply() = new SomeClass

}

SomeClass().printFilename()
SomeClass().printFilename()

class Person {
  var name = ""
}

object Person {
  def apply(name: String): Person = {
    val p = new Person
    p.name = name
    p
  }
}

Person(name = "alpha").name

class Person {
  var name: Option[String] = None
  var age: Option[Int] = None
  override def toString = s"$name, $age"
}

object Person {

  // a one-arg constructor
  def apply(name: Option[String]): Person = {
    var p = new Person
    p.name = name
    p
  }

  // a two-arg constructor
  def apply(name: Option[String], age: Option[Int]): Person = {
    var p = new Person
    p.name = name
    p.age = age
    p
  }

}
