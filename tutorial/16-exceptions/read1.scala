var F: scala.io.Source = null
try {
  F = scala.io.Source.fromFile("project.txt")
  for (line <- F.getLines())
    printf("%4d %s\n", line.length, line)
  F.close()
} catch {
  case e: java.io.FileNotFoundException => 
    println("Project file does not exist!")
  case e: java.io.IOException => {
    println("Error reading project file!")
    F.close()
  }
}
