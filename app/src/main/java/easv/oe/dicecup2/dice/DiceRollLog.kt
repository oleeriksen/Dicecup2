package easv.oe.dicecup2.dice

import java.io.Serializable
import java.lang.StringBuilder
import java.util.ArrayList

data class DiceRollLog(val dices: ArrayList<Int>) : Serializable {


    val diceAmount: Int
        get() = dices.size

    val diceAmountString: String
        get() = "Amount of rolled dice: $diceAmount"


    private val rollInstance: String
        get() {
            val str = StringBuilder("Amount of Dice: $diceAmount - Roll Instance: ")
            for (i in dices.indices) {
                str.append(dices[i])
                if (i < dices.size - 1) {
                    str.append(" - ")
                }
            }
            return str.toString()
        }

    override fun toString(): String {
        return rollInstance
    }
}