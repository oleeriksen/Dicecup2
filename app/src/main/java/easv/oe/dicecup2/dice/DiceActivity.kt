package easv.oe.dicecup2.dice

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.OrientationEventListener
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.widget.LinearLayoutCompat
import easv.oe.dicecup2.*
import kotlinx.android.synthetic.main.activity_dice.*
import kotlin.collections.ArrayList

class DiceActivity : BasicActivity() {

    //region Vars and vals


    private val TAG: String = "xyz"
    private var currentDiceAmount: Int = 2
    private lateinit var diceHistory: DiceHistoryManager
    private lateinit var diceManager: DiceManager
    private lateinit var utils: Utils
    private lateinit var allDices: List<ImageView>


    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)
        Log.d(TAG, "OnCreate")

        utils = Utils()
        diceHistory = DiceHistoryManager()
        diceManager = DiceManager()
        allDices = listOf(imgDice1, imgDice2, imgDice3, imgDice4, imgDice5, imgDice6, imgDice7, imgDice8, imgDice9)

        updateDiceVisibility(currentDiceAmount)

        addListeners()
    }

    //region Setup Listeners

    private fun addListeners() {

        seekBarDiceAmount.setOnSeekBarChangeListener(diceAmountSetupListener())
    }

    private fun diceAmountSetupListener(): SeekBar.OnSeekBarChangeListener{
        return object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                updateDiceVisibility(seek.progress)
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
        val intent = Intent(this, DiceStoryActivity::class.java)
        startActivity(intent)
    }

    //endregion

    //region dice methods

    private fun updateDiceVisibility(diceAmount:Int){

        currentDiceAmount = diceAmount
        tvDiceCount.text = currentDiceAmount.toString()
        seekBarDiceAmount.progress = diceAmount


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
                dice.setImageResource(diceManager.diceImages[ranNum])
                diceRolls.add(ranNum)
            }
            i += 1
        }

        diceHistory.addToHistory(DiceRollLog(diceRolls))
    }

    //endregion
}