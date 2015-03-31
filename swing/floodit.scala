// --------------------------------------------------------------------
// Flood-It! Game
// --------------------------------------------------------------------

import java.awt.{Graphics2D,Color}
import scala.swing._

// --------------------------------------------------------------------

case class Pos(val x: Int, val y: Int)

class Blocks(val width: Int, val height: Int) {
  private val data = Array.ofDim[Int](width, height)
  resetData()

  // Create initial pattern
  def resetData() = {
    for (x <- 0 until width)
      for (y <- 0 until height)
	data(x)(y) = (math.random * FloodIt.NumColors).toInt
  }

  // Return color of the pixel.
  def get(pos: Pos): Int = data(pos.x)(pos.y)

  // Set color of pixel.
  def set(pos: Pos, color: Int) = { 
    require(0 <= color && color <= FloodIt.NumColors)
    data(pos.x)(pos.y) = color
  }

  // Return array with neighbors of a pixel
  def neighbors(p: Pos): List[Pos] = {
    var nb = List[Pos]()
    val Pos(x, y) = p
    if (x > 0)
      nb = Pos(x-1, y) :: nb
    if (y > 0)
      nb = Pos(x, y-1) :: nb
    if (x + 1 < width) 
      nb = Pos(x+1, y) :: nb
    if (y + 1 < height)
      nb = Pos(x, y+1) :: nb
    nb
  }

  def allFilled(): Boolean = {
    val col = data(0)(0)
    for (x <- 0 until width; y <- 0 until height)
      if (data(x)(y) != col)
	return false
    true
  }

  def flood(start: Pos, color: Int) {
    val A = new scala.collection.mutable.Queue[Pos]

    val oldCol = get(start)
    if (oldCol == color)
      return
    
    set(start, color)
    A += start

    while (!A.isEmpty) {
      val q = A.dequeue()
      val nb = neighbors(q)
      for (r <- nb) {
	if (get(r) == oldCol) {
	  set(r, color)
	  A += r
	}
      }
    }
  }
}

// --------------------------------------------------------------------

class Canvas(val blocks: Blocks) extends Component {

  override def paintComponent(g : Graphics2D) {
    val d = size
    g.setColor(Color.white);
    g.fillRect(0,0, d.width, d.height);
    val rowWid = d.height / blocks.height
    val colWid = d.width / blocks.width
    val wid = rowWid min colWid
    val x0 = (d.width - blocks.width * wid)/2
    val y0 = (d.height - blocks.height * wid)/2
    for (x <- 0 until blocks.width) {
      for (y <- 0 until blocks.height) {
	g.setColor(FloodIt.colorFor(blocks.get(Pos(x, y))))
	g.fillRect(x0 + x * wid, y0 + y * wid, wid, wid)
      }
    }
  }
}

// --------------------------------------------------------------------

class FloodUI extends MainFrame {
  val turns = new Label("Turns: 0")
  var counter = 0

  val blocks = new Blocks(FloodIt.size, FloodIt.size)
  val canvas = new Canvas(blocks)

  title = FloodIt.Title
  preferredSize = new Dimension(640, 480)
  contents = new BorderPanel {
    border = Swing.MatteBorder(8, 8, 8, 8, Color.white)
    add(canvas, BorderPanel.Position.Center)
    val buttons = new BoxPanel(Orientation.Horizontal) {
      border = Swing.EmptyBorder(8, 0, 0, 0)
      background = Color.white
      contents += Button("New Game") { newGame() }
      contents += Swing.HGlue
      for (i <- 0 until FloodIt.NumColors) {
	val b = Button(" ") { colorClick(i) }
	b.background = FloodIt.colorFor(i)
	contents += b
      }
      contents += Swing.HGlue
      contents += turns
      contents += Swing.HGlue
      contents += Button("Quit") { System.exit(0) }
    }
    add(buttons, BorderPanel.Position.South)
  }

  private def setTurns() { turns.text = "Turns: %d".format(counter) }

  private def restartGame() {
    counter = 0
    setTurns()
    blocks.resetData()
    canvas.repaint()
  }

  def newGame() {
    if (Dialog.showConfirmation(canvas, "Do you want to start a new game",
				title=FloodIt.Title) == Dialog.Result.Ok)
      restartGame()
  }

  def colorClick(color: Int) {
    if (color == blocks.get(Pos(0,0))) {
      Dialog.showMessage(canvas, "You must be kidding!", title=FloodIt.Title)
    } else {
      counter += 1
      setTurns()
      blocks.flood(Pos(0,0), color)
      canvas.repaint()
      if (blocks.allFilled()) {
	val s = "Congratulations, you made it in %d turns".format(counter)
	Dialog.showMessage(canvas, s, title=FloodIt.Title)
	restartGame()
      }
    }
  }
}

object FloodIt {
  var size = 19
  val NumColors = 6
  val Title = "Flood It!"

  def colorFor(n: Int): Color = {
    n match {
      case 0 => Color.red
      case 1 => Color.green
      case 2 => Color.blue
      case 3 => Color.yellow
      case 4 => Color.pink
      case 5 => Color.cyan
      case _ => Color.white // should not happen
    }
  }
  
  def main(args: Array[String]) {
    if (args.length == 1)
      size = args(0).toInt

    val ui = new FloodUI
    ui.visible = true
  }
}

// --------------------------------------------------------------------
