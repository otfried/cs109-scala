
class Point(val x: Int, val y: Int)

class Rect(var corner: Point, var width: Int, var height: Int) {
  require(width > 0 && height > 0)
}
 
