def square(x: Int): Int = x * x

def squareList(s: List[Int]): List[Int] = 
  if (s == Nil) Nil else square(s.head) :: squareList(s.tail)

