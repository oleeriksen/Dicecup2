package easv.oe.dicecup2

import java.util.*

class Greetings {

    private val _englishGreetings = arrayOf( "Hello", "Hi")
    private val _danishGreetings = arrayOf( "Halløj", "Hej")

    val r =  Random();

    private fun getGreeting(array: Array<String>): String{
        val idx = r.nextInt(array.size)
        return array.get(idx) + " ";
    }

    public fun getEnglishGreeting(): String {
        return getGreeting(_englishGreetings);
    }

    public fun getDanishGreeting(): String{
        return getGreeting(_danishGreetings)

    }
}