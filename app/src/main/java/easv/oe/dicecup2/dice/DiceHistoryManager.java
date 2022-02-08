package easv.oe.dicecup2.dice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * stores dice rolls
 */
public class DiceHistoryManager implements Serializable {

    public List<DiceRollLog> historyList = new ArrayList<>();

    public List<DiceRollLog> getHistoryList() {
        return historyList;
    }


    public DiceHistoryManager() {
    }

    public void addToHistory(DiceRollLog history){
       historyList.add(history);
    }


}
