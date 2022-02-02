package easv.oe.dicecup2

import android.os.Bundle
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


    }

    private fun onDanishClick() {
        val str = greetingsManager.danishGreeting + " " + etName.text.toString()
        tvHeader.text = str
    }

    //endregion
}