package android.example.judgement.Game.TakeResults;

import android.content.Intent;
import android.example.judgement.Game.ShowFinalScore.DisplayFinalScore;
import android.example.judgement.Game.TakeHands.TakeHands;
import android.example.judgement.Game.TakeHands.TakeHandsAdapter;
import android.example.judgement.Game.template.GameTemplate;
import android.example.judgement.R;
import android.example.judgement.database.AppDatabase;
import android.example.judgement.database.Player;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TakeResults extends GameTemplate {

    private ListView listView;
    private TextView title;
    private ArrayList<String> names;
    TakeResultsAdapter adapter;
    private int count;
    private int round;
    private boolean startFrom0;
    private int truth_count;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_results);
        Intent intent = getIntent();
        round = intent.getExtras().getInt("ROUND_NUMBER");
        startFrom0 = intent.getExtras().getBoolean("START_FROM_0");
        title = findViewById(R.id.tv_take_res_title);

        setToolbarTitle("Taking Results");
        title.setText("Input Results of Round " + round);

        fab = findViewById(R.id.fab_take_res_go);
        listView = findViewById(R.id.lv_take_res_players);
        names = new ArrayList<>();
        if (AppDatabase.getAppDatabase(getApplicationContext()).dao().countPlayers() != 0) {
            List<Player> initPlayers = AppDatabase.getAppDatabase(getApplicationContext()).dao().getAllPlayers();
            for (Player player: initPlayers) {
                names.add(player.getName());
            }
        }
        count = AppDatabase.getAppDatabase(getApplicationContext()).dao().countPlayers();
        Player dealer = AppDatabase.getAppDatabase(getApplicationContext()).dao().findDealer(true);
        Collections.rotate(names, count - dealer.getId());

        // make an adapter
        adapter = new TakeResultsAdapter(this, names, round, fab);
        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Cannot go Back at this stage", Toast.LENGTH_SHORT).show();
    }

    public void Proceed(View view) {
        if (allFilled()){

            for (int i = 0; i < count; i++){
                ConstraintLayout cl = listView.findViewById(i);
                TextView nameView = cl.findViewById(R.id.tv_take_res_name);
                String name = nameView.getText().toString();
                RadioGroup rg = cl.findViewById(R.id.rg_take_res_result);
                RadioButton radio_yes = rg.findViewById(R.id.radio_yes);
                RadioButton radio_no = rg.findViewById(R.id.radio_no);
                if (radio_no.isChecked()) {
                    AppDatabase.addResult(getApplicationContext(), name, false);
                }
                else {
                    AppDatabase.addResult(getApplicationContext(), name, true);
                }
            }

            if (checkSumOfAllWinners()){
                startAppropriateActivity();
            }
            else {
                Toast.makeText(getApplicationContext(), "The sum of hands of all winners exceeds the number of cards dealt!", Toast.LENGTH_LONG).show();
            }
        }
        else if (truth_count == count) {
            Toast.makeText(getApplicationContext(), "All players cannot win!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Please input all data before proceeding!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkSumOfAllWinners() {
        List<Player> players = AppDatabase.getAppDatabase(getApplicationContext()).dao().getAllPlayers();
        int counter = 0;
        for (Player player : players) {
            if(player.getResult()) {
                counter += player.getPrediction();
            }
        }
        return (counter <= round);
    }

    private boolean allFilled() {
        truth_count = 0;
        for (int i = 0; i < count; i++){
            ConstraintLayout cl = listView.findViewById(i);
            RadioGroup rg = cl.findViewById(R.id.rg_take_res_result);
            RadioButton radio_yes = rg.findViewById(R.id.radio_yes);
            RadioButton radio_no = rg.findViewById(R.id.radio_no);

            if (! (radio_no.isChecked() || radio_yes.isChecked())) {
                return false;
            }
            else if (radio_yes.isChecked()){
                truth_count++;
            }
        }

        if (truth_count == count) {
            Toast.makeText(getApplicationContext(), "All players cannot win!", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int new_count = AppDatabase.getAllNames(getApplicationContext()).size();

        if (new_count <= count) {
            names = new ArrayList<>();
            if (AppDatabase.getAppDatabase(getApplicationContext()).dao().countPlayers() != 0) {
                List<Player> initPlayers = AppDatabase.getAppDatabase(getApplicationContext()).dao().getAllPlayers();
                for (Player player: initPlayers) {
                    names.add(player.getName());
                }
            }
            count = AppDatabase.getAppDatabase(getApplicationContext()).dao().countPlayers();
            Player dealer = AppDatabase.getAppDatabase(getApplicationContext()).dao().findDealer(true);
            Collections.rotate(names, count - dealer.getId());
            adapter = new TakeResultsAdapter(this, names, round, fab);
            listView.setAdapter(adapter);adapter.notifyDataSetChanged();
        }
    }

    private void startAppropriateActivity () {
        int new_count = AppDatabase.getAllNames(getApplicationContext()).size();
        int new_round_limit = 52 / new_count;

        int next_round;
        if (startFrom0) {
            next_round =  (round + 1);
        } else {
            next_round = (round - 1);
        }
        AppDatabase.addAllScores(getApplicationContext());
        AppDatabase.setAllPredictionsToReset(getApplicationContext());
        AppDatabase.setAllResultsToFalse(getApplicationContext());
        AppDatabase.moveDealer(getApplicationContext());

        if (next_round <= 0) {
            Intent end = new Intent(getApplicationContext(), DisplayFinalScore.class);
            startActivity(end);
        }
        else if (startFrom0 && new_round_limit < next_round) {
            Toast.makeText(getApplicationContext(), new_count + " people cannot play the " + next_round + "th round!\n Exiting!", Toast.LENGTH_LONG).show();
            Intent exit = new Intent(getApplicationContext(), DisplayFinalScore.class);
            startActivity(exit);
        }
        else if (!startFrom0 && next_round > new_round_limit) {
            Toast.makeText(getApplicationContext(), new_count + " people cannot play the " + next_round + "th round!\nJumping Down to the " + String.valueOf(new_round_limit) + "th round!", Toast.LENGTH_LONG).show();
            Intent jumpDown = new Intent(getApplicationContext(), TakeHands.class);
            jumpDown.putExtra("START_FROM_0", startFrom0);
            jumpDown.putExtra("ROUND_NUMBER", new_round_limit);
            startActivity(jumpDown);
        }
        else {
            Intent allow = new Intent(getApplicationContext(), TakeHands.class);
            allow.putExtra("START_FROM_0", startFrom0);
            allow.putExtra("ROUND_NUMBER", next_round);
            startActivity(allow);
        }
    }
}
