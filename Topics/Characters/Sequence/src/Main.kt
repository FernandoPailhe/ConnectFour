fun main() {
    val c1 = readln().first()
    val c2 = readln().first()
    val c3 = readln().first()

    println(c1.code - c2.code == -1 && c2.code - c3.code == -1)
}