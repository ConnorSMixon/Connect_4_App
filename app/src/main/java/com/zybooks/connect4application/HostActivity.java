package com.zybooks.connect4application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class HostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // sets StartFragment as default fragment
        fragmentTransaction.add(R.id.fragment_container, new StartFragment());
        fragmentTransaction.commit();

        // play background music
        boolean checked = SavedData.loadBoolean(SavedData.CHECKBOX_MUSIC, true, this);
        Intent intent = new Intent(this, MusicSoundService.class);

        if(checked) {
            startService(intent);
        }
    }

    int isPaused = 0;

    @Override
    protected void onPause() {
        super.onPause();
        isPaused ++;

        MusicSoundService.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPaused ++;

        if (isPaused > 1) {
            MusicSoundService.onResume();
        }
    }
}
