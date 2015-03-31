
import scala.compat.Platform.currentTime
import scala.io.Source

val fname = "words.txt"

def readWords(): Array[String] = {
  val F = Source.fromFile(fname)
  val A = new Array[String](200000)
  var count = 0
  for (line <- F.getLines()) {
    A(count) = line
    count += 1
  }
  val B = new Array[String](count)
  for (i <- 0 until count)
    B(i) = A(i)
  B
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

