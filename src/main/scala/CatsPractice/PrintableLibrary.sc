// phase 1
trait Printable[A] {
  def customFormat(value: A): String
}

// phase 2
object PrintableInstances {
  implicit val PrintableInt: Printable[Int] = new Printable[Int] {
    override def customFormat(value: Int): String =
      value.toString
  }
}

// phase 3
object PrintableInterface {
  implicit class PrintableOps[A](value: A) {
    def format(implicit ev: Printable[A]): Unit = {
      ev.customFormat(value)
    }
  }
}

import PrintableInstances._
