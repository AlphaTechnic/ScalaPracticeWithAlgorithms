package ZIOPractice.Either

import zio._

object ZEither extends ZIOAppDefault {
  val zeither: UIO[Either[String, Nothing]] =
    ZIO.fail("Uh oh!").either

  def run = zeither
}
