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

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

        Intent intent = new Intent(this, BackgroundSoundService.class);
        startService(intent);

    }

    public void createPieceAnimation(int pieceID) {

        Random randPiece = new Random();
        int piece = randPiece.nextInt(2)+1;

        switch (piece) {
            case 1:
                pieceID = R.drawable.red_piece;
                break;
            case 2:
                pieceID = R.drawable.yellow_piece;
                break;
        }

        ImageView imageView = (ImageView) findViewById(pieceID);

        imageView.setVisibility(imageView.VISIBLE);

        float bottomOfScreen = getResources().getDisplayMetrics()
                .heightPixels - (imageView.getHeight() * 4);
        //bottomOfScreen is where you want to animate to

        imageView.animate()
                .translationY(bottomOfScreen)
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(10000);
    }

    // Activity Methods

    public void onPlayClick(View view) {
        Intent play = new Intent(this, GameActivity.class);
        startActivity(play);
    }
}
