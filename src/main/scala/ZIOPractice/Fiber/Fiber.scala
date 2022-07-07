package ZIOPractice.Fiber

import zio._

// Pseudo Code
/*
object Fiber extends ZIOAppDefault {
  val fiberTest =
    for {
      fiber1 <- longRunnigJob.fork
      fiber2 <- anotherLongRunningJob.fork
      _ <- Console.printLine("Execution of two job started")
      result <- (fiber1 <*> fiber2).join
    } yield result

  def run = fiberTest
}
*/
