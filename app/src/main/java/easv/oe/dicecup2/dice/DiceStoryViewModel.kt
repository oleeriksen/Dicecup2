package easv.oe.dicecup2.dice

import android.util.Log
import androidx.lifecycle.ViewModel


private const val TAG= "DiceStoryViewModel"
class DiceStoryViewModel: ViewModel() {

    init {
        Log.d(TAG, "ViewModel instance created")
    }

     val diceImageManager:DiceImageManager by lazy{
        DiceImageManager()
    }


}