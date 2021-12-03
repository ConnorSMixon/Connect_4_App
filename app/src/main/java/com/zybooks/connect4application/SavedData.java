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

    public static void saveInt(String key, int value, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS1, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(key, value);

        editor.apply();
    }

    public static int loadInt(String key, int defaultPiece, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS1, MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultPiece);
    }

    public static void saveString(String key, String value, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS1, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, value);

        editor.apply();
    }

    public static String loadString(String key, String defaultValue, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS1, MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    public static void saveBoolean(String key, boolean value, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS1, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(key, value);

        editor.apply();
    }

    public static boolean loadBoolean(String key, boolean defaultValue, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS1, MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
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