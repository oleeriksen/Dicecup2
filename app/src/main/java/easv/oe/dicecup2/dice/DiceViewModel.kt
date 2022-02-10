package easv.oe.dicecup2.dice

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import easv.oe.dicecup2.Utils


private const val TAG= "DiceViewModel"
class DiceViewModel : ViewModel() {

    val utils:Utils by lazy {
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


}