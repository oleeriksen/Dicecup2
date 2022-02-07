package easv.oe.dicecup2.calculator

import android.os.Bundle
import easv.oe.dicecup2.BasicActivity
import easv.oe.dicecup2.R
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : BasicActivity() {


    private var userInput: String = ""
    private var userOutput: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        btn_1.setOnClickListener { onClickBtnInput("1") }
        btn_2.setOnClickListener { onClickBtnInput("2") }
        btn_3.setOnClickListener { onClickBtnInput("3") }
        btn_4.setOnClickListener { onClickBtnInput("4") }
        btn_5.setOnClickListener { onClickBtnInput("5") }
        btn_6.setOnClickListener { onClickBtnInput("6") }
        btn_7.setOnClickListener { onClickBtnInput("7") }
        btn_8.setOnClickListener { onClickBtnInput("8") }
        btn_9.setOnClickListener { onClickBtnInput("9") }
        btn_0.setOnClickListener { onClickBtnInput("0") }
        btn_divider.setOnClickListener { onClickBtnInput("/") }
        btn_gange.setOnClickListener { onClickBtnInput("x") }
        btn_minus.setOnClickListener { onClickBtnInput("-") }
        btn_plus.setOnClickListener { onClickBtnInput("+") }
        btn_comma.setOnClickListener { onClickBtnInput(",") }
        btn_delete.setOnClickListener { onClickBtnDelete() }

        updateInputTxt()

    }

    private fun updateInputTxt() {
        txt_input.text = userInput
    }

    private fun onClickBtnInput(str:String){
        userInput += str
        updateInputTxt()
    }

    private fun onClickBtnDelete() {
        userInput = userInput.dropLast(1)
        updateInputTxt()
    }
}