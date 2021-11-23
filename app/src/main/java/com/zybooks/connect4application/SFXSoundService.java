package com.zybooks.connect4application;

import android.content.Context;
import android.media.MediaPlayer;

public class SFXSoundService {

    public static MediaPlayer sfxPop, sfxClick;
    private static int count = 0;

    SFXSoundService(Context context) {
        sfxPop = MediaPlayer.create(context, R.raw.pop);
        sfxClick = MediaPlayer.create(context, R.raw.click);
        sfxPop.setVolume(.5f, .5f);
        sfxClick.setVolume(.5f, .5f);
    }

    public static void playSFX(MediaPlayer sfx) {
        if (count == 0) {
            sfx.start();
            count ++;
        }
        sfx.pause();
        sfx.seekTo(0);
        sfx.start();
    }
}
