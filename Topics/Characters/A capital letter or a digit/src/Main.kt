fun main() {
    val a = readln().first()

    println(a.isUpperCase() || (a.isDigit() && a != '0'))
}