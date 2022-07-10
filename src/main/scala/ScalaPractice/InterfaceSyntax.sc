// define type class
trait Printable[A] {
  def format(value: A) : String
}

object Printable {
  def apply[A](implicit ev: Printable[A]): Printable[A] = ev
}

//instances 구현
object PrintableInstances {
  implicit val PrintableInt : Printable[Int] = new Printable[Int] {
    def format(value: Int): String = {
      value.toString()
    }
  }
  //    이렇게도 구현 가능
  //    implicit val PrintableInt : Printable[Int] = (value: Int) => {
  //      value.toString()
  //    }
  implicit val PrintableString : Printable[String] = new Printable[String] {
    def format(value:String) : String = {
      value
    }
  }
}
// Interface Object
object PrintableInterfaceObject {
  def format[A](value : A)(implicit ev:Printable[A]) : String = {
    ev.format(value)
  }
  def print[A](value : A)(implicit ev:Printable[A]) : Unit = {
    println(ev.format(value))
  }
}
// Interface Syntax
object PrintableInterfaceSyntax {
  implicit class PrintableOps[A](value : A) {
    def format(implicit ev : Printable[A]):String = {
      ev.format(value)
    }
    def print(implicit ev : Printable[A]):Unit = {
      println(ev.format(value))
    }
  }
}

final case class Cat(name: String, age: Int, color: String)
object PrintableInstanceForCat {
  implicit val PrintableCat : Printable[Cat] = new Printable[Cat] {
    def format(value: Cat):String = {
      value.name + " is a "+value.age+" year-old "+value.color+" cat."
    }
  }
}

import PrintableInstanceForCat._
import PrintableInterfaceSyntax._
println(Printable[Cat].format(Cat("Tom",10,"black")))
Cat("Tom",10,"black").print