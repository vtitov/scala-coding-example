import scala.annotation.tailrec

trait SumMatcherLike {
  def findAddends: (Int, Seq[Int]) => Seq[Int]
}

class FastSumMatcher extends SumMatcherLike {

  def findAddends: (Int, Seq[Int]) => Seq[Int] = getSumPartsFast(Set.empty)

  @tailrec
  private def getSumPartsFast(set: Set[Int])(sum:Int, seq: Seq[Int]): Seq[Int] = {
    // convert to list as unapply head::tail does not work with Vector(0)
    seq.toList match {
      case Nil => Seq.empty
      case x :: tailSeq => // FIXME unapply list does not work with Vector(0)
        val y = sum - x
        if(set.contains(y)) { Seq(x,y).reverse } else { getSumPartsFast(set + x)(sum, tailSeq)}
    }
  }
}

class SlowSumMatcher extends SumMatcherLike {

  def findAddends: (Int, Seq[Int]) => Seq[Int] = getSumPartsSlow

  @tailrec
  private def getSumPartsSlow(sum:Int, seq: Seq[Int]): Seq[Int] = {
    // convert to list as unapply head::tail does not work with Vector(0)
    seq.toList match {
      case Nil => Seq.empty
      case x :: tailSeq => // FIXME unapply list does not work with Vector(0)
        tailSeq.find(y => x + y == sum) match {
          case None => getSumPartsSlow(sum, tailSeq)
          case Some(y) => Seq(x,y)
        }
    }
  }
}
