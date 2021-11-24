package com.zybooks.connect4application;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicSoundService {

    public static MediaPlayer musicMediaPlayer;
    public static int length;
    public static boolean onStart;

    MusicSoundService(Context context) {
        musicMediaPlayer = MediaPlayer.create(context, R.raw.background_music);
        musicMediaPlayer.setLooping(true);
        musicMediaPlayer.setVolume(.5f, .5f);
    }

    public static void playBackgroundMusic() {
        musicMediaPlayer.start();
        onStart = true;
    }

    public static void pauseBackgroundMusic() {
        musicMediaPlayer.pause();
        length = musicMediaPlayer.getCurrentPosition();
    }

    public static void resumeBackgroundMusic() {
        musicMediaPlayer.start();
        musicMediaPlayer.seekTo(length);
    }
}

