fun main() {
    var lastChar = ' '
    var countChar = 1
    val dna = readln()
    var output = ""

    dna.forEach {
        if (lastChar == ' ') {
            lastChar = it
        } else {
            if (it == lastChar) {
                countChar++
            } else {
                output += "$lastChar$countChar"
                lastChar = it
                countChar = 1
            }
        }
    }
    output += "$lastChar$countChar"
    println(output)
}