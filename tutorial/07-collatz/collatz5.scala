
def next(n: Int): Int = {
  if (n % 2 == 0)
    n / 2
  else
    3 * n + 1
}

def collatzBounded(n0: Int, steps: Int) {
  var n = n0
  var count = 0
  while (n != 1 && count < steps) {
    print(n)
    print(" ")
    n = next(n)
    count += 1
  }
  println
}
