package easv.oe.dicecup2;

import java.util.ArrayList;
import java.util.List;

/**
 * stores dice rolls
 */
public class DiceHistoryManager {

    static List<DiceRollLog> historyList = new ArrayList<>();

    public List<DiceRollLog> getHistoryList() {
        return historyList;
    }


    public DiceHistoryManager() {
    }

    public void addToHistory(DiceRollLog history){
       historyList.add(history);
    }


}
