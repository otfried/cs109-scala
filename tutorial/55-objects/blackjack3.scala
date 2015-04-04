
val Suits = Array("Clubs", "Spades", "Hearts", "Diamonds")
val Faces = Array("2", "3", "4", "5", "6", "7", "8", "9", "10", 
		  "Jack", "Queen", "King", "Ace")

class Card(val face: String, val suit: String) {
  require(Suits contains suit)
  require(Faces contains face)

  override def toString: String = {
    val a = if (face == "Ace" || face == "8") "an " else "a "
    a + face + " of " + suit
  }

  def value: Int = face match {
    case "Ace" => 11
    case "Jack" => 10
    case "Queen" => 10
    case "King" => 10
    case _ => face.toInt
  }
}

class Deck {
  private val cards = new Array[Card](52)
  private var count = 52
  generateDeck()
  shuffleDeck()
  
  private def generateDeck() {
    var i = 0
    for (suit <- Suits) {
      for (face <- Faces) {
	cards(i) = new Card(face, suit)
	i += 1
      }
    }
  }
  private def shuffleDeck() {
    for (i <- 1 to 52) {
      // 0..i-2 already shuffled
      val j = (math.random * i).toInt
      val cj = cards(j)
      cards(j) = cards(i-1)
      cards(i-1) = cj
    }
  }

  def draw(): Card = {
    assert(count > 0)
    count -= 1
    cards(count)
  }
}

// Compute the value of a hand of cards.
def handValue(hand: Array[Card]): Int = {
  var value = 0
  for (card <- hand)
    value += card.value
  value
}

/* Display the text prompt and let's the user enter a string.  If the
  user enters "y", the function returns "true", and if the user enters
  "n", the function returns "false" If the user enters anything else,
  the function prints "I beg your pardon!", and asks again, repeating
  this until the user has entered a correct string.  */
def askYesNo(prompt: String): Boolean = { 
  while (true) {
    val user_input = readLine(prompt)
    user_input match {
      case "y" => return true
      case "n" => return false
      case _ => println("I beg your pardon!")
    }
  }
  true // to make compiler happy
}

// Play one round of Blackjack
//  Returns 1 if player wins, -1 if dealer wins, and 0 for a tie.
def blackjack(): Int = {
  val deck = new Deck()

  // initial cards
  var player = Array(deck.draw())
  println("You are dealt " + player.head)
  var dealer = Array(deck.draw())
  println("Dealer is dealt a hidden card")
    
  player = player :+ deck.draw()
  println("You are dealt " + player.last)
  dealer = dealer :+ deck.draw()
  println("Dealer is dealt " + dealer.last)
  printf("Your total is %d\n", handValue(player))

  // player's turn to draw cards
  var want = true
  while (want && handValue(player) < 21) {
    want = askYesNo("Would you like another card? (y/n) ")
    if (want) {
      player = player :+ deck.draw()
      println("You are dealt " + player.last)
      printf("Your total is %d\n", handValue(player))
  
      // if the player's score is over 21, the player loses immediately.
      if (handValue(player) > 21) {
	println("You went over 21! You lost!")
	return -1
      }
    }
  }
  
  println("The dealer's hidden card was " + dealer.head)
  while (handValue(dealer) < 17) {
    dealer = dealer :+ deck.draw()
    println("Dealer is dealt " + dealer.last)
  }
  printf("The dealer's total is %d\n", handValue(dealer))
  
  // summary
  val player_total = handValue(player)
  val dealer_total = handValue(dealer)
  printf("\nYour total is %d\n", player_total)
  printf("The dealer's total is %d\n", dealer_total)

  if (dealer_total > 21) {
    println("The dealer went over 21! You win!")
    1
  } else if (player_total > dealer_total) {
    println("You win!")
    1
  } else if (player_total < dealer_total) {
    println("You lost!")
    -1
  } else {
    println("You have a tie!")
    0
  }
}

def gameLoop() {
  println("Welcome to Blackjack!")
  while (true) {
    println()
    blackjack()    
    if (!askYesNo("\nPlay another round? (y/n) "))
      return
  }
}

gameLoop()
