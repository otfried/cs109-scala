
val wordFile = "words.txt"

def anagramMap(): Map[String, Set[String]] = {
  val words = scala.io.Source.fromFile(wordFile).getLines.toSet
  var m = scala.collection.mutable.Map[String, Set[String]]()
  for (word <- words) {
    val s = word.sorted
    if (m contains s) {
      m(s) += word
    } else
      m(s) = Set(word)
  }
  m.toMap
}

val anagrams = anagramMap()

def findAnagrams(w: String): Set[String] = anagrams.getOrElse(w.sorted, Set())

def showLargeSets(k: Int, m: Int) {
  val b = new scala.collection.mutable.ArrayBuffer[String]
  for ((w, s) <- anagrams) {
    if (s.size >= k && w.length >= m)
      b += w
  }
  val a = b.toArray.sorted
  for (w <- a)
    println(anagrams(w) mkString ", ")
}
