package easv.oe.dicecup2

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import easv.oe.dicecup2.DiceManagers.DiceManager
import kotlinx.android.synthetic.main.activity_story.*
import kotlinx.android.synthetic.main.roll.view.*

class StoryActivity : BasicActivity() {

    lateinit var diceManager: DiceManager


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        diceManager = DiceManager()


        val diceHistoryManager = DiceHistoryManager()
        for (history in diceHistoryManager.historyList){
            addCustomUI(history)
        }

        back.setOnClickListener{ back()}

    }

    private fun back() {
        finish()
    }

    @SuppressLint("ResourceAsColor")
    fun addCustomUI(history: History){
        val view = layoutInflater.inflate(R.layout.roll, null)

        val diceId = diceManager.diceImages

        val allDices = listOf(view.dice1, view.dice2, view.dice3, view.dice4, view.dice5, view.dice6, view.dice7, view.dice8, view.dice9)

        view.txtRollText.setText("Amount of rolled dice: " + history.diceAmount)
        view.txtRollText.setTypeface(null, Typeface.BOLD)
        view.txtRollText.setTextColor(Color.WHITE)


        for((i, dice) in allDices.withIndex()){
            if(i<history.diceAmount){
                dice.visibility = View.VISIBLE
                allDices[i].setImageResource(diceId[history.diceRolls[i]])
            }
            else{
                dice.visibility = View.GONE
            }
        }

        container.addView(view)
    }
}