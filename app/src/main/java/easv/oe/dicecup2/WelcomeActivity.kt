package easv.oe.dicecup2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        btnDanish.setOnClickListener { v -> onDanishClick()}
        btnEnglish.setOnClickListener {v-> onEnglishClick()}

    }

    fun onEnglishClick() {
        val editText = findViewById<EditText>(R.id.etName);
        val name = editText.text.toString();
        val tvHeader = findViewById<TextView>(R.id.tvHeader)
        val gr = Greetings();
        val aGr = gr.getEnglishGreeting()
        tvHeader.text = aGr + name;
    }

    fun onDanishClick() {
        val editText = findViewById<EditText>(R.id.etName);
        val name = editText.text.toString();
        val tvHeader = findViewById<TextView>(R.id.tvHeader)
        val gr = Greetings();
        val aGr = gr.getDanishGreeting()
        tvHeader.text = aGr + name;
    }
}