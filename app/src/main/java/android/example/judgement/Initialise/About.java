package android.example.judgement.Initialise;

import android.example.judgement.Game.template.GameTemplate;
import android.example.judgement.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class About extends GameTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
