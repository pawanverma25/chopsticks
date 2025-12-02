package com.example.chopstick.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.window.OnBackInvokedDispatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chopstick.R;
import com.example.chopstick.model.Game;
import com.example.chopstick.model.Player;
import com.example.chopstick.resource.HandResource;
import com.example.chopstick.resource.PlayerResource;
import com.example.chopstick.util.ChopSticksConstant;

import java.util.Random;

public class GameOnScreen extends AppCompatActivity implements ChopSticksConstant {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chop_stick);
        Intent currentIntent = getIntent();

        // full screen
        getWindow().setFlags(WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS, WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS);

        Game game =new Game(this, currentIntent);
        ImageButton playButton = findViewById(R.id.Playbtn);

        playButton.setOnClickListener(view -> {
            game.playOneChance();
        });
    }

    @NonNull
    @Override
    public OnBackInvokedDispatcher getOnBackInvokedDispatcher() {
//        this.finish();
        return super.getOnBackInvokedDispatcher();
    }
}
