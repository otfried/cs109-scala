
def next(n: Int): Int = {
  if (n % 2 == 0)
    n / 2
  else
    3 * n + 1
}

def collatz(n0: Int) {
  var n = n0
  while (n != 1) {
    print(n)
    print(" ")
    n = next(n)
  }
  println(1)
}
