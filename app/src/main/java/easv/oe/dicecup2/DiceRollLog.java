package easv.oe.dicecup2;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DiceRollLog {

    private ArrayList<Integer> dices;

    public DiceRollLog(ArrayList<Integer> dices) {
        this.dices = dices;
    }

    public int getDiceAmount(){
        return dices.size();
    }

    public String getDiceAmountString(){
        return "Amount of rolled dice: " + getDiceAmount();
    }

    public ArrayList<Integer> getDices() {
        return dices;
    }

    public String getRollInstance() {
        StringBuilder str = new StringBuilder("Amount of Dice: " + getDiceAmount() + " - Roll Instance: ");

        for (int i = 0; i< dices.size(); i++) {
            str.append(dices.get(i));
            if(i< dices.size()-1){
                str.append(" - ");
            }

        }
        return str.toString();
    }

    @NonNull
    @Override
    public String toString() {
        return getRollInstance();
    }
}
