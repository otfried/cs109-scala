
def id(x: Int): Int = x

def cube(x: Int): Int = x * x * x

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

def sumInt(a: Int, b: Int): Int = sum(id, a, b)
def sumCubes(a: Int, b: Int): Int = sum(cube, a, b)
def sumFact(a: Int, b: Int): Int = sum(fact, a, b)

