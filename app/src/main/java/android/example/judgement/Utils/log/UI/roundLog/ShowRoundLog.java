package android.example.judgement.Utils.log.UI.roundLog;

import android.example.judgement.Initialise.TemplateActivity;
import android.example.judgement.R;
import android.example.judgement.Utils.log.Utils.Log;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ShowRoundLog extends TemplateActivity {


    private ListView listView;
    private TextView status;
    private showRoundAdapter adapter;


    @Override
    protected int useMenu() {
        return R.menu.empty;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_round_log);
        status = findViewById(R.id.tv_log_show_game_status);
        listView = findViewById(R.id.lv_log_show_game);

        adapter = new showRoundAdapter(this,getApplicationContext(), Log.getRoundLog(), status);
        listView.setAdapter(adapter);
    }
}
