import scala.language.higherKinds
import cats.Functor
import cats.instances.list._ // for Functor
import cats.instances.option._ // for Functor

val list1 = List(1, 2, 3)
val list2 = Functor[List].map(list1)(_ * 2)

val option1 = Option(123)
val option2 = Functor[Option].map(option1)(_.toString)

// lift
val func = (x: Int) => x + 1
val liftedFunc = Functor[Option].lift(func)
liftedFunc(Option(123))

// functor syntax
import scala.language.higherKinds
import cats.syntax.functor._ // for map

def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] =
  start.map(n => (n + 1) * 2)


doMath(Option(20))

doMath(List(1, 2, 3))