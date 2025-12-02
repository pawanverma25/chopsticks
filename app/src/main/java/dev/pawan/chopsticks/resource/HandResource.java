package dev.pawan.chopsticks.resource;

import android.widget.ImageButton;
import android.widget.ImageView;

import dev.pawan.chopsticks.R;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Pawan Verma
 * @since chopstick@v2.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandResource {
    public static final int HAND_0 = R.drawable.hand0;
    public static final int HAND_1 = R.drawable.hand1;
    public static final int HAND_3 = R.drawable.hand3;
    public static final int HAND_2 = R.drawable.hand2;
    public static final int HAND_4 = R.drawable.hand4;

    private ImageButton button;
    private ImageView dot;

}
