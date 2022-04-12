package com.example.chopstick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;

public class playScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageButton onePlayerButton = findViewById(R.id.onepbutton);
        ImageButton twoPlayerButton = findViewById(R.id.twopButton);
        onePlayerButton.setOnClickListener(view -> {
            Intent iChopOne =new Intent(playScreen.this, chopStickOne.class);
            startActivity(iChopOne);
            playScreen.this.finish();
        });

        twoPlayerButton.setOnClickListener(view -> {
            Intent iChop=new Intent(playScreen.this, chopStick.class);
            startActivity(iChop);
            playScreen.this.finish();
        });
    }
}