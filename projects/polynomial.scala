// --------------------------------------------------------------------
// Polynomials
// --------------------------------------------------------------------

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
  
  def + (rhs: Polynomial): Polynomial = {
    val deg = degree max rhs.degree
    val R = new Array[Int](deg + 1)
    for (i <- 0 to deg)
      R(i) = coeff(i) + rhs.coeff(i)
    new Polynomial(R)
  }
  
  def - (rhs: Polynomial): Polynomial = {
    val deg = degree max rhs.degree
    val R = new Array[Int](deg + 1)
    for (i <- 0 to deg)
      R(i) = coeff(i) - rhs.coeff(i)
    new Polynomial(R)
  }
  
  def * (rhs: Polynomial): Polynomial = {
    // first handle case if one factor is the zero polynomial
    if (degree < 0)
      this
    else if (rhs.degree < 0)
      rhs
    else {
      val deg = degree + rhs.degree
      val R = new Array[Int](deg + 1)
      for (i <- 0 to deg) {
	var sum = 0
	for (j <- 0 to i)
	  sum += coeff(j) * rhs.coeff(i - j)
	R(i) = sum
      }
      new Polynomial(R)
    }
  }

  def ^ (ex: Int): Polynomial = {
    ex match {
      case 0 => new Polynomial(Array(1))
      case 1 => this
      case 2 => this * this
      case 3 => this * this * this
      case _ => {
	val p = this ^ (ex / 2)
	if (ex % 2 == 1) p * p * this else p * p
      }
    }
  }

  // evaluate this polynomial at x
  def apply(x: Double): Double = {
    var result = 0.0
    for (i <- 0 to degree) {
      var power = 1.0
      for (j <- 1 to i)
	power *= x
      result += coeff(i) * power
    }
    result
  }
}
