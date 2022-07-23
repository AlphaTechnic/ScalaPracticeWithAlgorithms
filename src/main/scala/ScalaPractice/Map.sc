val someList = List("i am a boy", "you are a girl")
someList.map(str => str.split(" "))
someList.flatMap(str => str.split(" "))

//val aList = List(Option(1), Option(2), None)
//aList.map {
//  case Some(value) => Some(value * 2)
//  case None        =>
//}
//aList.flatMap { case Some(value) =>
//  List(Some(value * 2))
////  case None =>
//}

val list1 = List(List(1, 2), List(3), List(4, 5))
list1.flatten.sum
