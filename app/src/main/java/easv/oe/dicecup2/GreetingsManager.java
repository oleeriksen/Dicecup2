package easv.oe.dicecup2;

import java.util.ArrayList;
import java.util.List;

public class GreetingsManager {

    private Utils util = new Utils();
    private List<String> DanishGreetings = new ArrayList<>();
    private List<String> EnglishGreetings = new ArrayList<>();

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
