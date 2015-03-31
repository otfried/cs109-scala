def factorial(n: Int): Long = {
  require(n >= 0)
  var result = 1L
  for (i <- 1 to n)
    result *= i
  result
}

println(factorial(args(0).toInt))

