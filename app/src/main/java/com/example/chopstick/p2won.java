package com.example.chopstick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

public class p2won extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2won);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent restart = new Intent(p2won.this, chopStick.class);

        Button h = findViewById(R.id.homeBtn);
        Button r = findViewById(R.id.restartBtn);

        h.setOnClickListener(view -> p2won.this.finish());

        r.setOnClickListener(view -> {
            startActivity(restart);
            p2won.this.finish();
        });
    }
}