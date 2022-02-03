package easv.oe.dicecup2.greetings

import android.os.Bundle
import easv.oe.dicecup2.BasicActivity
import easv.oe.dicecup2.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : BasicActivity() {

    private lateinit var greetingsManager: GreetingsManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        setupListeners()

        greetingsManager = GreetingsManager()

    }

    private fun setupListeners(){
        btnDanish.setOnClickListener { onDanishClick()}
        btnEnglish.setOnClickListener { onEnglishClick()}
    }

    //region onClicks LANGUAGE

    private fun onEnglishClick() {
        val str = greetingsManager.englishGreeting + " " + etName.text.toString()
        tvHeader.text = str
        changeColor(etName.text.toString())
    }

    private fun onDanishClick() {
        val str = greetingsManager.danishGreeting + " " + etName.text.toString()
        tvHeader.text = str
        changeColor(etName.text.toString())
    }

    //endregion

    private fun changeColor(name: String){
        when(greetingsManager.checkName(name)){
            "Boy" -> constLayout.setBackgroundColor(-16776961)
            "Girl" -> constLayout.setBackgroundColor(-65536)
            "None" -> constLayout.setBackgroundColor(-1)
        }
    }
}