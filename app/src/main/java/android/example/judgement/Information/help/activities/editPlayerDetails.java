package android.example.judgement.Information.help.activities;

import android.example.judgement.Information.help.HelpTemplate;
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
        return super.image();
    }

    @Override
    protected String description() {
        return super.description();
    }

    @Override
    protected String pageTitle() {
        return "Adding, Removing, Renaming & Moving Players";
    }
}