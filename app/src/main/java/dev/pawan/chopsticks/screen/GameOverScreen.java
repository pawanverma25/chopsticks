package dev.pawan.chopsticks.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import dev.pawan.chopsticks.R;
import dev.pawan.chopsticks.chopStick;

public class GameOverScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        getWindow().setFlags(WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS, WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS);

        Intent restart = new Intent(this, chopStick.class);
        TextView resultTextView = findViewById(R.id.resultText);
        String resultText = getIntent().getStringExtra("resultText");
        if (resultText != null) {
            resultTextView.setText(resultText);
        }

        Button h = findViewById(R.id.homeBtn);
        Button r = findViewById(R.id.restartBtn);

        h.setOnClickListener(view -> this.finish());

        r.setOnClickListener(view -> {
            startActivity(restart);
            this.finish();
        });

    }
}
