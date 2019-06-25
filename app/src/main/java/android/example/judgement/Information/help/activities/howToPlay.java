package android.example.judgement.Information.help.activities;

import android.example.judgement.Information.help.HelpTemplate;
import android.example.judgement.Initialise.TemplateActivity;
import android.example.judgement.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class howToPlay extends TemplateActivity {

    TextView tv_howToPlay;

    @Override
    protected int useMenu() {
        return R.menu.about_exit_done;
    }

    @Override
    protected String setTitle() {
        return "Help Centre";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        tv_howToPlay = findViewById(R.id.tv_help_how_to_play);
    }
}
