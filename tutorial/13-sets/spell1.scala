
import scala.io.Source
import scala.io.StdIn.readLine

val fname = "words.txt"

val F = Source.fromFile(fname)
val words = F.getLines().toSet

while (true) {
  val w = readLine("Enter a word> ").trim
  if (w == "")
    sys.exit()
  if (words contains w)
    println(w + " is a word")
  else
    printf("Error: %s is not a word\n", w)
}
