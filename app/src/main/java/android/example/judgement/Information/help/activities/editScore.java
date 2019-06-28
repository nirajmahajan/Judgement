package android.example.judgement.Information.help.activities;

import android.example.judgement.Information.help.HelpTemplate;
import android.example.judgement.R;
import android.os.Bundle;

public class editScore extends HelpTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Class prevActivity() {
        return editPlayerDetails.class;
    }

    @Override
    protected Class nextActivity() {
        return viewingLogs.class;
    }

    @Override
    protected int image() {
        return R.drawable.edit_score;
    }

    String description = "You can edit a player's score but clicking on the 'edit Player Score' in the Navigation Drawer.\n\nPlease note that this option is not to be misused as all the changes done here will be logged and can be checked by anyone by simply accessing the change logs, which are not editable\n\n\n\n\n\n";

    @Override
    protected String description() {
        return description;
    }

    @Override
    protected String pageTitle() {
        return "Editing a Player's Score";
    }
}
