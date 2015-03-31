
def readPronounciations(): Map[String,Set[String]] = {
  val F = scala.io.Source.fromFile("cmudict.txt")
  var M = Map[String,Set[String]]()
  for (l <- F.getLines()) {
    if (l(0).isLetter) {
      val p = l.trim.split("\\s+", 2)
      val i = p(0).indexOf('(')
      val word = (if (i >= 0) p(0).substring(0,i) else p(0)).toLowerCase
      val pro = p(1)
      val S = M.getOrElse(word, Set())
      M = M + (word -> (S + pro))
    }
  }
  M
}

def reverseMap(M: Map[String,Set[String]]): Map[String,Set[String]] = {
  var R = Map[String,Set[String]]()
  for ((key, vals) <- M) {
    for (w <- vals) {
      val S = R.getOrElse(w, Set())
      R = R + (w -> (S + key))
    }
  }
  R
}

def showHomophones() {
  val M = readPronounciations()
  var R = reverseMap(M)
  var homophones = List[Set[String]]()
  for ((pro, words) <- R) {
    if (words.size > 1)
      homophones = words :: homophones
  }
  homophones = homophones.sortWith((x, y) => x.size > y.size)
  var count = 0
  for (words <- homophones) {
    count += 1
    printf("Group %d (%d words):\n", count, words.size)
    println("  " + words.mkString(" "))
  }
}

def findWords() {
  val M = readPronounciations()
  for ((word, pro1) <- M) {
    val ord = word.substring(1)
    if ((M contains ord) && (pro1 intersect M(ord)).nonEmpty)
      println(word)
  }
}

def solvePuzzle() {
  val M = readPronounciations()
  for ((word, pro1) <- M) {
    val ord = word.substring(1)
    val wor = word.substring(0, word.length-1)
    if ((M contains ord) && (M contains wor) && 
	(pro1 intersect M(ord) intersect M(wor)).nonEmpty)
      println(word)
  }
}

// showHomophones()


