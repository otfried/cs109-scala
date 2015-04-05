import java.awt.Font
import scala.swing._
import scala.swing.event._

class Solver {
  private val allWords = scala.io.Source.fromFile("words.txt").getLines().toSet
  private val scrabble = 
    (scala.io.Source.fromFile("scrabble.txt").getLines().toSet
     map ((s: String) => s.toLowerCase))
    
  private def matches(pattern: String, word: String): Boolean = {
    if (word.length != pattern.length)
      return false
    for (i <- 0 until word.length) {
      val p = pattern(i)
      if (p != '?' && p != word(i))
	return false
    }
    true
  }

  def findWords(pattern: String, dictNo: Int): List[String] = {
    var w = List[String]()
    val words = if (dictNo == 1) allWords else scrabble
    for (e <- words) {
      if (matches(pattern, e))
	w = e :: w
    }
    w
  }
}

class UI(val solver: Solver) extends MainFrame {
  private def restrictHeight(s: Component) {
    s.maximumSize = new Dimension(Short.MaxValue, s.preferredSize.height)
  }

  title = "Crossword Puzzle Helper"
  val searchField = new TextField { columns = 32 }
  val searchButton = new Button("Search")
  val searchLine = new BoxPanel(Orientation.Horizontal) {
    contents += searchField
    contents += Swing.HStrut(20)
    contents += searchButton
  }
  val resultField = new TextArea { 
    rows = 10
    lineWrap = true
    wordWrap = true
    editable = false
  }
  val dict = new ComboBox(List("Scrabble words", "All words"))
  val largerFont = Button("+") { fontChange(+1) }
  val smallerFont = Button("-") { fontChange(-1) }
  var fontSize = 12
  fontChange(0)
  
  // make sure that resizing only changes the resultField:
  restrictHeight(searchLine)
  
  contents = new BoxPanel(Orientation.Vertical) {
    contents += searchLine
    contents += Swing.VStrut(10)
    contents += new ScrollPane(resultField)
    contents += Swing.VStrut(10)
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += dict
      contents += Swing.HStrut(10)
      contents += largerFont
      contents += Swing.HStrut(10)
      contents += smallerFont
    }      
    border = Swing.EmptyBorder(10, 10, 10, 10)
  }

  listenTo(searchField)
  listenTo(searchButton)
  reactions += {
    case EditDone(`searchField`) => searchNow()
    case ButtonClicked(`searchButton`) => searchNow()
  }
  
  def fontChange(d: Int) {
    fontSize += d
    resultField.font = new Font(null, Font.PLAIN, fontSize)
  }

  def searchNow() {
    val pattern = searchField.text.toLowerCase
    val words = solver.findWords(pattern, dict.selection.index)
    if (words.length == 0) {
      resultField.text = "\n\nSorry, no words found."
    } else {
      resultField.text = words.sorted mkString "\n"
      resultField.caret.position = 0
    }
  }
}

object Crossword {
  def main(args: Array[String]) {
    val solver = new Solver
    val ui = new UI(solver)
    ui.visible = true
  }
}

