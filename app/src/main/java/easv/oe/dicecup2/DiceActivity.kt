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

    //region Vars and vals

    private var diceAmount: Int = 2
    private val TAG: String = "xyz"
    private lateinit var diceHistory: DiceHistoryManager
    private lateinit var utils: Utils
    private lateinit var allDices: List<ImageView>


    // mapping from 1..6 to drawables, the first index is unused
    private val diceId = intArrayOf(0, R.drawable.dice1,
        R.drawable.dice2,
        R.drawable.dice3,
        R.drawable.dice4,
        R.drawable.dice5,
        R.drawable.dice6)

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)
        Log.d(TAG, "OnCreate")

        utils = Utils()
        diceHistory = DiceHistoryManager()
        allDices = listOf(imgDice1, imgDice2, imgDice3, imgDice4, imgDice5, imgDice6, imgDice7, imgDice8, imgDice9)

        addListeners()
    }

    //region Setup Listeners

    private fun addListeners() {
        btnRoll.setOnClickListener { onClickRoll() }
        btnStory.setOnClickListener { onCLickStory() }
        seekBarDiceAmount.setOnSeekBarChangeListener(diceAmountSetupListener())
    }

    private fun diceAmountSetupListener(): SeekBar.OnSeekBarChangeListener{
        return object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                diceAmount = seek.progress
                tvDiceCount.text = diceAmount.toString()
                updateDiceVisibility()
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
            }
        }
    }

    //endregion

    //region OnClick Methods
    private fun onClickRoll(){
        // set dices
        updateDices()
        Log.d(TAG, "Roll")
    }

    private fun onCLickStory(){
        val intent = Intent(this, StoryActivity::class.java)
        startActivity(intent)
    }

    //endregion

    //region dice methods

    private fun updateDiceVisibility(){
        var i = 1
        for(dice in allDices){
            if(i <= diceAmount){
                dice.visibility = View.VISIBLE
            }
            else{
                dice.visibility = View.INVISIBLE
            }
            i += 1
        }
    }

    private fun updateDices() {
        val diceRolls = ArrayList<Int>()

        var i = 1
        for(dice in allDices){
            if(dice.visibility == View.VISIBLE) {
                val ranNum = utils.getRandomInt(1, 6)
                dice.setImageResource(diceId[ranNum])
                diceRolls.add(ranNum)
            }
            i += 1
        }

        diceHistory.addToHistory(History(diceRolls))
    }

    //endregion
}