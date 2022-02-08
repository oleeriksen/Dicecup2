package easv.oe.dicecup2.dice

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import easv.oe.dicecup2.BasicActivity
import easv.oe.dicecup2.R
import kotlinx.android.synthetic.main.activity_story.*
import kotlinx.android.synthetic.main.roll.view.*

class DiceStoryActivity() : BasicActivity() {

    lateinit var diceManager: DiceManager
    lateinit var diceHistoryManager : DiceHistoryManager
    //var diceHistoryManager = historyManager

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        diceManager = DiceManager()
        diceHistoryManager = intent.getSerializableExtra("h") as DiceHistoryManager


        for (history in diceHistoryManager.historyList){
            addCustomUI(history)
        }

        back.setOnClickListener{ onClickBack()}
    }





    @SuppressLint("ResourceAsColor")
    fun addCustomUI(rollLog: DiceRollLog){
        val view = layoutInflater.inflate(R.layout.roll, null)
        val allDices = listOf(view.dice1, view.dice2, view.dice3, view.dice4, view.dice5, view.dice6, view.dice7, view.dice8, view.dice9)

        view.txtRollText.text = rollLog.diceAmountString
        view.txtRollText.setTypeface(null, Typeface.BOLD)
        view.txtRollText.setTextColor(Color.WHITE)

        //For each dice, if the dice has an index lower than the total amount of dices in the current diceRollLog, the dice will be shown with an image;
        for((i, dice) in allDices.withIndex()){
            if(i<rollLog.diceAmount){
                dice.visibility = View.VISIBLE
                allDices[i].setImageResource(diceManager.diceImages[rollLog.dices[i]])
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