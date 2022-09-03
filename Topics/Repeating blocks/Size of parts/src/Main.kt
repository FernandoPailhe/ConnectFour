fun main() {
    val n = readln().toInt()

    var perfect = 0
    var larger = 0
    var rejection = 0
    
    repeat(n) {
        val input = readln().toInt()
        if (input == 0) {
            perfect ++
        } else if (input == 1) {
            larger ++
        } else {
            rejection ++
        }
    }
    println("$perfect $larger $rejection")
}
