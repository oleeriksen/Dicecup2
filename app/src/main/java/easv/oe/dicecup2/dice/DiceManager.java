package easv.oe.dicecup2.dice;

import easv.oe.dicecup2.R;

public class DiceManager {

    // mapping from 1..6 to drawables, the first index is unused
    private final int[] diceImages = {0, R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6};


    public DiceManager() {
    }

    public int[] getDiceImages() {
        return diceImages;
    }
}
