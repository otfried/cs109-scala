
def cube(x: Int): Int = x * x * x

def fact(x: Int): Int = {
  var f = 1
  for (i <- 1 to x)
    f *= i
  f
}

def sumInt(a: Int, b: Int): Int = {
  var s = 0
  for (i <- a to b)
    s += i
  s
}

def sumCubes(a: Int, b: Int): Int = {
  var s = 0
  for (i <- a to b)
    s += cube(i)
  s
}

def sumFact(a: Int, b: Int): Int = {
  var s = 0
  for (i <- a to b)
    s += fact(i)
  s
}

