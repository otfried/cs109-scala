
val K = 100 // number of intervals to approximate

def integrate(f: Double => Double, a: Double, b: Double): Double = {
  require(b > a)
  val delta = (b - a)/ K
  var total = 0.0
  var x = a
  for (i <- 1 to K) {
    total += (f(x) + f(x+delta)) * 0.5 * delta
    x += delta
  }
  total
}
