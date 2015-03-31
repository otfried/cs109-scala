def square(x: Int): Int = x * x

def squareList(s: List[Int]): List[Int] = 
  s match {
    case Nil => Nil
    case x :: xs => square(x) :: squareList(xs)
  }
