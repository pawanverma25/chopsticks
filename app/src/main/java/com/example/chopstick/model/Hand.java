package com.example.chopstick.model;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chopstick.resource.HandResource;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Pawan Verma
 * @since chopstick@v2.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Hand extends AppCompatActivity {
    private ImageButton button;
    private ImageView dot;
    private boolean isSelected;
    private int fingers;
    private boolean isAlive;
    private HandResource resource;

    public Hand(HandResource resource) {
        super();
        this.resource = resource;
        this.button = findViewById(resource.getButton());
        this.fingers = 1;
        this.isAlive = true;
        this.isSelected = false;
        this.button.setBackgroundResource(HandResource.HAND_1);
        this.dot = findViewById(resource.getDot());
        this.button.setOnClickListener(view -> {
            this.isSelected = !this.isSelected;
            if (this.isSelected) this.dot.setVisibility(View.VISIBLE);
            else this.dot.setVisibility(View.INVISIBLE);
        });
    }

    public void setFingers(int fingers) {
        this.fingers = fingers % 5;
        this.isAlive = fingers > 0;
        switch (fingers) {
            case 1:
                button.setBackgroundResource(HandResource.HAND_1);
                break;
            case 2:
                button.setBackgroundResource(HandResource.HAND_2);
                break;
            case 3:
                button.setBackgroundResource(HandResource.HAND_3);
                break;
            case 4:
                button.setBackgroundResource(HandResource.HAND_4);
                break;
            default:
                button.setBackgroundResource(HandResource.HAND_0);
                break;
        }
    }

}
