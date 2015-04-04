
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

val c1 = new Card("Ace", "Diamonds")
val c2 = new Card("Jack", "Spades")
val c3 = new Card("8", "Hearts")

val hand = Array(c1, c2, c3)

for (c <- hand)
  printf("Card %s has value %d\n", c, c.value)
