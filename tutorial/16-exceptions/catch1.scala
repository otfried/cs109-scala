import scala.io.StdIn.readLine

val str = readLine("Enter a number> ")
try {
  val x = str.toInt
  printf("You said: %d\n", x)
} catch {
  case e: NumberFormatException =>
    printf("'%s' is not a number\n", str);
}
