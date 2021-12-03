package com.C4.connect4application.utils;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;

import com.C4.connect4application.R;

public class GamePieceHelper {

    public static int[] GAME_PIECE_DRAWABLES = {
            R.drawable.piece_yellow,
            R.drawable.piece_orange,
            R.drawable.piece_green,
            R.drawable.piece_blue,
            R.drawable.piece_purple,
            R.drawable.piece_red
    };

    public static int[] GAME_PIECE_COLORS = {
            R.color.yellow,
            R.color.orange,
            R.color.green,
            R.color.blue,
            R.color.purple,
            R.color.red
    };

    public static int numberOfGamePieces() {
        return GAME_PIECE_DRAWABLES.length;
    }

    public static @DrawableRes int countToImageResource(int count) {
        int index = count % GAME_PIECE_DRAWABLES.length;
        return GAME_PIECE_DRAWABLES[index];
    }

    public static int imageResourceToCount(@DrawableRes int imageResource) {
        for(int i = 0; i < GAME_PIECE_DRAWABLES.length; i++) {
            if(GAME_PIECE_DRAWABLES[i] == imageResource) {
                return i;
            }
        }
        return 0;
    }

    public static @ColorRes int imageResourceToColor(@DrawableRes int imageResource) {
        int index = imageResourceToCount(imageResource);
        return GAME_PIECE_COLORS[index];
    }

    public static int checkForDuplicates(int count1, int count2) {
        if (count1 == count2) {
            if (count1 == 5) {
                count1 = 0;
            } else {
                count1++;
            }
        }
        return count1;
    }
}