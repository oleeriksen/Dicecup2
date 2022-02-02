package easv.oe.dicecup2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_dice.*
import java.util.*

class DiceActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)

        btnRoll.setOnClickListener { v -> onClickRoll() }
        btnStory.setOnClickListener { v-> onCLickStory() }
        seekBarDiceAmount.setOnSeekBarChangeListener(object :
        SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                diceAmount = seek.progress;
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
            }
        })
        Log.d(TAG, "OnCreate")

        diceHistory = DiceHistoryManager();
    }

    private var diceAmount: Int = 2;
    private val TAG: String = "xyz"
    lateinit var diceHistory: DiceHistoryManager;


    // mapping from 1..6 to drawables, the first index is unused
    private val diceId = intArrayOf(0, R.drawable.dice1,
        R.drawable.dice2,
        R.drawable.dice3,
        R.drawable.dice4,
        R.drawable.dice5,
        R.drawable.dice6)

    private val mRandomGenerator = Random()

    private fun onClickRoll(){
        val e1 = mRandomGenerator.nextInt(6) + 1
        val e2 = mRandomGenerator.nextInt(6) + 1
        val e3 = mRandomGenerator.nextInt(6) + 1

        // set dices

        val dices = arrayOf(e1, e2, e3);


        logDiceRoll(dices);
        updateDicesWith(dices)
        Log.d(TAG, "Roll")
    }

    private fun logDiceRoll(dices: Array<Int>){
        when(dices.size){
            1->{
                diceHistory.addToHistory(History(dices[0]))
            }
            2->{
                diceHistory.addToHistory(History(dices[0], dices[1]))
            }
            3->{
                diceHistory.addToHistory(History(dices[0], dices[1], dices[2]))
            }
            else->{

            }
        }
    }


    private fun onCLickStory(){
        val intent = Intent(this, StoryActivity::class.java)
        startActivity(intent);
    }

    private fun updateDicesWith(dices: Array<Int>) {

        when(dices.size){
            3->{
                imgDice1.setImageResource( diceId[dices[0]] )
                imgDice2.setImageResource( diceId[dices[1]] )
                imgDice3.setImageResource( diceId[dices[2]] )
            }
            else->{

            }
        }

    }
}