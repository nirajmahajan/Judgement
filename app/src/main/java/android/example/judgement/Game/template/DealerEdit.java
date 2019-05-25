package android.example.judgement.Game.template;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.example.judgement.Initialise.About;
import android.example.judgement.Initialise.TemplateActivity;
import android.example.judgement.R;
import android.example.judgement.database.AppDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

public class DealerEdit extends TemplateActivity {

    RadioButton selectedRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_edit);
        populateRadioGroup();
    }


    private void populateRadioGroup() {
        RadioGroup radioGroup = findViewById(R.id.rg_dealers);
        List<String> names = AppDatabase.getAllNames(getApplicationContext());
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        int i = 0;
        for (String name : names) {
            RadioButton rb = new RadioButton(this);
            rb.setText(name);
            rb.setTextSize(40);
            rb.setId(i);
            rb.setOnClickListener(onClickRadio);
            i++;
            radioGroup.addView(rb, p);
        }
    }

    private View.OnClickListener onClickRadio = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selectedRadio = ((RadioButton) v);
        }
    };

    public void makeDealerAndGo(View v) {
        if (selectedRadio == null) {
            Toast.makeText(getApplicationContext(), "Select a Player First", Toast.LENGTH_SHORT).show();
        }
        else {
            final String name = selectedRadio.getText().toString();
            new AlertDialog.Builder(DealerEdit.this)
                    .setTitle("Confirm Dealer")
                    .setMessage("Do you wish to make " + name + " the dealer?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AppDatabase.makeDealerByName(getApplicationContext(), name);
                            dialog.cancel();
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .show();
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Cannot go Back at this stage", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_edit_players, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.game_ep_menu_about) {
            Intent intent = new Intent(getApplicationContext(), About.class);
            startActivity(intent);
        }
        else if (id == R.id.game_ep_menu_done) {
            if (3 > AppDatabase.getAppDatabase(this).dao().countPlayers()){
                Toast.makeText(getApplicationContext(), "Need at least Three Players for the Game!", Toast.LENGTH_SHORT).show();
            }
            else {
                finish();
            }
        }
        else if (id == R.id.game_ep_int_menu_exit) {
            AppDatabase.getAppDatabase(this).dao().purge();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }


        return super.onOptionsItemSelected(item);
    }
}
