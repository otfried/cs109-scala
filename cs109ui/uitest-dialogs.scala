
import org.otfried.cs109.UI._

def main() {
  setTitle("CS109 UI Dialog Test")

  while (true) {
    showMessage("This is a message")

    val yesno = askYesNo("Do you like this?")
    printf("Answer: %s\n", yesno)

    val name = inputString("What is your name?")
    printf("Name: %s\n", name)
    
    val drink = askChoice("What do you like best", 
				  List("Beer", "Wine", "Makkoli", 
				       "Soju", "Water"))
    printf("Drink: %s\n", drink)

    val choice =askButtons("What do you want to do now?",
				   List("Exit", "More dialogs", "Nothing"))
    choice match {
      case 1 => close()
      case 2 => 
      case 3 => return
    }
  }
}

main()

