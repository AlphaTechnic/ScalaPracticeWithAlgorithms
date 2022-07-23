val list = List(1, 2) ::: List(3, 4)
list drop 3
list.filter(a => a <= 2)

val list = List(List(List(1, 2), 3), List(List(2, 3), 4))
list.flatten

val list = List(2, 3, 5, 7)
list slice (1, 3)
list splitAt 2
list drop 2
list take 2
list.partition(a => a <= 5)._2

val list = List("zpple", "to")
list.sortBy(a => a)
list.sorted

List(1, 2, 3, 4) zip List("apple", "banana", "car")

val list = List("apple, banana", "car, driver")
list
  .map(string => string.split(" ,"))

List(4, 5, 6).foldRight(0)(_ + _)
List(4, 5, 6).scanRight(0)(_ + _)
List(4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8, 8, 8).scan(0)(_ - _)
List(4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8, 8, 8).scanLeft(0)(_ - _)

List(1, 2, 3).mkString(",")
List(1, 2, 3).toString
Set((1, 2), (3, 4)).toMap
