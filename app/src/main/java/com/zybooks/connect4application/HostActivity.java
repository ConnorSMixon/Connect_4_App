package com.zybooks.connect4application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, new StartFragment());
        fragmentTransaction.commit();

        // play background music
        Intent intent = new Intent(this, BackgroundSoundService.class);
        startService(intent);
    }

    int isPaused = 0;

    @Override
    protected void onPause() {
        super.onPause();
        isPaused ++;

        BackgroundSoundService.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPaused ++;

        if (isPaused > 1) {
            BackgroundSoundService.onResume();
        }
    }
}
