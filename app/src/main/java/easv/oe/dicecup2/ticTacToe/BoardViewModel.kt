package easv.oe.dicecup2.ticTacToe

import android.widget.ImageView
import androidx.lifecycle.ViewModel

class BoardViewModel:ViewModel() {
    var boardFields = mutableListOf<BoardField>()
    var rowAmount = 3
    var colAmount = 3

    var currentPlayer = State.PLAYER1

    init {
        createBoardFields()
    }


    fun setupBoard(rows: Int, cols: Int){
        rowAmount = rows
        colAmount = cols

        createBoardFields()

    }

    fun createBoardFields() {
        boardFields.clear()

        var i = 0
        while (i < rowAmount) {
            var j = 0
            while (j < colAmount) {
                boardFields.add(BoardField(i, j))
                j++
            }
            i++
        }
    }

    fun onclickBoard(boardField: BoardField, boardImageview: ImageView) {
        boardImageview.setImageResource(boardField.onClicked(currentPlayer))

        nextPlayer()
    }

    private fun nextPlayer() {
        if(currentPlayer == State.PLAYER1){
            currentPlayer =  State.PLAYER2
        }
        else{
            currentPlayer = State.PLAYER1
        }
    }
}