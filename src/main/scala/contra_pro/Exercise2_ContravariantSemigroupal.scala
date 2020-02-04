package contra_pro

import cats.{ContravariantSemigroupal, Monoid}

class Exercise2_ContravariantSemigroupal {

  /** Exercise CS.1 - Define instance of ContravariantSemigroupal for Function1 */
  def fun1Divide[R](implicit MR: Monoid[R]): ContravariantSemigroupal[Function1[*, R]] =
    new ContravariantSemigroupal[Function1[*, R]] {
      override def contramap[A, B](fa: A => R)(f: B => A): B => R = ???
      override def product[A, B](fa: A => R, fb: B => R): ((A, B)) => R = ???
    }

  /** Exercise CS.2 - Define instance of ContravariantSemigroupal for Function1 */
  case class Serializer[A](run: A => Array[Byte])
  implicit val fragmentDivide: ContravariantSemigroupal[Serializer] = new ContravariantSemigroupal[Serializer] {
    override def product[A, B](fa: Serializer[A], fb: Serializer[B]): Serializer[(A, B)] = ???
    override def contramap[A, B](r: Serializer[A])(f: B => A): Serializer[B] = ???
  }

  /** Exercise CS.3 - Define instance of ContravariantSemigroupal for ZIO */
  case class ZIO[R,E,A](run: R => Either[E,A])
  def trioDivide[F[_], E, X](implicit ME: Monoid[E], MA: Monoid[X]): ContravariantSemigroupal[ZIO[*,E,X]] = new ContravariantSemigroupal[ZIO[*,E,X]] {
    override def contramap[A, B](fa: ZIO[A, E, X])(f: B => A): ZIO[B, E, X] = ???
    override def product[A, B](fa: ZIO[A, E, X], fb: ZIO[B, E, X]): ZIO[(A, B), E, X] = ???
  }
}
