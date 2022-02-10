package easv.oe.dicecup2.dice

import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.ViewModel
import easv.oe.dicecup2.R
import easv.oe.dicecup2.Utils
import kotlinx.android.synthetic.main.roll.view.*


private const val TAG= "DiceViewModel"
class DiceViewModel : ViewModel() {

    var currentDiceAmount = 2

    private val utils:Utils by lazy {
        Utils()
    }

    val diceHistoryManager :DiceHistoryManager by lazy {
        DiceHistoryManager()
    }

    init {
        Log.d(TAG, "ViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel about to be destroyed")
    }

    // mapping from 1..6 to drawables, the first index is unused
    private val diceImages = DiceImageManager().diceImages;

    fun getDiceImages(): IntArray {
        return diceImages
    }

    fun performRoll(allDices: List<ImageView>) {
        val diceRolls = ArrayList<Int>()

        var i = 1
        for(dice in allDices){
            if(dice.visibility == View.VISIBLE) {
                val ranNum = utils.getRandomInt(1, 6)
                dice.setImageResource(diceImages[ranNum])
                diceRolls.add(ranNum)
            }
            i += 1
        }

        diceHistoryManager.addToHistory(DiceRollLog(diceRolls))

    }

    fun updateDiceVisibility(dices: List<ImageView>, diceAmount: Int) {
        currentDiceAmount = diceAmount;

        var i = 1
        for(dice in dices){
            if(i <= diceAmount){
                dice.visibility = View.VISIBLE
            }
            else{
                dice.visibility = View.INVISIBLE
            }
            i += 1
        }
    }

    fun updateDiceFromHistory(dices: List<ImageView>) {
        if(diceHistoryManager.historyList.isNotEmpty()){
            val lastRoll = diceHistoryManager.getLastRoll()
            currentDiceAmount = lastRoll.diceAmount
            for((i, dice) in lastRoll.dices.withIndex()){
                dices[i].setImageResource(diceImages[dice])
            }
        }
    }

    fun addDiceRollToView(view: View, rollLog: DiceRollLog) {

        view.txtRollText.text = rollLog.diceAmountString
        view.txtRollText.setTypeface(null, Typeface.BOLD)
        view.txtRollText.setTextColor(Color.WHITE)

        val allDices = listOf(view.dice1, view.dice2, view.dice3, view.dice4, view.dice5, view.dice6, view.dice7, view.dice8, view.dice9)

        //For each dice, if the dice has an index lower than the total amount of dices in the current diceRollLog, the dice will be shown with an image;
        for((i, dice) in allDices.withIndex()){
            if(i<rollLog.diceAmount){
                dice.visibility = View.VISIBLE
                allDices[i].setImageResource(diceImages[rollLog.dices[i]])
            }
            else{
                dice.visibility = View.GONE
            }
        }

    }


}