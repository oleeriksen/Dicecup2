package easv.oe.dicecup2.greetings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import easv.oe.dicecup2.Utils;

/**
 * stores different greetings
 */
public class GreetingsManager {

    private final Utils util = new Utils();
    private final List<String> DanishGreetings = new ArrayList<>();
    private final List<String> EnglishGreetings = new ArrayList<>();

    private final List<String> boyNames = Arrays.asList("Liam", "Noah", "Oliver", "Elijah");
    private final List<String> girlNames = Arrays.asList("Olivia", "Emma", "Ava", "Charlotte");

    public GreetingsManager() {
        initGreetings();
    }

    private void initGreetings() {
        DanishGreetings.add("Hej");
        DanishGreetings.add("Halløj");

        EnglishGreetings.add("Hello");
        EnglishGreetings.add("Hi");
    }

    //region Greeting getters

    public String getDanishGreeting(){
        return getGreeting(DanishGreetings);
    }

    public String getEnglishGreeting(){
        return getGreeting(EnglishGreetings);
    }

    private String getGreeting(List<String> GreetingsList){
        return GreetingsList.get(util.getRandomInt(0, GreetingsList.size()-1));
    }

    //endregion

    //region name checking

    private Boolean checkBoyName(String name){
        for (String boyName : boyNames) {
            if(boyName.equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    private Boolean checkGirlName(String name){
        for (String girlName : girlNames) {
            if(girlName.equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    public String checkName(String name){
        if(checkBoyName(name))
            return "Boy";
        if(checkGirlName(name))
            return "Girl";

        return "None";
    }

    //endregion

}
