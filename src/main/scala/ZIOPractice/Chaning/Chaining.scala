package ZIOPractice.Chaning

import zio._
import zio.Console._

import java.io.IOException

object Chaining extends ZIOAppDefault {
  val sequenced: ZIO[Has[Console], IOException, Unit] =
    readLine.flatMap(input => printLine(s"You entered: $input"))

  def run = sequenced
}
