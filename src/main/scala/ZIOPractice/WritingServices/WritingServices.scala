package ZIOPractice.WritingServices

import zio._

object WritingServices extends ZIOAppDefault {
  // Service Definition
  trait Logging {
    def log(line: String): UIO[Unit]
  }

  // Companion object containing accessor methods
  object Logging {
    def log(line: String): URIO[Has[Logging], Unit] = ZIO.serviceWith[Logging](_.log(line))
  }

  // Live implementation of Logging service
  case class LoggingLive(console: Console, clock: Clock) extends Logging {
    override def log(line: String): UIO[Unit] =
      for {
        current <- clock.currentDateTime
        _ <- console.printLine(s"$current--$line").orDie
      } yield ()
  }

  // Companion object of LoggingLive containing the service implementation into the ZLayer
  object LoggingLive {
    val layer: URLayer[Has[Console] with Has[Clock], Has[Logging]] =
      (LoggingLive(_, _)).toLayer
  }

  override def run: ZIO[zio.ZEnv with Has[ZIOAppArgs], Any, Any] = ???
}
