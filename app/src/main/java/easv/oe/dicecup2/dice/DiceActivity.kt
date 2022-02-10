package easv.oe.dicecup2.dice

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
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

        diceViewModel.updateDiceFromHistory(allDices)
        updateDiceVisibility(diceViewModel.currentDiceAmount)
        addListeners()
    }

    //region Setup Listeners

    private fun addListeners() {

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            btnStory.setOnClickListener { onCLickStory() }
        }else{
            for (history in diceHistoryManager.historyList){
                addRollToHistoryUI(history, historyview)
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
        diceViewModel.performRoll(allDices)
        Log.d(TAG, "Roll")

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            historyview.removeAllViews()
            for (history in diceHistoryManager.historyList) {
                addRollToHistoryUI(history, historyview)
            }
        }

    }

    private fun onCLickStory(){

        val intent = DiceStoryActivity.newIntent(this, diceHistoryManager)
        startActivity(intent)

    }

    //endregion

    private fun updateDiceVisibility(diceAmount:Int){

        diceViewModel.updateDiceVisibility(allDices, diceAmount)

        val currentDiceAmount = diceViewModel.currentDiceAmount
        tvDiceCount.text = currentDiceAmount.toString()
        seekBarDiceAmount.progress = currentDiceAmount

    }


    @SuppressLint("ResourceAsColor")
    fun addRollToHistoryUI(rollLog: DiceRollLog, v: LinearLayout){

        val view = layoutInflater.inflate(R.layout.roll, null)

        diceViewModel.addDiceRollToView(view, rollLog)
        v.addView(view)
    }

}