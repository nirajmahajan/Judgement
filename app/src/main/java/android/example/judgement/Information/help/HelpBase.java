package android.example.judgement.Information.help;

import android.example.judgement.Initialise.TemplateActivity;
import android.example.judgement.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
}
