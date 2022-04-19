package com.example.chopstick;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class chopStick extends AppCompatActivity {
    public int[] p1LRp2LR = new int[4];
    public boolean[] Selectedp1LRp2LR = new boolean[4];
    public boolean p1Turn = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chop_stick);

        // full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //array initialisation
        Arrays.fill(Selectedp1LRp2LR, false);
        Arrays.fill(p1LRp2LR, 1);

        // initiation of all ImageButtons, Activities and Dots
        ImageButton p1Right = findViewById(R.id.p1righthand);
        ImageButton p1Left = findViewById(R.id.p1lefthand);
        ImageButton p2Right = findViewById(R.id.p2righthand);
        ImageButton p2Left = findViewById(R.id.p2lefthand);
        ImageButton playBtn = findViewById(R.id.Playbtn);
        ImageButton p1Shuffle = findViewById(R.id.p1Shuffle);
        ImageButton p2Shuffle = findViewById(R.id.p2Shuffle);

        Intent p1=new Intent(chopStick.this, p1Won.class);
        Intent p2=new Intent(chopStick.this, p2won.class);

        ImageView p1RDot = findViewById(R.id.p1RightDot);
        ImageView p1LDot = findViewById(R.id.p1LeftDot);
        ImageView p2RDot = findViewById(R.id.p2RightDot);
        ImageView p2LDot = findViewById(R.id.p2LeftDot);
        ImageView p1TDot = findViewById(R.id.p1TurnImage);
        ImageView p2TDot = findViewById(R.id.p2TurnImage);

        AtomicBoolean alert_visible= new AtomicBoolean(false);
        AtomicBoolean shuffled = new AtomicBoolean(false);

        AlertDialog.Builder shuffle_alert = new AlertDialog.Builder(chopStick.this);
        shuffle_alert.setMessage("What position do you want to shuffle to?")
                .setCancelable(false)
                .setPositiveButton("2:2", (dialog, id) -> {
                    if(p1Turn){
                        p1LRp2LR[0]=2;
                        p1LRp2LR[1]=2;
                        p1Turn = false;
                        p1TDot.setVisibility(View.INVISIBLE);
                        p2TDot.setVisibility(View.VISIBLE);
                        shuffled.set(false);
                        changeImage(p1Left, p1LRp2LR[0]);
                        changeImage(p1Right, p1LRp2LR[1]);
                    } else {
                        p1LRp2LR[2]=2;
                        p1LRp2LR[3]=2;
                        p1Turn=true;
                        p1TDot.setVisibility(View.VISIBLE);
                        p2TDot.setVisibility(View.INVISIBLE);
                        shuffled.set(false);
                        changeImage(p2Left, p1LRp2LR[2]);
                        changeImage(p2Right, p1LRp2LR[3]);
                    }
                    alert_visible.set(false);
                })
                .setNegativeButton("1:3", (dialog, id) -> {
                    //  Action for '1:3' Button
                    if(p1Turn){
                        p1LRp2LR[0]=1;
                        p1LRp2LR[1]=3;
                        p1Turn = false;
                        p1TDot.setVisibility(View.INVISIBLE);
                        p2TDot.setVisibility(View.VISIBLE);
                        shuffled.set(false);
                        changeImage(p1Left, p1LRp2LR[0]);
                        changeImage(p1Right, p1LRp2LR[1]);
                    } else {
                        p1LRp2LR[2]=1;
                        p1LRp2LR[3]=3;
                        p1Turn=true;
                        p1TDot.setVisibility(View.VISIBLE);
                        p2TDot.setVisibility(View.INVISIBLE);
                        shuffled.set(false);
                        changeImage(p2Left, p1LRp2LR[2]);
                        changeImage(p2Right, p1LRp2LR[3]);
                    }
                    alert_visible.set(false);
                });

        p1Right.setOnClickListener(view -> {
            if(p1LRp2LR[1]==0) return;
            if(Selectedp1LRp2LR[0]){
                p1LDot.setVisibility(View.INVISIBLE);
                Selectedp1LRp2LR[0]=false;
            }
            p1RDot.setVisibility(View.VISIBLE);
            Selectedp1LRp2LR[1] = true;

        });


        p2Right.setOnClickListener(view -> {
            if(p1LRp2LR[3]==0) return;
            if (Selectedp1LRp2LR[2]) {
                p2LDot.setVisibility(View.INVISIBLE);
                Selectedp1LRp2LR[2] = false;
            }
            p2RDot.setVisibility(View.VISIBLE);
            Selectedp1LRp2LR[3] = true;
        });

        p1Left.setOnClickListener(view -> {
            if(p1LRp2LR[0]==0) return;
            if(Selectedp1LRp2LR[1]){

                p1RDot.setVisibility(View.INVISIBLE);
                Selectedp1LRp2LR[1]=false;
            }
            p1LDot.setVisibility(View.VISIBLE);
            Selectedp1LRp2LR[0]=true;
        });

        p2Left.setOnClickListener(view -> {
            if(p1LRp2LR[2]==0) return;
            if(Selectedp1LRp2LR[3]){
                p2RDot.setVisibility(View.INVISIBLE);
                Selectedp1LRp2LR[3]=false;
            }
            p2LDot.setVisibility(View.VISIBLE);
            Selectedp1LRp2LR[2]=true;
        });

        // play button
        playBtn.setOnClickListener(view -> {
            if(!((Selectedp1LRp2LR[0]||Selectedp1LRp2LR[1])&&(Selectedp1LRp2LR[2]||Selectedp1LRp2LR[3]))) return;
            if(p1Turn){
                p1TDot.setVisibility(View.INVISIBLE);
                p2TDot.setVisibility(View.VISIBLE);
                if(Selectedp1LRp2LR[0]){
                    if(Selectedp1LRp2LR[3]){
                        p1LRp2LR[3]+=p1LRp2LR[0];
                        if(p1LRp2LR[3]>4){
                            p1LRp2LR[3]-=5;
                        }
                        changeImage(p2Right,p1LRp2LR[3]);
                    }else{
                        p1LRp2LR[2]+=p1LRp2LR[0];
                        if(p1LRp2LR[2]>4){
                            p1LRp2LR[2]-=5;
                        }
                        changeImage(p2Left,p1LRp2LR[2]);
                    }
                } else if (Selectedp1LRp2LR[1]){
                    if(Selectedp1LRp2LR[3]){
                        p1LRp2LR[3]+=p1LRp2LR[1];
                        if(p1LRp2LR[3]>4){
                            p1LRp2LR[3]-=5;
                        }
                        changeImage(p2Right,p1LRp2LR[3]);
                    }else{
                        p1LRp2LR[2]+=p1LRp2LR[1];
                        if(p1LRp2LR[2]>4){
                            p1LRp2LR[2]-=5;
                        }
                        changeImage(p2Left,p1LRp2LR[2]);
                    }
                }
                p1Turn=false;
            } else {
                p1TDot.setVisibility(View.VISIBLE);
                p2TDot.setVisibility(View.INVISIBLE);
                if(Selectedp1LRp2LR[2]){
                    if(Selectedp1LRp2LR[0]){
                        p1LRp2LR[0]+=p1LRp2LR[2];
                        if(p1LRp2LR[0]>4){
                            p1LRp2LR[0]-=5;
                        }
                        changeImage(p1Left,p1LRp2LR[0]);
                    }else{
                        p1LRp2LR[1]+=p1LRp2LR[2];
                        if(p1LRp2LR[1]>4){
                            p1LRp2LR[1]-=5;
                        }
                        changeImage(p1Right,p1LRp2LR[1]);
                    }
                } else if (Selectedp1LRp2LR[3]){
                    if(Selectedp1LRp2LR[0]){
                        p1LRp2LR[0]+=p1LRp2LR[3];
                        if(p1LRp2LR[0]>4){
                            p1LRp2LR[0]-=5;
                        }
                        changeImage(p1Left,p1LRp2LR[0]);
                    }else{
                        p1LRp2LR[1]+=p1LRp2LR[3];
                        if(p1LRp2LR[1]>4){
                            p1LRp2LR[1]-=5;
                        }
                        changeImage(p1Right,p1LRp2LR[1]);
                    }
                }
                p1Turn=true;
            }
            Arrays.fill(Selectedp1LRp2LR, false);
            p1RDot.setVisibility(View.INVISIBLE);
            p2LDot.setVisibility(View.INVISIBLE);
            p1LDot.setVisibility(View.INVISIBLE);
            p2RDot.setVisibility(View.INVISIBLE);
            if(p1LRp2LR[0]==p1LRp2LR[1] && p1LRp2LR[0]==0){
                startActivity(p2);
                chopStick.this.finish();
            } else if (p1LRp2LR[2]==p1LRp2LR[3] && p1LRp2LR[2]==0) {
                startActivity(p1);
                chopStick.this.finish();
            }
        });

        // p1 Shuffle Button

        p1Shuffle.setOnClickListener(view -> {
            if (p1Turn){
                if ((p1LRp2LR[0]==0 && p1LRp2LR[1]==2) || (p1LRp2LR[1]==0 && p1LRp2LR[0]==2)) {
                    p1LRp2LR[0]=1;
                    p1LRp2LR[1]=1;
                    shuffled.set(true);
                }
                else if ((p1LRp2LR[0]==0 && p1LRp2LR[1]==3) || (p1LRp2LR[1]==0 && p1LRp2LR[0]==3)) {
                    p1LRp2LR[0]=1;
                    p1LRp2LR[1]=2;
                    shuffled.set(true);
                }
                else if ((p1LRp2LR[0]==0 && p1LRp2LR[1]==4) || (p1LRp2LR[1]==0 && p1LRp2LR[0]==4)) {
                    AlertDialog alert = shuffle_alert.create();
                    alert_visible.set(true);
                    alert.setTitle("Shuffle Alert");
                    alert.show();
                    shuffled.set(true);
                }
                else if ((p1LRp2LR[0]==1 && p1LRp2LR[1]==3) || (p1LRp2LR[1]==1 && p1LRp2LR[0]==3)) {
                    p1LRp2LR[0]=2;
                    p1LRp2LR[1]=2;
                    shuffled.set(true);
                }
                else if (p1LRp2LR[0]==2 && p1LRp2LR[1]==2) {
                    p1LRp2LR[0]=1;
                    p1LRp2LR[1]=3;
                    shuffled.set(true);
                }
                else if ((p1LRp2LR[0]==1 && p1LRp2LR[1]==4) || (p1LRp2LR[1]==1 && p1LRp2LR[0]==4)) {
                    p1LRp2LR[0]=2;
                    p1LRp2LR[1]=3;
                    shuffled.set(true);
                }
                else if ((p1LRp2LR[0]==2 && p1LRp2LR[1]==3) || (p1LRp2LR[1]==2 && p1LRp2LR[0]==3)) {
                    p1LRp2LR[0]=1;
                    p1LRp2LR[1]=4;
                    shuffled.set(true);
                }
                else if (p1LRp2LR[0]==3 && p1LRp2LR[1]==3) {
                    p1LRp2LR[0]=2;
                    p1LRp2LR[1]=4;
                    shuffled.set(true);
                }
                else if ((p1LRp2LR[0]==2 && p1LRp2LR[1]==4) || (p1LRp2LR[1]==2 && p1LRp2LR[0]==4)) {
                    p1LRp2LR[0]=3;
                    p1LRp2LR[1]=3;
                    shuffled.set(true);
                }


                if (!shuffled.get()) return;
                if (alert_visible.get()) return;
                p1Turn = false;
                p1TDot.setVisibility(View.INVISIBLE);
                p2TDot.setVisibility(View.VISIBLE);
                shuffled.set(false);
                changeImage(p1Left, p1LRp2LR[0]);
                changeImage(p1Right, p1LRp2LR[1]);
            }
        });

        // p2 Shuffle Button

        p2Shuffle.setOnClickListener(view -> {
            if (!p1Turn) {
                if ((p1LRp2LR[2] == 0 && p1LRp2LR[3] == 2) || (p1LRp2LR[3] == 0 && p1LRp2LR[2] == 2)) {
                    p1LRp2LR[2] = 1;
                    p1LRp2LR[3] = 1;
                    shuffled.set(true);
                } else if ((p1LRp2LR[2] == 0 && p1LRp2LR[3] == 3) || (p1LRp2LR[3] == 0 && p1LRp2LR[2] == 3)) {
                    p1LRp2LR[2] = 1;
                    p1LRp2LR[3] = 2;
                    shuffled.set(true);
                } else if ((p1LRp2LR[2] == 0 && p1LRp2LR[3] == 4) || (p1LRp2LR[3] == 0 && p1LRp2LR[2] == 4)) {
                    AlertDialog alert = shuffle_alert.create();
                    alert.setTitle("Shuffle Alert");
                    alert_visible.set(true);
                    alert.show();
                    shuffled.set(true);
                } else if ((p1LRp2LR[2] == 1 && p1LRp2LR[3] == 3) || (p1LRp2LR[3] == 1 && p1LRp2LR[2] == 3)) {
                    p1LRp2LR[2] = 2;
                    p1LRp2LR[3] = 2;
                    shuffled.set(true);
                } else if (p1LRp2LR[2] == 2 && p1LRp2LR[3] == 2) {
                    p1LRp2LR[2] = 1;
                    p1LRp2LR[3] = 3;
                    shuffled.set(true);
                } else if ((p1LRp2LR[2] == 1 && p1LRp2LR[3] == 4) || (p1LRp2LR[3] == 1 && p1LRp2LR[2] == 4)) {
                    p1LRp2LR[2] = 2;
                    p1LRp2LR[3] = 3;
                    shuffled.set(true);
                } else if ((p1LRp2LR[2] == 2 && p1LRp2LR[3] == 3) || (p1LRp2LR[3] == 2 && p1LRp2LR[2] == 3)) {
                    p1LRp2LR[2] = 1;
                    p1LRp2LR[3] = 4;
                    shuffled.set(true);
                } else if (p1LRp2LR[2] == 3 && p1LRp2LR[3] == 3) {
                    p1LRp2LR[2] = 2;
                    p1LRp2LR[3] = 4;
                    shuffled.set(true);
                } else if ((p1LRp2LR[2] == 2 && p1LRp2LR[3] == 4) || (p1LRp2LR[3] == 2 && p1LRp2LR[2] == 4)) {
                    p1LRp2LR[2] = 3;
                    p1LRp2LR[3] = 3;
                    shuffled.set(true);
                }

                if (!shuffled.get()) return;
                if (alert_visible.get()) return;
                p1Turn=true;
                p1TDot.setVisibility(View.VISIBLE);
                p2TDot.setVisibility(View.INVISIBLE);
                shuffled.set(false);
                changeImage(p2Left, p1LRp2LR[2]);
                changeImage(p2Right, p1LRp2LR[3]);

            }
        });

    }


    protected void changeImage(ImageButton imgBtn, int i){
        switch(i) {
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