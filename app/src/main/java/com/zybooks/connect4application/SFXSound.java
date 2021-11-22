package com.zybooks.connect4application;

import android.content.Context;
import android.media.MediaPlayer;

public class SFXSound {
    public static void playSFX(Context context, int sound) {
        MediaPlayer sfx = MediaPlayer.create(context, sound);
        sfx.setVolume(.1f, .1f);
        sfx.start();
    }
}
