package easv.oe.dicecup2;

import java.util.ArrayList;
import java.util.List;

/**
 * temporary database (uses a static list to remember dice rolls)
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
