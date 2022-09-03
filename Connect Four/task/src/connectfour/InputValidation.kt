package connectfour

class InputValidation {

    fun setBoardInterface(): Board {
        while (true) {
            println(DIMENSIONS_BOARD_REQUIREMENT)
            val input = readln().filterNot { it.isWhitespace() }.lowercase()
            if (input == "") {
                return Board()
            }
            if (isValidBoardInput(input)) {
                val boardDimension = extractBoardDimensions(input)
                val boardValidation = isValidRange(boardDimension.first, boardDimension.second)
                if (boardValidation.first && boardValidation.second) {
                    return Board(boardDimension.first, boardDimension.second)
                } else {
                    if (boardValidation.first) {
                        println(OUT_OF_BOUNDS_COLUMNS)
                    } else {
                        println(OUT_OF_BOUNDS_ROWS)
                    }
                    continue
                }
            } else {
                println(INVALID_INPUT)
                continue
            }
        }
    }

    private fun isValidBoardInput(input: String) = BOARD_REGEX.matches(input)

    private fun extractBoardDimensions(input: String): Pair<Int, Int> {
        val dimensions = input.split(SPLIT_REGEX)
        return Pair(dimensions[0].toInt(), dimensions[1].toInt())
    }

    private fun isValidRange(rows: Int, columns: Int): Pair<Boolean, Boolean> {
        return if (rows in BOARD_RANGE) {
            Pair(true, columns in BOARD_RANGE)
        } else {
            Pair(false, false)
        }
    }

    fun setGamesInterface(): Int {
        while (true) {
            println(GAMES_QUANTITY_REQUIREMENT)
            val input = readln().filterNot { it.isWhitespace() }.lowercase()
            return if (input == "") {
                DEFAULT_GAMES_QUANTITY
            } else if (isValidGamesInput(input)) {
                input.toInt()
            } else {
                println(INVALID_INPUT)
                continue
            }
        }
    }

    private fun isValidGamesInput(input: String): Boolean {
        val intInput = input.toIntOrNull()
        return if (intInput != null) {
            intInput > 0
        } else {
            false
        }
    }

    private fun printTurn(playerName: String) = println("$playerName$PLAYER_TURNS_SUFFIX")

    fun setPlayInterface(currentMatch: Match) {
        while (currentMatch.isPlaying) {
            printTurn(currentMatch.getTurn())
            val playerInput = readln()
            if (playerInput.toIntOrNull() != null) {
                when (val play = playerInput.toInt()) {
                    in 1..currentMatch.board.columns -> {
                        currentMatch.currentPlay = play - 1
                        break
                    }
                    else -> {
                        println("$COLUMN_OUT_OF_RANGE${currentMatch.board.columns})")
                        continue
                    }
                }
            } else {
                if (playerInput == END_GAME) {
                    currentMatch.isPlaying = false
                    println(GAME_OVER)
                } else {
                    println(INCORRECT_COLUMN_NUMBER)
                    continue
                }
            }
        }
    }

}