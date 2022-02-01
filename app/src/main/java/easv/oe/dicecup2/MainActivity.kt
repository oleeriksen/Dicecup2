package easv.oe.dicecup2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val TAG: String = "xyz"

    // mapping from 1..6 to drawables, the first index is unused
    private val diceId = intArrayOf(0, R.drawable.dice1,
        R.drawable.dice2,
        R.drawable.dice3,
        R.drawable.dice4,
        R.drawable.dice5,
        R.drawable.dice6)

    private val mRandomGenerator = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRoll.setOnClickListener { v -> onClickRoll() }
        Log.d(TAG, "OnCreate")

    }

    private fun onClickRoll(){
        val e1 = mRandomGenerator.nextInt(6) + 1
        val e2 = mRandomGenerator.nextInt(6) + 1

        // set dices
        updateDicesWith(e1, e2)
        Log.d(TAG, "Roll")
    }


    private fun onCLickStory(){
        val intent = Intent(this, StoryActivity::class.java)
    }

    private fun updateDicesWith(d1: Int, d2: Int) {
        imgDice1.setImageResource( diceId[d1] )
        imgDice2.setImageResource( diceId[d2] )
    }



}
