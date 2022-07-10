package ZIOPractice.ForkingEffects

import zio.Console.printLine
import zio._

object ForkingEffects extends ZIOAppDefault {
  def fib(n: Long): UIO[Long] = ZIO.succeed {
    if (n <= 1) ZIO.succeed(n)
    else fib(n - 1).zipWith(fib(n - 2))(_+ _)
  }.flatten

  val fib100Fiber: UIO[Fiber[Nothing, Long]] =
    for {
      fiber <- fib(100).fork
      _ <- ZIO.succeed(fiber.toString)
    } yield fiber

  def run = fib100Fiber
}
