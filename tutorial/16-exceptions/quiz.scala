
def g(s: String, n: Int) {
  if (n > 10)
    throw new IllegalArgumentException
  for (i <- 1 to n)
    println(s)
}
  
def f(s: String) {
  val t = s + " World"
  g(t, 3)
}

def main(m: Int) {
  try {
    val r = 13 * m
    f("Hello")
  } catch {
    case e: IllegalArgumentException =>
      println("Oops, something was wrong")
  }
}

main(3)


