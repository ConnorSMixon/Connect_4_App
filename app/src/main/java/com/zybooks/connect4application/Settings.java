package com.zybooks.connect4application;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;

public class Settings {
    public static String PIECE_1_DATA = "key1";
    public static String PIECE_2_DATA = "key2";
    public static String SHARED_PREFS = "connect_4_app_shared_prefs";
    
    public static void saveGamePiece(String string, int value, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(string, value);

        editor.apply();
    }

    public static int loadGamePiece(String string, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getInt(string, R.drawable.piece_red);
    }
}