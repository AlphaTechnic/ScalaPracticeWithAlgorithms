import cats.Monad
import cats.Id

implicit def IdMondad: Monad[Id] = new Monad[Id] {
  override def pure[A](a: A): Id[A] = {
    a
  }
  override def flatMap[A, B](fa: Id[A])(f: A => Id[B]): Id[B] = {
    f(fa)
  }

  override def map[A, B](fa: Id[A])(f: A => B): Id[B] =
    this.flatMap(fa)(a => this.pure(f(a)))
}
