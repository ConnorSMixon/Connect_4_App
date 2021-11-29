package com.zybooks.connect4application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity {
    public void onNeedHelpClicked(View view)
    {
        Intent needInfo = new Intent(this, NeedInfo.class);
        startActivity(needInfo);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent needInfo = new Intent(this, NeedInfo.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Animation bounceAnimation = AnimationUtils.loadAnimation(this,R.anim.bounce_anim);
        final Button needHelp = findViewById(R.id.help_more_information);
        needHelp.setOnClickListener(view -> needHelp.startAnimation(bounceAnimation));
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }
    }


    public void onNeedNoHelpClick(View view) {
        Intent noInfo = new Intent(this, DontNeedInfo.class);
        startActivity(noInfo);
    }

}