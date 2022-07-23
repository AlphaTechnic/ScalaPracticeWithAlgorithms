package AlgorithmProb._1009

import java.io.BufferedInputStream
import java.util.Scanner

object Main {
  val sc = new Scanner(new BufferedInputStream(System.in))
  val MOD = 10

  def inputSideEffect(): List[(Int, Int)] = {
    val iter = sc.nextInt()
    var list: List[(Int, Int)] = List()
    for (_ <- 1 to iter) {
      val tuple2 = inputTwoInt()
      list = list.appended(tuple2)
    }
    list
  }

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

  def getRemain(tuple2: (Int, Int)): Int = {
    val a = tuple2._1
    val b = tuple2._2

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
    val tupleList: List[(Int, Int)] = inputSideEffect()

    val answer = tupleList.map(elem => getRemain(elem))

    outputSideEffect(answer)
  }
}
