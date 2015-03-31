
object Kermit {
  println("Kermit is born")
  
  def main(args: Array[String]) {
    val n = if (args.length == 1) args(0).toInt else 1
    for (i <- 1 to n)
      println("It's not easy being green!")
  }
}
