// You can experiment here, it won’t be checked

fun main(args: Array<String>) {
    val a = readln().first()
    val b = readln().first()

    if (a.isDigit() && b.isDigit()) {
        a.lowercaseChar()
        b.lowercaseChar()
        println(a == b)
    } else {
        println(false)
    }


}
