package com.C4.connect4application;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class PauseFragment extends Fragment {
    public static boolean musicChecked = true, sfxChecked = true;
    public static CheckBox musicCheckbox;

    private View parentView;
    private SFXSound sfx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this.requireActivity() fragment
        parentView = inflater.inflate(R.layout.fragment_pause, container, false);

        sfx = new SFXSound(this.requireActivity());

        // on click listener for up button
        ImageView upButton = parentView.findViewById(R.id.activityOptionsBackArrow);
        previousFragment(upButton);

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

    private void previousFragment(ImageView imageView) {
        imageView.setOnClickListener(view -> {
            sfx.playSFX(SFXSound.sfxClick, SFXSound.sfxClickCount, this.requireActivity());
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction ft = getParentFragmentManager().beginTransaction();
            fm.popBackStack();
            ft.commit();
        });
    }

    private void toggleBackgroundMusic(Context context) {
        musicCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicChecked = musicCheckbox.isChecked();
                SavedData.saveBoolean(SavedData.CHECKBOX_MUSIC, musicChecked, context);
            }
        });
    }

    private void toggleSFX(CheckBox checkBox, Context context) {
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sfxChecked = checkBox.isChecked();
                SavedData.saveBoolean(SavedData.CHECKBOX_SFX, sfxChecked, context);
            }
        });
    }
}