trait Printable[A] {
  def format(value: A): String
}

implicit val PrintableInt: Printable[Int] = new Printable[Int] {
  def format(value: Int): String = {
    value.toString()
  }
}

implicit class PrintableOps[A](value: A) {
  def format(implicit ev: Printable[A]): String = {
    ev.format(value)
  }
}

123.format
