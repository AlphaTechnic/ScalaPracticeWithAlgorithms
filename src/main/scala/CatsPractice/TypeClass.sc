sealed trait Json

final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json
case object JsNull extends Json

final case class Person(name: String, email: String)

// phase 1
trait JsonWriter[A] {
  def write(value: A): Json
}


// phase 2. 타입 각각에 대해 write 다르게 정의하여 인스턴스 생성
object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String] = new JsonWriter[String] {
    override def write(value: String) =
      JsString(value)
  }

  implicit val personWriter: JsonWriter[Person] = new JsonWriter[Person] {
    override def write(value: Person) =
      JsObject(
        Map(
          "name" -> JsString(value.name),
          "email" -> JsString(value.email)
        )
      )
  }
}


// phase 3. 인터페이스 => 타입클래스
object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonWriter[A]): Json =
      w.write(value)
  }
}

import JsonWriterInstances._
import JsonSyntax._

Person("juho", "abc@gmail.com").toJson
