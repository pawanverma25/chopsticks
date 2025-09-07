package com.example.chopstick;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chopstick.screen.AboutScreen;
import com.example.chopstick.screen.PlayScreen;

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

        Intent i = new Intent(MainActivity.this, PlayScreen.class);
        Intent a = new Intent(MainActivity.this, AboutScreen.class);

        play.setOnClickListener(view -> startActivity(i));

        abt.setOnClickListener(view -> startActivity(a));

        quit.setOnClickListener(view -> {
            MainActivity.this.finish();
            System.exit(0);
        });

    }
}