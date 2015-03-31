import scala.swing._

class UI extends MainFrame {
  title = "Grid Panel"
  contents = new GridPanel(3, 2) {
    contents += new Label("A Label")
    contents += new Button("A Button")
    contents += new Button("Another Button")
    contents += new Button("Button Three")
    contents += new CheckBox("Check me!")
    contents += Button("Close") { sys.exit(0) }
  }
}

object PanelThree {
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
  }
}
