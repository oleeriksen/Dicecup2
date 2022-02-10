package easv.oe.dicecup2.dice

import easv.oe.dicecup2.Utils

data class Dice(var currentEyes: Int = 1) {

    val possibleRolls = intArrayOf(1,2,3,4,5,6)

    fun roll(){
        currentEyes = possibleRolls[Utils().getRandomInt(0, possibleRolls.size-1)]
    }


}