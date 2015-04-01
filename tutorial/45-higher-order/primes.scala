
val n = args(0).toInt
val sqrtn = math.sqrt(n).toInt

var s = (2 to n).toList

while (s.head < sqrtn) {
  print(s.head + " ")
  s = s.tail filter (_ % s.head != 0)
}
println(s mkString " ")
