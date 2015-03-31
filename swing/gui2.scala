import scala.swing._

class UI extends MainFrame {
  title = "GUI Program #2"
  preferredSize = new Dimension(320, 240)
  contents = Button("Press me, please") { println("Thank you") }
}

object GuiProgramTwo {
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
  }
}
