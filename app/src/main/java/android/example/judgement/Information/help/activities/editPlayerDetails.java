package android.example.judgement.Information.help.activities;

import android.example.judgement.Information.help.HelpTemplate;
import android.example.judgement.R;
import android.os.Bundle;

public class editPlayerDetails extends HelpTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Class prevActivity() {
        return viewingScorecard.class;
    }

    @Override
    protected Class nextActivity() {
        return editScore.class;
    }

    @Override
    protected int image() {
        return R.drawable.edit_player_details;
    }

    String description = "You can edit add, delete, rename, or change the places of players, by clicking on 'edit Player Details' in the Navigation Drawer\n\nYou can click on the floating button on the bottom right corner to add new players\n\nYou can use the 'pen icon' or the 'bin icon' next to a player's name to rename or delete the player respectively\n\nThe app expects the names of the players to be in the same order as they are sitting, in order to rotate the role of the dealer.\nHence to maintain this, you can use the arrow buttons to the left of a player's name to change his/her position";

    @Override
    protected String description() {
        return description;
    }

    @Override
    protected String pageTitle() {
        return "Adding, Removing, Renaming & Moving Players";
    }
}
