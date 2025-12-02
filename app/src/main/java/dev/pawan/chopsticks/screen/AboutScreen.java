package dev.pawan.chopsticks.screen;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import dev.pawan.chopsticks.R;

public class AboutScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getWindow().setFlags(WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS, WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS);

        TextView gameDesc = findViewById(R.id.game_desc);
        gameDesc.setMovementMethod(new ScrollingMovementMethod());

    }
}