
import scala.collection.mutable.Map

def histogram(fname: String): Map[String, Int] = {
  val F = scala.io.Source.fromFile(fname)
  val hist = Map[String, Int]()

  for (line <- F.getLines()) {
    if (line != "") {
      val words = line.split("[ ,:;.?!()-]+")
      for (word <- words) {
	val upword = word.toUpperCase
	if (hist contains upword)
	  hist(upword) += 1
	else
	  hist(upword) = 1
      }
    }
  }
  hist
}

def printHistogram(h: Map[String, Int]) {
  val sorted = h.toArray.sorted
  for ((word, count) <- sorted)
    printf("%20s: %d\n", word, count)
}

if (args.length != 1) {
  println("Usage: scala histogram1.scala <file name>")
  sys.exit(1)
}
  
val fname = args(0)
val hist = histogram(fname)
printHistogram(hist)
