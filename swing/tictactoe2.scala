import scala.swing._
import scala.swing.event._
import java.awt.{Color,Graphics2D,BasicStroke}
import java.awt.geom._

// --------------------------------------------------------------------

class Board {
  private val grid = Array(0, 1, 2,
			   0, 1, 0,
			   0, 2, 0)

  def apply(x: Int, y: Int): Int = grid(3 * y + x)
}

// --------------------------------------------------------------------

class Canvas(val board: Board) extends Component {
  preferredSize = new Dimension(320, 320)

  listenTo(mouse.clicks)
  reactions += {
    case MouseClicked(_, p, _, _, _) => mouseClick(p.x, p.y)
  }

  // returns squareSide, x0, y0, wid
  private def squareGeometry: (Int, Int, Int, Int) = {
    val d = size
    val squareSide = d.height min d.width
    val x0 = (d.width - squareSide)/2
    val y0 = (d.height - squareSide)/2
    (squareSide, x0, y0, squareSide/3)
  }

  private def mouseClick(x: Int, y: Int) {
    val (squareSide, x0, y0, wid) = squareGeometry
    if (x0 <= x && x < x0 + squareSide &&
	y0 <= y && y < y0 + squareSide) {
      val col = (x - x0) / wid
      val row = (y - y0) / wid
      printf("Mouse pressed in field %d, %d\n", col, row)
    }
  }
    
  override def paintComponent(g : Graphics2D) {
    g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, 
		       java.awt.RenderingHints.VALUE_ANTIALIAS_ON)
    g.setColor(Color.WHITE);
    val d = size
    g.fillRect(0,0, d.width, d.height)
    val (squareSide, x0, y0, wid) = squareGeometry
    g.setColor(Color.BLACK)
    // vertical lines
    for (x <- 1 to 2)
      g.draw(new Line2D.Double(x0 + x * wid, y0, x0 + x * wid, y0 + squareSide))
    // horizontal lines
    for (y <- 1 to 2)
      g.draw(new Line2D.Double(x0, y0 + y * wid, x0 + squareSide, y0 + y * wid))
    g.setStroke(new BasicStroke(3f))
    for (x <- 0 until 3) {
      for (y <- 0 until 3) {
	board(x, y) match {
	  case 1 =>
	    g.setColor(Color.RED)
	    g.draw(new Ellipse2D.Double(x0 + x * wid + 10, y0 + y * wid + 10, 
					wid - 20, wid - 20))
	  case 2 =>
	    g.setColor(new Color(0, 160, 0))
	    val x1 = x0 + x * wid + 10
	    val y1 = y0 + y * wid + 10
	    g.draw(new Line2D.Double(x1, y1, x1 + wid - 20, y1 + wid - 20))
	    g.draw(new Line2D.Double(x1, y1 + wid - 20, x1 + wid - 20, y1))
	  case _ => // draw nothing
	}
      }
    }
  }
}

// --------------------------------------------------------------------

class UI(val board: Board) extends MainFrame {
  private def restrictHeight(s: Component) {
    s.maximumSize = new Dimension(Short.MaxValue, s.preferredSize.height)
  }

  title = "Tic Tac Toe #2"

  val canvas = new Canvas(board)
  val newGameButton = Button("New Game") { newGame() }
  val quitButton = Button("Quit") { sys.exit(0) }
  val buttonLine = new BoxPanel(Orientation.Horizontal) {
    contents += newGameButton
    contents += Swing.HGlue
    contents += quitButton
  }

  // make sure that resizing only changes the TicTacToeDisplay
  restrictHeight(buttonLine)
  
  contents = new BoxPanel(Orientation.Vertical) {
    contents += canvas
    contents += Swing.VStrut(10)
    contents += buttonLine
    border = Swing.EmptyBorder(10, 10, 10, 10)
  }

  def newGame() {
    println("New Game!")
  }
}

object TicTacToeTwo {
  def main(args: Array[String]) {
    val board = new Board
    val ui = new UI(board)
    ui.visible = true
  }
}
