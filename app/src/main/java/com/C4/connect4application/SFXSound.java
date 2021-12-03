package com.C4.connect4application;

import android.content.Context;
import android.media.MediaPlayer;

public class SFXSound {

    public static MediaPlayer sfxPop, sfxClick;
    public static int sfxPopCount, sfxClickCount;

    SFXSound(Context context) {
        sfxPop = MediaPlayer.create(context, R.raw.pop);
        sfxClick = MediaPlayer.create(context, R.raw.click);
        sfxPop.setVolume(.5f, .5f);
        sfxClick.setVolume(.5f, .5f);
    }

    public void playSFX(MediaPlayer sfx, int count, Context context) {
        boolean value = SavedData.loadBoolean(SavedData.CHECKBOX_SFX, true, context);
        if (value) {
            if (count == 0) {
                sfx.start();
                count++;
            }
            sfx.pause();
            sfx.seekTo(0);
            sfx.start();
        }
    }
}