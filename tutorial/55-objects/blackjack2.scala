
val Suits = Array("Clubs", "Spades", "Hearts", "Diamonds")
val Faces = Array("2", "3", "4", "5", "6", "7", "8", "9", "10", 
		  "Jack", "Queen", "King", "Ace")

class Card(val face: String, val suit: String) {
  require(Suits contains suit)
  require(Faces contains face)

  override def toString: String = {
    val a = if (face == "Ace" ||
                face == "8")
                "an " else "a "
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

val deck = new Deck
for (i <- 1 to 10)
  println(deck.draw())

