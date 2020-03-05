package contra_pro

import cats.Functor
import cats.arrow.Profunctor

class Exercise3_Profunctor {

  /** Exercise P.1 - Define instance of Profunctor for Function1 */
  val Function1Profunctor: Profunctor[Function1] = new Profunctor[Function1] {
    override def dimap[A,B,C,D](fab: A => B)(f: C => A)(g: B => D): C => D = ???
  }

  /** Exercise P.2 - Define 2 different instances of Profunctor for ZIO */
  case class ZIO[R,E,A](run: R => Either[E,A])

  def zioProfunctor[E]: Profunctor[ZIO[*,E,*]] = new Profunctor[ZIO[*,E,*]] {
    override def dimap[R,A,RR,AA](fab: ZIO[R,E,A])(f: RR => R)(g: A => AA): ZIO[RR,E,AA] = ???
  }

  def zioProfunctor2[A]: Profunctor[ZIO[*,*,A]] = new Profunctor[ZIO[*,*,A]] {
    override def dimap[R,E,RR,EE](fab: ZIO[R,E,A])(f: RR => R)(g: E => EE): ZIO[RR,EE,A] = ???
  }

  /** Exercise P.3 - Define instance of Profunctor for Kleisli */
  case class Kleisli[F[_],A,B](run: A => F[B])

  def kleisliProfunctor[M[_]](MM: Functor[M]): Profunctor[Kleisli[M,*,*]] = new Profunctor[Kleisli[M,*,*]] {
    override def dimap[A,B,C,D](fab: Kleisli[M,A,B])(f: C => A)(g: B => D): Kleisli[M,C,D] = ???
  }

  /** Exercise P.4 - Define instance of Profunctor for Kleisli */
  case class CoKleisli[F[_],A,B](run: F[A] => B)

  def coKleisliProfunctor[F[_]](implicit FF: Functor[F]): Profunctor[CoKleisli[F,*,*]] = new Profunctor[CoKleisli[F,*,*]] {
    override def dimap[A, B, C, D](fab: CoKleisli[F, A, B])(f: C => A)(g: B => D): CoKleisli[F, C, D] = ???
  }
}
