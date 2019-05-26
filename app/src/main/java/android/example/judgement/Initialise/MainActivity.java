package android.example.judgement.Initialise;

import android.content.Intent;
import android.example.judgement.R;
import android.example.judgement.database.AppDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Judgement");
        toolbar.setTitleTextColor(Color.WHITE);
    }

    @Override
    protected void onDestroy() {
//        AppDatabase.getAppDatabase(getApplicationContext()).dao().purge();
//        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    public void goToPlayerInit(View view) {
        Intent intent = new Intent(this.getApplicationContext(), Init_Players.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.welcome_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_about) {
            Intent intent = new Intent(getApplicationContext(), About.class);
            startActivity(intent);
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
