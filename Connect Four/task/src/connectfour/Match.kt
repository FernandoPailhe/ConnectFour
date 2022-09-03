package connectfour

const val DEFAULT_ROWS = 6
const val DEFAULT_COLUMNS = 7
const val IS_FULL_1 = "Column "
const val IS_FULL_2 = " is full"
const val ZERO = 0
const val ONE = 1
const val TWO = 2
const val WINNING_CONDITION = 4

class Match(
    val player1: Player,
    val player2: Player,
    val board: Board = Board(),
    val gamesToPlay: Int = ONE,
) {
    var gamesPlayed = ONE
    var isPlaying: Boolean = true
    var gameState: String = VALID_PLAY
    var currentPlay = ZERO
    private var turn: Int = ONE
    private var playCount: Int = ZERO

    fun play(): String {
        var i = this.board.rows
        val player = if (this.turn == 1) player1 else player2
        if (this.board.plays[ZERO][this.currentPlay] != ' ') {
            return "$IS_FULL_1${this.currentPlay + 1}$IS_FULL_2"
        }
        for (row in this.board.plays.reversed()) {
            i--
            if (row[this.currentPlay] == ' ') {
                row[this.currentPlay] = player.disc
                gameState = checkWinner(player)
                return gameState
            }
        }
        return VALID_PLAY
    }

    private fun checkWinner(player: Player): String {
        if (checkHorizontal(player.disc)) {
            if (this.turn == ONE) this.player1.points += 2 else this.player2.points += 2
            return player.name
        }
        if (checkVertical(player.disc)) {
            if (this.turn == ONE) this.player1.points += 2 else this.player2.points += 2
            return player.name
        }
        if (checkDiagonal(player.disc)) {
            if (this.turn == ONE) this.player1.points += 2 else this.player2.points += 2
            return player.name
        }
        this.playCount++
        if (this.playCount == board.rows * board.columns) {
            this.player1.points++
            this.player2.points++
            return DRAW
        }
        changeTurn()
        return VALID_PLAY
    }

    private fun changeTurn() {
        if (this.turn == ONE) {
            this.turn = TWO
        } else {
            this.turn = ONE
        }
    }

    private fun checkHorizontal(disc: Char): Boolean {
        var lastColumn = " "
        for (row in this.board.plays) {
            for (column in row) {
                if (column == lastColumn[0] && column == disc) {
                    lastColumn += column
                    if (lastColumn.length == WINNING_CONDITION) {
                        return true
                    }
                } else {
                    lastColumn = column.toString()
                }
            }
        }
        return false
    }

    private fun checkVertical(disc: Char): Boolean {
        var lastRow = " "
        for (column in 0 until board.columns) {
            for (row in 0 until board.rows) {
                if (this.board.plays[row][column] == lastRow[ZERO] && this.board.plays[row][column] == disc) {
                    lastRow += row
                    if (lastRow.length == WINNING_CONDITION) {
                        return true
                    }
                } else {
                    lastRow = this.board.plays[row][column].toString()
                }
            }
        }
        return false
    }

    private fun checkDiagonal(disc: Char): Boolean {
        for (row in 0 until board.rows) {
            for (column in 0 until board.columns) {
                if (row - 3 >= 0 && column + 3 < board.columns) {
                    if (this.board.plays[row][column] == disc) {
                        if (this.board.plays[row][column] == this.board.plays[row - 1][column + 1] && this.board.plays[row][column] == this.board.plays[row - 2][column + 2] && this.board.plays[row][column] == this.board.plays[row - 3][column + 3]) {
                            return true
                        }
                    }
                }
                if (row + 3 < board.rows && column + 3 < board.columns) {
                    if (this.board.plays[row][column] == disc) {
                        if (this.board.plays[row][column] == this.board.plays[row + 1][column + 1] && this.board.plays[row][column] == this.board.plays[row + 2][column + 2] && this.board.plays[row][column] == this.board.plays[row + 3][column + 3]) {
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    fun setNewGame(): Boolean {
        return if (gamesPlayed < gamesToPlay) {
            this.board.clear()
            this.playCount = 0
            this.turn = if (gamesPlayed / 2 == 1) 1 else 2
            this.gamesPlayed++
            this.gameState = VALID_PLAY
            true
        } else {
            false
        }
    }

    fun getTurn() = if (this.turn == ONE) player1.name else player2.name

}




