
def next(n: Int): Int = {
  if (n % 2 == 0)
    n / 2
  else
    3 * n + 1
}
