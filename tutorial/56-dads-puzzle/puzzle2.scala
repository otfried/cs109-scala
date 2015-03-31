
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
}

val board = new Board
board.draw()
println()
board.blocks(3) = board.blocks(3).move((0,1))
board.draw()
