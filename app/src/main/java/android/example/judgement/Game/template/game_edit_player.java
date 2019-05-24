package android.example.judgement.Game.template;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.example.judgement.Initialise.About;
import android.example.judgement.R;
import android.example.judgement.database.AppDatabase;
import android.example.judgement.database.Player;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class game_edit_player extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> names;
    InitAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_edit_player);
        Toolbar toolbar = findViewById(R.id.toolbar_game_ep_init);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.lv_ep_init_players);
        names = new ArrayList<>();
        if (AppDatabase.getAppDatabase(getApplicationContext()).dao().countPlayers() != 0) {
            List<Player> initPlayers = AppDatabase.getAppDatabase(getApplicationContext()).dao().getAllPlayers();
            for (Player player: initPlayers) {
                names.add(player.getName());
            }
        }

        // make an adapter
        adapter = new InitAdapter(this, names);

        listView.setAdapter(adapter);
    }

    public void addPlayers(final View view) {
        // create edit text for dialogbox
        final EditText et_GetName = new EditText(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            et_GetName.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
        }
        final Context ctx = getApplicationContext();
        final LayoutInflater inflater = this.getLayoutInflater();

        et_GetName.setHint("Name");
        new AlertDialog.Builder(this)
                .setTitle("Add a Player")
                .setMessage("Please Enter the Player's Name")
                .setView(et_GetName)
                .setCancelable(false)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int initialCount = AppDatabase.getAppDatabase(ctx).dao().countPlayers();
                        final String name = et_GetName.getText().toString();
                        AppDatabase.verifiedAddPlayer(ctx, name);
                        int finalCount = AppDatabase.getAppDatabase(ctx).dao().countPlayers();
                        dialog.cancel();
                        if(initialCount != finalCount) {
                            names.add(name);
                            AppDatabase.normalizeIDs(getApplicationContext());
                            adapter.notifyDataSetChanged();
                        }
                        dialog.cancel();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .show();
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
