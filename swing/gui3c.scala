import java.awt.Color
import scala.swing._

class UI extends MainFrame {
  title = "GUI Program #3"
  contents = new BoxPanel(Orientation.Vertical) {
    val la = new Label("Look at me!", Swing.EmptyIcon, Alignment.Center)
    la.foreground = Color.BLUE
    contents += la
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += Button("Press me, please") { println("Thank you") }
    contents += Swing.VStrut(5)
    contents += Button("Close") { sys.exit(0) }
    for (e <- contents)
      e.xLayoutAlignment = 0.5
    border = Swing.EmptyBorder(10, 10, 10, 10)
  }
}

object GuiProgramThree {
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
  }
}
