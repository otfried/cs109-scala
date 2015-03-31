def test(s: String): Int = {
  (s.toDouble * 100).toInt
}

def show(s: String) {
  try {
    println(test(s))
  } catch {
    case e: NumberFormatException =>
      println("Incorrect input")
  }
}
