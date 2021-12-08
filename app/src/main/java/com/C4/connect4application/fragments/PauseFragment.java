package com.C4.connect4application.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.C4.connect4application.R;
import com.C4.connect4application.utils.SavedData;

public class PauseFragment extends Fragment {
    public static boolean musicChecked = true, sfxChecked = true, isInflated = false;
    public CheckBox musicCheckbox;

    private View parentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        parentView = inflater.inflate(R.layout.fragment_pause, container, false);

        isInflated = true;

        // links background music checkbox to background music class
        boolean musicCheckboxVal = SavedData.loadBoolean(SavedData.CHECKBOX_MUSIC, true, this.requireActivity());
        setCheckboxDrawable(musicCheckboxVal);
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
            setCheckboxDrawable(musicChecked);
            SavedData.saveBoolean(SavedData.CHECKBOX_MUSIC, musicChecked, context);
        });
    }

    private void toggleSFX(CheckBox checkBox, Context context) {
        checkBox.setOnClickListener(view -> {
            sfxChecked = checkBox.isChecked();
            SavedData.saveBoolean(SavedData.CHECKBOX_SFX, sfxChecked, context);
        });
    }

    private void setCheckboxDrawable(boolean value) {
        ImageView musicIcon = parentView.findViewById(R.id.music_image_indicator);
        Drawable drawable;
        if (value) {
            drawable = ContextCompat.getDrawable(this.requireActivity(), R.drawable.volume_on);
        } else {
            drawable = ContextCompat.getDrawable(this.requireActivity(), R.drawable.volume_off);
        }
        musicIcon.setImageDrawable(drawable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isInflated = false;
    }
}