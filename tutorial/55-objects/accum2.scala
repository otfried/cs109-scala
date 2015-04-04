class Accumulator {
  private var current = 0
  def add(n: Int) {
    current += n
  }
  def sum: Int = current
}

var acc1 = new Accumulator
acc1.add(7)
acc1.add(13)
println(acc1.sum)

