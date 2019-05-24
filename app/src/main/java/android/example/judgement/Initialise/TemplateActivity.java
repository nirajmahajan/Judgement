package android.example.judgement.Initialise;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.example.judgement.R;
import android.example.judgement.database.AppDatabase;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class TemplateActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void setContentView(int layoutResID)
    {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_template, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.template_activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (useToolbar())
        {
            setSupportActionBar(toolbar);
            setTitle("Judgement");
            toolbar.setTitleTextColor(Color.WHITE);
        }
        else
        {
            toolbar.setVisibility(View.GONE);
        }
    }
    protected boolean useToolbar() {
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public Toolbar getToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        return toolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_about) {
            Intent intent = new Intent(getApplicationContext(), About.class);
            startActivity(intent);
        }
        else if (id == R.id.menu_newGame) {
            final Activity context = this;
            new AlertDialog.Builder(context)
                    .setTitle("Confirm Your Actions")
                    .setMessage("Are you sure you want to start a new game?")
                    .setCancelable(false)
                    .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AppDatabase.getAppDatabase(getApplicationContext()).dao().purge();
                            Intent intent = new Intent(context, Init_Players.class);
                            dialog.cancel();
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    })
                    .show();
        }
        else if (id == R.id.menu_exit) {
            AppDatabase.getAppDatabase(this).dao().purge();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }


        return super.onOptionsItemSelected(item);
    }
}
