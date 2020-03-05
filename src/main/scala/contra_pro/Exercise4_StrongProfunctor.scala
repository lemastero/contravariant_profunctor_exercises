package contra_pro

import cats.arrow.{Choice, Strong}

class Exercise4_StrongProfunctor {

  /** Exercise SP.1 - Define instance of Strong Profunctor for Function1 */
  val Function1Strong: Strong[Function1] = new Strong[Function1] {
    override def first[A,B,C](fa: A => B): ((A, C)) => (B, C) = ???
    override def second[A,B,C](fa: A => B): ((C, A)) => (C, B) = ???
    override def dimap[A,B,C,D](fab: A => B)(f: C => A)(g: B => D): C => D = ???
  }

  /** Exercise SP.2 - Define  Step Profunctor in terms of Strong and Choice Profunctors */
  trait Step[P[_,_]] extends Choice[P] with Strong[P] {
    def step[A,B,C,D](pab: P[A,B]): P[Either[D, (A,C)], Either[D, (B,C)]] = ???
  }
}
