package android.example.judgement.Information.help.activities;

import android.example.judgement.Information.help.HelpTemplate;
import android.example.judgement.R;
import android.os.Bundle;

public class finishingGame extends HelpTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean hideNextButton() {
        return true;
    }

    @Override
    protected Class prevActivity() {
        return viewingLogs.class;
    }


    @Override
    protected int image() {
        return R.drawable.hep_display_final;
    }

    String description ="After all the rounds are finished, the app will display the final results.\n\nYou can verify the activity in each round by accessing the logs in the toolbar\n\nIf you want to play again, you can click on the 'Play Again' button, which will redirect you to the initial screen, without deleting the players.\n\n\n\n\n\n";

    @Override
    protected String description() {
        return description;
    }

    @Override
    protected String pageTitle() {
        return "Finishing the Game";
    }
}
