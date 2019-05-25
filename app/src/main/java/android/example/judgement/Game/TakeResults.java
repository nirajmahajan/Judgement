package android.example.judgement.Game;

import android.example.judgement.Game.template.GameTemplate;
import android.example.judgement.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TakeResults extends GameTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_results);
    }
}
