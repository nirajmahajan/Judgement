package android.example.judgement.Utils.log.Utils;

import android.content.Context;
import android.example.judgement.Utils.database.AppDatabase;
import android.example.judgement.Utils.database.Player;
import android.example.judgement.Utils.log.Utils.EditScoreLog.EditScoreInstance;
import android.example.judgement.Utils.log.Utils.RoundLog.RoundObject;
import android.example.judgement.Utils.log.Utils.RoundLog.playerObject;

import java.util.ArrayList;
import java.util.List;

public class Log {
    private static ArrayList<RoundObject> roundLog = new ArrayList<>();
    private static ArrayList<EditScoreInstance> editScoreLog = new ArrayList<>();

    public static ArrayList<EditScoreInstance> getEditScoreLog() {
        return editScoreLog;
    }

    public static void setEditScoreLog(ArrayList<EditScoreInstance> editScoreLog) {
        Log.editScoreLog = editScoreLog;
    }

    public static ArrayList<RoundObject> getRoundLog() {
        return roundLog;
    }

    public static void setRoundLog(ArrayList<RoundObject> roundLog) {
        Log.roundLog = roundLog;
    }

    public static void captureRoundLog(Context context, int cards_dealt) {
        List<Player> players = AppDatabase.getAppDatabase(context).dao().getAllPlayers();
        ArrayList<playerObject> temp_list = new ArrayList<>();
        RoundObject curr_round = new RoundObject();
        curr_round.setCards_dealt(cards_dealt);

        for (Player player : players) {
            playerObject temp = new playerObject();
            temp.setName(player.getName());
            temp.setPredicted(player.getPrediction());
            temp.setDealer(player.getDealer());
            temp.setWon(player.getResult());
            temp_list.add(temp);
        }
        curr_round.setPlayerResults(temp_list);
        roundLog.add(curr_round);
    }

    public static void recordEditScore(String name, int from, int to) {
        EditScoreInstance es = new EditScoreInstance();
        es.setFrom(from);
        es.setName(name);
        es.setTo(to);
        editScoreLog.add(es);
    }

    public static void clearRoundLog() {
        roundLog = new ArrayList<RoundObject>();
    }

    public static void clearEditLog() {
        editScoreLog = new ArrayList<EditScoreInstance>();
    }
}
