
import scala.collection.mutable.Set

def sieve(n: Int): Set[Int] = {
  var s = Set[Int]()
  for (i <- 2 to n)
    s += i
  val sqrtn = math.sqrt(n).toInt
  for (i <- 2 to sqrtn) {
    if (s contains i) {
      var k = i * i
      while (k <= n) {
	s -= k
	k += i
      }
    }
  }
  s
}
  
val num = if (args.nonEmpty) args(0).toInt else 1000

val primes = sieve(num).toArray.sorted

for (i <- primes)
  print(i + " ")
println()
