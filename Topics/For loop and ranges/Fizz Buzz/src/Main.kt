fun main() {
    val a = readln().toInt()
    val b = readln().toInt()

    for (i in a..b) {
        var str = ""
        if (i % 3 == 0) str = "Fizz"
        if (i % 5 == 0) str += "Buzz"
        if (str == "") str = i.toString()
        println(str)
    }
}
