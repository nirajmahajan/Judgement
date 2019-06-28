package android.example.judgement.Information.help.activities;

import android.example.judgement.Information.help.HelpTemplate;
import android.example.judgement.R;
import android.os.Bundle;

public class viewingLogs extends HelpTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Class prevActivity() {
        return editScore.class;
    }

    @Override
    protected Class nextActivity() {
        return finishingGame.class;
    }

    @Override
    protected int image() {
        return R.drawable.help_log;
    }

    @Override
    protected int backgroundColor() {
        return R.color.cyan;
    }

    String description = "You can access the log og the game from the Navigation Drawer\n\nThe game log contains all the details of all the rounds COMPLETED till now\nIt has the name of the dealer, players' prediction and the outcomes\n\nThe edit score log has the details regarding all the changes done to the score manually\nIt has the name of the player and the initial and final score after the change\n\nThis log can be used to catch blunders/cheating, if any, that can be done by the app operator by editing score, or by entering incorrect results of rounds.\n\n\n\n\n\n";

    @Override
    protected String description() {
        return description;
    }

    @Override
    protected String pageTitle() {
        return "Viewing the game log";
    }
}
