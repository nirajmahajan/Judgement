package android.example.judgement.Information.help.activities;

import android.example.judgement.Information.help.HelpTemplate;
import android.example.judgement.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class chooseMode extends HelpTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean hidePrevButton() {
        return true;
    }
}
