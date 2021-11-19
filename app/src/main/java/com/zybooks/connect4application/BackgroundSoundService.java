package com.zybooks.connect4application;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;
public class BackgroundSoundService extends Service {

    public static MediaPlayer mediaPlayer;
    public static int length;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100, 100);
        mediaPlayer.start();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        return startId;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public static void onPause() {
        mediaPlayer.pause();
        length = mediaPlayer.getCurrentPosition();
    }

    public static void onResume() {
        mediaPlayer.seekTo(length);
        mediaPlayer.start();
    }
}

