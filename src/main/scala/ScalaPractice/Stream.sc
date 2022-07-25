def inc(head: Int): Stream[Int] =
  head #:: inc(head + 1)

inc(10).take(5).toList
//inc()

val a = List('a' -> 1, 'b' -> 2, 'c' -> 3)
val first = a.map(_._1)
val second = a.map(_._2)
(first zip second).toMap
