package easv.oe.dicecup2

import android.content.Intent
import android.os.Bundle
import easv.oe.dicecup2.calculator.CalculatorActivity
import easv.oe.dicecup2.dice.DiceActivity
import easv.oe.dicecup2.greetings.WelcomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BasicActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnWelcome.setOnClickListener{onClickWelcome()}
        btnDices.setOnClickListener{onClickDices()}
        btnCalculator.setOnClickListener{ onClickCalculator()}
    }

    private fun onClickWelcome()  {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }

    private fun onClickDices(){
        val intent = Intent(this, DiceActivity::class.java)
        startActivity(intent)
    }

    private fun onClickCalculator(){
        val intent = Intent(this, CalculatorActivity::class.java)
        startActivity(intent)
    }



}
