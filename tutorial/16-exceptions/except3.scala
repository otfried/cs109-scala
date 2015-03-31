import scala.io.StdIn.readLine

def f(n: Int) {
  printf("Starting f(%d) ... \n", n)
  g(n)
  printf("Ending f(%d) ... \n", n)
}

def g(n: Int) {
  printf("Starting g(%d) ... \n", n)
  if (n < 0)
    throw new IllegalArgumentException
  printf("The value is %d\n", n)
  printf("Ending g(%d) ... \n", n)
}

def main() {
  while (true) {
    val s = readLine("Enter a number> ")
    if (s.isEmpty)
      return
    try {
      println("Beginning of try block")
      val n = s.toInt
      f(n)
      println("End of try block")
    } catch {
      case e: NumberFormatException 
      => println("Please enter a number!")
      case e: IllegalArgumentException
      => e.printStackTrace()
    }
  }
}

main()
