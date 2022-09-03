fun main() {
    val a = readln().first()
    val b = readln().first()

    if (a.isLetter() && b.isLetter()) {
        println(a.toLowerCase() == b.toLowerCase())
    } else {
        println(false)
    }
}