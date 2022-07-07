package ZIOPractice.ZipCombinator

import zio._

import java.io.IOException

object ZipCombinator extends ZIOAppDefault {
  val zipCombi: ZIO[Has[Console] with Has[Random], IOException, Unit] =
    for {
      _ <- Console.printLine("Enter a new user name")
      tuple <- Random.nextUUID zip Console.readLine
      (uuid, username) = tuple
      _ <- Console.printLine(uuid)
      _ <- Console.printLine(username)
    } yield ()

  def run: ZIO[Has[Console] with Has[Random], IOException, Unit] = zipCombi
}
