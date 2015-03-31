
def fact(x: Int): Int = {
  var f = 1
  for (i <- 1 to x)
    f *= i
  f
}

def sum(f: Int => Int, a: Int, b: Int): Int = {
  var s = 0
  for (i <- a to b)
    s += f(i)
  s
}

def sumInt(a: Int, b: Int): Int = sum((x: Int) => x, a, b)
def sumCubes(a: Int, b: Int): Int = sum((x : Int) => x * x * x, a, b)
def sumFact(a: Int, b: Int): Int = sum(fact, a, b)

