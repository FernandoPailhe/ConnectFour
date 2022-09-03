const val PASSWORD = "password : "
const val PASS = "pass"
const val NOT_FOUND = "not found"

fun main() {
    var password = ""
    val html = readln().split("?")
    val output = html[1].split("&").joinToString("\n") {
        val pairKeyValue = it.split("=")
        if (pairKeyValue[0] == PASS) {
            password = pairKeyValue[1]
        }
        if (pairKeyValue[1].isEmpty()) {
            "${pairKeyValue[0]} : $NOT_FOUND"
        } else {
            "${pairKeyValue[0]} : ${pairKeyValue[1]}"
        }
    }
    println(
        if (password != "") output + "\n" + PASSWORD + password else output
    )
}