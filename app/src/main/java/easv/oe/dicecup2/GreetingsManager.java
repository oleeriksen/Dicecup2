package easv.oe.dicecup2;

import java.util.ArrayList;
import java.util.List;

/**
 * stores different greetings
 */
public class GreetingsManager {

    private final Utils util = new Utils();
    private final List<String> DanishGreetings = new ArrayList<>();
    private final List<String> EnglishGreetings = new ArrayList<>();

    public GreetingsManager() {
        initGreetings();
    }

    private void initGreetings() {
        DanishGreetings.add("Hej");
        DanishGreetings.add("Halløj");

        EnglishGreetings.add("Hello");
        EnglishGreetings.add("Hi");
    }

    public String getDanishGreeting(){
        return getGreeting(DanishGreetings);
    }

    public String getEnglishGreeting(){
        return getGreeting(EnglishGreetings);
    }

    private String getGreeting(List<String> GreetingsList){
        return GreetingsList.get(util.getRandomInt(0, GreetingsList.size()-1));
    }
}
