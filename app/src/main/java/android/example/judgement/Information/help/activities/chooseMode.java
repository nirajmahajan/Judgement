package android.example.judgement.Information.help.activities;

import android.example.judgement.Information.help.HelpTemplate;
import android.example.judgement.R;
import android.os.Bundle;

public class chooseMode extends HelpTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Class nextActivity() {
        return addingPlayers.class;
    }

    @Override
    protected boolean hidePrevButton() {
        return true;
    }

    @Override
    protected int image() {
        return R.drawable.choose_mode;
    }

    @Override
    protected int backgroundColor() {
        return R.color.cyan;
    }

    String description = "Select a mode from the given options.\nClick on 'Need Help' for further details\nCustom Trumps is the default selected mode";

    @Override
    protected String description() {
        return description;
    }

    @Override
    protected String pageTitle() {
        return "Choosing a mode";
    }
}
