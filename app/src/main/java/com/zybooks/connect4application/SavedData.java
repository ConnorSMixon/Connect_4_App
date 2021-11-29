package com.zybooks.connect4application;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class SavedData {
    public static String SHARED_PREFS1 = "connect_4_app_shared_prefs1";
    public static String PIECE_1_DATA = "piece1";
    public static String PIECE_2_DATA = "piece2";
    public static String CHECKBOX_MUSIC = "CheckboxMusic";
    public static String CHECKBOX_SFX = "CheckboxSFX";

    public static void saveInt(String string, int value, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS1, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(string, value);

        editor.apply();
    }

    public static int loadInt(String string, int defaultPiece, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS1, MODE_PRIVATE);
        return sharedPreferences.getInt(string, defaultPiece);
    }

    public static void saveBoolean(String string, boolean value, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS1, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(string, value);

        editor.apply();
    }

    public static boolean loadBoolean(String string, boolean defaultValue, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS1, MODE_PRIVATE);
        return sharedPreferences.getBoolean(string, defaultValue);
    }

    public static void registerBoolean(Context context, SharedPreferences.OnSharedPreferenceChangeListener listener) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS1, MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public static void unregisterBoolean(Context context, SharedPreferences.OnSharedPreferenceChangeListener listener) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS1, MODE_PRIVATE);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }
}