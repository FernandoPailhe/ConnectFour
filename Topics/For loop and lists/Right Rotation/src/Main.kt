fun main() {
    val size = readln().toInt()
    val list = mutableListOf<Int>()
    for (element in 0 until size) {
        list += readln().toInt()
    }
    val rotationStep = readln().toInt() % size
    val rotatedList = mutableListOf<Int>()
    for (index in list.indices) {
        var rotatedIndex = index - rotationStep
        if (rotatedIndex < 0) {
            rotatedIndex += list.size
        }
        rotatedList += list[rotatedIndex]
    }

    println(rotatedList.joinToString(" "))
}