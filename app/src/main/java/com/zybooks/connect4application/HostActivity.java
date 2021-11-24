package com.zybooks.connect4application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class HostActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        // constructor call
        new MusicSoundService(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // sets StartFragment as default fragment
        fragmentTransaction.add(R.id.fragment_container, new StartFragment());
        fragmentTransaction.commit();

        // load music checkbox value
        checked = SavedData.loadBoolean(SavedData.CHECKBOX_MUSIC, true, this);
        if (checked && !MusicSoundService.onStart) {
            // if checkbox is checked by default on app launch
            MusicSoundService.playBackgroundMusic();
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        checked = SavedData.loadBoolean(SavedData.CHECKBOX_MUSIC, true, this);
        if (checked && !MusicSoundService.onStart) {
            // if checkbox is checked before sound has started
            MusicSoundService.playBackgroundMusic();
        } else if (checked && MusicSoundService.onStart){
            // if checkbox is checked after sound has started
            MusicSoundService.resumeBackgroundMusic();
        } else if (!checked && MusicSoundService.onStart) {
            // is checkbox is unchecked after sound has started
            MusicSoundService.pauseBackgroundMusic();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicSoundService.pauseBackgroundMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (checked) {
            MusicSoundService.resumeBackgroundMusic();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SavedData.registerBoolean(this, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SavedData.unregisterBoolean(this, this);
    }
}
