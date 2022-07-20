def toInt(valueStr: String): Option[Int] = {
  try {
    Some(Integer.parseInt(valueStr))
  }
  catch {
    case e: Exception => None
  }
}

val a = toInt("1")
val a = toInt("1")

val b = toInt("foo")
val b = toInt("foo")

val stringA = "1"
val stringB = "foo"
val stringC = "3"

val y = for {
  a <- toInt(stringA)
  b <- toInt(stringB)
  c <- toInt(stringC)
} yield a + b + c

toInt("1").foreach(println)
toInt("x").foreach(println)
toInt("y").foreach(println)
