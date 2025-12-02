package com.example.chopstick.model;

import androidx.annotation.NonNull;

import java.util.Objects;

import lombok.Data;

@Data
public class State {
    int player1LeftFingers, player1RightFingers, player2LeftFingers, player2RightFingers;
    boolean isPlayer1Turn;

    State(int player1LeftFingers, int player1RightFingers, int player2LeftFingers, int player2RightFingers, boolean isPlayer1Turn) {
        this.player1LeftFingers = normalize(player1LeftFingers);
        this.player1RightFingers = normalize(player1RightFingers);
        this.player2LeftFingers = normalize(player2LeftFingers);
        this.player2RightFingers = normalize(player2RightFingers);
        this.isPlayer1Turn = isPlayer1Turn;
    }

    private int normalize(int v) {
        return v % 5; // values wrap around modulo 5
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof State state)) return false;
        return player1LeftFingers == state.player1LeftFingers && player1RightFingers == state.player1RightFingers &&
                player2LeftFingers == state.player2LeftFingers && player2RightFingers == state.player2RightFingers &&
                isPlayer1Turn == state.isPlayer1Turn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player1LeftFingers, player1RightFingers, player2LeftFingers, player2RightFingers, isPlayer1Turn);
    }

    @NonNull
    @Override
    public String toString() {
        return (isPlayer1Turn ? "My turn" : "Opp turn") +
                " | Me: (" + player1LeftFingers + "," + player1RightFingers + ")" +
                " Opp: (" + player2LeftFingers + "," + player2RightFingers + ")";
    }

    public State flipPlayers() {
        return new State(player2LeftFingers, player2RightFingers, player1LeftFingers, player1RightFingers, !isPlayer1Turn);
    }
}