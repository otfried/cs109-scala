
class Point(val x: Int, val y: Int) {
  override def toString: String = "(%d, %d)".format(x, y)
}

class Rect(var corner: Point, var width: Int, var height: Int) {
  require(width > 0 && height > 0)
  override def toString: String = 
    "[%d ~ %d, %d ~ %d]".format(corner.x, corner.x + width, 
				corner.y, corner.y + height)
}
 
