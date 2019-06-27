package android.example.judgement.Information.help.activities;

import android.example.judgement.Information.help.HelpTemplate;
import android.example.judgement.R;
import android.os.Bundle;

public class viewingScorecard extends HelpTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Class prevActivity() {
        return enteringResults.class;
    }

    @Override
    protected Class nextActivity() {
        return editPlayerDetails.class;
    }

    @Override
    protected int image() {
        return R.drawable.scorecard;
    }

    @Override
    protected int backgroundColor() {
        return R.color.cyan;
    }

    String description = "You can view the scorecard anytime by clicking on the 'scorecard' icon on the toolbar, or in the navigation drawer, which can be toggled with the help of the hamburger icon";

    @Override
    protected String description() {
        return description;
    }

    @Override
    protected String pageTitle() {
        return "Viewing the Scorecard";
    }
}
