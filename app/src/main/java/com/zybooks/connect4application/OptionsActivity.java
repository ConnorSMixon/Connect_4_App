package com.zybooks.connect4application;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
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

        int pieceData1 = Settings.loadGamePiece(Settings.PIECE_1_DATA, this);
        int pieceData2 = Settings.loadGamePiece(Settings.PIECE_2_DATA, this);

        imageView1.setImageResource(pieceData1);
        imageView2.setImageResource(pieceData2);

        findDisplayedImage();
        circulatingImage();

        // change color of notification bar
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.theme2_black));
    }

    public void circulatingImage(){
        imageView1.setOnClickListener(view -> {
            // get the resource
            int imageResource = GamePieceHelper.countToImageResource(count1);
            // increment count
            count1 ++;
            // check if we need to reset the count.
            if(count1 >= GamePieceHelper.numberOfGamePieces()) {
                count1 = 0;
            }

            Drawable drawable = ContextCompat.getDrawable(OptionsActivity.this, imageResource);
            imageView1.setImageDrawable(drawable);

            Settings.saveGamePiece(Settings.PIECE_1_DATA, imageResource, this);
        });

        imageView2.setOnClickListener(view -> {
            int imageResource = GamePieceHelper.countToImageResource(count2);
            count2 ++;
            if(count2 >= GamePieceHelper.numberOfGamePieces()) {
                count2 = 0;
            }
            Drawable drawable = ContextCompat.getDrawable(OptionsActivity.this, imageResource);
            imageView2.setImageDrawable(drawable);

            Settings.saveGamePiece(Settings.PIECE_2_DATA, imageResource, this);
        });
    }

    public void findDisplayedImage() {
        count1 = GamePieceHelper.imageResourceToCount(pieceData1);
        count2 = GamePieceHelper.imageResourceToCount(pieceData2);
    }
}



