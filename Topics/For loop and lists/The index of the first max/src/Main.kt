fun main() {
    val size = readln().toInt()
    var maxVal = 0
    var firstMaxIndex = 0
    for (i in 0 until size) {
        val number = readln().toInt()
        if (number > maxVal) {
            maxVal = number
            firstMaxIndex = i
        }
    }
    println(firstMaxIndex)
}
