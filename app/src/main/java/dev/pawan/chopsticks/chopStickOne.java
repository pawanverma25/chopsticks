package dev.pawan.chopsticks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import dev.pawan.chopsticks.screen.GameOverScreen;
import dev.pawan.chopsticks.util.ChopSticksConstant;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class chopStickOne extends AppCompatActivity implements ChopSticksConstant {
    public int[] pLRcLR = new int[4];
    public boolean[] SelectedpLRcLR = new boolean[4];
    public boolean pTurn;
    public AtomicBoolean shuffled;
    public ImageView cpuTDot, pTDot, pRDot, pLDot, cpuLDot, cpuRDot;
    public ImageButton cpuLeft, cpuRight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chop_stick_one);

        // full screen
        getWindow().setFlags(WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS, WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS);

        //array initialisation
        Arrays.fill(SelectedpLRcLR, false);
        Arrays.fill(pLRcLR, 1);

        //initialization
        ImageButton pRight = findViewById(R.id.p1righthand);
        ImageButton pLeft = findViewById(R.id.p1lefthand);
        cpuLeft = findViewById(R.id.p2lefthand);
        cpuRight = findViewById(R.id.p2righthand);

        ImageButton pShuffle = findViewById(R.id.p1Shuffle);
        ImageButton playBtn = findViewById(R.id.Playbtn);

        pTDot = findViewById(R.id.p1TurnImage);
        cpuTDot = findViewById(R.id.p2TurnImage);

        pRDot = findViewById(R.id.p1RightDot);
        pLDot = findViewById(R.id.p1LeftDot);
        cpuLDot = findViewById(R.id.p2LeftDot);
        cpuRDot = findViewById(R.id.p2RightDot);

        int rand = new Random().nextInt(2);
        pTurn = rand == 0;
        if (pTurn) {
            pTDot.setVisibility(View.VISIBLE);
            cpuTDot.setVisibility(View.INVISIBLE);
        } else {
            pTDot.setVisibility(View.INVISIBLE);
            cpuTDot.setVisibility(View.VISIBLE);
        }

        Intent gameOverIntent = new Intent(chopStickOne.this, GameOverScreen.class);

        shuffled = new AtomicBoolean(false);
        AtomicBoolean alert_visible = new AtomicBoolean(false);


        // shuffle alert
        AlertDialog.Builder shuffle_alert = new AlertDialog.Builder(chopStickOne.this);
        shuffle_alert.setMessage("What position do you want to shuffle to?")
                .setCancelable(false)
                .setPositiveButton("2:2", (dialog, id) -> {
                    if (pTurn) {
                        pLRcLR[0] = 2;
                        pLRcLR[1] = 2;
                        pTurn = false;
                        pTDot.setVisibility(View.INVISIBLE);
                        cpuTDot.setVisibility(View.VISIBLE);
                        shuffled.set(false);
                        changeImage(pLeft, pLRcLR[0]);
                        changeImage(pRight, pLRcLR[1]);
                    }
                    alert_visible.set(false);
                })
                .setNegativeButton("1:3", (dialog, id) -> {
                    //  Action for '1:3' Button
                    if (pTurn) {
                        pLRcLR[0] = 1;
                        pLRcLR[1] = 3;
                        pTurn = false;
                        pTDot.setVisibility(View.INVISIBLE);
                        cpuTDot.setVisibility(View.VISIBLE);
                        shuffled.set(false);
                        changeImage(pLeft, pLRcLR[0]);
                        changeImage(pRight, pLRcLR[1]);
                    } else {
                        pLRcLR[2] = 1;
                        pLRcLR[3] = 3;
                        pTurn = true;
                        pTDot.setVisibility(View.VISIBLE);
                        cpuTDot.setVisibility(View.INVISIBLE);
                        shuffled.set(false);
                        changeImage(cpuLeft, pLRcLR[2]);
                        changeImage(cpuRight, pLRcLR[3]);
                    }
                    alert_visible.set(false);
                });


        pRight.setOnClickListener(view -> {
            if (pLRcLR[1] == 0 || (!pTurn)) return;

            if (SelectedpLRcLR[0]) {
                pLDot.setVisibility(View.INVISIBLE);
                SelectedpLRcLR[0] = false;
            }
            pRDot.setVisibility(View.VISIBLE);
            SelectedpLRcLR[1] = true;

        });


        cpuRight.setOnClickListener(view -> {
            if (pLRcLR[3] == 0 || (!pTurn)) return;
            if (SelectedpLRcLR[2]) {
                cpuLDot.setVisibility(View.INVISIBLE);
                SelectedpLRcLR[2] = false;
            }
            cpuRDot.setVisibility(View.VISIBLE);
            SelectedpLRcLR[3] = true;
        });

        pLeft.setOnClickListener(view -> {
            if (pLRcLR[0] == 0 || (!pTurn)) return;
            if (SelectedpLRcLR[1]) {

                pRDot.setVisibility(View.INVISIBLE);
                SelectedpLRcLR[1] = false;
            }
            pLDot.setVisibility(View.VISIBLE);
            SelectedpLRcLR[0] = true;
        });

        cpuLeft.setOnClickListener(view -> {
            if (pLRcLR[2] == 0 || (!pTurn)) return;
            if (SelectedpLRcLR[3]) {
                cpuRDot.setVisibility(View.INVISIBLE);
                SelectedpLRcLR[3] = false;
            }
            cpuLDot.setVisibility(View.VISIBLE);
            SelectedpLRcLR[2] = true;
        });

        // play button
        playBtn.setOnClickListener(view -> {
            if (pTurn) {
                if (!((SelectedpLRcLR[0] || SelectedpLRcLR[1]) && (SelectedpLRcLR[2] || SelectedpLRcLR[3])))
                    return;
                pTDot.setVisibility(View.INVISIBLE);
                cpuTDot.setVisibility(View.VISIBLE);
                if (SelectedpLRcLR[0]) {
                    if (SelectedpLRcLR[3]) {
                        pLRcLR[3] += pLRcLR[0];
                        if (pLRcLR[3] > 4) {
                            pLRcLR[3] -= 5;
                        }
                        changeImage(cpuRight, pLRcLR[3]);
                    } else {
                        pLRcLR[2] += pLRcLR[0];
                        if (pLRcLR[2] > 4) {
                            pLRcLR[2] -= 5;
                        }
                        changeImage(cpuLeft, pLRcLR[2]);
                    }
                } else if (SelectedpLRcLR[1]) {
                    if (SelectedpLRcLR[3]) {
                        pLRcLR[3] += pLRcLR[1];
                        if (pLRcLR[3] > 4) {
                            pLRcLR[3] -= 5;
                        }
                        changeImage(cpuRight, pLRcLR[3]);
                    } else {
                        pLRcLR[2] += pLRcLR[1];
                        if (pLRcLR[2] > 4) {
                            pLRcLR[2] -= 5;
                        }
                        changeImage(cpuLeft, pLRcLR[2]);
                    }
                }
                pTurn = false;
            } else {
                pTDot.setVisibility(View.VISIBLE);
                cpuTDot.setVisibility(View.INVISIBLE);
                selectForCPU();
                if (SelectedpLRcLR[2]) {
                    if (SelectedpLRcLR[0]) {
                        pLRcLR[0] += pLRcLR[2];
                        if (pLRcLR[0] > 4) {
                            pLRcLR[0] -= 5;
                        }
                        changeImage(pLeft, pLRcLR[0]);
                    } else {
                        pLRcLR[1] += pLRcLR[2];
                        if (pLRcLR[1] > 4) {
                            pLRcLR[1] -= 5;
                        }
                        changeImage(pRight, pLRcLR[1]);
                    }
                } else if (SelectedpLRcLR[3]) {
                    if (SelectedpLRcLR[0]) {
                        pLRcLR[0] += pLRcLR[3];
                        if (pLRcLR[0] > 4) {
                            pLRcLR[0] -= 5;
                        }
                        changeImage(pLeft, pLRcLR[0]);
                    } else {
                        pLRcLR[1] += pLRcLR[3];
                        if (pLRcLR[1] > 4) {
                            pLRcLR[1] -= 5;
                        }
                        changeImage(pRight, pLRcLR[1]);
                    }
                }
                pTurn = true;
            }
            cpuRDot.setVisibility(View.INVISIBLE);
            pLDot.setVisibility(View.INVISIBLE);
            cpuLDot.setVisibility(View.INVISIBLE);
            pRDot.setVisibility(View.INVISIBLE);
            Arrays.fill(SelectedpLRcLR, false);
            if (pLRcLR[0] == pLRcLR[1] && pLRcLR[0] == 0) {
                gameOverIntent.putExtra("resultText", RESULT_TEXT_CPU_WON);
                chopStickOne.this.finish();
            } else if (pLRcLR[2] == pLRcLR[3] && pLRcLR[2] == 0) {
                gameOverIntent.putExtra("resultText", RESULT_TEXT_PLAYER_WON);
                chopStickOne.this.finish();
            }
        });

        // player Shuffle Button
        pShuffle.setOnClickListener(view -> {
            if (pTurn) {
                if ((pLRcLR[0] == 0 && pLRcLR[1] == 2) || (pLRcLR[1] == 0 && pLRcLR[0] == 2)) {
                    pLRcLR[0] = 1;
                    pLRcLR[1] = 1;
                    shuffled.set(true);
                } else if ((pLRcLR[0] == 0 && pLRcLR[1] == 3) || (pLRcLR[1] == 0 && pLRcLR[0] == 3)) {
                    pLRcLR[0] = 1;
                    pLRcLR[1] = 2;
                    shuffled.set(true);
                } else if ((pLRcLR[0] == 0 && pLRcLR[1] == 4) || (pLRcLR[1] == 0 && pLRcLR[0] == 4)) {
                    AlertDialog alert = shuffle_alert.create();
                    alert_visible.set(true);
                    alert.setTitle("Shuffle Alert");
                    alert.show();
                    shuffled.set(true);
                } else if ((pLRcLR[0] == 1 && pLRcLR[1] == 3) || (pLRcLR[1] == 1 && pLRcLR[0] == 3)) {
                    pLRcLR[0] = 2;
                    pLRcLR[1] = 2;
                    shuffled.set(true);
                } else if (pLRcLR[0] == 2 && pLRcLR[1] == 2) {
                    pLRcLR[0] = 1;
                    pLRcLR[1] = 3;
                    shuffled.set(true);
                } else if ((pLRcLR[0] == 1 && pLRcLR[1] == 4) || (pLRcLR[1] == 1 && pLRcLR[0] == 4)) {
                    pLRcLR[0] = 2;
                    pLRcLR[1] = 3;
                    shuffled.set(true);
                } else if ((pLRcLR[0] == 2 && pLRcLR[1] == 3) || (pLRcLR[1] == 2 && pLRcLR[0] == 3)) {
                    pLRcLR[0] = 1;
                    pLRcLR[1] = 4;
                    shuffled.set(true);
                } else if (pLRcLR[0] == 3 && pLRcLR[1] == 3) {
                    pLRcLR[0] = 2;
                    pLRcLR[1] = 4;
                    shuffled.set(true);
                } else if ((pLRcLR[0] == 2 && pLRcLR[1] == 4) || (pLRcLR[1] == 2 && pLRcLR[0] == 4)) {
                    pLRcLR[0] = 3;
                    pLRcLR[1] = 3;
                    shuffled.set(true);
                }


                if (!shuffled.get()) return;
                if (alert_visible.get()) return;
                pTurn = false;
                pTDot.setVisibility(View.INVISIBLE);
                cpuTDot.setVisibility(View.VISIBLE);
                shuffled.set(false);
                changeImage(pLeft, pLRcLR[0]);
                changeImage(pRight, pLRcLR[1]);
                Arrays.fill(SelectedpLRcLR, false);
            }
        });
    }

    protected void cpuShuffle() {
        if (!pTurn) {
            if ((pLRcLR[2] == 0 && pLRcLR[3] == 2) || (pLRcLR[3] == 0 && pLRcLR[2] == 2)) {
                pLRcLR[2] = 1;
                pLRcLR[3] = 1;
                shuffled.set(true);
            } else if ((pLRcLR[2] == 0 && pLRcLR[3] == 3) || (pLRcLR[3] == 0 && pLRcLR[2] == 3)) {
                pLRcLR[2] = 1;
                pLRcLR[3] = 2;
                shuffled.set(true);
            } else if ((pLRcLR[2] == 0 && pLRcLR[3] == 4) || (pLRcLR[3] == 0 && pLRcLR[2] == 4)) {
                pLRcLR[2] = 2;
                pLRcLR[3] = 2;
                shuffled.set(true);
            } else if ((pLRcLR[2] == 1 && pLRcLR[3] == 3) || (pLRcLR[3] == 1 && pLRcLR[2] == 3)) {
                pLRcLR[2] = 2;
                pLRcLR[3] = 2;
                shuffled.set(true);
            } else if (pLRcLR[2] == 2 && pLRcLR[3] == 2) {
                pLRcLR[2] = 1;
                pLRcLR[3] = 3;
                shuffled.set(true);
            } else if ((pLRcLR[2] == 1 && pLRcLR[3] == 4) || (pLRcLR[3] == 1 && pLRcLR[2] == 4)) {
                pLRcLR[2] = 2;
                pLRcLR[3] = 3;
                shuffled.set(true);
            } else if ((pLRcLR[2] == 2 && pLRcLR[3] == 3) || (pLRcLR[3] == 2 && pLRcLR[2] == 3)) {
                pLRcLR[2] = 1;
                pLRcLR[3] = 4;
                shuffled.set(true);
            } else if (pLRcLR[2] == 3 && pLRcLR[3] == 3) {
                pLRcLR[2] = 2;
                pLRcLR[3] = 4;
                shuffled.set(true);
            } else if ((pLRcLR[2] == 2 && pLRcLR[3] == 4) || (pLRcLR[3] == 2 && pLRcLR[2] == 4)) {
                pLRcLR[2] = 3;
                pLRcLR[3] = 3;
                shuffled.set(true);
            }
            if (!shuffled.get()) return;
            pTurn = true;
            pTDot.setVisibility(View.VISIBLE);
            cpuTDot.setVisibility(View.INVISIBLE);
            changeImage(cpuLeft, pLRcLR[2]);
            changeImage(cpuRight, pLRcLR[3]);
        }

    }

    protected void selectForCPU() {
        //TODO: write algorithm for single player

        if (pLRcLR[2] == 0) {
            if (((2 * pLRcLR[3] + pLRcLR[0] == 5) && pLRcLR[1] != 0)) {
                SelectedpLRcLR[1] = true;
                SelectedpLRcLR[3] = true;
                return;
            } else if (((2 * pLRcLR[3] + pLRcLR[1] == 5) && pLRcLR[0] != 0)) {
                SelectedpLRcLR[0] = true;
                SelectedpLRcLR[3] = true;
                return;
            }
        } else if (pLRcLR[3] == 0) {
            if (((2 * pLRcLR[2] + pLRcLR[0] == 5) && pLRcLR[1] != 0)) {
                SelectedpLRcLR[1] = true;
                SelectedpLRcLR[2] = true;
                return;
            } else if (((2 * pLRcLR[2] + pLRcLR[1] == 5) && pLRcLR[0] != 0)) {
                SelectedpLRcLR[0] = true;
                SelectedpLRcLR[2] = true;
                return;
            }
        }


        if ((pLRcLR[0] + pLRcLR[2]) == 5) {
            SelectedpLRcLR[0] = true;
            SelectedpLRcLR[2] = true;
            return;
        } else if ((pLRcLR[1] + pLRcLR[2]) == 5) {
            SelectedpLRcLR[1] = true;
            SelectedpLRcLR[2] = true;
            return;
        } else if ((pLRcLR[0] + pLRcLR[3]) == 5) {
            SelectedpLRcLR[0] = true;
            SelectedpLRcLR[3] = true;
            return;
        } else if ((pLRcLR[1] + pLRcLR[3]) == 5) {
            SelectedpLRcLR[1] = true;
            SelectedpLRcLR[3] = true;
            return;
        }

        if ((pLRcLR[2] == 0) || (pLRcLR[3] == 0)) {
            cpuShuffle();
            if (shuffled.get()) {
                shuffled.set(false);
                return;
            }
        }

        int sRandom = new Random().nextInt(2);
        if (sRandom == 1) {
            cpuShuffle();
            if (shuffled.get()) {
                shuffled.set(false);
                return;
            }
        }

        int pRandom = new Random().nextInt(2);
        int cpuRandom = new Random().nextInt(2) + 2;

        if (pLRcLR[pRandom] != 0) {
            SelectedpLRcLR[pRandom] = true;
        } else {
            SelectedpLRcLR[1 - pRandom] = true;
        }
        if (pLRcLR[cpuRandom] != 0) {
            SelectedpLRcLR[cpuRandom] = true;
        } else {
            SelectedpLRcLR[5 - cpuRandom] = true;
        }

    }


    protected void changeImage(ImageButton imgBtn, int i) {
        switch (i) {
            case 1:
                imgBtn.setBackgroundResource(R.drawable.hand1);
                break;
            case 2:
                imgBtn.setBackgroundResource(R.drawable.hand2);
                break;
            case 3:
                imgBtn.setBackgroundResource(R.drawable.hand3);
                break;
            case 4:
                imgBtn.setBackgroundResource(R.drawable.hand4);
                break;
            default:
                imgBtn.setBackgroundResource(R.drawable.hand0);
                break;
        }
    }
}