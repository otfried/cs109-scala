class Polynomial(coeffs: Array[Int]) {

  def degree: Int = ???

  def coeff(i: Int): Int = ???
  
  override def toString: String = ???
  
  def + (rhs: Polynomial): Polynomial = ???
  
  def - (rhs: Polynomial): Polynomial = ???
  
  def * (rhs: Polynomial): Polynomial = ???

  def ^ (ex: Int): Polynomial = ???

  def apply(x: Double): Double = ???
}
