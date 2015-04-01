import org.otfried.cs109.UI._
import java.awt.image.BufferedImage
import java.awt.{Graphics2D,Color,Font,BasicStroke}
import java.awt.geom._

def draw(canvas: BufferedImage, x: Int, y: Int) {
  val g = canvas.createGraphics()
  g.setColor(Color.WHITE)
  g.fillRect(0, 0, canvas.getWidth, canvas.getHeight)
  g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, 
		     java.awt.RenderingHints.VALUE_ANTIALIAS_ON)
  g.setColor(Color.RED)
  g.fill(new Ellipse2D.Double(x, y, 40.0, 40.0))
  g.dispose()
}

def main() {
  setTitle("CS109 UI Animation test")

  val canvas = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)

  var x = 30
  var y = 30
  while (x < 500) {
    draw(canvas, x, y)
    x += 2
    y += 1
    show(canvas)
    Thread.sleep(10)
  }
}

main()
