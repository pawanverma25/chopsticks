package com.example.chopstick.resource;

import com.example.chopstick.R;

import lombok.Data;

/**
 * @author Pawan Verma
 * @since chopstick@v2.0
 */

@Data
public class HandResource {
    public static final int HAND_0 = R.drawable.hand0;
    public static final int HAND_1 = R.drawable.hand1;
    public static final int HAND_3 = R.drawable.hand3;
    public static final int HAND_2 = R.drawable.hand2;
    public static final int HAND_4 = R.drawable.hand4;

    private int button;
    private int dot;

}
