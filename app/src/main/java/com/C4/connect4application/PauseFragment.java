package com.C4.connect4application;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.fragment.app.Fragment;

public class PauseFragment extends Fragment {
    public static boolean musicChecked = true, sfxChecked = true, isInflated = false;
    public static CheckBox musicCheckbox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View parentView = inflater.inflate(R.layout.fragment_pause, container, false);

        isInflated = true;
        SFXSound sfx = new SFXSound(this.requireActivity());

        // links background music checkbox to background music class
        boolean musicCheckboxVal = SavedData.loadBoolean(SavedData.CHECKBOX_MUSIC, true, this.requireActivity());
        musicCheckbox = parentView.findViewById(R.id.checkbox_music);
        musicCheckbox.setChecked(musicCheckboxVal);
        toggleBackgroundMusic(this.requireActivity());

        // links sfx checkbox to sfx sounds
        boolean sfxCheckboxVal = SavedData.loadBoolean(SavedData.CHECKBOX_SFX, true, this.requireActivity());
        CheckBox sfxCheckbox = parentView.findViewById(R.id.checkbox_sfx);
        sfxCheckbox.setChecked(sfxCheckboxVal);
        toggleSFX(sfxCheckbox, this.requireActivity());

        return parentView;
    }

    private void toggleBackgroundMusic(Context context) {
        musicCheckbox.setOnClickListener(view -> {
            musicChecked = musicCheckbox.isChecked();
            SavedData.saveBoolean(SavedData.CHECKBOX_MUSIC, musicChecked, context);
        });
    }

    private void toggleSFX(CheckBox checkBox, Context context) {
        checkBox.setOnClickListener(view -> {
            sfxChecked = checkBox.isChecked();
            SavedData.saveBoolean(SavedData.CHECKBOX_SFX, sfxChecked, context);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isInflated = false;
    }
}