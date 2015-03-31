
def readPronounciations(): Map[String,String] = {
  val F = scala.io.Source.fromFile("cmudict.txt")
  var M = Map[String,String]()
  for (l <- F.getLines()) {
    if (l(0).isLetter) {
      val p = l.trim.split("\\s+", 2)
      val word = p(0).toLowerCase
      if (!(word contains '(')) {
	val pro = p(1)
	M = M + (word -> pro)
      }
    }
  }
  M
}

def reverseMap(M: Map[String,String]): Map[String,Set[String]] = {
  var R = Map[String,Set[String]]()
  for ((word, pro) <- M) {
    val S = R.getOrElse(pro, Set())
    R = R + (pro -> (S + word))
  }
  R
}

def showHomophones1(k: Int) {
  val m = readPronounciations()
  var r = reverseMap(m)
  var homophones = r.toArray.sortWith((x,y) => x._2.size > y._2.size)
  for ((pro, words) <- homophones) {
    if (words.size >= k) {
      printf("%s (%d words):\n", pro, words.size)
      println("  " + words.mkString(" "))
    }
  }
}

def showHomophones(k: Int) {
  val m = readPronounciations()
  var r = reverseMap(m)
  for ((pro, words) <- r) {
    if (words.size >= k) {
      printf("%s (%d words):\n", pro, words.size)
      println("  " + words.mkString(" "))
    }
  }
}

def findWords() {
  val m = readPronounciations()
  for ((word, pro) <- m) {
    val ord = word.substring(1)
    if (pro == m.getOrElse(ord, ""))
      println(word)
  }
}

def solvePuzzle() {
  val m = readPronounciations()
  for ((word, pro) <- m) {
    if (word.length > 2 && word(0) != word(1)) {
      val ord1 = word.substring(1)
      val ord2 = word(0) + word.substring(2)
      if (pro == m.getOrElse(ord1, "") && 
	  pro == m.getOrElse(ord2, ""))
	println(word)
    }
  }
}
