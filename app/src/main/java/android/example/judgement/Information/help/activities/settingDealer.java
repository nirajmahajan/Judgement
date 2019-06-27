package android.example.judgement.Information.help.activities;

import android.example.judgement.Information.help.HelpTemplate;
import android.example.judgement.R;
import android.os.Bundle;

public class settingDealer extends HelpTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Class prevActivity() {
        return addingPlayers.class;
    }

    @Override
    protected Class nextActivity() {
        return takingBids.class;
    }

    @Override
    protected int image() {
        return R.drawable.choose_dealer;
    }

    String description = "The game can start in two ways - either from the round where 1 card is distributed to each player, or from the round where the maximum cards are distributed.\nPlease note that this option, once chosen, cannot be altered.\n\nThen choose the initial dealer from the given list and press 'GO' whenever ready";

    @Override
    protected String description() {
        return description;
    }

    @Override
    protected String pageTitle() {
        return "Selecting the starting deal and Dealer";
    }
}
