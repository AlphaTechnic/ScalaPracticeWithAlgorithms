case class Person(name: String, age: Int, score: Int)

//object Person {
//  def unapply(p: Person): (String, Int) = {
//    val tuple = (p.name, p.age)
//    tuple
//  }
//}

val p = Person(name = "kim", age = 15, score = 1)
val result = Person.unapply(p)
val aa = Person.toString()
