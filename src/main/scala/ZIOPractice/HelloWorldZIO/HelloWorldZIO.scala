package ZIOPractice.HelloWorldZIO

import zio._
import java.io.IOException

object HelloWorldZIO extends ZIOAppDefault {
  val myApp: ZIO[Has[Console], IOException, Unit] =
    Console.printLine("Hello, World!")

  def run = myApp
}

