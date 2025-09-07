package com.example.chopstick.model;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chopstick.resource.PlayerResource;
import com.example.chopstick.screen.AboutScreen;

import lombok.Data;

@Data
public class Player extends AppCompatActivity {
    private Hand leftHand;
    private Hand rightHand;
    private boolean isTurn;
    private boolean isWinner;
    private PlayerResource resource;

}
