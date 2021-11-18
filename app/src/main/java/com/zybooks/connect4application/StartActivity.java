package com.zybooks.connect4application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // change notification bar color
        Miscellaneous.notificationBarColor(this);

        // play background music
        Intent intent = new Intent(this, BackgroundSoundService.class);
        startService(intent);
    }

    // activity methods
    public void onPlayClick(View view) {

        Intent play = new Intent(this, GameActivity.class);
        startActivity(play);
    }

    public void onOptionsClick(View view) {

        Intent options = new Intent(this, OptionsActivity.class);
        startActivity(options);
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
