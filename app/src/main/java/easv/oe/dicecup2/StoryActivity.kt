package easv.oe.dicecup2

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_story.*

class StoryActivity : BasicActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)
        var historyString = ""


        val diceHistoryManager = DiceHistoryManager()
        for (history in diceHistoryManager.historyList){
                historyString += history.rollArraylist.toString() + "\n"
            addCustomUI(history.diceAmount, history.diceRolls)
        }

        back.setOnClickListener{ back()}

    }

    private fun back() {
        finish()
    }

    @SuppressLint("ResourceAsColor")
    fun addCustomUI(amount: Int, array: ArrayList<Int>){
        var view = layoutInflater.inflate(R.layout.roll, null)
        var rollAmount = view.findViewById<TextView>(R.id.txtRollText)

        var d1 = view.findViewById<ImageView>(R.id.dice1)
        var d2 = view.findViewById<ImageView>(R.id.dice2)
        var d3 = view.findViewById<ImageView>(R.id.dice3)
        var d4 = view.findViewById<ImageView>(R.id.dice4)
        var d5 = view.findViewById<ImageView>(R.id.dice5)
        var d6 = view.findViewById<ImageView>(R.id.dice6)
        var d7 = view.findViewById<ImageView>(R.id.dice7)
        var d8 = view.findViewById<ImageView>(R.id.dice8)
        var d9 = view.findViewById<ImageView>(R.id.dice9)

        val diceId = intArrayOf(0, R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6)

        val images = ArrayList<ImageView>();
        images.add(d1)
        images.add(d2)
        images.add(d3)
        images.add(d4)
        images.add(d5)
        images.add(d6)
        images.add(d7)
        images.add(d8)
        images.add(d9)

        rollAmount.setText("Amount of rolled dice: " + amount)
        rollAmount.setTypeface(null, Typeface.BOLD)
        rollAmount.setTextColor(Color.WHITE)
        when(amount){
            2-> d2.visibility= View.VISIBLE
            3-> {d2.visibility= View.VISIBLE
                d3.visibility= View.VISIBLE}
            4-> {d2.visibility= View.VISIBLE
                d3.visibility= View.VISIBLE
                d4.visibility= View.VISIBLE}
            5-> {d2.visibility= View.VISIBLE
                d3.visibility= View.VISIBLE
                d4.visibility= View.VISIBLE
                d5.visibility= View.VISIBLE}
            6-> {d2.visibility= View.VISIBLE
                d3.visibility= View.VISIBLE
                d4.visibility= View.VISIBLE
                d5.visibility= View.VISIBLE
                d6.visibility= View.VISIBLE}
            7-> {d2.visibility= View.VISIBLE
                d3.visibility= View.VISIBLE
                d4.visibility= View.VISIBLE
                d5.visibility= View.VISIBLE
                d6.visibility= View.VISIBLE
                d7.visibility= View.VISIBLE}
            8-> {d2.visibility= View.VISIBLE
                d3.visibility= View.VISIBLE
                d4.visibility= View.VISIBLE
                d5.visibility= View.VISIBLE
                d6.visibility= View.VISIBLE
                d7.visibility= View.VISIBLE
                d8.visibility= View.VISIBLE}
            9-> {d2.visibility= View.VISIBLE
                d3.visibility= View.VISIBLE
                d4.visibility= View.VISIBLE
                d5.visibility= View.VISIBLE
                d6.visibility= View.VISIBLE
                d7.visibility= View.VISIBLE
                d8.visibility= View.VISIBLE
                d9.visibility= View.VISIBLE}
            else -> {d2.visibility= View.GONE
                d3.visibility= View.GONE
                d4.visibility= View.GONE
                d5.visibility= View.GONE
                d6.visibility= View.GONE
                d7.visibility= View.GONE
                d8.visibility= View.GONE
                d9.visibility= View.GONE}
        }

        for (i in (0 until amount)) {
            images[i].setImageResource(diceId[array[i]])
        }


        container.addView(view)
    }
}