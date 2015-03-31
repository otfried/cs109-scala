
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
  CS109UI.setTitle("CS109 UI Test #2")

  val canvas = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)

  var x = 30
  var y = 30
  while (x < 500) {
    draw(canvas, x, y)
    x += 5
    y += 2
    CS109UI.show(canvas)
    Thread.sleep(20)
  }

  val (x1, y1) = CS109UI.waitMouse()
  printf("Mouse clicked at %d, %d\n", x1, y1)

  while (true) {
    val ch = CS109UI.waitKey()
    printf("Got character %c\n", ch)
    if (ch == 'q')
      CS109UI.close()
  }
}

main()
