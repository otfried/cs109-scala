
import scala.compat.Platform.currentTime

def makestring(A: Array[Int]): String = {
  var s = new StringBuilder
  for (e <- A) {
    if (s.isEmpty)
      s ++= e.toString
    else
      s ++= ", " + e.toString
  }
  s.toString
}

val n = args(0).toInt
val A = (1 to n).toArray

println("Starting...")
val t0 = currentTime
val str = makestring(A)
val t1 = currentTime
// println(str)
printf("Making a string of %d numbers took %d milliseconds\n", n, t1 - t0)
