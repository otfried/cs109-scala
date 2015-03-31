
def main() {
  CS109UI.setTitle("CS109 UI Test #1")

  while (true) {
    CS109UI.showMessage("This is a message")

    val yesno = CS109UI.askYesNo("Do you like this?")
    printf("Answer: %s\n", yesno)

    val name = CS109UI.inputString("What is your name?")
    printf("Name: %s\n", name)
    
    val drink = CS109UI.askChoice("What do you like best", 
				  List("Beer", "Wine", "Makkoli", 
				       "Soju", "Water"))
    printf("Drink: %s\n", drink)

    val choice =CS109UI.askButtons("What do you want to do now?",
				   List("Exit", "More dialogs", "Nothing"))
    choice match {
      case 1 => CS109UI.close()
      case 2 => 
      case 3 => return
    }
  }
}

main()

