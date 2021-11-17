package com.zybooks.connect4application;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;

public class Settings {

    public static String SHARED_PREFS;
    
    public static void saveIntData(String string, int value, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(string, value);

        editor.apply();
    }

    public static int loadData(String string, int value, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getInt(string, value);
    }

    public static void updateViews(ImageView imageView, int data) {
        imageView.setImageResource(data);
    }
}
