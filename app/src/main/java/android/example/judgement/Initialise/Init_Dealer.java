package android.example.judgement.Initialise;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.example.judgement.R;
import android.example.judgement.database.AppDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Init_Dealer extends TemplateActivity {

    boolean startFrom0 = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init__dealer);
        askForStart();
    }

    private void askForStart() {

        int end  = 52 / AppDatabase.getAppDatabase(getApplicationContext()).dao().countPlayers();
        new AlertDialog.Builder(this)
                .setTitle("How do you wish to start")
                .setMessage("Do you wish to start dealing from 1?\n(select 'no' for starting from " + String.valueOf(end) + " )")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startFrom0 = true;
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startFrom0 = false;
                        dialog.cancel();
                    }
                })
                .show();

    }
}
