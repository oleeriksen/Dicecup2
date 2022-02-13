package easv.oe.dicecup2.dice

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import easv.oe.dicecup2.BasicActivity
import easv.oe.dicecup2.R
import kotlinx.android.synthetic.main.activity_dice.*

private const val TAG = "DiceActivity"


class DiceActivity : BasicActivity() {

    //region Vars and vals
    private val orientation: Int by lazy { resources.configuration.orientation }


    private val diceViewModel :DiceViewModel by lazy {
        ViewModelProvider(this).get(DiceViewModel::class.java)
    }

    private val diceHistoryManager: DiceHistoryManager by lazy{
        diceViewModel.diceHistoryManager
    }


    //endregion

    //region override

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate(Bundle?) called")
        setContentView(R.layout.activity_dice)

        setupOrientation()
        addListeners()
        setupFragments()

        loadDice()

    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")

    }
    //endregion

    //region setup orientation

    private fun setupOrientation() {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportActionBar?.hide()
        } else {
            // In portrait
        }
    }
    //endregion

    //region setup fragments

    private fun setupFragments() {
        val currentDiceListFragment = supportFragmentManager.findFragmentById(R.id.diceFragment)
        if(currentDiceListFragment == null) {
            diceViewModel.diceListFragment = DiceListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.diceFragment, diceViewModel.diceListFragment).commit()
        }
        else{
            diceViewModel.diceListFragment = currentDiceListFragment as DiceListFragment
        }
    }

    //endregion

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
                diceViewModel.diceListFragment.setDiceAmount(seek.progress)
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

        diceViewModel.roll()
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

    //region Dice

    private fun loadDice() {
        diceViewModel.updateFragmentDiceFromHistory()
        updateDiceVisibility(diceViewModel.currentDiceAmount)
    }

    private fun updateDiceVisibility(diceAmount:Int) {
        diceViewModel.currentDiceAmount = diceAmount
        val currentDiceAmount = diceViewModel.currentDiceAmount
        tvDiceCount.text = currentDiceAmount.toString()

    }

    //endregion


    @SuppressLint("ResourceAsColor")
    fun addRollToHistoryUI(rollLog: DiceRollLog, v: LinearLayout){

        val view = layoutInflater.inflate(R.layout.roll, null)

        diceViewModel.addDiceRollToView(view, rollLog)
        v.addView(view)
    }

}