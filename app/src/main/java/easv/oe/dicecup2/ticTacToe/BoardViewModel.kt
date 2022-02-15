package easv.oe.dicecup2.ticTacToe

import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModel

class BoardViewModel:ViewModel() {
    var boardFields = mutableListOf<BoardField>()
    var rowAmount = 3
    var colAmount = 3

    var currentPlayer = State.PLAYER1
    var winningPlayer = State.FREE
    var gameEnded = false

    init {
        createBoardFields()
    }

    fun createBoardFields() {
        boardFields.clear()
        winningPlayer = State.FREE
        currentPlayer = State.PLAYER1
        gameEnded = false

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
        if(!gameEnded) {
            boardImageview.setImageResource(boardField.onClicked(currentPlayer))

            if (checkIfWon()) {
                Toast.makeText(
                    boardImageview.context,
                    winningPlayer.name + " won",
                    Toast.LENGTH_SHORT
                ).show()
                gameEnded = true
            } else {
                nextPlayer()
            }
        }
    }

    private fun nextPlayer() {
        if(currentPlayer == State.PLAYER1){
            currentPlayer =  State.PLAYER2
        }
        else{
            currentPlayer = State.PLAYER1
        }
    }

    private fun checkIfWon(): Boolean {

        var p1H1 = true
        var p1H2 = true
        var p1H3 = true

        var p1V1 = true
        var p1V2 = true
        var p1V3 = true

        var p1D1 = true
        var p1D2 = true

        var p2H1 = true
        var p2H2 = true
        var p2H3 = true

        var p2V1 = true
        var p2V2 = true
        var p2V3 = true

        var p2D1 = true
        var p2D2 = true

        val intHorizontal = arrayListOf<Int>(0,3,6)
        val intDiagonal1 = arrayListOf<Int>(0,4,8)
        val intDiagonal2 = arrayListOf<Int>(2,4,6)


        for(field in boardFields){

            for(i in intHorizontal){
                if(field.position == i){
                    if (field.state != State.PLAYER1){
                        p1V1 = false
                    }
                    if (field.state != State.PLAYER2){
                        p2V1 = false
                    }
                }

                else if(field.position == i+1){
                    if (field.state != State.PLAYER1){
                        p1V2 = false
                    }
                    if (field.state != State.PLAYER2){
                        p2V2 = false
                    }
                }
                else if(field.position == i+2){
                    if (field.state != State.PLAYER1){
                        p1V3 = false
                    }
                    if (field.state != State.PLAYER2){
                        p2V3 = false
                    }
                }
            }

            if(field.position<3) {
                if(field.state != State.PLAYER1){
                    p1H1 = false
                }
                if(field.state != State.PLAYER2){
                    p2H1 = false
                }
            }
            else if(field.position<6){
                if(field.state != State.PLAYER1){
                    p1H2 = false
                }
                if(field.state != State.PLAYER2){
                    p2H2 = false
                }
            }
            else if(field.position<9){
                if(field.state != State.PLAYER1){
                    p1H3 = false
                }
                if(field.state != State.PLAYER2){
                    p2H3 = false
                }
            }

            for(i in intDiagonal1){
                if(field.position == i){
                    if(field.state != State.PLAYER1){
                        p1D1 = false
                    }
                    if(field.state != State.PLAYER2){
                        p2D1 = false
                    }
                }
            }

            for(i in intDiagonal2){
                if(field.position == i){
                    if(field.state != State.PLAYER1){
                        p1D2 = false
                    }
                    if(field.state != State.PLAYER2){
                        p2D2 = false
                    }
                }
            }
        }

        val p1Won = (p1H1 || p1H2 || p1H3 || p1V1 || p1V2 || p1V3 || p1D1 ||p1D2)
        val p2Won = (p2H1 || p2H2 || p2H3 || p2V1 || p2V2 || p2V3 || p2D1 ||p2D2)
        val isWon = p1Won || p2Won

        if(isWon){
            if(p1Won){
                winningPlayer = State.PLAYER1
            }

            if(p2Won){
                winningPlayer = State.PLAYER2
            }
        }



            return isWon

    }
}