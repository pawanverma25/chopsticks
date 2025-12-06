package dev.pawan.chopsticks.model;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import dev.pawan.chopsticks.resource.PlayerResource;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class Player {
    private String name;
    private Hand leftHand;
    private Hand rightHand;
    private boolean isComputer;
    private ImageButton shuffleButton;
    private ImageView turnIndicator;
    private PlayerResource resource;
    private TextView nameTextView;
    private AlertDialog alertDialogShuffle;
    private boolean isAlertVisible;
    private boolean hasShuffled;
    private boolean isLeftHandSelected;
    private boolean isRightHandSelected;

    public Player(PlayerResource resource, Context context) {
        super();
        this.resource = resource;
        this.leftHand = new Hand(resource.getLeftHand());
        this.rightHand = new Hand(resource.getRightHand());
        this.turnIndicator = resource.getTurnIndicator();
        this.shuffleButton = resource.getShuffleButton();
        this.nameTextView = resource.getNameTextView();
        this.isAlertVisible = false;
        this.nameTextView.setText(this.name);
        this.alertDialogShuffle = new AlertDialog.Builder(context)
                .setTitle("Shuffle Alert")
                .setMessage("What position do you want to shuffle to?")
                .setCancelable(false)
                .setPositiveButton("2:2", (dialog, id) -> {
                    this.setFingers(2 ,2);
                    Toast.makeText(context, "Shuffled to 2:2", Toast.LENGTH_SHORT).show();
                    this.setAlertVisible(false);
                })
                .setNegativeButton("1:3", (dialog, id) -> {
                    this.setFingers(1 ,3);
                    Toast.makeText(context, "Shuffled to 1:3", Toast.LENGTH_SHORT).show();
                    this.setAlertVisible(false);
                }).create();
        this.shuffleButton.setOnClickListener(view -> {
            if(this.isAlertVisible) return;
            int sum = this.leftHand.getFingers() + this.rightHand.getFingers();
            if (sum == 4) {
                this.alertDialogShuffle.show();
                this.setAlertVisible(true);
                this.hasShuffled = true;
            } else {
                for(int i = 1; i < sum; i++){
                    if(i == this.leftHand.getFingers() || i == this.rightHand.getFingers() || i >= 5 || sum - i >= 5) continue;
                    else {
                        this.setFingers(i, sum - i);
                        this.hasShuffled = true;
                        Toast.makeText(context, "Shuffled to " + i + ":" + (sum - i), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if(!this.hasShuffled)
                    Toast.makeText(context, "Not a valid move", Toast.LENGTH_SHORT).show();
            }
        });
        this.leftHand.getButton().setOnClickListener(view -> {
            if(!this.leftHand.isAlive()) return;
            this.setLeftHandSelected(!this.isLeftHandSelected);
        });
        this.rightHand.getButton().setOnClickListener(view -> {
            if(!this.rightHand.isAlive()) return;
            this.setRightHandSelected(!this.isRightHandSelected);
        });
        this.isComputer = false;
    }

    public void setLeftHandSelected(boolean isLeftHandSelected) {
        this.isLeftHandSelected = isLeftHandSelected;
        if (isLeftHandSelected) {
            this.leftHand.getDot().setVisibility(View.VISIBLE);
            this.rightHand.setSelected(false);
            this.rightHand.getDot().setVisibility(View.INVISIBLE);
            this.isRightHandSelected = false;
        } else {
            this.leftHand.getDot().setVisibility(View.INVISIBLE);
        }
    }

    public void setRightHandSelected(boolean isRightHandSelected) {
        this.isRightHandSelected = isRightHandSelected;
        if (isRightHandSelected) {
            this.rightHand.getDot().setVisibility(View.VISIBLE);
            this.leftHand.setSelected(false);
            this.leftHand.getDot().setVisibility(View.INVISIBLE);
            this.isLeftHandSelected = false;
        } else {
            this.rightHand.getDot().setVisibility(View.INVISIBLE);
        }
    }

    public void setFingers(int leftHandFingers, int rightHandFingers){
        this.leftHand.setFingers(leftHandFingers);
        this.rightHand.setFingers(rightHandFingers);
    }

    public void setName(String name) {
        this.name = name;
        if(name != null && name.equals("Computer")) {
            this.isComputer = true;
            this.nameTextView.setRotationY(180f);
            this.nameTextView.setRotationX(180f);

        }
        this.nameTextView.setText(name);
    }

    public void unselectHands() {
        this.isLeftHandSelected = false;
        this.isRightHandSelected = false;
        this.leftHand.getDot().setVisibility(View.INVISIBLE);
        this.rightHand.getDot().setVisibility(View.INVISIBLE);
    }

    public void setIsComputer(boolean isComputer){
        this.isComputer = isComputer;
        if(isComputer && this.shuffleButton != null) this.shuffleButton.setVisibility(View.INVISIBLE);
    }
}
