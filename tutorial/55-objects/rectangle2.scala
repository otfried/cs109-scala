
class Point(val x: Int, val y: Int) {
  override def toString: String = "(%d, %d)".format(x, y)
}

class Rect(x: Int, y: Int, var width: Int, var height: Int) {
  require(width > 0 && height > 0)
  var corner = new Point(x, y)
  override def toString: String = 
    "[%d ~ %d, %d ~ %d]".format(corner.x, corner.x + width, 
				corner.y, corner.y + height)
}
 
