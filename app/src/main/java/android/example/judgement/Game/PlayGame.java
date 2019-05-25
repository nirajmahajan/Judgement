package android.example.judgement.Game;

import android.content.Intent;
import android.example.judgement.Game.TakeHands.TakeHands;
import android.example.judgement.database.AppDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PlayGame extends AppCompatActivity {

    boolean startFrom0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startFrom0 = getIntent().getExtras().getBoolean("START_FROM_0");
        super.onCreate(savedInstanceState);
        AppDatabase.setAllScoresToZero(getApplicationContext());
        AppDatabase.setAllResultsToFalse(getApplicationContext());
        AppDatabase.setAllPredictionsToReset(getApplicationContext());
        Intent intent = new Intent(getApplicationContext(), TakeHands.class);
        int dealnum;
        if (startFrom0) {
            dealnum = 1;
        }
        else {
            dealnum = 52 / AppDatabase.getAppDatabase(getApplicationContext()).dao().countPlayers();
        }
        intent.putExtra("START_FROM_0", startFrom0);
        intent.putExtra("ROUND_NUMBER", dealnum);
        startActivity(intent);
    }
}
