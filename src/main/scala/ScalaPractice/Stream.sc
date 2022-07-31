def inc(head: Int): Stream[Int] =
  head #:: inc(head + 1)

inc(10).take(5).toList
//inc()

val a = List('a' -> 1, 'b' -> 2, 'c' -> 3)
val first = a.map(_._1)
val second = a.map(_._2)
(first zip second).toMap

val evens = List(1, 3, 5) filter (_ % 2 == 0)
evens.headOption

val list = List(1, 2, 5)
list.find(_ % 2 == 0)

def nextOption = if (util.Random.nextInt > 0) Some(1) else None
nextOption.fold(-1)(x => x)
//nextOption.getOrElse(77)
nextOption orElse nextOption

def loopAndFail(end: Int, failAt: Int): Int = {
  for (i <- 1 to end) {
    println(s"${i})")
    if (i == failAt) {
      throw new Exception("Too many iterations!")
    }
  }
  end
}

util.Try(loopAndFail(10, 3))
//loopAndFail(10, 3)

def nextError = util.Try {
  List(1, 2, 3)
}

nextError foreach { x =>
  println("success!! " + x)
}
