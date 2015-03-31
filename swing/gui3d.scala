import scala.swing._

class UI extends MainFrame {
  title = "GUI Program #3"
  contents = new BoxPanel(Orientation.Vertical) {
    val la = new Label("Look at me!")
    la.foreground = java.awt.Color.BLUE
    la.xLayoutAlignment = 0.5
    contents += la
    contents += Swing.VStrut(10)
    contents += Swing.Glue
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += Swing.HGlue
      contents += Button("Press me, please") { println("Thank you") }
      contents += Swing.HStrut(5)
      contents += Button("Close") { sys.exit(0) }
      for (e <- contents)
	e.yLayoutAlignment = 1.0
    }
    border = Swing.EmptyBorder(10, 10, 10, 10)
  }
}

object GuiProgramThree {
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
  }
}
