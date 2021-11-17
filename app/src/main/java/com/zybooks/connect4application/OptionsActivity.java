package com.zybooks.connect4application;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class OptionsActivity extends AppCompatActivity {

    public static int imageResource1, imageResource2, count1, count2;
    public static String viewKey1 = "key1", viewKey2 = "key2";
    private int pieceData1, pieceData2;
    private ImageView imageView1, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        imageView1 = findViewById(R.id.piece1_selector);
        imageView2 = findViewById(R.id.piece2_selector);

        pieceData1 = Settings.loadData(viewKey1, imageResource1, this);
        pieceData2 = Settings.loadData(viewKey2, imageResource2, this);
        Settings.updateViews(imageView1, pieceData1);
        Settings.updateViews(imageView2, pieceData2);

        findDisplayedImage();
        circulatingImage();

        // change color of notification bar
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.theme2_black));
        }
    }

    public void circulatingImage(){
        imageView1.setOnClickListener(view -> {
            count1 ++;

            imageResource1 = countToImageResource(count1);

            Drawable drawable = ContextCompat.getDrawable(OptionsActivity.this, imageResource1);
            imageView1.setImageDrawable(drawable);

            Settings.saveIntData(viewKey1, imageResource1, this);
        });

        imageView2.setOnClickListener(view -> {
            count2 ++;

            imageResource2 = countToImageResource(count2);

            Drawable drawable = ContextCompat.getDrawable(OptionsActivity.this, imageResource2);
            imageView2.setImageDrawable(drawable);

            Settings.saveIntData(viewKey2, imageResource2, this);
        });
    }

    public void findDisplayedImage() {
        count1 = imageResourceToCount(pieceData1);
        count2 = imageResourceToCount(pieceData2);
    }

    public int countToImageResource(int count) {
        switch (count) {
            case 1:
                return R.drawable.piece_yellow;
            case 2:
                return R.drawable.piece_orange;
            case 3:
                return R.drawable.piece_green;
            case 4:
                return R.drawable.piece_blue;
            case 5:
                return R.drawable.piece_purple;
            case 6:
            default:
                return R.drawable.piece_red;
        }
    }

    public int imageResourceToCount(int pieceData) {
        switch (pieceData) {
            case R.drawable.piece_yellow:
                return 1;
            case R.drawable.piece_orange:
                return 2;
            case R.drawable.piece_green:
                return 3;
            case R.drawable.piece_blue:
                return 4;
            case R.drawable.piece_purple:
                return 5;
            case R.drawable.piece_red:
            default:
                return 0;
        }
    }

    public static int imageResourceToColor(int pieceData) {
        switch (pieceData) {
            case R.drawable.piece_yellow:
                return R.color.yellow;
            case R.drawable.piece_orange:
                return R.color.orange;
            case R.drawable.piece_green:
                return R.color.green;
            case R.drawable.piece_blue:
                return R.color.blue;
            case R.drawable.piece_purple:
                return R.color.purple;
            case R.drawable.piece_red:
            default:
                return R.color.red;
        }
    }
}



