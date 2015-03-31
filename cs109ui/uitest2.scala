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

def showSleep(canvas: BufferedImage, color: Color, ms: Int) {
  draw(canvas, color)       // draw rectangle
  show(canvas)
  Thread.sleep(ms)          // wait ms milliseconds
}

def main() {
  setTitle("CS109 UI Blinking Rectangle")

  val canvas = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)

  showSleep(canvas, Color.WHITE, 500) // 0.5 sec white picture
  showSleep(canvas, Color.RED, 1000)  // 1 sec red rectangle
  showSleep(canvas, Color.WHITE, 500) // 0.5 sec white picture
  showSleep(canvas, Color.BLUE, 1000) // 1 sec blue rectangle
  showSleep(canvas, Color.WHITE, 5000) // 5 secs white picture  
  
  close() // close window and terminate program
}

main()
