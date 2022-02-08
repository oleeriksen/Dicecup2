package easv.oe.dicecup2.calculator

import android.os.Bundle
import easv.oe.dicecup2.BasicActivity
import easv.oe.dicecup2.R
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : BasicActivity() {


    private var userInput: String = ""
    private var userOutput: String = ""
    private val operators = """/[+/-x]/g""".toRegex()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        updateTxt()

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
        btn_comma.setOnClickListener { onClickComma() }
        btn_delete.setOnClickListener { onClickBtnDelete() }
        btn_enter.setOnClickListener { onClickEnter() }

        updateInputTxt()

    }

    private fun updateTxt(){
        updateInputTxt()
        updateOutputTxt()
    }

    private fun updateInputTxt() {
        txt_input.text = userInput
    }

    private fun updateOutputTxt(){
        txt_output.text = userOutput
    }

    private fun onClickBtnInput(str:String){
        userInput += str
        updateInputTxt()
    }

    private fun onClickBtnDelete() {
        userInput = userInput.dropLast(1)
        updateInputTxt()
    }

    private fun onClickComma(){

        if(userInput.contains(",")){

            var inputArr = userInput.split(operators)
            if(!inputArr[inputArr.size-1].contains(",")){
                userInput += ","
            }
        }
        else{
            userInput += ","
        }
        updateInputTxt()
    }

    private fun onClickEnter(){

        var result = 0.0

        if (txt_input.text.isNotBlank()) {
            if (userInput.contains(operators)) {

                if (userInput.contains("x")) {
                    //TODO
                } else if (userInput.contains("+")) {
                    val plusArr = userInput.split("+")

                    for (number in plusArr) {
                       result = result.plus(number.toDouble())
                    }
                }
            }
        }

        userOutput = result.toString()
        updateTxt()
    }
}