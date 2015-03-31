
def next(n: Int): Int = {
  if (n % 2 == 0)
    n / 2
  else
    3 * n + 1
}

def collatzBounded(n0: Int, steps: Int): Int = {
  var n = n0
  var count = 0
  while (n != 1 && count < steps) {
    n = next(n)
    count += 1
  }
  count
}

def findLong(n: Int, steps: Int) {
  for (i <- 2 to n) {
    val count = collatzBounded(i, steps)
    if (count >= steps) { 
      printf("Starting at %d needs %d steps.\n", i, count)
    }
  }
}
