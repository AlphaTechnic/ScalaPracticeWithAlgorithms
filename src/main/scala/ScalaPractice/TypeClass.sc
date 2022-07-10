// Type class
// 상속을 하지 않고 기능을 확장할 수 있게 해주는 패턴
// polymorphism의 예시

// 1. type class itself
trait Logger[T] {
  def logging(value: T): String
}


// 2. instances for particular types
// 타입별 연산을 구현한 인스턴스
import java.time._

object Loggers {
  implicit val i: Logger[Int] = new Logger[Int] {
    def logging(value: Int): String
    = LocalDateTime.now().toString + "-" + value.toString
  }

  implicit val s: Logger[String] = new Logger[String] {
    def logging(value: String)
    = LocalDateTime.now().toString + "-" + value
  }
}


// 3. interface methods that we expose to users
// 실제 사용자가 사용하게 될 메소드 정의
// 해당 메소드는 implicit 파라미터로 기능을 전달받게 된다.
object Log {
  def logging[T](value: T)(implicit logger: Logger[T]): String = {
    logger.logging(value)
  }
}

// 이용하는 방식
import Loggers._

Log.logging(10000)
Log.logging("This is test")