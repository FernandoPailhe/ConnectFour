package connectfour

const val GAME_TITLE = "Connect Four"
const val PLAYER_HINT = "player's name:"
const val PLAYER_1_HINT = "First "
const val PLAYER_2_HINT = "Second "
const val DIMENSIONS_BOARD_REQUIREMENT = "Set the board dimensions (Rows x Columns)\nPress Enter for default (6 x 7)"
const val GAMES_QUANTITY_REQUIREMENT = "Do you want to play single or multiple games?\n" +
        "For a single game, input 1 or press Enter\n" +
        "Input a number of games:"
const val DEFAULT_GAMES_QUANTITY = 1
const val VERSUS_TEMPLATE = " VS "
const val BOARD_SUFFIX: String = " board"
const val BOARD_SPLIT = " X "
const val SINGLE_GAME = "Single game"
const val TOTAL_GAMES_PREFIX = "Total "
const val TOTAL_GAMES_SUFFIX = " games"
const val SCORE = "Score"
const val GAME_1 = "Game #1"
const val GAME_PREFIX = "Game #"
const val OUT_OF_BOUNDS_ROWS = "Board rows should be from 5 to 9"
const val OUT_OF_BOUNDS_COLUMNS = "Board columns should be from 5 to 9"
const val INVALID_INPUT = "Invalid input"
const val COLUMN_OUT_OF_RANGE = "The column number is out of range (1 - "
const val INCORRECT_COLUMN_NUMBER = "Incorrect column number"
const val END_GAME = "end"
const val GAME_OVER = "Game over!"
const val DRAW = "It is a draw"
const val WIN_1 = "Player "
const val WIN_2 = " won"
const val PLAYER_TURNS_SUFFIX = "'s turn:"
const val PLAYER_1_DISC = 'o'
const val PLAYER_2_DISC = '*'
const val VALID_PLAY = "isValidPlay"
val BOARD_RANGE = 5..9
val BOARD_REGEX = Regex("\\d?\\dx\\d?\\d")
val SPLIT_REGEX = Regex("x")

class UserInterface {

    private val inputValidation = InputValidation()

    fun startGame() {
        println(GAME_TITLE)
        println("$PLAYER_1_HINT$PLAYER_HINT")
        val player1 = readln()
        println("$PLAYER_2_HINT$PLAYER_HINT")
        val player2 = readln()
        val board = inputValidation.setBoardInterface()
        val gamesToPlay = inputValidation.setGamesInterface()
        val currentMatch = Match(Player(player1, PLAYER_1_DISC), Player(player2, PLAYER_2_DISC), board, gamesToPlay)
        printValidGame(currentMatch.player1.name, currentMatch.player2.name, currentMatch.board, gamesToPlay)
        printBoard(board)
        nextTurn(currentMatch)
    }

    private fun nextTurn(currentMatch: Match) {
        while (currentMatch.gameState == VALID_PLAY) {
            inputValidation.setPlayInterface(currentMatch)
            if (!currentMatch.isPlaying) break
            val resultOfPlay = currentMatch.play()
            printBoard(currentMatch.board)
            when (resultOfPlay) {
                VALID_PLAY -> continue
                DRAW -> printDrawMessage(currentMatch)
                currentMatch.player1.name -> printWinnerMessage(currentMatch, currentMatch.player1.name)
                currentMatch.player2.name -> printWinnerMessage(currentMatch, currentMatch.player2.name)
                else -> println(resultOfPlay)
            }
        }
    }

    private fun printValidGame(player1: String, player2: String, board: Board, games: Int) {
        println("$player1$VERSUS_TEMPLATE$player2\n" +
                "${board.rows}$BOARD_SPLIT${board.columns}$BOARD_SUFFIX")
        if (games == 1) {
            println(SINGLE_GAME)
        } else {
            println("$TOTAL_GAMES_PREFIX$games$TOTAL_GAMES_SUFFIX")
            println(GAME_1)
        }
    }

    private fun printBoard(board: Board) {
        repeat(board.columns) {
            print(" ${it + 1}")
            if (it + 1 == board.columns) {
                println()
            }
        }
        repeat(board.rows) {
            println(board.plays[it].joinToString("\u2551", "\u2551", "\u2551"))
        }
        repeat(board.columns + 1) {
            when (it) {
                0 -> {
                    print("\u255a")
                }
                board.columns -> {
                    print("\u2550\u255d")
                    println()
                }
                else -> {
                    print("\u2550\u2569")
                }
            }
        }
    }

    private fun printDrawMessage(currentMatch: Match) {
        println(DRAW)
        if (currentMatch.setNewGame()) {
            printNewGame(currentMatch)
        } else {
            if (currentMatch.gamesToPlay > ONE) {
                println(SCORE)
                println("${currentMatch.player1.name}: ${currentMatch.player1.points} ${currentMatch.player2.name}: ${currentMatch.player2.points}")
            }
            println(GAME_OVER)
        }
    }

    private fun printWinnerMessage(currentMatch: Match, winner: String) {
        println("$WIN_1$winner$WIN_2")
        if (currentMatch.setNewGame()) {
            printNewGame(currentMatch)
        } else {
            if (currentMatch.gamesToPlay > ONE) {
                println(SCORE)
                println("${currentMatch.player1.name}: ${currentMatch.player1.points} ${currentMatch.player2.name}: ${currentMatch.player2.points}")
            }
            println(GAME_OVER)
        }
    }

    private fun printNewGame(currentMatch: Match) {
        println(SCORE)
        println("${currentMatch.player1.name}: ${currentMatch.player1.points} ${currentMatch.player2.name}: ${currentMatch.player2.points}")
        println("${GAME_PREFIX}${currentMatch.gamesPlayed}")
        printBoard(currentMatch.board)
        nextTurn(currentMatch)
    }

}