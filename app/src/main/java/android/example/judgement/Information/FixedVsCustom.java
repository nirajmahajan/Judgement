package android.example.judgement.Information;

import android.example.judgement.Initialise.TemplateActivity;
import android.example.judgement.R;
import android.os.Bundle;

public class FixedVsCustom extends TemplateActivity {

    @Override
    protected int useMenu() {
        return R.menu.about_exit_done;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_vs_custom);
    }
}
