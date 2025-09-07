package com.example.chopstick.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chopstick.R;
import com.example.chopstick.chopStick;

public class GameOverScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
