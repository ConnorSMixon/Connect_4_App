package com.zybooks.connect4application;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class OptionsActivity extends AppCompatActivity {

    public static final String EXTRA_COLOR = "boobs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

    }

    public void onColorSelected(View view) {
        int piece1 = 0;
        if (view.getId() == R.id.radio_red) {
            piece1 = R.drawable.piece_red;
        } else if (view.getId() == R.id.radio_orange) {
            piece1= R.drawable.piece_orange;
        } else if (view.getId() == R.id.radio_green) {
            piece1 = R.drawable.piece_green;
        }  else if (view.getId() == R.id.radio_purple) {
            piece1 = R.drawable.piece_purple;
        }  else if (view.getId() == R.id.radio_yellow) {
        piece1 = R.drawable.piece_yellow;
        }  else if (view.getId() == R.id.radio_blue) {
        piece1 = R.drawable.piece_blue;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_COLOR, piece1);
        setResult(RESULT_OK, intent);
        finish();

    }
}



