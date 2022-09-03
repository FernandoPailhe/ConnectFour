fun main() {
    val (s, n) = readln().split(" ")
    if (n.toInt() < s.length) {
        println(s.substring(n.toInt()) + s.substring(0, n.toInt()))
    } else {
        println(s)
    }
}