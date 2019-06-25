package android.example.judgement.Information.help;

import android.content.Intent;
import android.example.judgement.Information.help.activities.addingPlayers;
import android.example.judgement.Information.help.activities.chooseMode;
import android.example.judgement.Information.help.activities.editPlayerDetails;
import android.example.judgement.Information.help.activities.editScore;
import android.example.judgement.Information.help.activities.enteringResults;
import android.example.judgement.Information.help.activities.finishingGame;
import android.example.judgement.Information.help.activities.howToPlay;
import android.example.judgement.Information.help.activities.settingDealer;
import android.example.judgement.Information.help.activities.takingBids;
import android.example.judgement.Information.help.activities.viewingLogs;
import android.example.judgement.Information.help.activities.viewingScorecard;
import android.example.judgement.Initialise.TemplateActivity;
import android.example.judgement.R;
import android.os.Bundle;
import android.view.View;

public class HelpBase extends TemplateActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_base);
    }

    @Override
    protected int useMenu() {
        return R.menu.about_exit_done;
    }

    public void howToPlay(View view) {
        Intent intent = new Intent(this, howToPlay.class);
        startActivity(intent);
    }

    public void mode(View view) {
        Intent intent = new Intent(this, chooseMode.class);
        startActivity(intent);
    }

    public void initPlayer(View view) {
        Intent intent = new Intent(this, addingPlayers.class);
        startActivity(intent);
    }

    public void initDealer(View view) {
        Intent intent = new Intent(this, settingDealer.class);
        startActivity(intent);
    }

    public void takeHands(View view) {
        Intent intent = new Intent(this, takingBids.class);
        startActivity(intent);
    }

    public void takeResults(View view) {
        Intent intent = new Intent(this, enteringResults.class);
        startActivity(intent);
    }

    public void scorecard(View view) {
        Intent intent = new Intent(this, viewingScorecard.class);
        startActivity(intent);
    }

    public void editPlayer(View view) {
        Intent intent = new Intent(this, editPlayerDetails.class);
        startActivity(intent);
    }

    public void editScore(View view) {
        Intent intent = new Intent(this, editScore.class);
        startActivity(intent);
    }

    public void logs(View view) {
        Intent intent = new Intent(this, viewingLogs.class);
        startActivity(intent);
    }

    public void finalMove(View view) {
        Intent intent = new Intent(this, finishingGame.class);
        startActivity(intent);
    }
}
