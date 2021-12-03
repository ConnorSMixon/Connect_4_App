package com.C4.connect4application.utils;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.C4.connect4application.R;

public class MusicSoundService extends Service {

    public static MediaPlayer mediaPlayer;
    public static int length;
    public static boolean onStart = false;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100, 100);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        onStart = true;
        return startId;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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
