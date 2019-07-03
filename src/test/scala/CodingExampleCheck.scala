
import org.scalacheck._

object CodingExampleCheck extends Properties("MatchersCheck") {
  import Prop.forAll

  val fastMatcher = new FastSumMatcher
  val slowMatcher = new SlowSumMatcher

  property("match") = forAll{(sum: Int, list: Seq[Int]) =>
    fastMatcher.findAddends(sum, list) match {
      case Nil =>
        slowMatcher.findAddends(sum, list).equals(Nil)
      case x::y::Nil =>
        x+y == sum
      case other =>
        throw new IllegalStateException("unexpected result of getSumParts")
    }
  }

}
