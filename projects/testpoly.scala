
object PolyTest {
  implicit def intToPolynomial(a: Int): Polynomial = {
    new Polynomial(Array(a))
  }
  
  def main(args: Array[String]) {
    val X = new Polynomial(Array(0, 1))
    
    val p1 = new Polynomial(Array(3))
    val p2 = new Polynomial(Array(-1, 3, -4, 0, -6))
    val p3 = new Polynomial(Array(0, 0, 1))
    
    val p4 = -1 * (X^5) + 3 * (X^3) - (X^2) + 5
    val p5 = (X - 1) * (X - 3) * (X + 5)^2
    
    printf("p1 = %s\n", p1)
    printf("p2 = %s\n", p2)
    printf("p3 = %s\n", p3)
    printf("p4 = %s\n", p4)
    printf("p5 = %s\n", p5)
    printf("p1 + p2 = %s\n", p1 + p2)
    printf("p1 * p3 = %s\n", p1 * p3)
    printf("p3 * p4 = %s\n", p3 * p4)
    printf("p2 - p4 = %s\n", p2 - p4)
    printf("p2 * p5 = %s\n", p2 * p5)
    
    printf("p1(5) = %g\n", p1(5))
    printf("p2(0) = %g\n", p2(0))
    printf("p3(2) = %g\n", p3(2))
    printf("p5(-5) = %g\n", p5(-5.0))
    
    // printf("p5' = %s\n", p5.derivative)
    // printf("p5'' = %s\n", p5.derivative.derivative)
  }
}
