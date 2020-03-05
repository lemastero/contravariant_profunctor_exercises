package contra_pro

import cats.Contravariant
import monix.eval.Task

class Exercise1_Contravariant {

  /** Exercise C.1 - Define Contravariant instance for Predicate */
  case class Predicate[A](fun: A => Boolean)

  val PredicateContravariant: Contravariant[Predicate] = new Contravariant[Predicate] {
    override def contramap[A, B](pred: Predicate[A])(fba: B => A): Predicate[B] = ???
  }

  /** Exercise C.2 - Define instance of Contravariant for Show */
  trait Show[A] {
    def asString(a: A): String
  }
  val ShowConravariant: Contravariant[Show] = new Contravariant[Show] {
    override def contramap[A, B](fa: Show[A])(f: B => A): Show[B] = ???
  }

  /** Exercise C.3 - Define instance of Contravariant for Function1 with fixed output */
  def fun1Contravariant[R]: Contravariant[Function1[*, R]] = new Contravariant[* => R] {
    override def contramap[A, AA](fa: A => R)(f: AA => A): AA => R = ???
  }

  /** Exercise C.4 - Define instance of Contravariant for Reader */
  case class Reader[R,V](run: R => V)

  def readerContra[V]: Contravariant[Reader[?, V]] = new Contravariant[Reader[*, V]] {
    override def contramap[A, B](fa: Reader[A, V])(f: B => A): Reader[B, V] = ???
  }

  /** Exercise C.5 - Define instance of Contravariant for ZIO */
  case class ZIO[R,E,A](run: R => Either[E,A])

  def zioContravariant[F[_], E, A]: Contravariant[ZIO[*,E,A]] =
    new Contravariant[ZIO[*,E,A]] {
      override def contramap[R, RR](fa: ZIO[R, E, A])(f: RR => R): ZIO[RR, E, A] = ???
    }

  /** Exercise C.6 - Define instance of Contravariant for Logger */
  case class Logger[A](log: A => Task[String])

  val loggerContravariant: Contravariant[Logger] = new Contravariant[Logger] {
    override def contramap[A, B](fa: Logger[A])(f: B => A): Logger[B] = ???
  }

  /** Exercise C.7 - Define Contravariant instance for Opposite category */
  case class Op[R,A](getOp: A => R)

  def opContravariant[R]: Contravariant[Op[R, *]] = new Contravariant[Op[R, *]] {
    override def contramap[A, B](fa: Op[R, A])(f: B => A): Op[R, B] = ???
  }

  /** Exercise C.8 - Define Contravariant instance for Equiv */
  val EquivContravariant: Contravariant[Equiv] = new Contravariant[Equiv] {
    override def contramap[A, B](fa: Equiv[A])(f: B => A): Equiv[B] = ???
  }

  /** Exercise C.9 - Define instance of Contravariant for Ordering */
  val OrderingContravariant: Contravariant[Ordering] = new Contravariant[Ordering] {
    override def contramap[A, B](fa: Ordering[A])(f: B => A): Ordering[B] = ???
  }

  /** Exercise C.10 - Define instance of Contravariant for PartialOrdering */
  val PartialOrderingContravariant: Contravariant[PartialOrdering] = new Contravariant[PartialOrdering] {
    override def contramap[A,B](fa: PartialOrdering[A])(f: B => A): PartialOrdering[B] = new PartialOrdering[B] {
      override def tryCompare(x: B, y: B): Option[Int] = ???
      override def lteq(x: B, y: B): Boolean = ???
    }
  }

  /** Exercise C.11 - Kleisli Contravariant */
  case class Kleisli[F[_],A,B](run: A => F[B])

  def kleisliContravariant[F[_],B]: Contravariant[Kleisli[F,*,B]] = new Contravariant[Kleisli[F,*,B]] {
    override def contramap[A, AA](fa: Kleisli[F, A, B])(f: AA => A): Kleisli[F, AA, B] = ???
  }

  /** Exercise C.12 - Define instance of Contravariant for ContravariantCoyoneda */
  trait ContravariantCoyoneda[F[_], A] {
    type B
    val fb: F[B]
    val m: A => B

    def lowerCoyo(implicit CF: Contravariant[F]): F[A] = CF.contramap(fb)(m)
  }

  object ContravariantCoyoneda {
    def liftCoyo[F[_], AA](fa: F[AA]): ContravariantCoyoneda[F, AA] = new ContravariantCoyoneda[F,AA] {
      type B = AA
      val fb: F[B] = fa
      val m: AA => B = identity[AA]
    }
  }

  def cotravariantContravariantCoyo[F[_]]: Contravariant[ContravariantCoyoneda[F,*]] = new Contravariant[ContravariantCoyoneda[F,*]] {
    override def contramap[AA,BB](fa: ContravariantCoyoneda[F,AA])(f: BB => AA): ContravariantCoyoneda[F,BB] = ???
  }
}
