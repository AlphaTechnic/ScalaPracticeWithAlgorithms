package ZIOPractice.Composing

import zio._
import zio.Console

object Composing extends ZIOAppDefault{
  val greeting =
    for {
      _ <- Console.printLine("Hello! What is your name?")
      n <- Console.readLine
      _ <- Console.printLine("Hello, " + n + ", good to meet you!")
    } yield ()

  def run = greeting
}
