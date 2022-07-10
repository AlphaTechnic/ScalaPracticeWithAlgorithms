package ZIOPractice.FiberSupervision

import zio._
import zio.Clock._
import zio.Console._

object FiberSupervision extends ZIOAppDefault {
  val child: ZIO[Has[Clock] with Has[Console], Any, Unit] =
    printLine("Child fiber beginning execution...").orDie *>
      ZIO.sleep(5.seconds) *>
      printLine("Hello from a child fiber!").orDie

  val parent: ZIO[Has[Clock] with Has[Console], Any, Unit] =
    printLine("Parent fiber beginning execution...").orDie *>
      child.fork *>
      ZIO.sleep(3.seconds) *>
      printLine("Hello from a parent fiber!").orDie

  val example: ZIO[Has[Clock] with Has[Console], Nothing, Unit] =
    for {
      fiber <- parent.fork
      _ <- ZIO.sleep(1.second)
      _ <- fiber.interrupt
      _ <- ZIO.sleep(10.seconds)
    } yield ()

  def run: ZIO[Has[Clock] with Has[Console], Nothing, Unit] = example
}
