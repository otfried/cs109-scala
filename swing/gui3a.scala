import scala.swing._

class UI extends MainFrame {
  title = "GUI Program #3"
  val box = new BoxPanel(Orientation.Vertical)
  box.contents += new Label("Look at me!")
  box.contents += Button("Press me, please") { println("Thank you") }
  box.contents += Button("Close") { sys.exit(0) }
  contents = box
}

object GuiProgramThree {
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
  }
}
