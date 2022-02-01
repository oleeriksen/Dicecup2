package easv.oe.dicecup2;

public class History {

    String rollInstance;

    public History(int dice1, int dice2) {
        this.rollInstance = String.valueOf(dice1) + " - " + String.valueOf(dice2);
    }

    public String getRollInstance() {
        return rollInstance;
    }

    @Override
    public String toString() {
        return rollInstance;
    }
}
