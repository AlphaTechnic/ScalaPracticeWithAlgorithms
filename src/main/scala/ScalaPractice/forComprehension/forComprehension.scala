package ScalaPractice.forComprehension

object forComprehension {
  def foo(n: Int, v: Int) =
    for (i <- 0 until n;
         j <- 0 until n if i + j == v)
    yield (i, j)

  def main(args: Array[String]): Unit = {
    foo(10, 10) foreach {
      case (i, j) =>
        println(s"($i, $j) ")  // prints (1, 9) (2, 8) (3, 7) (4, 6) (5, 5) (6, 4) (7, 3) (8, 2) (9, 1)
    }
  }

}
