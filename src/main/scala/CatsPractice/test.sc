//def countPositive(nums: List[Int]) = nums.foldLeft(Right(0)) { (accumulator, num) =>
//  if(num > 0) {
//    accumulator.map(_ + 1)
//  } else {
//    Left("Negative. Stopping!")
//  }
//}

import cats.syntax.either._

def countPositive(nums: List[Int]) =
  nums.foldLeft(0.asRight[String]) { (accumulator, num) =>
    if(num > 0) {
      accumulator.map(_ + 1)
    } else {
      Left("Negative. Stopping!")
    }
  }

countPositive(List(1, -2, 3))

import cats.implicits._

val right: Either[String, Int] = 7.asRight[String]

val left: Either[String, Int] = "hello 🐈s".asLeft[Int]
