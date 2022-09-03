const val MAX = 3
fun main() {
    val str = readln()
    val vowelsList = listOf('a', 'e', 'i', 'o', 'u', 'y')
    var repetition = 0
    var change = 0
    var lastVowel = false

    for (char in str) {
        if (vowelsList.contains(char)) {
            if (lastVowel) {
                repetition++
            } else {
                repetition = 1
            }
            if (repetition == MAX) {
                change++
                repetition = 1
            }
            lastVowel = true
        } else {
            if (!lastVowel) {
                repetition++
            } else {
                repetition = 1
            }
            if (repetition == MAX) {
                change++
                repetition = 1
            }
            lastVowel = false
        }
    }

    println(change)
}