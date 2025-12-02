package dev.pawan.chopsticks.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import dev.pawan.chopsticks.R;
import dev.pawan.chopsticks.util.ChopSticksConstant;

public class PlayScreen extends AppCompatActivity implements ChopSticksConstant {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);


        getWindow().setFlags(WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS, WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS);

        ImageButton onePlayerButton = findViewById(R.id.onepbutton);
        ImageButton twoPlayerButton = findViewById(R.id.twopButton);
        onePlayerButton.setOnClickListener(view -> {
            Intent gameScreen = new Intent(PlayScreen.this, GameOnScreen.class);
            gameScreen.putExtra(ONE_PLAYER_MODE, true);
            startActivity(gameScreen);
            PlayScreen.this.finish();
        });

        twoPlayerButton.setOnClickListener(view -> {
            Intent gameScreen = new Intent(PlayScreen.this, GameOnScreen.class);
            gameScreen.putExtra(ONE_PLAYER_MODE, false);
            startActivity(gameScreen);
            PlayScreen.this.finish();
        });

    }
}