def identityWithMsg[A](value: A)(func: A => Unit): A = {
  func(value)
  value
}

def successMsg[A](msg: A): Unit = {
  println(s"Successfully implemented!!")
}

identityWithMsg(value = "hello world!")(successMsg)
identityWithMsg(value = 123)(successMsg)
