
val NRows = 5
val NCols = 4
val NSubrows = 3
val NSubcols = 5

class Block(val width: Int, val height: Int,
	    val x: Int, val y: Int) {

  override def toString: String = 
    "Block(%d, %d) @ (%d, %d)".format(width, height, x, y)

  def draw(row: Int, col: Int, subrow: Int): Boolean = {
    if (col < x || x + width <= col ||
	row < y || y + height <= row)
      false 
    else {
      val top = (subrow == 0 && row == y + height - 1)
      val bottom = (subrow == NSubrows-1 && row == y)
      val left = (col == x)
      val right = (col == x + width - 1)
      if (top || bottom) {
	print(if (left) "+" else "-")
	print("-" * (NSubcols - 2))
	print(if (right) "+" else "-")
      } else {
	print(if (left) "|" else " ")
	print(" " * (NSubcols - 2))
	print(if (right) "|" else " ")
      }
      true
    }
  }

  def canMove(dir: (Int, Int)): Boolean = {
    val (dx, dy) = dir
    (0 <= x + dx && x + width + dx <= NCols &&
     0 <= y + dy && y + height + dy <= NRows)
  }
    
  def move(dir: (Int, Int)): Block = {
    require(canMove(dir))
    val (dx, dy) = dir
    new Block(width, height, x + dx, y + dy)
  }

  def overlaps(other: Block): Boolean = 
    !(x + width <= other.x || x >= other.x + other.width ||
      y + height <= other.y || y >= other.y + other.height)
}

class Board {
  val blocks = new Array[Block](9)
  blocks(0) = new Block(2, 2, 0, 3)
  blocks(1) = new Block(2, 1, 2, 4)
  blocks(2) = new Block(2, 1, 2, 3)
  blocks(3) = new Block(2, 1, 2, 1)
  blocks(4) = new Block(2, 1, 2, 0)
  blocks(5) = new Block(1, 1, 0, 2)
  blocks(6) = new Block(1, 1, 1, 2)
  blocks(7) = new Block(1, 2, 0, 0)
  blocks(8) = new Block(1, 2, 1, 0)

  def draw() {
    for (row <- NRows - 1 to 0 by -1) {
      for (subrow <- 0 until NSubrows) {
	for (col <- 0 until NCols) {
	  if (!blocks.exists(x => x.draw(row, col, subrow)))
	    print(" " * NSubcols)
	}
	println()
      }
    }
  }

  def canMove(b: Block, dir: (Int, Int)): Boolean = {
    b.canMove(dir) && {
      val bm = b.move(dir)
      !blocks.exists(x => x != b && bm.overlaps(x))
    }
  }

  def done: Boolean = (blocks(0).x == 0 && blocks(0).y == 0)

  def movableBlocks(dir: (Int, Int)): Seq[(Int, Block)] = 
    ((0 until blocks.length) zip blocks) filter (x => canMove(x._2, dir))
  
  def update(i: Int, dir: (Int, Int)) {
    blocks(i) = blocks(i).move(dir)
  }
}

def askDirection(prompt: String): (Int, Int) = { 
  while (true) {
    val user_input = readLine(prompt)
    user_input.toLowerCase match {
      case "x" => sys.exit(0)
      case "l" => return (-1, 0)
      case "r" => return (1, 0)
      case "u" => return (0, 1)
      case "d" => return (0, -1)
      case _ => println("I beg your pardon!")
    }
  }
  (0, 0) // to make compiler happy
}

def askIndex(prompt: String, maxVal: Int): Int = {
  while (true) {
    val user_input = readLine(prompt)
    try {
      val k = user_input.toInt - 1
      if (0 <= k && k < maxVal)
	return k
      println("Enter a number between 1 and " + maxVal)
    } catch {
      case e: NumberFormatException => 
	println("I beg your pardon!")
    }
  }
  0 // to make compiler happy
}

def main() {
  val board = new Board
  while (!board.done) {
    board.draw()
    val dir = askDirection("Which way do you want to move (u/d/l/r)? ")
    val b = board.movableBlocks(dir)
    b.length match {
      case 0 => println("No such move is possible")
      case 1 => board.update(b(0)._1, dir)
      case _ => {
	println("Select block to move: ")
	for (i <- 0 until b.length)
	  println("  [%d] (%d,%d)".format(i+1, b(i)._2.x, b(i)._2.y))
	val k = askIndex("Move which block? ", b.length) 
	board.update(b(k)._1, dir)
      }
    }
  }
  println("Congratulations!")
}

main()
