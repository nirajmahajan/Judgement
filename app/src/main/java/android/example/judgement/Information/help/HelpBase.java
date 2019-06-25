package android.example.judgement.Information.help;

import android.example.judgement.Initialise.TemplateActivity;
import android.example.judgement.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HelpBase extends TemplateActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_base);
    }

    @Override
    protected int useMenu() {
        return R.menu.about_exit_done;
    }

    public void howToPlay(View view) {

    }

    public void mode(View view) {

    }

    public void initPlayer(View view) {

    }

    public void initDealer(View view) {

    }

    public void takeHands(View view) {

    }

    public void takeResults(View view) {

    }

    public void scorecard(View view) {

    }

    public void editPlayer(View view) {

    }

    public void editScore(View view) {

    }

    public void logs(View view) {

    }

    public void finalMove(View view) {

    }
}
