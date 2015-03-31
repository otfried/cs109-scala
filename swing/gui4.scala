import java.awt.Color
import scala.swing._

class UI extends MainFrame {
  val la = new Label("Look at me!")

  la.foreground = Color.BLUE
  title = "GUI Program #4"

  contents = new BoxPanel(Orientation.Vertical) {
    contents += la
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += Button("Press me, please") { pressMe() }
    contents += Swing.VStrut(5)
    contents += Button("Change text") { changeText() }
    contents += Swing.VStrut(5)
    contents += Button("Close") { closeMe() }
    border = Swing.EmptyBorder(10, 10, 10, 10)
  }

  def pressMe() {
    Dialog.showMessage(contents.head, "Thank you!", title="You pressed me")
  }

  def changeText() {
    val r = Dialog.showInput(contents.head, "New label text", initial=la.text)
    r match {
      case Some(s) => la.text = s
      case None => 
    }
  }

  def closeMe() {
    val res = Dialog.showConfirmation(contents.head, 
				      "Do you really want to quit?", 
				      optionType=Dialog.Options.YesNo,
				      title=title)
    if (res == Dialog.Result.Ok)
      sys.exit(0)
  }
}

object GuiProgramFour {
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
  }
}
