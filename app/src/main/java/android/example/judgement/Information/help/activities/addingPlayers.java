package android.example.judgement.Information.help.activities;

import android.example.judgement.Information.help.HelpTemplate;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class addingPlayers extends HelpTemplate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Class prevActivity() {
        return chooseMode.class;
    }

    @Override
    protected Class nextActivity() {
        return settingDealer.class;
    }

    @Override
    protected int image() {
        return super.image();
    }

    @Override
    protected String description() {
        return super.description();
    }

    @Override
    protected String pageTitle() {
        return "Adding Players";
    }
}
