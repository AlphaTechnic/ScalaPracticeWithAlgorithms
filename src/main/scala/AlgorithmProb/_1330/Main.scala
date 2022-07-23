package AlgorithmProb._1330

import java.io.BufferedInputStream
import java.util.Scanner

object Main {
  val sc = new Scanner(new BufferedInputStream(System.in))

  def inputSideEffect(): (Int, Int) = {
    val a = sc.nextInt()
    val b = sc.nextInt()
    (a, b)
  }

  def outputSideEffect[A](answer: A): Unit = {
    println(answer)
  }

  def main(args: Array[String]): Unit = {
    val tuple2 = inputSideEffect()

    val answer = tuple2 match {
      case (a, b) if (a > b)  => ">"
      case (a, b) if (a == b) => "=="
      case (a, b) if (a < b)  => "<"
    }

    outputSideEffect(answer)
  }
}
