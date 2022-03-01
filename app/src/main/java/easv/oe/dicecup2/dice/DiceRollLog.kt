package easv.oe.dicecup2.dice

import java.io.Serializable
import java.lang.StringBuilder
import java.util.*

data class DiceRollLog(val dices: ArrayList<Int>) : Serializable {

    val date: Date = Calendar.getInstance().time

    val diceAmount: Int
        get() = dices.size

    val diceAmountString: String
        get() = "Amount of rolled dice: $diceAmount"


    private val rollInstance: String
        get() {
            val str = StringBuilder("Time of Roll: $date - Amount of Dice: $diceAmount - Roll Instance: ")
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