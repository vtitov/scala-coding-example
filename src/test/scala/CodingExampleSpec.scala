
import org.scalatest.Matchers._
import org.scalatest.WordSpec


trait CodingExampleBehaviours {
  this: WordSpec =>
  val sumMatcher: SumMatcherLike

  def findAndVerifyAddends(sum: Int, seq: Seq[Int])(expected: Seq[Int]): Boolean = {
    if(expected.sum != sum) throw new IllegalArgumentException(s"${expected.sum} != $sum")
    expected == sumMatcher.findAddends(sum, seq)
  }

  "no match" should {
    "fail with zero and empty" in {
      sumMatcher.findAddends(0, Nil) should be(Nil)
    }
    "fail with empty" in {
      sumMatcher.findAddends(10, Nil) should be(Nil)
    }
    "fail with single element" in {
      sumMatcher.findAddends(10, 10 :: Nil) should be(Nil)
    }
    "fail with single zero element" in {
      sumMatcher.findAddends(10, 1 :: 0 :: Nil) should be(Nil)
    }
    "fail with two too small elements" in {
      sumMatcher.findAddends(10, 1 :: 0 :: Nil) should be(Nil)
    }
  }
  "match" should {
    "pass with two matching elements" in {
      sumMatcher.findAddends(10, 1 :: 9 :: Nil) should equal(Seq(1, 9))
    }
    "pass several matching elements" in {
      sumMatcher.findAddends(10, Seq(1, 2, 3, 4, 5, 5, 11, 12, 13)) should equal(Seq(5, 5))
    }
  }
}


class SlowSpec extends WordSpec with CodingExampleBehaviours {
  val sumMatcher = new SlowSumMatcher
  "match slowly" should {
    "pass with multiple matching sets" in {
      findAndVerifyAddends (12, Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)) (Seq(3, 9)) shouldBe true
    }
  }
}

class FastSpec extends WordSpec with CodingExampleBehaviours {

  val sumMatcher = new FastSumMatcher
  "match fast" should {
    "pass with multiple matching sets" in {
      findAndVerifyAddends (12, Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)) (Seq(5, 7)) shouldBe true
    }
  }
}
