package AlgorithmProb._1000

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

    val answer = tuple2._1 + tuple2._2

    outputSideEffect(answer)
  }
}
