package easv.oe.dicecup2.ticTacToe

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import easv.oe.dicecup2.R
import kotlinx.android.synthetic.main.activity_tic_tac_toe.*

private const val TAG = "TicTacToeActivity"

class TicTacToeActivity : AppCompatActivity() {

    private val ticTacToeViewModel:TicTacToeViewModel by lazy{
        TicTacToeViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)
        Log.d(TAG, "OnCreate(Bundle?) called")
        val currentBoardFragment = supportFragmentManager.findFragmentById(R.id.boardHolder)

        if(currentBoardFragment == null) {
            ticTacToeViewModel.ticTacToeBoardFragment = TicTacToeBoardFragment.newInstance()

            supportFragmentManager.beginTransaction()
                .add(R.id.boardHolder, ticTacToeViewModel.ticTacToeBoardFragment).commit()
        }
        else{
            ticTacToeViewModel.ticTacToeBoardFragment = currentBoardFragment as TicTacToeBoardFragment
        }

        btnCreate.setOnClickListener { _ -> createBoard() }
    }

    private fun createBoard() {
        ticTacToeViewModel.ticTacToeBoardFragment.setupBoard()
    }


}
