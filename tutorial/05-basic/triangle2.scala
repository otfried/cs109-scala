def triangle(n: Int) {
  for (i <- 1 to n) {
    for (j <- 1 to i)  
	print("*")
    println()
  }
}

val size = args(0).toInt
triangle(size)
