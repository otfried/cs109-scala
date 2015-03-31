
def next(n: Int): Int = {
  if (n % 2 == 0)
    n / 2
  else
    3 * n + 1
}

def collatzCount(n0: Int): Int = {
  var n = n0
  var count = 0
  while (n != 1) {
    n = next(n)
    count += 1
  }
  count
}

def findMax(n: Int) {
  var maxCount = 0
  var maxStart = 1
  for (i <- 2 to n) {
    val count = collatzCount(i)
    if (count > maxCount) {
      maxCount = count
      maxStart = i
    }
  }
  printf("Starting at %d needs %d steps.\n", maxStart, maxCount)
}
