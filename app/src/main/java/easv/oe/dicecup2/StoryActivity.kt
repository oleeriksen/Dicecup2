package easv.oe.dicecup2

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import easv.oe.dicecup2.DiceManagers.DiceManager
import kotlinx.android.synthetic.main.activity_dice.*
import kotlinx.android.synthetic.main.activity_story.*
import kotlinx.android.synthetic.main.roll.*
import kotlinx.android.synthetic.main.roll.view.*

class StoryActivity : BasicActivity() {

    lateinit var diceManager: DiceManager


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        diceManager = DiceManager()
        var historyString = ""


        val diceHistoryManager = DiceHistoryManager()
        for (history in diceHistoryManager.historyList){
                historyString += history.rollArraylist.toString() + "\n"
            addCustomUI(history.diceAmount, history.diceRolls)
        }

        back.setOnClickListener{ back()}

    }

    private fun back() {
        finish()
    }

    @SuppressLint("ResourceAsColor")
    fun addCustomUI(amount: Int, array: ArrayList<Int>){
        var view = layoutInflater.inflate(R.layout.roll, null)

        var d1 = view.findViewById<ImageView>(R.id.dice1)
        var d2 = view.findViewById<ImageView>(R.id.dice2)
        var d3 = view.findViewById<ImageView>(R.id.dice3)
        var d4 = view.findViewById<ImageView>(R.id.dice4)
        var d5 = view.findViewById<ImageView>(R.id.dice5)
        var d6 = view.findViewById<ImageView>(R.id.dice6)
        var d7 = view.findViewById<ImageView>(R.id.dice7)
        var d8 = view.findViewById<ImageView>(R.id.dice8)
        var d9 = view.findViewById<ImageView>(R.id.dice9)

        val diceId = diceManager.diceImages

        val allDices = listOf(view.dice1, view.dice2, view.dice3, view.dice4, view.dice5, view.dice6, view.dice7, view.dice8, view.dice9)

        view.txtRollText.setText("Amount of rolled dice: " + amount)
        view.txtRollText.setTypeface(null, Typeface.BOLD)
        view.txtRollText.setTextColor(Color.WHITE)


        var i = 0
        for(dice in allDices){
            if(i<amount){
                dice.visibility = View.VISIBLE
                allDices[i].setImageResource(diceId[array[i]])
            }
            else{
                dice.visibility = View.GONE
            }
            i++
        }

        container.addView(view)
    }
}