import scala.swing._

class UI extends MainFrame {
  title = "GridBagPanel"
  contents = new GridBagPanel {
    def constraints(x: Int, y: Int, 
		    gridwidth: Int = 1, gridheight: Int = 1,
		    weightx: Double = 0.0, weighty: Double = 0.0,
		    fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.None) 
    : Constraints = {
      val c = new Constraints
      c.gridx = x
      c.gridy = y
      c.gridwidth = gridwidth
      c.gridheight = gridheight
      c.weightx = weightx
      c.weighty = weighty
      c.fill = fill
      c
    }

    add(new Label("Label @ (0,0)") {border=Swing.EtchedBorder(Swing.Lowered) },
	constraints(0, 0, gridheight=2, fill=GridBagPanel.Fill.Both))
    add(new ToggleButton("Button @ (2,0)"),
	constraints(2, 0))
    add(new Button("Button @ (2,1)"), 
	constraints(2, 1))
    add(new Button("Button @ (2,2)"), 
	constraints(2, 2))
    add(new CheckBox("Check me!"), 
	constraints(0, 2))
    add(new TextField { columns = 32 }, 
	constraints(1, 0, weightx=1.0, fill=GridBagPanel.Fill.Horizontal))
    add(new ScrollPane(new TextArea),
	constraints(1, 1, gridheight=3, weighty = 1.0, 
		    fill=GridBagPanel.Fill.Both))
    add(Button("Close") { sys.exit(0) }, 
	constraints(0, 4, gridwidth=3, fill=GridBagPanel.Fill.Horizontal))
  }
}

object PanelFour {
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
  }
}
