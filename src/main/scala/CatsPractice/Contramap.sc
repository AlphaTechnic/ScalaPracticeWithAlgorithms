trait Printable[-A] {
  self =>
  def customFormat(value: A): String
  def contramap[B](func: B => A): Printable[B] = new Printable[B] {
    override def customFormat(value: B): String = {
      self.customFormat(func(value))
    }
  }
}

trait List[+A] {
  def map
}

object Printable {
  def apply[A](implicit ev: Printable[A]): Printable[A] = ev
}

implicit val anyPrintable: Printable[Any] = new Printable[Any] {
  override def customFormat(value: Any): String = {
    value.toString
  }
}

implicit class printableOps[A](a: A)(implicit ev: Printable[A]) {
  def customFormat(value: A): String = ev.customFormat(value)
}

"sefawfe".customFormat("ABCDE// ")

implicit val stringPrintable: Printable[String] = Printable[Int].contramap((x: String) => (x.toInt))
Printable[Int].contramap((x: Double) => (x.toInt)).customFormat(3.6)

// for test
def format[A](value: A)(implicit p: Printable[A]): String = p.customFormat(value)

implicit val stringPrintable: Printable[String] =
  new Printable[String] {
    override def customFormat(value: String): String =
      "[" + value + "]"
  }

implicit val booleanPrintable: Printable[Boolean] =
  new Printable[Boolean] {
    override def customFormat(value: Boolean): String =
      if (value) "yeees"
      else "nooo"
  }

format("hello")
format(true)


// Box 클래스를 위한 Printable 인스턴스
final case class Box[A](value: A)

implicit def boxPrintable[A](implicit ev: Printable[A]): Printable[Box[A]] = {
  ev.contramap[Box[A]](x => x.value)
}

format(Box("hello"))
format(Box(true))
