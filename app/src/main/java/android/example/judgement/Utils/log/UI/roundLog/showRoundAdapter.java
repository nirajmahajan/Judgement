package android.example.judgement.Utils.log.UI.roundLog;

import android.app.Activity;
import android.content.Context;
import android.example.judgement.R;
import android.example.judgement.Utils.log.Utils.RoundLog.RoundObject;
import android.example.judgement.Utils.log.Utils.RoundLog.playerObject;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class showRoundAdapter extends ArrayAdapter {
    private Activity activity;
    private Context context;
    private TextView status;
    ArrayList<RoundObject> log;

    showRoundAdapter(Activity activity, Context context, ArrayList<RoundObject> log, TextView status) {
        super(activity, R.layout.object_log_game, log);
        this.log = log;
        this.status = status;
        this.activity = activity;
        this.context = context;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.object_log_game, null, true);

        TextView tv_id = rowView.findViewById(R.id.tv_log_game_id);
        TextView tv_cards_dealt = rowView.findViewById(R.id.tv_log_game_cards_dealt);
        LinearLayout lv_results = (LinearLayout) rowView.findViewById(R.id.lv_log_show_game_sub_round);

        if(log.size() != 0) {
            status.setVisibility(View.INVISIBLE);
        }

        RoundObject current = log.get(position);
        tv_id.setText(String.valueOf(position+1));
        tv_cards_dealt.setText("Cards Dealt :" + String.valueOf(current.getCards_dealt()));


        ArrayList<playerObject> curr_results = current.getPlayerResults();
        for (playerObject pob : curr_results) {
            View child = activity.getLayoutInflater().inflate(R.layout.object_log_game_subround, null, true);


            TextView tv_id_sub = child.findViewById(R.id.tv_log_game_sub_id);
            TextView tv_name = child.findViewById(R.id.tv_log_game_sub_name);
            TextView tv_predicted = child.findViewById(R.id.tv_log_game_sub_predicted);
            TextView tv_result = child.findViewById(R.id.tv_log_game_sub_result);

            String dealer = "";
            if (pob.isDealer()) {
                dealer = "  (Dealer)";
            }
            tv_id_sub.setText(String.valueOf(position + 1));
            tv_name.setText(pob.getName() + dealer);
            tv_predicted.setText("Predicted : " + String.valueOf(pob.getPredicted()));
            if (pob.isWon()) {
                tv_result.setText("Won");
                tv_result.setTextColor(Color.parseColor("#0B7500"));
            } else {
                tv_result.setText("Lost");
                tv_result.setTextColor(Color.parseColor("#FF0000"));
            }
            lv_results.addView(child);
        }



        return rowView;
    }
}
