import scala.swing._
import scala.swing.event._

class UI extends MainFrame {
  def restrictHeight(s: Component) {
    s.maximumSize = new Dimension(Short.MaxValue, s.preferredSize.height)
  }

  title = "GUI Program #6"
  
  val nameField = new TextField { columns = 32 }
  val likeScala = new CheckBox("I like Scala")
  likeScala.selected = true
  val status1 = new RadioButton("학부생")
  val status2 = new RadioButton("대학원생")
  val status3 = new RadioButton("교수")
  status3.selected = true
  val statusGroup = new ButtonGroup(status1, status2, status3)
  val gender = new ComboBox(List("don't know", "female", "male"))
  val commentField = new TextArea { rows = 8; lineWrap = true; wordWrap = true }
  val pressMe = new ToggleButton("Press me!")
  pressMe.selected = true
  pressMe.tooltip = "This button wants to be pressed"

  restrictHeight(nameField)
  restrictHeight(gender)

  contents = new BoxPanel(Orientation.Vertical) {
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += new Label("My name")
      contents += Swing.HStrut(5)
      contents += nameField
    }
    contents += Swing.VStrut(5)
    contents += likeScala
    contents += Swing.VStrut(5)
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += status1
      contents += Swing.HStrut(10)
      contents += status2
      contents += Swing.HStrut(10)
      contents += status3
    }
    contents += Swing.VStrut(5)
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Gender")
      contents += Swing.HStrut(20)
      contents += gender
    }
    contents += Swing.VStrut(5)
    contents += new Label("Comments")
    contents += Swing.VStrut(3)
    contents += new ScrollPane(commentField)
    contents += Swing.VStrut(5)
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += pressMe
      contents += Swing.HGlue
      contents += Button("Close") { reportAndClose() }
    }
    for (e <- contents)
      e.xLayoutAlignment = 0.0
    border = Swing.EmptyBorder(10, 10, 10, 10)
  }

  listenTo(nameField)
  listenTo(commentField)
  listenTo(gender.selection)
  listenTo(likeScala)
  listenTo(status1)
  listenTo(status2)
  listenTo(status3)
  listenTo(pressMe)

  reactions += {
    case EditDone(`nameField`) => 
      println("Your name is now: " + nameField.text)
    case EditDone(`commentField`) => 
      println("You changed the comments")
    case SelectionChanged(`gender`) =>
      println("Your gender is now: " + gender.selection.item)
    case ButtonClicked(`likeScala`) =>
      if (!likeScala.selected) {
	if (Dialog.showConfirmation(contents.head, 
				    "Are you sure you don't like Scala?")
	    != Dialog.Result.Yes)
	  likeScala.selected = true
      }
    case ButtonClicked(s) =>
      println("Button click on button: '" + s.text + "'")
  }

  def reportAndClose() {
    println("Your name: " + nameField.text)
    println("You like Scala: " + likeScala.selected)
    println("Undergraduate: " + status1.selected)
    println("Graduate: " + status2.selected)
    println("Professor: " + status3.selected)
    println("Gender: " + gender.selection.item + 
	    " (Index: " + gender.selection.index + ")")
    println("Comments: " + commentField.text)
    println("'Press me' is pressed: " + pressMe.selected)
    sys.exit(0)
  }
}

object GuiProgramSix {
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
  }
}
