class User(val name: String) {
  def greet: String = s"Hello from $name"
  override def toString = s"User($name)"
}

val users = List(new User("Shoto"), new User("Art3mis"), new User("Aesch"))
val third = users.find(_.name contains "3")
val greet = third map (_.greet) getOrElse "hi"

abstract class Car {
  val year: Int
  val automatic: Boolean = true
  def color: String

  override def toString: String = s"$color-$year Car"
}

class RedMini(val year: Int) extends Car {
  def color = "Red"
}
val m: Car = new RedMini(2005)
