package dev.pawan.chopsticks.model;

import java.util.*;

public class ChopstickAI {

    private final Map<State, Integer> memo = new HashMap<>();

    private int aiDifficulty = 20;

    public Set<State> generateAllPossibleNextStates(State s){
        Set<State> result = new HashSet<>();
        if(s.isTerminal() != 0) return result;
        //case1 l -> l
        if(s.isPlayer1Turn && Math.min(s.player1LeftFingers, s.player2LeftFingers) > 0) result.add(new State(s.player1LeftFingers, s.player1RightFingers, s.player2LeftFingers + s.player1LeftFingers, s.player2RightFingers, false));
        if(!s.isPlayer1Turn && Math.min(s.player2LeftFingers, s.player1LeftFingers) > 0) result.add(new State(s.player1LeftFingers + s.player2LeftFingers, s.player1RightFingers, s.player2LeftFingers, s.player2RightFingers, true));
        //case2 l -> r
        if(s.isPlayer1Turn && Math.min(s.player1LeftFingers, s.player2RightFingers) > 0) result.add(new State(s.player1LeftFingers, s.player1RightFingers, s.player2LeftFingers, s.player2RightFingers + s.player1LeftFingers, false));
        if(!s.isPlayer1Turn && Math.min(s.player2LeftFingers, s.player1RightFingers) > 0) result.add(new State(s.player1LeftFingers, s.player1RightFingers + s.player2LeftFingers, s.player2LeftFingers, s.player2RightFingers, true));
        //case3 r -> l
        if(s.isPlayer1Turn && Math.min(s.player1RightFingers, s.player2LeftFingers) > 0) result.add(new State(s.player1LeftFingers, s.player1RightFingers, s.player2LeftFingers + s.player1RightFingers, s.player2RightFingers, false));
        if(!s.isPlayer1Turn && Math.min(s.player2RightFingers, s.player1LeftFingers) > 0) result.add(new State(s.player1LeftFingers + s.player2RightFingers, s.player1RightFingers, s.player2LeftFingers, s.player2RightFingers, true));
        //case4 r -> r
        if(s.isPlayer1Turn && Math.min(s.player1RightFingers, s.player2RightFingers) > 0) result.add(new State(s.player1LeftFingers, s.player1RightFingers, s.player2LeftFingers, s.player2RightFingers + s.player1RightFingers, false));
        if(!s.isPlayer1Turn && Math.min(s.player2RightFingers, s.player1RightFingers) > 0) result.add(new State(s.player1LeftFingers, s.player1RightFingers + s.player2RightFingers, s.player2LeftFingers, s.player2RightFingers, true));
        //case6 shuffle
        if(s.isPlayer1Turn) {
            int total = s.player1LeftFingers + s.player1RightFingers;
            for(int i = 1; i < total; i++) {
                if(i != s.player1LeftFingers && i != s.player1RightFingers && i < 5 && (total - i) < 5) result.add(new State(i, total - i, s.player2LeftFingers, s.player2RightFingers, false));
            }
        } else {
            int total = s.player2LeftFingers + s.player2RightFingers;
            for(int i = 1; i < total; i++) {
                if(i != s.player2LeftFingers && i != s.player2RightFingers && i < 5 && (total - i) < 5) result.add(new State(s.player1LeftFingers, s.player1RightFingers, i, total - i, true));
            }
        }

        return result;
    }

    private Integer solve(final State s, int currentDepth) {
        if(currentDepth > aiDifficulty) return 0;
        if(memo.containsKey(s)) return memo.get(s);
        int result = s.isTerminal();
        if(result != 0){
            memo.put(s, result);
            return result;
        }

        Set<State> moves = generateAllPossibleNextStates(s);
        int best = -2;

        for(State nextState : moves){
            int v = -solve(nextState, currentDepth + 1);
            if(v > best) {
                best = v;
                if(best == 1){
                    break;
                }
            }
        }
        if(best == -2) best = -1;
        memo.put(s, best);
        return best;
    }

    public State chooseBestMove (final State s){
        Set<State> moves = generateAllPossibleNextStates(s);
        this.aiDifficulty = (int)Math.abs(Math.random() * 100);
        int bestValue = -2;
        State bestMove = null;
        for(State move : moves){
            int v = -solve(move,1);
            if(v > bestValue){
                bestValue = v;
                bestMove = move;
            }
        }
        return bestMove;
    }

}
