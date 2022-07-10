import cats.Show
import cats.implicits.catsSyntaxEq
import cats.instances.int._

val showInt: Show[Int] = Show.apply[Int]
val intAsString: String = showInt.show(123)

import cats.syntax.show._
val showInt = 123.show
