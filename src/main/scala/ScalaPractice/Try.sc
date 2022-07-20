import scala.util.{Try,Success,Failure}

def toInt(s: String): Try[Int] = Try(Integer.parseInt(s.trim))

val a = toInt("1")
val b = toInt("boo")

val c: Either[String, Int] = Left("A")
