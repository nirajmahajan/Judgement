package android.example.judgement.Game.ShowFinalScore;

import android.content.Intent;
import android.example.judgement.Initialise.About;
import android.example.judgement.Initialise.Init_Players;
import android.example.judgement.R;
import android.example.judgement.database.AppDatabase;
import android.example.judgement.database.Player;
import android.example.judgement.log.Utils.Log;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DisplayFinalScore extends AppCompatActivity {

    private ListView listView;
    private ArrayList<ArrayList<String>> names;
    EndScoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_final_score);

        Toolbar toolbar = findViewById(R.id.toolbar_sce_game_init);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.lv_sce_init_players);
        names = new ArrayList<>();

        if (AppDatabase.getAppDatabase(getApplicationContext()).dao().countPlayers() != 0) {
            List<Player> initPlayers = AppDatabase.getAppDatabase(getApplicationContext()).dao().getAllPlayers();
            Collections.sort(initPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    if (o1.getScore() < o2.getScore()) {
                        return 1;
                    } else if (o1.getScore() == o2.getScore()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
            int prev_score = -1;
            for (Player player : initPlayers) {
                if (player.getScore() == prev_score) {
                    names.get(0).add(player.getName());
                } else {
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(player.getName());
                    names.add(temp);
                    prev_score = player.getScore();
                }
            }

            adapter = new EndScoreAdapter(this, names);
            listView.setAdapter(adapter);

        }
    }

    public void startAgain(View view) {
        AppDatabase.setAllPredictionsToReset(getApplicationContext());
        AppDatabase.setAllResultsToFalse(getApplicationContext());
        AppDatabase.setAllScoresToZero(getApplicationContext());
        Log.clearEditLog();
        Log.clearRoundLog();

        Intent intent = new Intent(getApplicationContext(), Init_Players.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void quit(View view) {
        AppDatabase.getAppDatabase(this).dao().purge();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Cannot go Back at this stage", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.game_menu_about) {
            Intent intent = new Intent(getApplicationContext(), About.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
