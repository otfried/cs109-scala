
object Days {
  val digits = "0123456789"

  // if s is not a legal date, or is not in range, 
  // then throws IllegalArgumentException
  def getDate(s: String): Date = {
    if (s.length != 10 || s(4) != '/' || s(7) != '/')
      throw new IllegalArgumentException
    for (i <- 0 until s.length) {
      if (i != 4 && i != 7 && !digits.contains(s(i)))
	throw new IllegalArgumentException
    }
    val year = s.substring(0, 4).toInt
    val month = s.substring(5, 7).toInt
    val day = s.substring(8).toInt
    Date(year, month, day)
  }

  def main(args: Array[String]) {
    try {
      if (args.length == 1) {
	val d = getDate(args(0))
	printf("%s is a %s\n", args(0), d.dayOfWeek)
      } else if (args.length == 2) {
	val d1 = getDate(args(0))
	val d2 = getDate(args(1))
	printf("There are %d days between %s and %s\n", d2 - d1, d1, d2)
      } else if (args.length == 3) {
	val d1 = getDate(args(0))
	val sign = if (args(1) == "-") -1 else +1
	val dist = args(2).toInt
	val d2 = d1 + sign * dist
	printf("%s %s %d days = %s\n", d1, args(1), dist, d2)
      } else {
	Console.err.println("Must have one, two, or three arguments")
      }
    } catch {
      case e: NumberFormatException =>
	Console.err.println("Illegal number")
      case e: IllegalArgumentException => 
	Console.err.println("Illegal date")
    }
  }
}
