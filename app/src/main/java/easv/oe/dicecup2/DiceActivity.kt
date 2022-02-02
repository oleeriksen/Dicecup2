package easv.oe.dicecup2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_dice.*
import java.util.*
import kotlin.collections.ArrayList

class DiceActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)

        allDices = listOf(imgDice1, imgDice2, imgDice3, imgDice4, imgDice5, imgDice6, imgDice7, imgDice8, imgDice9);

        btnRoll.setOnClickListener { v -> onClickRoll() }
        btnStory.setOnClickListener { v-> onCLickStory() }
        seekBarDiceAmount.setOnSeekBarChangeListener(object :
        SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                diceAmount = seek.progress;
                tvDiceCount.text = diceAmount.toString();
                updateDiceVisibility()
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
    lateinit var allDices: List<ImageView>


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
        val e4 = mRandomGenerator.nextInt(6) + 1
        val e5 = mRandomGenerator.nextInt(6) + 1
        val e6 = mRandomGenerator.nextInt(6) + 1
        val e7 = mRandomGenerator.nextInt(6) + 1
        val e8 = mRandomGenerator.nextInt(6) + 1
        val e9 = mRandomGenerator.nextInt(6) + 1

        // set dices

        val dices = arrayOf(e1, e2, e3);
        updateDices()
        Log.d(TAG, "Roll")
    }

    private fun giveRandomNum(): Int {
        return mRandomGenerator.nextInt(6) + 1;
    }

    private fun logDiceRoll(dices: ArrayList<Int>){
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

    private fun updateDiceVisibility(){
        var i: Int = 1
        for(dice in allDices){
            if(i <= diceAmount){
                dice.visibility = View.VISIBLE;
            }
            else{
                dice.visibility = View.INVISIBLE;
            }
            i += 1
        }
    }

    private fun updateDices() {
        var diceRolls = ArrayList<Int>()

        var i: Int = 1
        for(dice in allDices){
            if(i <= diceAmount){
                val ranNum = giveRandomNum()
                dice.visibility = View.VISIBLE;
                dice.setImageResource(diceId[ranNum])
                diceRolls.add(ranNum);
            }
            else{
                dice.visibility = View.INVISIBLE;
            }
            i += 1
        }
    }
}