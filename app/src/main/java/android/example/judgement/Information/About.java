package android.example.judgement.Information;

import android.example.judgement.Game.template.GameTemplate;
import android.example.judgement.Initialise.TemplateActivity;
import android.example.judgement.R;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class About extends TemplateActivity {

    @Override
    protected int useMenu() {
        return R.menu.done;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
