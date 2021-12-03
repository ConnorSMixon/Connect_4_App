package com.C4.connect4application.Fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.C4.connect4application.GameLogic.SavedData;
import com.C4.connect4application.utils.MusicSoundService;
import com.C4.connect4application.R;

public class HostFragment extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        // constructor call

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // sets StartFragment as default fragment
        fragmentTransaction.add(R.id.fragment_container, new StartFragment());
        fragmentTransaction.commit();

        View outsideFragment = findViewById(R.id.fragment_container);
        outsideFragment.setOnClickListener(view -> {
            FragmentManager fm;
            fm = getSupportFragmentManager();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            fm.popBackStack();
            ft.commit();
        });
        // load music checkbox value
        checked = SavedData.loadBoolean(SavedData.CHECKBOX_MUSIC, true, this);
        if (checked && !MusicSoundService.onStart) {
            // if checkbox is checked by default on app launch
            Intent intent = new Intent(this, MusicSoundService.class);
            startService(intent);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        checked = SavedData.loadBoolean(SavedData.CHECKBOX_MUSIC, true, this);
        if (s.equals(SavedData.CHECKBOX_MUSIC)) {
            if (checked && !MusicSoundService.onStart) {
                // if checkbox is checked before sound has started
                Intent intent = new Intent(this, MusicSoundService.class);
                startService(intent);
            } else if (checked && MusicSoundService.onStart) {
                // if checkbox is checked after sound has started
                MusicSoundService.onResume();
            } else if (!checked && MusicSoundService.onStart) {
                // is checkbox is unchecked after sound has started
                MusicSoundService.onPause();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (checked && MusicSoundService.onStart) {
            MusicSoundService.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checked && MusicSoundService.onStart) {
            MusicSoundService.onResume();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SavedData.registerBoolean(this, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SavedData.unregisterBoolean(this, this);
    }
}
