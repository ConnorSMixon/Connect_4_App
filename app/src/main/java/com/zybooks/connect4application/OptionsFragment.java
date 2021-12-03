package com.zybooks.connect4application;

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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zybooks.connect4application.utils.GamePieceHelper;

public class OptionsFragment extends Fragment {
    public static int pieceCount1 = 0, pieceCount2 = 0;
    public static int pieceData1, pieceData2;
    public static boolean musicChecked = true, sfxChecked = true, isInflated = false;
    public CheckBox musicCheckbox;

    private ImageView imageView1, imageView2;
    private SFXSound sfx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this.requireActivity() fragment
        View parentView = inflater.inflate(R.layout.fragment_options, container, false);

        sfx = new SFXSound(this.requireActivity());
        isInflated = true;

        imageView1 = parentView.findViewById(R.id.piece1_selector);
        imageView2 = parentView.findViewById(R.id.piece2_selector);

        pieceData1 = SavedData.loadInt(SavedData.PIECE_1_DATA, R.drawable.piece_red, this.requireActivity());
        pieceData2 = SavedData.loadInt(SavedData.PIECE_2_DATA, R.drawable.piece_yellow, this.requireActivity());

        imageView1.setImageResource(pieceData1);
        imageView2.setImageResource(pieceData2);

        // logic for piece selectors
        findDisplayedImage();
        circulatingImage();

        // on click listener for up button
        ImageView upButton = parentView.findViewById(R.id.activityOptionsBackArrow);
        upButton.setOnClickListener(view -> {
            previousFragment();
        });

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

        // change color of notification bar
        Miscellaneous.setNotificationBarColor(this.requireActivity());

        return parentView;
    }

    public void circulatingImage(){
        imageView1.setOnClickListener(view -> {
            sfx.playSFX(SFXSound.sfxPop, SFXSound.sfxPopCount, this.requireActivity());
            // increment count
            pieceCount1++;
            // check if reset is needed
            if (pieceCount1 == GamePieceHelper.numberOfGamePieces()) {
                pieceCount1 = 0;
            }
            // check for duplicates and resolve if needed
            pieceCount1 = GamePieceHelper.checkForDuplicates(pieceCount1, pieceCount2);
            // get the resource
            int imageResource = GamePieceHelper.countToImageResource(pieceCount1);

            Drawable drawable = ContextCompat.getDrawable(this.requireActivity(), imageResource);
            imageView1.setImageDrawable(drawable);
            Animation.bounceAnimation(imageView1, this.requireActivity());

            SavedData.saveInt(SavedData.PIECE_1_DATA, imageResource, this.requireActivity());
        });

        imageView2.setOnClickListener(view -> {
            sfx.playSFX(SFXSound.sfxPop, SFXSound.sfxPopCount, this.requireActivity());
            pieceCount2 ++;
            if(pieceCount2 == GamePieceHelper.numberOfGamePieces()) {
                pieceCount2 = 0;
            }
            pieceCount2 = GamePieceHelper.checkForDuplicates(pieceCount2, pieceCount1);
            int imageResource = GamePieceHelper.countToImageResource(pieceCount2);

            Drawable drawable = ContextCompat.getDrawable(this.requireActivity(), imageResource);
            imageView2.setImageDrawable(drawable);
            Animation.bounceAnimation(imageView2, this.requireActivity());

            SavedData.saveInt(SavedData.PIECE_2_DATA, imageResource, this.requireActivity());
        });
    }

    public void findDisplayedImage() {
        pieceCount1 = GamePieceHelper.imageResourceToCount(pieceData1);
        pieceCount2 = GamePieceHelper.imageResourceToCount(pieceData2);
    }

    private void previousFragment() {
        sfx.playSFX(SFXSound.sfxClick, SFXSound.sfxClickCount, this.requireActivity());
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        fm.popBackStack();
        ft.commit();
        isInflated = false;
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
