package android.example.judgement.Game.TakeResults;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.example.judgement.R;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TakeResultsAdapter extends ArrayAdapter {
    private final Activity context;
    private int round;
    private int count = 0;
    FloatingActionButton fab;
    ArrayList<String> names;

    TakeResultsAdapter(Activity context, ArrayList<String> names, int round, FloatingActionButton fab) {
        super(context, R.layout.take_results_object, names);
        this.names = names;
        this.context = context;
        this.round = round;
        this.fab = fab;
    }

    @SuppressLint({"RestrictedApi", "ResourceAsColor"})
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.take_results_object, null, true);

        rowView.setId(position);
        TextView tv_id = rowView.findViewById(R.id.tv_id_take_res);
        TextView tv_name = rowView.findViewById(R.id.tv_take_res_name);
        RadioGroup rg_response = rowView.findViewById(R.id.rg_take_res_result);

        tv_id.setText(String.valueOf(position + 1));
        tv_name.setText(names.get(position));


        return rowView;
    }
}
