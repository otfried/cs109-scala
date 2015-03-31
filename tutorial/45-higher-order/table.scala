
def printTable(f: Int => Int, a: Int, b: Int) {
  for (i <- a to b) {
    printf("%4d: %d\n", i, f(i))
  }
}

