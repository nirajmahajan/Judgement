package android.example.judgement.log.UI.editScoreLog;

import android.app.Activity;
import android.content.Context;
import android.example.judgement.R;
import android.example.judgement.log.Utils.EditScoreLog.EditScoreInstance;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter {
    private Activity activity;
    private Context context;
    private TextView status;
    ArrayList<EditScoreInstance> log;

    public Adapter(Activity activity, Context context, ArrayList<EditScoreInstance> log, TextView status) {
        super(activity, R.layout.object_log_edit_score, log);
        this.log = log;
        this.status = status;
        this.activity = activity;
        this.context = context;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.object_log_edit_score, null, true);

        TextView tv_id = rowView.findViewById(R.id.tv_log_es_id);
        TextView tv_name = rowView.findViewById(R.id.tv_log_es_name);
        TextView tv_change = rowView.findViewById(R.id.tv_log_es_change);

        if(log.size() != 0) {
            status.setVisibility(View.INVISIBLE);
        }

        EditScoreInstance current = log.get(position);
        tv_id.setText(String.valueOf(position+1));
        tv_name.setText(current.getName());
        tv_change.setText("Changed From " + current.getFrom() + " To " + current.getTo());

        return rowView;
    }
}

