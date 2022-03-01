package easv.oe.dicecup2.dice

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import easv.oe.dicecup2.BasicActivity
import easv.oe.dicecup2.R
import kotlinx.android.synthetic.main.activity_story.*
import kotlinx.android.synthetic.main.roll.view.*


private const val INTENT_EXTRA_DiceHistoryManager = "diceStoryActivity_intentExtra_HistoryManager"

private const val TAG = "DiceStoryActivity"
class DiceStoryActivity() : BasicActivity() {

    //region vals and vars

    private val diceStoryViewModel:DiceViewModel by lazy {
        ViewModelProvider(this).get(DiceViewModel::class.java)
    }

    //endregion

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)
        Log.d(TAG, "OnCreate(Bundle?) called")

        diceStoryViewModel.diceHistoryManager = intent.getSerializableExtra(INTENT_EXTRA_DiceHistoryManager) as DiceHistoryManager

        val currentDiceListFragment = supportFragmentManager.findFragmentById(R.id.diceRollListFragment)
        if(currentDiceListFragment == null) {
            diceStoryViewModel.diceRollListFragment = DiceRollListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.diceRollListFragment, diceStoryViewModel.diceRollListFragment)
                .commit()
        }
        else{
            diceStoryViewModel.diceRollListFragment = currentDiceListFragment as DiceRollListFragment
        }

        for (history in diceStoryViewModel.diceHistoryManager.historyList){
            addCustomUI(history)
        }
        back.setOnClickListener{ onClickBack()}
        swt_text.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            onClickText()
        })
    }

    companion object {
        fun newIntent(packageContext: Context, dHM: DiceHistoryManager) : Intent {
            return Intent(packageContext, DiceStoryActivity:: class.java).apply {
                putExtra(INTENT_EXTRA_DiceHistoryManager, dHM) }
        }
    }


    private fun onClickText() {
        if(swt_text.isChecked){
            val scrollParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1.0f
            )
            val scrollParams2 = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                0f
            )
            scrollView4.setLayoutParams(scrollParams)
            scrollView3.setLayoutParams(scrollParams2)
        } else {
            val scrollParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1.0f
            )
            val scrollParams2 = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                0f
            )
            scrollView4.setLayoutParams(scrollParams2)
            scrollView3.setLayoutParams(scrollParams)
        }
    }


    @SuppressLint("ResourceAsColor")
    fun addCustomUI(rollLog: DiceRollLog){
        val view = layoutInflater.inflate(R.layout.roll, null)
        val allDices = listOf(view.dice1, view.dice2, view.dice3, view.dice4, view.dice5, view.dice6, view.dice7, view.dice8, view.dice9)

        view.txtRollText.text = rollLog.diceAmountString
        view.txtRollText.setTypeface(null, Typeface.BOLD)
        view.txtRollText.setTextColor(Color.WHITE)
        view.txt_date.text = rollLog.diceTimeString;
        view.txt_date.setTextColor(Color.WHITE);

        //For each dice, if the dice has an index lower than the total amount of dices in the current diceRollLog, the dice will be shown with an image;
        for((i, dice) in allDices.withIndex()){
            if(i<rollLog.diceAmount){
                dice.visibility = View.VISIBLE
                allDices[i].setImageResource(diceStoryViewModel.diceImages[rollLog.dices[i]])
            }
            else{
                dice.visibility = View.GONE
            }
        }

        container.addView(view)
    }



    //region onClick methods

    private fun onClickBack() {
        finish()
    }

    //endregion
}