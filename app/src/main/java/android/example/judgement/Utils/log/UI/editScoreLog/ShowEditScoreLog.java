package android.example.judgement.Utils.log.UI.editScoreLog;

import android.example.judgement.Initialise.TemplateActivity;
import android.example.judgement.R;
import android.example.judgement.Utils.log.Utils.Log;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ShowEditScoreLog extends TemplateActivity {

    private ListView listView;
    private TextView status;
    private Adapter adapter;

    @Override
    protected int useMenu() {
        return R.menu.empty;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_edit_score_log);
        status = findViewById(R.id.tv_log_show_es_status);
        listView = findViewById(R.id.lv_log_show_edit_score);

        adapter = new Adapter(this,getApplicationContext(), Log.getEditScoreLog(), status);
        listView.setAdapter(adapter);
    }
}
