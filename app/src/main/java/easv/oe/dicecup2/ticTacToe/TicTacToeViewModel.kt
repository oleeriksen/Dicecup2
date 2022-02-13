package easv.oe.dicecup2.ticTacToe

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG =  "TicTacToeViewModel"
class TicTacToeViewModel : ViewModel() {
    var currentPlayer: Int = 1

    init {
        Log.d(TAG, "ViewModel created")
    }

    fun nextPlayer(){
        if(currentPlayer == 1){
            currentPlayer = 2
        }
        else{
            currentPlayer = 1
        }
    }


}