package easv.oe.dicecup2;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class History {

    ArrayList<Integer> diceRolls;

    public History(ArrayList<Integer> diceRolls) {
        this.diceRolls = diceRolls;
    }

    public int getDiceAmount(){
        return diceRolls.size();
    }

    public String getRollInstance() {
        StringBuilder str = new StringBuilder("Amount of Dice: " + getDiceAmount() + " - Roll Instance: ");

        for (int i=0; i<diceRolls.size(); i++) {
            str.append(diceRolls.get(i));
            if(i<diceRolls.size()-1){
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
