import scala.swing._

class UI extends MainFrame {
  title = "GUI Program #3"
  contents = new BoxPanel(Orientation.Vertical) {
    contents += new Label("Look at me!")
    contents += Button("Press me, please") { println("Thank you") }
    contents += Button("Close") { sys.exit(0) }
  }
}

object GuiProgramThree {
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
  }
}
