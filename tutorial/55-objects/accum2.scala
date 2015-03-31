class Accumulator {
  private var sum = 0
  def add(n: Int) {
    sum += n
  }
  def value: Int = sum
}

var acc1 = new Accumulator
acc1.add(7)
acc1.add(13)
println(acc1.sum)
println(acc1.value)
