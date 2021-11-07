package com.zybooks.connect4application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity{

    private int pieceID;
    static boolean active = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Random randPiece = new Random();
        int piece = randPiece.nextInt(2)+1;

        if (piece == 1) {
            pieceID = R.id.red_piece;
        }
        else {
            pieceID = R.id.yellow_piece;
        }

        pieceAnimation(pieceID);

    }

    public void pieceAnimation(int pieceID) {
        ImageView imageView = (ImageView) findViewById(pieceID);

        float bottomOfScreen = getResources().getDisplayMetrics()
                .heightPixels - (imageView.getHeight() * 4);
        //bottomOfScreen is where you want to animate to

        imageView.animate()
                .translationY(bottomOfScreen)
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(20000);
    }
}
