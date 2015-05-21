class Polynomial(coeffs: Array[Int]) {
  private val A = createCoeffs(coeffs)
  
  private def createCoeffs(A: Array[Int]): Array[Int] = {
    var s = A.length - 1
    while (s >= 0 && A(s) == 0)
      s -= 1
    A take (s+1)
  }
  
  def degree: Int = A.length - 1

  def coeff(i: Int): Int = if (i < A.length) A(i) else 0
  
  override def toString: String = {
    var s = new StringBuilder
    var plus = ""
    var minus = "-"
    for (i <- degree to 0 by -1) {
      if (coeff(i) != 0) {
	var c = coeff(i)
	s ++= (if (c > 0) plus else minus)
	plus = " + "; minus = " - "
	c = c.abs
	if (i == 0)
	  s ++= c.toString
	else {
	  if (c != 1)
	    s ++= c.toString + " * "
	  if (i > 1)
	    s ++= "X^" + i.toString
	  else 
	    s ++= "X"
	}
      }
    }
    s.toString
  }
  
  def + (rhs: Polynomial): Polynomial = ???
  
  def - (rhs: Polynomial): Polynomial = ???
  
  def * (rhs: Polynomial): Polynomial = ???

  def ^ (ex: Int): Polynomial = ???

  def apply(x: Double): Double = ???
}
