package com.zybooks.connect4application;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.zybooks.connect4application.utils.GamePieceHelper;

public class OptionsActivity extends AppCompatActivity {
    public static int count1 = 0;
    public static int count2 = 0;

    public static int pieceData1, pieceData2;
    private ImageView imageView1, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        imageView1 = findViewById(R.id.piece1_selector);
        imageView2 = findViewById(R.id.piece2_selector);

        pieceData1 = SavedData.loadInt(SavedData.PIECE_1_DATA, R.drawable.piece_red, this);
        pieceData2 = SavedData.loadInt(SavedData.PIECE_2_DATA, R.drawable.piece_yellow, this);

        imageView1.setImageResource(pieceData1);
        imageView2.setImageResource(pieceData2);

        findDisplayedImage();
        circulatingImage();

        // change color of notification bar
        Miscellaneous.setNotificationBarColor(this);

        // on click listener for up button
        ImageView upButton = findViewById(R.id.activityOptionsBackArrow);
        Miscellaneous.previousActivity(upButton, this);

        final MediaPlayer popsound = MediaPlayer.create(this, R.raw.pop);
    }

    public void circulatingImage(){
        imageView1.setOnClickListener(view -> {
            // increment count
            count1 ++;
            // check if reset is needed
            if (count1 == GamePieceHelper.numberOfGamePieces()) {
                count1 = 0;
            }
            // check for duplicates and resolve if needed
            count1 = GamePieceHelper.checkForDuplicates(count1, count2);
            // get the resource
            int imageResource = GamePieceHelper.countToImageResource(count1);
            // check if we need to reset the count.


            Drawable drawable = ContextCompat.getDrawable(OptionsActivity.this, imageResource);
            imageView1.setImageDrawable(drawable);
            Animation.bounceAnimation(imageView1, this);

            SavedData.saveInt(SavedData.PIECE_1_DATA, imageResource, this);
        });

        imageView2.setOnClickListener(view -> {
            count2 ++;
            if(count2 == GamePieceHelper.numberOfGamePieces()) {
                count2 = 0;
            }
            count2 = GamePieceHelper.checkForDuplicates(count2, count1);
            int imageResource = GamePieceHelper.countToImageResource(count2);
            Drawable drawable = ContextCompat.getDrawable(OptionsActivity.this, imageResource);
            imageView2.setImageDrawable(drawable);
            Animation.bounceAnimation(imageView2, this);

            SavedData.saveInt(SavedData.PIECE_2_DATA, imageResource, this);
        });
    }

    public void findDisplayedImage() {
        count1 = GamePieceHelper.imageResourceToCount(pieceData1);
        count2 = GamePieceHelper.imageResourceToCount(pieceData2);
    }
}



