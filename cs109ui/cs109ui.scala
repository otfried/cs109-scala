/*
 * How to make a jar file:
 *  jar cvf cs109ui.jar org
 */

package org.otfried.cs109

import scala.swing._
import scala.swing.event._
import java.awt.{Color,Graphics2D}
import java.awt.image.BufferedImage

// --------------------------------------------------------------------

// --------------------------------------------------------------------

private class Canvas extends Component {
  private var image: BufferedImage = null

  focusable = true
  preferredSize = new Dimension(480, 320)
  
  listenTo(mouse.clicks)
  listenTo(keys)

  reactions += {
    case MouseClicked(_, p, _, _, _) => sendEvent(UI.Mouse(p.x, p.y))
    case KeyTyped(_, c, _, _) => sendEvent(UI.Key(c))
  }

  def sendEvent(r: UI.Result) {
    UI.timer.stop()   // key or mouse event cancels timeout
    UI.queue synchronized {
      UI.queue.add(r)
      UI.queue.notify()
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

// --------------------------------------------------------------------

private class UI(canvas: Canvas) extends MainFrame {
  title = "CS109 UI"
  resizable = false

  contents = new BoxPanel(Orientation.Vertical) {
    contents += canvas
    border = Swing.EmptyBorder(10, 10, 10, 10)
  }
}

// --------------------------------------------------------------------

object UI {
  trait Result
  case class Key(ch: Char) extends Result
  case class Mouse(x: Int, y: Int) extends Result
  object TimeOut extends Result

  val timeOutChar = '\u0000'

  private val timeOutOp = new javax.swing.AbstractAction() {
    def actionPerformed(e : java.awt.event.ActionEvent) = 
      queue synchronized {
	queue.add(TimeOut)
	queue.notify()
      }
  }

  protected[cs109] val queue = 
    new java.util.concurrent.ConcurrentLinkedQueue[Result]

  protected[cs109] val timer = 
    new javax.swing.Timer(1000, timeOutOp)
  
  private val canvas = new Canvas
  private val ui = new UI(canvas)
  println("CS109 UI version 2015/03/31")

  def setTitle(title: String) {
    Swing.onEDTWait { ui.title = title }
  }

  def show(image: BufferedImage) {
    Swing.onEDT {
      canvas.setImage(image)
      val insets = ui.peer.getInsets()
      ui.size = 
	new Dimension(image.getWidth + 20 + insets.left + insets.right, 
		      image.getHeight + 20 + insets.top + insets.bottom)
      ui.visible = true
    }
  }

  def setTimeOut(ms: Int) {
    timer.setRepeats(false)
    timer.setInitialDelay(ms)
    timer.restart()
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
        case TimeOut => return timeOutChar
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
        case TimeOut => return (-1, -1)
      }
    }
    (0, 0)
  }

  def close() {
    Swing.onEDTWait {
      ui.close()
    }
    sys.exit(0)
  }
    
  // ------------------------------------------------------------
  // Dialogs
  // ------------------------------------------------------------

  def showMessage(msg: String) {
    Swing.onEDTWait {
      ui.visible = true
      Dialog.showMessage(canvas, msg, title=ui.title)
    }
  }

  def askYesNo(question: String): Boolean = {
    var result: Boolean = false
    Swing.onEDTWait {
      ui.visible = true
      Dialog.showConfirmation(canvas, question, title=ui.title) match {
	case Dialog.Result.Yes => result = true
	case _ =>
      }
    }
    result
  }

  def inputString(msg: String): String = {
    var result: String = ""
    Swing.onEDTWait {
      ui.visible = true
      val r = Dialog.showInput(canvas, msg, title=ui.title,
			       Dialog.Message.Plain, 
			       Swing.EmptyIcon, Nil, "")
      r match {
	case Some(s) => result = s
	case None =>
      }
    }
    result
  }

  // up to three buttons are okay
  def askButtons(question: String, buttons: List[String]): Int =  {
    var result: Int = 0
    Swing.onEDTWait {
      ui.visible = true
      val r = Dialog.showOptions(canvas, message=question, title=ui.title,
				 entries = buttons, initial = 1)
      r match {
	case Dialog.Result.Yes => result = 1
	case Dialog.Result.No => result = 2
	case Dialog.Result.Cancel => result = 3
	case _ => 
      }
    }
    result
  }
    
  def askChoice(msg: String, choices: List[String]): String = {
    var result: String = ""
    Swing.onEDTWait {
      ui.visible = true
      val r = Dialog.showInput(canvas, msg, title=ui.title,
			       Dialog.Message.Plain, 
			       Swing.EmptyIcon, choices, null)
      r match {
	case Some(s) => result = s
	case None =>
      }
    }
    result
  }
}

// --------------------------------------------------------------------
