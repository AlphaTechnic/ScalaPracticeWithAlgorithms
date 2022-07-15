import cats.Eval

// Eval.always는 lazy하게 연산된다.(def와 비슷)
val greeting = Eval.
  always {println("Step1"); "Hello"}.
  map {str => println("Step2"); s"$str world"}

greeting.value


// 다만 eval 인스턴스라고 할지라도 mapping functions는 항상 lazy
