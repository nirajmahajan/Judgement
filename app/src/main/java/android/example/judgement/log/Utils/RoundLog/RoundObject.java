package android.example.judgement.log.Utils.RoundLog;

import java.util.ArrayList;

public class RoundObject {
    ArrayList<playerObject> playerResults;
    int cards_dealt;

    public ArrayList<playerObject> getPlayerResults() {
        return playerResults;
    }

    public void setPlayerResults(ArrayList<playerObject> playerResults) {
        this.playerResults = playerResults;
    }

    public int getCards_dealt() {
        return cards_dealt;
    }

    public void setCards_dealt(int cards_dealt) {
        this.cards_dealt = cards_dealt;
    }
}
