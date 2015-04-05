import scala.io.StdIn.readInt

object NumberGame {
  var secret = 0
  var guess = -1
  
  def answerGuess() {
    if (guess == secret)
      println("You got it")
    else if (guess > secret)
      println("Too big")
    else if (guess < secret)
      println("Too small")
  }
  
  def main(args: Array[String]) {
    secret = (math.random * 100).toInt
    while (guess != secret) {
      print("Guess my number> ")
      guess = readInt()
      answerGuess()
    }
  }
}
