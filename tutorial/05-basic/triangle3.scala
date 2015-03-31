def triangle(n: Int) {
  for (i <- 1 to n) {
    for (j <- 1 to i)  
	print("*")
    println()
  }
}

if (args.length == 1) {
  val size = args(0).toInt
  triangle(size)
} else 
  println("Usage: scala triangle.scala <size>")
