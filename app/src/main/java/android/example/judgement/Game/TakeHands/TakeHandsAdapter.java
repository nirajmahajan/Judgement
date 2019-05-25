package android.example.judgement.Game.TakeHands;

import android.app.Activity;
import android.example.judgement.R;
import android.example.judgement.database.AppDatabase;
import android.example.judgement.database.Player;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TakeHandsAdapter extends ArrayAdapter {
    private final Activity context;
    private int round;
    FloatingActionButton fab;
    ArrayList<String> names;

    TakeHandsAdapter(Activity context, ArrayList<String> names, int round, FloatingActionButton fab) {
        super(context, R.layout.take_hands_object, names);
        this.names = names;
        this.context = context;
        this.round = round;
        this.fab = fab;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.take_hands_object, null, true);


        TextView tv_id = rowView.findViewById(R.id.tv_id_take);
        TextView tv_name = rowView.findViewById(R.id.tv_take_name);
        final TextView tv_hands = rowView.findViewById(R.id.tv_take_hands);
        final EditText et_hands = rowView.findViewById(R.id.et_take_hands);
        final ImageButton b_set = rowView.findViewById(R.id.b_set_take);
        final ImageButton b_edit = rowView.findViewById(R.id.b_edit_take);
        final Player player = AppDatabase.getAppDatabase(context).dao().findByName(names.get(position));
        Player next = AppDatabase.nextPlayer(context, player);
        Player previous = AppDatabase.previousPlayer(context, player);
        final boolean dealer = player.getDealer();
        if (next.getPrediction() != -1 && player.getPrediction() != -1){
            AppDatabase.addCement(context, player.getName(), true);
        }
        if(dealer) {
            rowView.setBackgroundColor(R.color.lightCobalt);
        }
        if (allEntered()) {
            fab.setVisibility(View.VISIBLE);
        }
        else {
            fab.setVisibility(View.INVISIBLE);
        }

        tv_id.setText(String.valueOf(position + 1));
        tv_name.setText(names.get(position));
        tv_hands.setText(String.valueOf(player.getPrediction()));
        if (previous.getDealer() && player.getPrediction() == -1) {
            tv_hands.setVisibility(View.INVISIBLE);
            b_edit.setVisibility(View.INVISIBLE);
            b_set.setVisibility(View.VISIBLE);
            et_hands.setVisibility(View.VISIBLE);
        }
        else if (previous.getDealer() && next.getPrediction() == -1) {
            tv_hands.setVisibility(View.VISIBLE);
            b_edit.setVisibility(View.VISIBLE);
            b_set.setVisibility(View.INVISIBLE);
            et_hands.setVisibility(View.INVISIBLE);
        }
        else if (previous.getDealer()) {
            tv_hands.setVisibility(View.VISIBLE);
            b_edit.setVisibility(View.INVISIBLE);
            b_set.setVisibility(View.INVISIBLE);
            et_hands.setVisibility(View.INVISIBLE);
        }

        else if (previous.getPrediction() == -1) {
            tv_hands.setVisibility(View.INVISIBLE);
            b_edit.setVisibility(View.INVISIBLE);
            b_set.setVisibility(View.INVISIBLE);
            et_hands.setVisibility(View.INVISIBLE);
        }
        else if (dealer && player.getPrediction() == -1) {
            et_hands.setVisibility(View.VISIBLE);
            b_edit.setVisibility(View.INVISIBLE);
            b_set.setVisibility(View.VISIBLE);
            tv_hands.setVisibility(View.INVISIBLE);
            if ((round - allSum()) < 0) {
                et_hands.setHint("Any");
            }
            else {
                et_hands.setHint("Not " + String.valueOf(round - allSum()));
            }
        }
        else if(player.getPrediction() == -1){
            tv_hands.setVisibility(View.INVISIBLE);
            b_edit.setVisibility(View.INVISIBLE);
            b_set.setVisibility(View.VISIBLE);
            et_hands.setVisibility(View.VISIBLE);
        }
        else if (dealer) {
            et_hands.setVisibility(View.INVISIBLE);
            b_edit.setVisibility(View.VISIBLE);
            b_set.setVisibility(View.INVISIBLE);
            tv_hands.setVisibility(View.VISIBLE);
        }
        else if (next.getPrediction() == -1) {
            et_hands.setVisibility(View.INVISIBLE);
            b_edit.setVisibility(View.VISIBLE);
            b_set.setVisibility(View.INVISIBLE);
            tv_hands.setVisibility(View.VISIBLE);
        }
        else {
            et_hands.setVisibility(View.INVISIBLE);
            b_edit.setVisibility(View.INVISIBLE);
            b_set.setVisibility(View.INVISIBLE);
            tv_hands.setVisibility(View.VISIBLE);
        }
        if (player.getCement() && !dealer) {
            et_hands.setVisibility(View.INVISIBLE);
            b_edit.setVisibility(View.INVISIBLE);
            b_set.setVisibility(View.INVISIBLE);
            tv_hands.setVisibility(View.VISIBLE);
        }

        b_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_hands.getText().toString().equals("")) {
                    Toast.makeText(context, "Please provide a valid number", Toast.LENGTH_SHORT).show();
                }
                else {
                    int num = Integer.parseInt(et_hands.getText().toString());
                    if (num < 0 || num > round) {
                        Toast.makeText(context, "Invalid Input", Toast.LENGTH_SHORT).show();
                        et_hands.setText("");
                    } else if (dealer && num == (round - allSum())) {
                        Toast.makeText(context, String.valueOf(num) + " hands are restricted for the dealer", Toast.LENGTH_SHORT).show();
                        et_hands.setText("");
                    } else {
                        AppDatabase.addPrediction(context, names.get(position), num);
                        Toast.makeText(context, names.get(position) + " has predicted " + String.valueOf(num) + " hands", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                }
            }
        });

        b_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase.addPrediction(context, player.getName(), -1);
                notifyDataSetChanged();
            }
        });

        return rowView;
    }

    private int allSum() {
        List<Player> playerList = AppDatabase.getAppDatabase(context).dao().getAllPlayers();
        int sum = 0;
        for (Player player : playerList) {
            if (player.getPrediction() != -1) {
                sum += player.getPrediction();
            }
        }
        return  sum;
    }
    private boolean allEntered() {
        boolean all_filled = true;
        for (String name : names) {
            all_filled = all_filled && (AppDatabase.getAppDatabase(context).dao().findByName(name).getPrediction() != -1);
        }
        return  all_filled;
    }
}