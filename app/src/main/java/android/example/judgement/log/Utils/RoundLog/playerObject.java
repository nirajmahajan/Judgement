package android.example.judgement.log.Utils.RoundLog;

public class playerObject {
    String name;
    int predicted;
    boolean dealer;
    boolean won;


    public boolean isDealer() {
        return dealer;
    }

    public void setDealer(boolean dealer) {
        this.dealer = dealer;
    }

    public int getPredicted() {
        return predicted;
    }

    public void setPredicted(int predicted) {
        this.predicted = predicted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }
}
