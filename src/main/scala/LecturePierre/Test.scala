package LecturePierre

import zio._

import scala.language.postfixOps

object Test extends scala.App {
  // ZIO[-R, +E, +A]
  val f1: ZIO[Any, Nothing, Unit] = ZIO.succeed { println("hello 1") }
  val f2: ZIO[Any, Throwable, Unit] = ZIO.attempt { 1 / 0 }

  trait WeatherApi {
    def getWeather: ZIO[Any, Throwable, String]
  }

  case class WeatherApiImpl(random: Random) extends WeatherApi {
    def getWeather: ZIO[Any, Throwable, String] =
      random.nextIntBetween(1, 4).map {
        case 1 => "rainy"
        case 2 => "sunny"
        case 3 => "cloudy"
      }
  }

  val layer: ZLayer[Has[Random], Nothing, Has[WeatherApi]] =
    ZIO
      .service[Random]
      .map(random => WeatherApiImpl(random))
      .toLayer

  val f: ZIO[Has[Clock] with Has[Console] with Has[WeatherApi], Nothing, Unit] =
    for {
      weather <- ZIO.serviceWith[WeatherApi](_.getWeather).orDie
      _ <- Console.printLine(weather).delay(2 seconds).ignore
      b <- f2.catchAll(e => Console.printLine(s"An error happened: $e").ignore)
    } yield ()

  val g: ZIO[zio.ZEnv, Nothing, Unit] = f.repeatN(3).provideCustomLayer(layer)

  zio.Runtime.default.unsafeRun(g)
}
