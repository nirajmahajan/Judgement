package android.example.judgement.Game.template.ScoreboardDisplay;

import android.app.Activity;
import android.example.judgement.R;
import android.example.judgement.database.AppDatabase;
import android.example.judgement.database.Player;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreboardAdapter extends ArrayAdapter {
    private final Activity context;
    ArrayList<ArrayList<String>> names;

    ScoreboardAdapter(Activity context, ArrayList<ArrayList<String>> names) {
        super(context, R.layout.init_ep_object, names);
        this.names = names;
        this.context = context;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.scoreboard_display_object, null, true);

        TextView tv_rank = rowView.findViewById(R.id.tv_sce_object_rank);
        TextView tv_name = rowView.findViewById(R.id.tv_sce_object_name);
        TextView tv_score = rowView.findViewById(R.id.tv_sce_object_score);

        int curr_rank = position + 1;
        ArrayList<String> playersWithRank = names.get(position);
        String toDisplay = "";
        if (playersWithRank.size() == 1){
            toDisplay += playersWithRank.get(0);
        }
        else{
            toDisplay += playersWithRank.get(0);
            playersWithRank.remove(0);
            for (String name : playersWithRank) {
                toDisplay += "\n";
                toDisplay += name;
            }
        }


        Player player = AppDatabase.getAppDatabase(context).dao().findByName(playersWithRank.get(0));
        tv_rank.setText(String.valueOf(curr_rank));
        tv_name.setText(toDisplay);
        tv_score.setText(String.valueOf(player.getScore()));

        return rowView;
    }
}