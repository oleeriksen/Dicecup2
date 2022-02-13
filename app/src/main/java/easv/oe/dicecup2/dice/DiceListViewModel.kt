package easv.oe.dicecup2.dice

import androidx.lifecycle.ViewModel

class DiceListViewModel: ViewModel() {
    var currentDiceAmount = 2
    val dice = mutableListOf<Dice>()

    init {
        dice.add(Dice())
    }

    fun updateDice() {
        if(dice.size < currentDiceAmount)
        while(dice.size < currentDiceAmount){
            dice.add(Dice())
        }

        else if(dice.size > currentDiceAmount){
            var diff = dice.size-currentDiceAmount
            while (diff>0){
                dice.removeAt(dice.size - diff)
                diff --
            }
        }

    }

    fun rollDice(diceHistoryManager: DiceHistoryManager) {

        val diceRolls = ArrayList<Int>()
        for(die in dice){
            die.roll()
            diceRolls.add(die.currentEyes)
        }

        diceHistoryManager.addToHistory(DiceRollLog(diceRolls))
    }

    fun updateDiceFromHistory(lastRoll: DiceRollLog) {

        currentDiceAmount = lastRoll.diceAmount
        updateDice()
        for((i, die) in lastRoll.dices.withIndex()){
            dice[i].currentEyes = die
        }
    }
}