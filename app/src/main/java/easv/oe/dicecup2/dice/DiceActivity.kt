package easv.oe.dicecup2.dice

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import easv.oe.dicecup2.BasicActivity
import easv.oe.dicecup2.R
import easv.oe.dicecup2.Utils
import kotlinx.android.synthetic.main.activity_dice.*
import kotlinx.android.synthetic.main.roll.view.*

private const val TAG = "DiceActivity"


class DiceActivity : BasicActivity() {

    //region Vars and vals

    private var currentDiceAmount: Int = 2
    private lateinit var utils: Utils
    private lateinit var allDices: List<ImageView>

    private val diceViewModel :DiceViewModel by lazy {
        ViewModelProvider(this).get(DiceViewModel::class.java)
    }

    private val diceHistoryManager: DiceHistoryManager by lazy{
        diceViewModel.diceHistoryManager
    }


    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)
        Log.d(TAG, "OnCreate(Bundle?) called")


        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportActionBar?.hide()
        } else {
            // In portrait
        }

        utils = Utils()
        allDices = listOf(imgDice1, imgDice2, imgDice3, imgDice4, imgDice5, imgDice6, imgDice7, imgDice8, imgDice9)

        updateDiceFromHistory()
        //updateDiceVisibility(currentDiceAmount)
        addListeners()
    }

    //region Setup Listeners

    private fun addListeners() {

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            btnStory.setOnClickListener { onCLickStory() }
        }else{
            for (history in diceHistoryManager.getHistoryList()){
                addCustomUI(history, historyview)
            }
        }
        btnRoll.setOnClickListener { onClickRoll() }


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

        val intent = DiceStoryActivity.newIntent(this, diceHistoryManager)
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
                dice.setImageResource(diceViewModel.getDiceImages()[ranNum])
                diceRolls.add(ranNum)
            }
            i += 1
        }

        diceHistoryManager.addToHistory(DiceRollLog(diceRolls))

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            historyview.removeAllViews()
            for (history in diceHistoryManager.historyList) {
                addCustomUI(history, historyview)
            }
        }
    }

    private fun updateDiceFromHistory() {
        if(diceHistoryManager.historyList.isNotEmpty()){
            val lastRoll =diceHistoryManager.historyList[diceHistoryManager.historyList.size-1]
            updateDiceVisibility(lastRoll.diceAmount)
            for((i, dice) in lastRoll.dices.withIndex()){
                allDices[i].setImageResource(diceViewModel.getDiceImages()[dice])
            }
        }
        else {
            updateDiceVisibility(currentDiceAmount)
        }
    }

    @SuppressLint("ResourceAsColor")
    fun addCustomUI(rollLog: DiceRollLog, v: LinearLayout){
        val view = layoutInflater.inflate(R.layout.roll, null)
        val allDices = listOf(view.dice1, view.dice2, view.dice3, view.dice4, view.dice5, view.dice6, view.dice7, view.dice8, view.dice9)

        view.txtRollText.text = rollLog.diceAmountString
        view.txtRollText.setTypeface(null, Typeface.BOLD)
        view.txtRollText.setTextColor(Color.WHITE)

        //For each dice, if the dice has an index lower than the total amount of dices in the current diceRollLog, the dice will be shown with an image;
        for((i, dice) in allDices.withIndex()){
            if(i<rollLog.diceAmount){
                dice.visibility = View.VISIBLE
                allDices[i].setImageResource(diceViewModel.getDiceImages()[rollLog.dices[i]])
            }
            else{
                dice.visibility = View.GONE
            }
        }
        v.addView(view)
    }
    //endregion
}