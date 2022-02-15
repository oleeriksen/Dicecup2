package easv.oe.dicecup2.ticTacToe

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import easv.oe.dicecup2.R
import kotlinx.android.synthetic.main.activity_tic_tac_toe.*

private const val TAG = "TicTacToeActivity"

class TicTacToeActivity : AppCompatActivity() {

    val ticTacToeViewModel:TicTacToeViewModel by lazy{
        TicTacToeViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)
        val currentBoardFragment = supportFragmentManager.findFragmentById(R.id.boardHolder)

        if(currentBoardFragment == null) {
            ticTacToeViewModel.boardFragment = BoardFragment.newInstance()

            supportFragmentManager.beginTransaction()
                .add(R.id.boardHolder, ticTacToeViewModel.boardFragment).commit()
        }
        else{
            ticTacToeViewModel.boardFragment = currentBoardFragment as BoardFragment
        }

        btnCreate.setOnClickListener { _ -> createBoard() }
    }

    private fun createBoard() {
        ticTacToeViewModel.boardFragment.setupBoard()

        /*
         val rows = Integer.parseInt(etRows.text.toString())
        val cols = Integer.parseInt(etCols.text.toString())
        initializeBoard(rows, cols)
        Log.d(TAG, "createBoard")

         */
    }


    private fun initializeBoard(rows: Int, cols: Int) {

        gameboard.removeAllViewsInLayout()
        for (x in 0 until rows) {
            val currentLL = TableRow(this)
            gameboard.addView(currentLL)
            //currentLL.orientation = LinearLayout.HORIZONTAL
            for (y in 0 until cols) {
                val b = BoardField(this, x, y, R.drawable.circlewhite)
                b.setOnClickListener { v -> onClickBoardField(v as BoardField) }
                currentLL.addView(b)
                Log.d(TAG, "field created ($x, $y)")
            }
        }

    }

    fun onClickBoardField(b: BoardField) {
        Log.d(TAG, "Field (" + b.mRow + "," + b.mCol + ") clicked")
        b.enterNextState()
    }

    class BoardField : AppCompatImageView {
        var mRow = 0
        var mCol = 0

        private var mState = 0

        constructor(ct: Context, r: Int, c: Int, state: Int) : super(ct) {
            mRow = r
            mCol = c
            mState = state
            setImageResource(mState)
            this.adjustViewBounds = true
            minimumWidth = 130
            scaleType = ScaleType.FIT_CENTER
        }

        fun enterNextState() {
            mState = nextState(mState)
            setImageResource(mState)
        }

        @Throws(Exception::class)
        fun nextState(state: Int): Int {
            when (state) {
                R.drawable.circlewhite -> return R.drawable.circlegreen
                R.drawable.circlegreen -> return R.drawable.circlered
                R.drawable.circlered -> return R.drawable.circlewhite
            }
            throw Exception("State in boardfield corrupt")
        }
    }
}
