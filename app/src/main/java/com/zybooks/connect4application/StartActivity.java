package com.zybooks.connect4application;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity{

    private int pieceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);



        Intent intent = new Intent(this, BackgroundSoundService.class);
        startService(intent);

    }

    // Activity Methods

    public void onPlayClick(View view) {
        Intent play = new Intent(this, GameActivity.class);
        startActivity(play);
    }
}
