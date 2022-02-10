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
import kotlinx.android.synthetic.main.activity_dice.*

private const val TAG = "DiceActivity"
private const val KEY_INDEX = "index"


class DiceActivity : BasicActivity() {

    //region Vars and vals
    private lateinit var allDices: List<ImageView>
    private var allGeneratedDices: List<ImageView> = emptyList()
    private val orientation: Int by lazy { resources.configuration.orientation }

    private val diceViewModel :DiceViewModel by lazy {
        ViewModelProvider(this).get(DiceViewModel::class.java)
    }

    private val diceHistoryManager: DiceHistoryManager by lazy{
        diceViewModel.diceHistoryManager
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
        outState.putSerializable(KEY_INDEX, diceViewModel.diceHistoryManager)
    }


    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate(Bundle?) called")
        setContentView(R.layout.activity_dice)


        if(savedInstanceState?.getSerializable(KEY_INDEX) != null) {
                diceViewModel.diceHistoryManager = savedInstanceState.getSerializable(KEY_INDEX) as DiceHistoryManager
            }



        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportActionBar?.hide()
        } else {
            // In portrait
        }

        allDices = listOf(imgDice1, imgDice2, imgDice3, imgDice4, imgDice5, imgDice6, imgDice7, imgDice8, imgDice9)

        diceViewModel.updateDiceFromHistory(allDices)
        updateDiceVisibility(diceViewModel.currentDiceAmount)
        addListeners()
    }

    //region Setup Listeners

    private fun addListeners() {


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

    private fun addXAmountOfDices(numberOfDices:Int) {
        if(numberOfDices > 0){
            val layout1 = LinearLayout(this)
            layoutDices.addView(layout1)
            for (i in 1..3){
                allGeneratedDices += listOf(addDiceToThisLayout(layout1))
                println("Layout 1 $i")
            }
        }
        if(numberOfDices > 3){
            val layout2 = LinearLayout(this)
            layoutDices.addView(layout2)
            for (i in 1.. (numberOfDices - 3)){
                allGeneratedDices += listOf(addDiceToThisLayout(layout2))
                var j = i+3
                println("Layout 2 $j")
                if(i >= 3)
                    break
            }
        }
        if (numberOfDices > 6){
            val layout3 = LinearLayout(this)
            layoutDices.addView(layout3)
            for (i in 7..numberOfDices){
                allGeneratedDices += listOf(addDiceToThisLayout(layout3))
                println("Layout 3 $i")
            }
        }
    }

    private fun addDiceToThisLayout(layout: LinearLayout): ImageView {
        val dice = ImageView(this)
        dice.adjustViewBounds = true
        dice.maxWidth = 100
        dice.scaleType = ImageView.ScaleType.CENTER_INSIDE
        dice.setImageResource(R.drawable.dice1)
        layout.addView(dice)
        return dice;
    }

}