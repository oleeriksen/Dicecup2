package easv.oe.dicecup2.dice

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import easv.oe.dicecup2.R

//Not used for anything

class DiceFragment: Fragment() {

    lateinit var dice:Dice
    private lateinit var diceImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dice = Dice()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dice_fragment, container, false)
        diceImage = view.findViewById(R.id.diceImage) as ImageView

        update()
        return view
    }

    fun rollDice(){
        dice.roll()
        update()
    }


    fun update(){
        diceImage.setImageResource(DiceImageManager().diceImages[dice.currentEyes])
    }

}