
case class Point(x: Int, y: Int, color: String)

def more(l: Array[Point]) {
  for (i <- 0 until l.length) {
    l(i) = Point(l(i).y, l(i).x, l(i).color)
  }
  // HERE
}

def test(m: Int, s: String) {
  val a = Point(1, m, s)
  val b = Array(a, Point(2, 7, "blue"))
  val c = b(1)
  more(b)
}
