
import scala.compat.Platform.currentTime
import scala.io.Source

val fname = "words.txt"

def readWords(): Array[String] = {
  val F = Source.fromFile(fname)
  var A = new scala.collection.mutable.ArrayBuffer[String]
  for (line <- F.getLines()) {
    A += line
  }
  A.toArray
}

println("Starting...")
val t0 = currentTime
val A = readWords()
val t1 = currentTime
printf("Reading all %d words took %d milliseconds\n", A.length, t1 - t0)

println()
println("The first ten words are:")
for (i <- 0 until 10)
  println(A(i))

