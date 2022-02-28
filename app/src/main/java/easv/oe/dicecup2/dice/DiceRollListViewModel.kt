package easv.oe.dicecup2.dice

import androidx.lifecycle.ViewModel

class DiceRollListViewModel : ViewModel() {

    var rolls = mutableListOf<DiceRollLog>()

    init{
        val dices: ArrayList<Int> = ArrayList()

        dices.add(1)
        dices.add(3)

        val dices2: ArrayList<Int> = ArrayList()

        dices2.add(2)
        dices2.add(3)
        dices2.add(1)

        rolls.add(DiceRollLog(dices))
        rolls.add(DiceRollLog(dices2))
    }

    fun setRolls(diceHistoryManager: DiceHistoryManager){
        rolls = diceHistoryManager.historyList
    }

}