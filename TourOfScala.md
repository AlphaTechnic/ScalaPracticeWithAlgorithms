# Intro

- OOP
- FP
- statically typed

# Basics

- Blocks

  - 블락의 결과는 그냥 어떤 value

- `functions` vs `methods`

  - functions

    ```scala
    (x: Int) => x + 1
    ```

  - methods

    ```scala
    def add(x: Int, y: Int): Int = x + y
    ```

- case class
  - new 키워드 없이 선언할 수 있음
  - compared by `value`, not by reference
- traits
  - 자바 인터페이스 비슷
  - override 키워드로 메서드 구현

# Classes

- Private Members and Getter / Setter

  ```scala
  class Point {
    private var _x = 0
    private var _y = 0
    private val bound = 100
  
    def x: Int = _x
    def x_= (newValue: Int): Unit = {
      if (newValue < bound) _x = newValue else printWarning()
    }
  
    def y: Int = _y
    def y_= (newValue: Int): Unit = {
      if (newValue < bound) _y = newValue else printWarning()
    }
  
    private def printWarning() = println("WARNING: Out of bounds")
  }
  
  val point1 = new Point
  point1.x = 99
  point1.y = 101 // prints the warning
  ```

  -  the method has `_=` appended to the identifier of the **getter** and the parameters come after.

# Traits

- 자바의 interface와 비슷
- classes and objects can extend traits
- traits는 instantiated 될 수 없음 => constructor 파라미터 없음

```scala
trait Iterator[A] {
  def hasNext: Boolean
  def next(): A
}

class IntIterator(to: Int) extends Iterator[Int] {
  private var current = 0
  override def hasNext: Boolean = current < to
  override def next(): Int = {
    if (hasNext) {
      val t = current
      current += 1
      t
    } else 0
  }
}


val iterator = new IntIterator(10)
iterator.next()  // returns 0
iterator.next()  // returns 1
```

# Tuples

- 패턴 매칭

```scala
val ingredient = ("Sugar", 25)
val (name, quantity) = ingredient

println(name) // Sugar
println(quantity) // 25
```

# Class Composition with Mixins

- Mixin
  - mixins are traits which are used to **compose a class**
- example 1

```scala
abstract class A {
  val message: String
}
class B extends A {
  val message = "I'm an instance of class B"
}
trait C extends A {
  def loudMessage = message.toUpperCase()
}

class D extends B with C
val d = new D
println(d.message)  // I'm an instance of class B
println(d.loudMessage)  // I'M AN INSTANCE OF CLASS B
```

- example 2

```scala
abstract class AbsIterator {
  type T
  def hasNext: Boolean
  def next(): T
}

class StringIterator(s: String) extends AbsIterator {
  type T = Char
  private var i = 0
  def hasNext = i < s.length
  def next() = {
    val ch = s charAt i
    i += 1
    ch
  }
}

trait RichIterator extends AbsIterator {
  def foreach(f: T => Unit): Unit = while (hasNext) f(next())
}

class RichStringIter extends StringIterator("Scala") with RichIterator
val richStringIter = new RichStringIter
richStringIter.foreach(println)
```

# Higher-order Functions

- functions that accept functions
- functions that return functions

# Currying

- Implicit parameters

  ```scala
  def execute(arg: Int)(implicit ec: scala.concurrent.ExecutionContext) = ???
  ```

- partial application

  ```scala
  val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val numberFunc = numbers.foldLeft(List[Int]()) _
  
  val squares = numberFunc((xs, x) => xs :+ x*x)
  println(squares) // List(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)
  
  val cubes = numberFunc((xs, x) => xs :+ x*x*x)
  println(cubes)  // List(1, 8, 27, 64, 125, 216, 343, 512, 729, 1000)
  ```

# Case Classes

- copying

  ```scala
  case class Message(sender: String, recipient: String, body: String)
  
  val message4 = Message("julien@bretagne.fr", "travis@washington.us", "Me zo o komz gant ma amezeg")
  val message5 = message4.copy(sender = message4.recipient, recipient = "claire@bourgogne.fr")
  message5.sender  // travis@washington.us
  message5.recipient // claire@bourgogne.fr
  message5.body  // "Me zo o komz gant ma amezeg"
  ```

  - **optionally change** the constructor arguments

# Singletone Objects

- companion objects

```scala
import scala.math._

case class Circle(radius: Double) {
  import Circle._
  def area: Double = calculateArea(radius)
}

object Circle {
  private def calculateArea(radius: Double): Double = Pi * pow(radius, 2.0)
}

val circle1 = Circle(5.0)

circle1.area
```

- **companion object** can also contian **factory methods**

```scala
class Email(val username: String, val domainName: String)

object Email {
  def fromString(emailString: String): Option[Email] = {
    emailString.split('@') match {
      case Array(a, b) => Some(new Email(a, b))
      case _ => None
    }
  }
}

val scalaCenterEmail = Email.fromString("scala.center@epfl.ch")
scalaCenterEmail match {
  case Some(email) => println(
    s"""Registered an email
       |Username: ${email.username}
       |Domain name: ${email.domainName}
     """.stripMargin)
  case None => println("Error: could not parse email")
}
```

# Extractor Objects

```scala
import scala.util.Random

object CustomerID {

  def apply(name: String) = s"$name--${Random.nextLong}"

  def unapply(customerID: String): Option[String] = {
    val stringArray: Array[String] = customerID.split("--")
    if (stringArray.tail.nonEmpty) Some(stringArray.head) else None
  }
}

val customer1ID = CustomerID("Sukyoung")  // Sukyoung--23098234908
customer1ID match {
  case CustomerID(name) => println(name)  // prints Sukyoung
  case _ => println("Could not extract a CustomerID")
}

```



# Variance

- Variance is the correlation of subtyping relationships of complex types and the subtyping relationships of their component types. 

- **covariance(공변성): +**

  - Animal output을 하는 컨테이너에 Bird가 output 되는 것은 자연스럽다.

    ```scala
    val functionofBirdoutput: Function[AnimalOutput] = Function[BirdOutput]()
    ```

  - 다른 예시

    ```scala
    val ListOfAny: List[Any] = List[String]()
    ```
    
  
- **contravariance(반공변성): -**

    - Bird도 Animal이다.

    - Bird가 input으로 들어가서 동작하는 컨테이너에 Animal input이 들어가는 것은 자연스럽다.

      ```scala
      val functionOfBirdInput: Function[BirdInput] = Function[AnimalInput]()
      ```

- **invariance(불공변성)**

    - java의 ArrayList는 invariant하다.

    ```scala
    val ArrayListOfAny: java.util.ArrayList[Any] = java.util.ArrayList[String]()
    
    error: type mismatch;
     found   : util.this.ArrayList[String]
     required: util.this.ArrayList[scala.this.AnyRef]
    Note: String <: scala.this.AnyRef, but Java-defined class ArrayList is invariant in type E.
    You may wish to investigate a wildcard type such as `_ <: scala.this.AnyRef`. (SLS 3.2.10)
      val ArrayListOfAny: java.util.ArrayList[Any] = java.util.ArrayList[String]()
    ```















