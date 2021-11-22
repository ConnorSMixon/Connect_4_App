package com.zybooks.connect4application;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class MusicSoundService extends Service {

    public static MediaPlayer musicMediaPlayer;
    public static int length;
    public static boolean isPaused = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        musicMediaPlayer = MediaPlayer.create(this, R.raw.background_music);
        musicMediaPlayer.setLooping(true); // Set looping
        musicMediaPlayer.setVolume(100, 100);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        musicMediaPlayer.start();
        return startId;
    }

    @Override
    public void onDestroy() {
        musicMediaPlayer.stop();
        musicMediaPlayer.release();
    }

    public static void onPause() {
        musicMediaPlayer.pause();
        length = musicMediaPlayer.getCurrentPosition();
        isPaused = true;
    }

    public static void onResume() {
        musicMediaPlayer.seekTo(length);
        musicMediaPlayer.start();
    }
}

