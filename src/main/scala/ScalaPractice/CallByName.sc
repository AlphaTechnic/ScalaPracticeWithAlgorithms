def doubles(x: => Int): Int = {
  println(s"Now doubling ${x}")
  x * 2
}

def f(i: Int): Int = {
  println(s"Hello from f(${i})")
  i
}

doubles(f(8))
