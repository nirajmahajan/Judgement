package android.example.judgement.Information.help.activities;

import android.example.judgement.Information.help.HelpTemplate;
import android.example.judgement.R;
import android.os.Bundle;

public class takingBids extends HelpTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Class prevActivity() {
        return settingDealer.class;
    }

    @Override
    protected Class nextActivity() {
        return enteringResults.class;
    }

    @Override
    protected int image() {
        return R.drawable.take_bids;
    }

    String description = "Enter the predictions of each player in a chronological order, as prompted by the app.\nYou cannot enter the prediction for any player other than the one the app is expecting.\n\n The player highlighted in a dark shade, at the bottom of the list is the dealer, and will predict last\n\nThere may be a restriction on the dealer's prediction which will appear, when the app prompts for the dealer's prediction\n\nThe number of cards to be distributed in the round are displayed on the top\n\nSuggestions have been provided for 0, 1 and 2 hands, since they are quite common. Do use them to save time\n\nThere is an option to edit a player's prediction, but this option stays valid only till the next player has not predicted, after which, it vanishes.\n\nIn case of fixed trumps, the trump for the round will be visible below the umber of cards to be distributed\n\nYou can view the scorecard at any time by pressing the scorecard icon on the toolbar\n\nYou can also restart the current round, start a new game, or exit the app from the toolbar menu.";

    @Override
    protected String description() {
        return description;
    }

    @Override
    protected String pageTitle() {
        return "Taking Bids";
    }
}
