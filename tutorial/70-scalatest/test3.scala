import org.scalatest.FunSuite

class AdditionCheckSuite extends FunSuite {
  val two = 2
  val three = 3

  test("one plus one") {
    assert(1 + 1 == two)
    assert(1 + 1 != three)
  }

  test("one plus one with result") {
    assertResult(two) { 1 + 1 }
  }

  test("division by zero") {
    intercept[ArithmeticException] {
      1 / 0
    }
  }
}
