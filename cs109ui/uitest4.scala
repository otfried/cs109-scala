import org.otfried.cs109.UI._

import java.awt.image.BufferedImage
import java.awt.{Graphics2D,Color,Font,BasicStroke}
import java.awt.geom._

def draw(canvas: BufferedImage, color: Color) {
  val g = canvas.createGraphics()
  g.setColor(Color.WHITE)
  g.fillRect(0, 0, canvas.getWidth, canvas.getHeight)
  g.setColor(color)
  g.fillRect(100, 100, 300, 300)
  g.dispose()
}

def main() {
  setTitle("CS109 UI Keyboard Input Test")

  val canvas = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)

  draw(canvas, Color.RED)
  show(canvas)

  println("Now click the moused inside the CS109 UI windows")
  println("Close the window to terminate the program")

  while (true) {
    val (x,y) = waitMouse()
    printf("Mouse click at (%d,%d)\n", x, y)
  }
}

main()
