package com.example.chopstick.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChopstickAI {
    private final Map<State, Boolean> memo = new HashMap<>();
    public boolean isWinning(State s, int depth) {
        if (depth == 0) return false; // limit for recursion, not game-theoretic but practical

        // If opponent is dead (after move), current player wins
        if (s.player2LeftFingers == 0 && s.player2RightFingers == 0) return true;
        // If current player is dead, they lose
        if (s.player1LeftFingers == 0 && s.player1RightFingers == 0) return false;

        if (memo.containsKey(s)) return Boolean.TRUE.equals(memo.get(s));

        for (State next : generateMoves(s)) {
            State flipped = next.flipPlayers();
            if (!isWinning(flipped, depth - 1)) {
                memo.put(s, true);
                return true;
            }
        }

        memo.put(s, false);
        return false;
    }

    private List<State> generateMoves(State s) {
        List<State> moves = new ArrayList<>();

        int[] myHands = {s.player1LeftFingers, s.player1RightFingers};
        int[] oppHands = {s.player2LeftFingers, s.player2RightFingers};

        for (int mh : myHands) {
            if (mh == 0) continue;
            for (int i = 0; i < 2; i++) {
                if (oppHands[i] == 0) continue;
                int[] newOpp = oppHands.clone();
                newOpp[i] = (newOpp[i] + mh) % 5;
                moves.add(new State(newOpp[0], newOpp[1], s.player1LeftFingers, s.player1RightFingers, true));
            }
        }

        return moves;
    }
    public State bestMove(State current) {
        for (State next : generateMoves(current)) {
            if (isWinning(next, 5)) {
                return next;
            }
        }
        List<State> moves = generateMoves(current);
        return moves.isEmpty() ? null : moves.get(0);
    }
}

