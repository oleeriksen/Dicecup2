package easv.oe.dicecup2;

public class History {


    int diceAmount;
    String rollInstance;

    public History(int dice1) {
        this.diceAmount = 1;
        this.rollInstance = String.valueOf(dice1);
    }

    public History(int dice1, int dice2) {
        this.diceAmount = 2;
        this.rollInstance = String.valueOf(dice1) + " - " + String.valueOf(dice2);
    }

    public History(int dice1, int dice2, int dice3){
        this.diceAmount = 3;
        this.rollInstance =  dice1 + " - " + String.valueOf(dice2) + " - " + String.valueOf(dice3);
    }

    public History(int dice1, int dice2, int dice3, int dice4){
        this.diceAmount = 4;
        this.rollInstance =  dice1 + " - " + String.valueOf(dice2) + " - " + String.valueOf(dice3) + " - " + String.valueOf(dice4);
    }

    public History(int dice1, int dice2, int dice3, int dice4, int dice5){
        this.diceAmount = 5;
        this.rollInstance =  dice1 + " - " + String.valueOf(dice2) + " - " + String.valueOf(dice3) + " - " + String.valueOf(dice4) +" - " + String.valueOf(dice5);
    }

    public History(int dice1, int dice2, int dice3, int dice4, int dice5, int dice6){
        this.diceAmount = 6;
        this.rollInstance =  dice1 + " - " + String.valueOf(dice2) + " - " + String.valueOf(dice3) + " - " + String.valueOf(dice4) +" - " + String.valueOf(dice5) + " - " + String.valueOf(dice6);
    }
    public History(int dice1, int dice2, int dice3, int dice4, int dice5, int dice6,int dice7){
        this.diceAmount = 7;
        this.rollInstance =  dice1 + " - " + String.valueOf(dice2) + " - " + String.valueOf(dice3) + " - " + String.valueOf(dice4) +" - " + String.valueOf(dice5) + " - " + String.valueOf(dice6) + " - " + String.valueOf(dice7);
    }

    public History(int dice1, int dice2, int dice3, int dice4, int dice5, int dice6,int dice7, int dice8){
        this.diceAmount = 8;
        this.rollInstance =  dice1 + " - " + String.valueOf(dice2) + " - " + String.valueOf(dice3) + " - " + String.valueOf(dice4) +" - " + String.valueOf(dice5) + " - " + String.valueOf(dice6) + " - " + String.valueOf(dice7) + " - " + String.valueOf(dice8);
    }

    public History(int dice1, int dice2, int dice3, int dice4, int dice5, int dice6,int dice7, int dice8, int dice9){
        this.diceAmount = 9;
        this.rollInstance =  dice1 + " - " + String.valueOf(dice2) + " - " + String.valueOf(dice3) + " - " + String.valueOf(dice4) +" - " + String.valueOf(dice5) + " - " + String.valueOf(dice6) + " - " + String.valueOf(dice7) + " - " + String.valueOf(dice8) +" - " + String.valueOf(dice9);
    }

    public String getRollInstance() {
        return rollInstance;
    }

    @Override
    public String toString() {
        return rollInstance;
    }
}
