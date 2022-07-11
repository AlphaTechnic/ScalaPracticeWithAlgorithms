import zio.UIO

//
trait Printable[A] {

}



// 인터페이스는
// 1. 추상 스태틱 함수를 만들 수 없다
// 2. 자기 자신 타입을 지정할 수 없다
//trait Addable {
//
//  // 이거 안됨
//  // def getZero: Self
//
//  // 이거 안됨
//  def add(rhs: Self): Self
//}

// 타입파라미터를 활용하면 억지로라도 가능하다
trait Addable[A] {
  def getZero: A
  def add(rhs: A): A
}

// 인터페이스를 통해서 구현한거에요
class MyInteger(val value: Int) extends Addable[MyInteger] {

  override def getZero = new MyInteger(0)

  override def add(rhs: MyInteger) =
    new MyInteger(value + rhs.value)
}

class MyComplex(val real: Int, imaginary: Int) extends MyInteger(real) {
}

val lhs = new MyComplex(5, 2)
val rhs = new MyComplex(3, 5)

val myIntegerLhs: MyInteger = lhs

rhs.getZero // 어색해

// 타입이 MyInteger다. 원하는게 아님
val result = lhs.add(rhs)

// 타입 클래스 방식
// 인터페이스처럼 extend 해서 쓰는게 아님
trait AddableV2[A] {
  def getZeroV2: A
  def addV2(lhs: A, rhs: A): A
}

// 타입 클래스 MyInteger 구현
implicit val myIntegerAddable: AddableV2[MyInteger] = new AddableV2[MyInteger] {
  override def getZeroV2 = new MyInteger(0)

  override def addV2(lhs: MyInteger, rhs: MyInteger) = new MyInteger(lhs.value + rhs.value)
}

// Addable을 좀 더 쉽게 찾기 위한 헬퍼 함수
object AddableV2 {
  def apply[A](implicit ev: AddableV2[A]): AddableV2[A] = ev
}

// 1번
implicitly[AddableV2[MyInteger]].getZeroV2
// 2번
AddableV2.apply[MyInteger].getZeroV2
// 3번
AddableV2[MyInteger].getZeroV2

implicit class AddableV2Ops[A](lhs: A)(implicit ev: AddableV2[A]) {
  def addV2(rhs: A): A = ev.addV2(lhs, rhs)
}

val three = new MyInteger(3)
val five = new MyInteger(5)

// 4번
val eight = three.addV2(five)


def getAddableAndDoMultiply[A: AddableV2](a: A, multiplyCount: Int): A = {
  var result: A = a
  for (_ <- 0 until multiplyCount - 1) {
    result = a.addV2(result)
  }
  result
}

val a = getAddableAndDoMultiply(eight, 5)
println(a.value)


List(1)


val option = Some(1)
val option: Option[Int] = (None: Option[Nothing])
val option: Option[String] = None
val option: Option[Float] = None
val efew: Unit = ()


implicit class StringOps(lhs: String) {
  def +#@@#(rhs: String): String = lhs + rhs
}


val a = "Hello" +#@@# "World"
println(a)
