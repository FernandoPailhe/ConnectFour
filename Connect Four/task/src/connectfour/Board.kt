package connectfour

data class Board(
    val rows: Int = DEFAULT_ROWS,
    val columns: Int = DEFAULT_COLUMNS,
    var plays: MutableList<MutableList<Char>> = MutableList(rows) { MutableList(columns) { ' ' } },
)

fun Board.clear() {
    plays = MutableList(rows) { MutableList(columns) { ' ' } }
}
