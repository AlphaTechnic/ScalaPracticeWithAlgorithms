package ScalaPractice.Future

import concurrent.ExecutionContext.Implicits.global
import concurrent.Future

object Future {
  def apply(i: Int) = ???

  def main(args: Array[String]): Unit = {
    def nextFtr(i: Int = 0): Future[Int] = Future {
      def rand(x: Int) = util.Random.nextInt(x)

      Thread.sleep(rand(5000))
      if (rand(3) > 0) i + 1 else throw new Exception
    }

    nextFtr(1) fallbackTo nextFtr(1)
  }
}
