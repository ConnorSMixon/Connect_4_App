package com.zybooks.connect4application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.FileReader;
import java.util.zip.Inflater;

public class StartActivity extends AppCompatActivity{

    ImageButton optionsButton;
    public static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        optionsButton = findViewById(R.id.options_button);

        // change notification bar color and action bar display
        Miscellaneous.setNotificationBarColor(this);

        // play background music
        Intent intent = new Intent(this, BackgroundSoundService.class);
        startService(intent);
    }

    // activity methods
    public void onPlayClick(View view) {
        Intent play = new Intent(this, GameActivity.class);
        startActivity(play);
        Animation.activityTransition(R.anim.enter_left, R.anim.exit_left, this);
    }

    public void onOptionsClick() {
            ImageButton optionsButton = findViewById(R.id.options_button);

            optionsButton.setOnClickListener(view -> {
                count++;
            });
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
