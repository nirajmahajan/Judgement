package android.example.judgement.Game.template;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.example.judgement.Game.template.ScoreboardDisplay.ScoreboardDisplay;
import android.example.judgement.Game.template.ScoreboardEdit.ScoreBoardEdit;
import android.example.judgement.Game.template.editPlayer.game_edit_player;
import android.example.judgement.Information.About;
import android.example.judgement.Initialise.Init_Players;
import android.example.judgement.R;
import android.example.judgement.Utils.Utils;
import android.example.judgement.Utils.database.AppDatabase;
import android.example.judgement.Utils.log.UI.editScoreLog.ShowEditScoreLog;
import android.example.judgement.Utils.log.UI.roundLog.ShowRoundLog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

public class GameTemplate extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout fullView;
    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID)
    {
        fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_game_template, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.game_template_activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);


        toolbar = findViewById(R.id.game_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Judgement");
        toolbar.setTitleTextColor(Color.WHITE);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                hideKeyboard(activity);
                super.onDrawerOpened(drawerView);
            }
        };
        if (useDrawer())
        {
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            navigationView.setNavigationItemSelectedListener(this);
        }
        else
        {
            drawer.setVisibility(View.GONE);
        }
    }
    protected boolean useDrawer() {
        return true;
    }

    public void setToolbarTitle (String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (useDrawer())
        {
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            navigationView.setNavigationItemSelectedListener(this);
        }
        else
        {
            drawer.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.game_menu_about) {
            Intent intent = new Intent(getApplicationContext(), About.class);
            startActivity(intent);
        }
        else if (id == R.id.game_menu_newGame) {
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
        else if (id == R.id.game_menu_exit) {
            Utils.quitApp(getApplicationContext(), this);
        } else if (id == R.id.game_menu_scorecard){
            Intent intent = new Intent(getApplicationContext(), ScoreboardDisplay.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_scoreboard) {
            Intent intent = new Intent(getApplicationContext(), ScoreboardDisplay.class);
            startActivity(intent);
        } else if (id == R.id.nav_player_edit_mode) {
            Intent intent = new Intent(getApplicationContext(), game_edit_player.class);
            startActivity(intent);
        } else if (id == R.id.nav_score_edit_mode) {
            Intent intent = new Intent(getApplicationContext(), ScoreBoardEdit.class);
            startActivity(intent);
        } else if (id == R.id.nav_dealer_edit_mode) {
            Intent intent = new Intent(getApplicationContext(), DealerEdit.class);
            startActivity(intent);
        } else if (id == R.id.nav_round_log) {
            Intent intent = new Intent(getApplicationContext(), ShowRoundLog.class);
            startActivity(intent);
        } else if (id == R.id.nav_edit_score_log) {
            Intent intent = new Intent(getApplicationContext(), ShowEditScoreLog.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
