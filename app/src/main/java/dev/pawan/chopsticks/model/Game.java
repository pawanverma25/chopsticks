package dev.pawan.chopsticks.model;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import dev.pawan.chopsticks.R;
import dev.pawan.chopsticks.resource.HandResource;
import dev.pawan.chopsticks.resource.PlayerResource;
import dev.pawan.chopsticks.screen.GameOverScreen;
import dev.pawan.chopsticks.util.ChopSticksConstant;

import java.util.Random;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Game implements ChopSticksConstant {
    private Player player1;
    private Player player2;
    private Player winner;
    private boolean isSinglePlayer;
    private boolean isPlayer1Turn;
    private boolean isGameOver;
    private AlertDialog errorAlert;
    private AppCompatActivity context;
    private Intent currentIntent;
    private State state;


    public Game(AppCompatActivity context, Intent currentIntent) {
        this.context = context;
        this.currentIntent = currentIntent;
        this.setSinglePlayer(currentIntent.getBooleanExtra(ONE_PLAYER_MODE, false));
        HandResource player11Left = new HandResource(context.findViewById(R.id.p1lefthand), context.findViewById(R.id.p1LeftDot));
        HandResource player11Right = new HandResource(context.findViewById(R.id.p1righthand), context.findViewById(R.id.p1RightDot));
        PlayerResource player1Resource = new PlayerResource().setLeftHand(player11Left).setRightHand(player11Right).setTurnIndicator(context.findViewById(R.id.p1TurnImage)).setShuffleButton(context.findViewById(R.id.p1Shuffle)).setNameTextView(context.findViewById(R.id.player1));
        Player player1 = new Player(player1Resource, context);
        if (this.isSinglePlayer()) {
            player1.setName("You");
        } else {
            player1.setName("Player 1");
        }
        this.setPlayer1(player1);

        HandResource player22Left = new HandResource(context.findViewById(R.id.p2lefthand), context.findViewById(R.id.p2LeftDot));
        HandResource player22Right = new HandResource(context.findViewById(R.id.p2righthand), context.findViewById(R.id.p2RightDot));
        PlayerResource player2Resource = new PlayerResource().setLeftHand(player22Left).setRightHand(player22Right).setTurnIndicator(context.findViewById(R.id.p2TurnImage)).setShuffleButton(context.findViewById(R.id.p2Shuffle)).setNameTextView(context.findViewById(R.id.player2));
        Player player2 = new Player(player2Resource, context);
        if (this.isSinglePlayer()) {
            player2.setName("Computer");
            player2.setIsComputer(true);
        } else {
            player2.setName("Player 2");
        }
        this.setPlayer2(player2);
        this.setIsPlayer1Turn(new Random().nextBoolean());
        this.setState(new State(1,1,1,1,this.isPlayer1Turn));
        this.errorAlert = new AlertDialog.Builder(context)
                .setTitle("Shuffle Alert")
                .setMessage("What position do you want to shuffle to?")
                .setCancelable(true)
                .setPositiveButton("Ok", (dialog, id) -> {
                    dialog.dismiss();
                }).create();
    }
    public void playOneChance() {
        if (isGameOver) return;
        Player currentPlayer = isPlayer1Turn ? player1 : player2;
        Player opponentPlayer = isPlayer1Turn ? player2 : player1;
        if(currentPlayer.isHasShuffled() || opponentPlayer.isHasShuffled()) {
            currentPlayer.setHasShuffled(false);
            opponentPlayer.setHasShuffled(false);
            setState(new State(player1.getLeftHand().getFingers(), player1.getRightHand().getFingers(), player2.getLeftHand().getFingers(), player2.getRightHand().getFingers(), !isPlayer1Turn));
            return;
        }
        State nextState;

        if (currentPlayer.isComputer()) {
            ChopstickAI ai = new ChopstickAI();
            nextState = ai.chooseBestMove(this.state);
            this.setState(nextState);
        } else {
            Hand selectedHand = getSelectedHand(currentPlayer);
            Hand targetHand = getSelectedHand(opponentPlayer);
            if(targetHand == null || selectedHand == null) {
                Toast.makeText(context, "Please select the hands", Toast.LENGTH_SHORT).show();
                return;
            }
            int newFingers = (selectedHand.getFingers() + targetHand.getFingers()) % 5;
            targetHand.setFingers(newFingers);
            nextState = new State(player1.getLeftHand().getFingers(), player1.getRightHand().getFingers(), player2.getLeftHand().getFingers(), player2.getRightHand().getFingers(), !isPlayer1Turn);
            this.setState(nextState);
        }

        if (!opponentPlayer.getLeftHand().isAlive() && !opponentPlayer.getRightHand().isAlive()) {
            isGameOver = true;
            winner = currentPlayer;
            Intent gameOverIntent = new Intent(context, GameOverScreen.class);
            gameOverIntent.putExtra("resultText", "Congrats!!\n" + winner.getName() + " wins!");
            context.startActivity(gameOverIntent);
            context.finish();
        }
    }

    private Hand getSelectedHand(Player currentPlayer) {
        return currentPlayer.isLeftHandSelected() ? currentPlayer.getLeftHand() : currentPlayer.isRightHandSelected() ? currentPlayer.getRightHand() : null;
    }

    public void setIsPlayer1Turn(boolean isPlayer1Turn) {
        this.isPlayer1Turn = isPlayer1Turn;
        if (isPlayer1Turn) {
            player1.getTurnIndicator().setVisibility(android.view.View.VISIBLE);
            player2.getTurnIndicator().setVisibility(android.view.View.INVISIBLE);
            player1.getShuffleButton().setVisibility(android.view.View.VISIBLE);
            player2.getShuffleButton().setVisibility(android.view.View.INVISIBLE);
        } else {
            player1.getTurnIndicator().setVisibility(android.view.View.INVISIBLE);
            player2.getTurnIndicator().setVisibility(android.view.View.VISIBLE);
            player1.getShuffleButton().setVisibility(android.view.View.INVISIBLE);
            if(!player2.isComputer()) player2.getShuffleButton().setVisibility(android.view.View.VISIBLE);
        }
        player1.unselectHands();
        player2.unselectHands();
    }

    private void setState(State state) {
        this.state = state;
        this.player1.setFingers(state.getPlayer1LeftFingers(), state.getPlayer1RightFingers());
        this.player2.setFingers(state.getPlayer2LeftFingers(), state.getPlayer2RightFingers());
        this.setIsPlayer1Turn(state.isPlayer1Turn());
    }
}
