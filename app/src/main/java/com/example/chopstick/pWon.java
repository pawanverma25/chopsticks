package com.example.chopstick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

public class pWon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwon);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent restart = new Intent(pWon.this, chopStickOne.class);

        Button h = findViewById(R.id.homeBtn);
        Button r = findViewById(R.id.restartBtn);

        h.setOnClickListener(view -> pWon.this.finish());

        r.setOnClickListener(view -> {
            startActivity(restart);
            pWon.this.finish();
        });
    }
}