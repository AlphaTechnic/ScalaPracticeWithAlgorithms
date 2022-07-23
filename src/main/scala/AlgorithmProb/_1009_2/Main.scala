package AlgorithmProb._1009_2

import java.io.BufferedInputStream
import java.util.Scanner

object Main {
  val sc = new Scanner(new BufferedInputStream(System.in))
  val MOD = 10

  def inputTwoInt(): (Int, Int) = {
    val a = sc.nextInt()
    val b = sc.nextInt()
    (a, b)
  }

  def outputSideEffect(answer: List[Int]): Unit = {
    for (elem <- answer) {
      println(elem)
    }
  }

  def getRemain(a: Int, b: Int): Int = {
    var total = 1
    for (_ <- 1 to b) {
      total = (total * a) % MOD
    }
    total match {
      case ans if ans != 0 => ans
      case _               => 10
    }
  }

  def main(args: Array[String]): Unit = {
    val iter = sc.nextInt()
    for (_ <- 1 to iter) {
      val tuple2 = inputTwoInt()

      val answer = getRemain(tuple2._1, tuple2._2)

      println(answer)
    }
  }
}
