package easv.oe.dicecup2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.activity_story.*

class StoryActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)
        var historyString :String = "";


        val diceHistoryManager = DiceHistoryManager();
        for (history in diceHistoryManager.getHistoryList()){
            historyString += history.toString() + "\n";
        }

        txtDiceStory.text =(historyString);
    }
}