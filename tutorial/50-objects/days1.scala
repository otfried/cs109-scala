
val monthLength = Array(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

class Date(val year: Int, val month: Int, val day: Int) {
  require(1901 <= year && year <= 2099)
  require(1 <= month && month <= 12)
  require(1 <= day && day <= monthLength(month - 1))
  require(month != 2 || day <= 28 || (year % 4) == 0)
}
