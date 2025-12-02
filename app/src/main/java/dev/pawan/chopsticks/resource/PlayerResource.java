package dev.pawan.chopsticks.resource;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResource {
    private HandResource leftHand;
    private HandResource rightHand;
    private ImageView turnIndicator;
    private ImageButton shuffleButton;
    private TextView nameTextView;

    public PlayerResource setLeftHand(HandResource leftHand) {
        this.leftHand = leftHand;
        return this;
    }

    public PlayerResource setRightHand(HandResource rightHand) {
        this.rightHand = rightHand;
        return this;
    }

    public PlayerResource setTurnIndicator(ImageView turnIndicator) {
        this.turnIndicator = turnIndicator;
        return this;
    }

    public PlayerResource setShuffleButton(ImageButton shuffleButton) {
        this.shuffleButton = shuffleButton;
        return this;
    }

    public PlayerResource setNameTextView(TextView nameTextView) {
        this.nameTextView = nameTextView;
        return this;
    }

}
