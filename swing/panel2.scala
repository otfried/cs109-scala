import scala.swing._

class UI extends MainFrame {
  title = "Border Panel"
  contents = new BorderPanel {
    add(new Button("North"), BorderPanel.Position.North)
    add(new Button("Center"), BorderPanel.Position.Center)
    add(new Button("East"), BorderPanel.Position.East)
    add(new Button("West"), BorderPanel.Position.West)
    add(Button("South: Close") { sys.exit(0) }, BorderPanel.Position.South)
  }
}

object PanelTwo {
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
  }
}
