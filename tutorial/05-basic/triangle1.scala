def triangle(n: Int) {
  for (i <- 1 to n) {
    for (j <- 1 to i)  
	print("*")
    println()
  }
}

val size = 5
triangle(size)
