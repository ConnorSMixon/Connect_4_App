package com.zybooks.connect4application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class HelpActivity extends AppCompatActivity {


    private Object NeedInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

    }
    public void onNeedHelpClick(View view) {
        Intent info = new Intent(this, NeedInfo.class);
        startActivity(info);
    }
    public void onNeedNoHelpClick(View view) {
        Intent noInfo = new Intent(this, DontNeedInfo.class);
        startActivity(noInfo);
    }

}