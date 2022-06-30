package Practice.unapply

import scala.util.Random

object Unapply {
  object CustomerID {

    def apply(name: String) = s"$name--${Random.nextLong}"

    def unapply(customerID: String): Option[String] = {
      val stringArray: Array[String] = customerID.split("--")
      if (stringArray.tail.nonEmpty) Some(stringArray.head) else None
    }
  }

  def main(args: Array[String]): Unit = {
    val customer1ID = CustomerID("Sukyoung") // Sukyoung--23098234908
    println(customer1ID)
    customer1ID match {
      case CustomerID(name) => println(name) // prints Sukyoung
      case _ => println("Could not extract a CustomerID")
    }
  }
}
