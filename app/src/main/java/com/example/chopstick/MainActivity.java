package com.example.chopstick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // for Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button play = findViewById(R.id.startplayBtn);
        Button abt = findViewById(R.id.startaboutBtn);
        Button quit = findViewById(R.id.quitBtn);

        Intent i = new Intent(MainActivity.this, playScreen.class);
        Intent a = new Intent(MainActivity.this, about.class);

        play.setOnClickListener(view -> startActivity(i));

        abt.setOnClickListener(view -> startActivity(a));

        quit.setOnClickListener(view -> {
            MainActivity.this.finish();
            System.exit(0);
        });

    }
}