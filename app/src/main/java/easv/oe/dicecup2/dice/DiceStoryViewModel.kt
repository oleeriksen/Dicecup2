package easv.oe.dicecup2.dice

import android.util.Log
import androidx.lifecycle.ViewModel


private const val TAG= "DiceStoryViewModel"
class DiceStoryViewModel: ViewModel() {
    var diceRollListFragment: DiceRollListFragment
    var diceHistoryManager :DiceHistoryManager


    val diceImageManager:DiceImageManager by lazy{
        DiceImageManager()
    }



    init {
        Log.d(TAG, "ViewModel instance created")
        diceRollListFragment = DiceRollListFragment()
        diceHistoryManager = DiceHistoryManager()
    }




}