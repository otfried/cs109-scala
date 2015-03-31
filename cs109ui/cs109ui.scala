/*
 * How to make a jar file:
 *  jar cvf cs109ui.jar *.class
 */

import scala.swing._
import scala.swing.event._
import java.awt.{Color,Graphics2D}
import java.awt.image.BufferedImage

private object Timer {
  def apply(interval: Int, repeats: Boolean = false)(op: => Unit) {
    val timeOut = new javax.swing.AbstractAction() {
      def actionPerformed(e : java.awt.event.ActionEvent) = op
    }
    val t = new javax.swing.Timer(interval, timeOut)
    t.setRepeats(repeats)
    t.start()
  }
}

private class Canvas extends Component {
  private var image: BufferedImage = null

  focusable = true

  listenTo(mouse.clicks)
  listenTo(keys)

  reactions += {
    case MouseClicked(_, p, _, _, _) => sendEvent(CS109UI.Mouse(p.x, p.y))
    case KeyTyped(_, c, _, _) => sendEvent(CS109UI.Key(c))
  }

  def sendEvent(r: CS109UI.Result) {
    CS109UI.queue synchronized {
      CS109UI.queue.add(r)
      CS109UI.queue.notify()
    }
  }

  override def paintComponent(g : Graphics2D) {
    if (image == null) {
      val d = size
      g.setColor(Color.WHITE)
      g.fillRect(0,0, d.width, d.height)
    } else 
      g.drawImage(image, null, 0, 0)
  }

  def setImage(im: BufferedImage) {
    if (image == null || image.getWidth != im.getWidth 
	|| image.getHeight != im.getHeight) {
      image = new BufferedImage(im.getWidth, im.getHeight, 
				BufferedImage.TYPE_INT_RGB)
    }
    image.setData(im.getData())
    preferredSize = new Dimension(im.getWidth, im. getHeight)
    repaint()
  }
}

private class UI(canvas: Canvas) extends MainFrame {
  title = "CS109 UI"
  preferredSize = new Dimension(640, 480)
  contents = new BoxPanel(Orientation.Vertical) {
    contents += canvas
    border = Swing.EmptyBorder(10, 10, 10, 10)
  }
}

object CS109UI {
  trait Result
  case class Key(ch: Char) extends Result
  case class Mouse(x: Int, y: Int) extends Result
  
  val queue = new java.util.concurrent.ConcurrentLinkedQueue[Result]

  private val canvas = new Canvas
  private val ui = new UI(canvas)
  ui.resizable = false
  println("CS109UI version 2015/03/31")

  def setTitle(title: String) {
    Swing.onEDT { ui.title = title }
  }

  def show(image: BufferedImage) {
    canvas.setImage(image)
    if (ui.visible) {
      val insets = ui.peer.getInsets()
      ui.size = new Dimension(image.getWidth + 20 + insets.left + insets.right, 
			      image.getHeight + 20 + insets.top + insets.bottom)
    } else
      ui.visible = true
  }

  def waitEvent(): Result = {
    queue synchronized {
      while (queue.isEmpty) {
	queue.wait()
      }
      queue.remove() 
    }
  }

  def waitKey(): Char = {
    while (true) {
      val r = waitEvent()
      r match {
	case Key(ch) => return ch
	case Mouse(_, _) => // ignore it
      }
    }
    ' '
  }

  def waitMouse(): (Int, Int) = {
    while (true) {
      val r = waitEvent()
      r match {
	case Key(_) => // ignore it
	case Mouse(x, y) => return (x, y)
      }
    }
    (0, 0)
  }

  def close() {
    ui.close()
    sys.exit(0)
  }
    
  // ------------------------------------------------------------
  // Dialogs
  // ------------------------------------------------------------

  def showMessage(msg: String) {
    Dialog.showMessage(canvas, msg, title=ui.title)
  }

  def askYesNo(question: String): Boolean = {
    Dialog.showConfirmation(canvas, question, title=ui.title) match {
      case Dialog.Result.Yes => true
      case Dialog.Result.No => false
      case _ => false
    }
  }

  // up to three buttons are okay
  def askButtons(question: String, buttons: List[String]): Int =  {
    val result = Dialog.showOptions(canvas, message=question, title=ui.title,
				    entries = buttons, initial = 1)
    result match {
      case Dialog.Result.Yes => 1
      case Dialog.Result.No => 2
      case Dialog.Result.Cancel => 3
      case _ => 0
    }
  }

  def askChoice(msg: String, choices: List[String]): String = {
    val result = Dialog.showInput(canvas, msg, title=ui.title,
				  Dialog.Message.Plain, 
				  Swing.EmptyIcon,
				  choices, null)
    result match {
      case Some(s) => s
      case None => ""
    }
  }

  def inputString(msg: String): String = {
    val result = Dialog.showInput(canvas, msg, title=ui.title,
				  Dialog.Message.Plain, 
				  Swing.EmptyIcon,
				  Nil, "")
    result match {
      case Some(s) => s
      case None => ""
    }
  }
}
