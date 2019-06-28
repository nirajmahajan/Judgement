package android.example.judgement.Information.help.activities;

import android.example.judgement.Information.help.HelpTemplate;
import android.example.judgement.R;
import android.os.Bundle;

public class enteringResults extends HelpTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Class prevActivity() {
        return takingBids.class;
    }

    @Override
    protected Class nextActivity() {
        return viewingScorecard.class;
    }

    @Override
    protected int image() {
        return R.drawable.take_result;
    }

    String description = "Enter the results of the round by selecting yes or no appropriately\n\nThe prediction of each player is displayed next to his/her name.\n\nSome basic checks have been implemented which aim at reducing the possibility of incorrect data being entered\n\n\n\n\n\n";

    @Override
    protected String description() {
        return description;
    }

    @Override
    protected String pageTitle() {
        return "Entering Results of the Round";
    }
}
