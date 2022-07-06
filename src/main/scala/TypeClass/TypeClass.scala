package TypeClass

object TypeClass {
  sealed trait Message

  trait Serializer[A] {
    def serialize(thing: A): Message
  }

  final case class PlainTextMessage(body: String) extends Message

  final case class User(name: String, mail: String)

  object ListSerializer {
    implicit val UserWriter: Serializer[User] =
      new Serializer[User] {
        override def serialize(user: User): Message =
          PlainTextMessage(s"name:${user.name}, mail:${user.mail}")
      }
  }

  // a. Interface Object
  object Message {
    def toMessage[A](thing: A)(implicit s: Serializer[A]): Message =
      s.serialize(thing)
  }

  // b. Interface Syntax
  object ListSerializerSyntax {
    implicit class ListSerializerOps[A](thing: A) {
      def toMessage(implicit s: Serializer[A]): Message =
        s.serialize(thing)
    }
  }

  // c. implicit
  import ListSerializer._
  def serialize(user: User): Message = implicitly[Serializer[User]].serialize(user)


  def main(args: Array[String]): Unit = {
    // a
    import ListSerializer._
    println(Message.toMessage(User("Kim", "Kim@gmail.com")))

    // b
    import ListSerializer._
    import ListSerializerSyntax._
    println(User("Sean", "sean@naver.com").toMessage)

    // c
    println(serialize(User("Lee", "lee@kakao.com")))
  }
}
