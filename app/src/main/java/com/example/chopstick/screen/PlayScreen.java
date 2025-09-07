package com.example.chopstick.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chopstick.R;
import com.example.chopstick.chopStick;
import com.example.chopstick.chopStickOne;

public class PlayScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageButton onePlayerButton = findViewById(R.id.onepbutton);
        ImageButton twoPlayerButton = findViewById(R.id.twopButton);
        onePlayerButton.setOnClickListener(view -> {
            Intent iChopOne = new Intent(PlayScreen.this, chopStickOne.class);
            startActivity(iChopOne);
            PlayScreen.this.finish();
        });

        twoPlayerButton.setOnClickListener(view -> {
            Intent iChop = new Intent(PlayScreen.this, chopStick.class);
            startActivity(iChop);
            PlayScreen.this.finish();
        });
    }
}