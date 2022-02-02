package easv.oe.dicecup2;

import java.util.ArrayList;
import java.util.List;

/**
 * stores dice rolls
 */
public class DiceHistoryManager {

    static List<History> historyList = new ArrayList<>();

    public List<History> getHistoryList() {
        return historyList;
    }

    public DiceHistoryManager() {
    }

    public void addToHistory(History history){
       historyList.add(history);
    }

}
