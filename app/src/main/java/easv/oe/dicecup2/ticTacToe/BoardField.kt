package easv.oe.dicecup2.ticTacToe

import easv.oe.dicecup2.R

data class BoardField(val row: Int, val col:Int) {

    var state = State.FREE


    fun getImage(): Int {
        return when(state){
            State.FREE -> R.drawable.circlewhite
            State.PLAYER1 -> R.drawable.circlegreen
            State.PLAYER2 -> R.drawable.circlered
        }
    }

    fun onClicked(stateNew : State): Int {
        if(state == State.FREE){
            when(stateNew){
                State.PLAYER2->
                    state = State.PLAYER2
                State.PLAYER1 ->
                    state = State.PLAYER1
            }
        }
        return getImage()
    }

}