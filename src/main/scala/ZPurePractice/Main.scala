package ZPurePractice

import zio.prelude.fx.ZPure

object Main extends App {
  // ZPure[+W, -S1, +S2, -R, +E, +A]

  // default:         (S1, R) => (List[W], Either[E, (S2, A)])
  // clearLogOnError: (S1, R) => Either[E, (List[W], S2, A)]

  // KingdomProgram[A] = ZPure[KingdomEvent, KingdomState, KingdomState, KingdomEnv, ValidationError, A]
  // KingdomReader[A] = ZPure[Any, KingdomState, KingdomEnv, ValidationError, A]

  case class KingdomState(value: Int)
  case class KingdomEnv(threshold: Int)

  def myProgram(
      i: Int
  ): ZPure[String, KingdomState, KingdomState, KingdomEnv, String, Unit] = {
    for {
      _ <- ZPure.log("Enter myProgram!!!!!")

      threshold <- ZPure.serviceWith[KingdomEnv](env => env.threshold)
      _ <-
        if (i > threshold)
          ZPure.fail("i is over the threshold")
        else
          ZPure.unit[KingdomState]
      state <- ZPure.get[KingdomState]

      _ <- ZPure.log(s"before: $state")
      _ <- ZPure.set(state.copy(value = state.value + i))
      _ <- ZPure.log(s"after: $state")

      _ <- ZPure.log(s"End of myProgram!!!!")
    } yield ()
  }

  val res1 = myProgram(3)
    .provideService(KingdomEnv(threshold = 2))
    .clearLogOnError
    .runAll(KingdomState(0))

  val res2 = myProgram(2)
    .provideService(KingdomEnv(threshold = 2))
    .clearLogOnError
    .runAll(KingdomState(0))

  println(res1)
  println(res2)
}
