package dev.pawan.chopsticks.util;

import android.widget.ImageButton;

import dev.pawan.chopsticks.R;

public enum GameUtility {
    INSTANCE;

    public static void updateHandGesture(ImageButton imgBtn, int i) {
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
