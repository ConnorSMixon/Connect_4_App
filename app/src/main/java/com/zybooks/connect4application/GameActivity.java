package com.zybooks.connect4application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import info.overrideandroid.connect4.R;

public class GameActivity extends AppCompatActivity implements GameMenuController.MenuControllerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        MenuView menuView = findViewById(R.id.menuView);
        GameMenuController gameMenuController = new GameMenuController(this, menuView);
        menuView.setListeners(gameMenuController);


    }

    @Override
    public void onPlay(@NonNull GameRules gameRules) {
        Intent gamePlayIntent = new Intent(this,GamePlayActivity.class);
        gamePlayIntent.putExtras(gameRules.exportTo(new Bundle()));
        startActivity(gamePlayIntent);
    }
}
