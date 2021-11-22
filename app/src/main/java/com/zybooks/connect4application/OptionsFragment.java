package com.zybooks.connect4application;

import android.content.Context;
import android.content.Intent;
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
    public static int count1 = 0;
    public static int count2 = 0;
    public static int pieceData1, pieceData2;
    public static boolean checked = true;

    private ImageView imageView1, imageView2;
    private static View parentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this.requireActivity() fragment
        parentView = inflater.inflate(R.layout.fragment_options, container, false);

        imageView1 = parentView.findViewById(R.id.piece1_selector);
        imageView2 = parentView.findViewById(R.id.piece2_selector);

        pieceData1 = SavedData.loadInt(SavedData.PIECE_1_DATA, R.drawable.piece_red, this.requireActivity());
        pieceData2 = SavedData.loadInt(SavedData.PIECE_2_DATA, R.drawable.piece_yellow, this.requireActivity());

        imageView1.setImageResource(pieceData1);
        imageView2.setImageResource(pieceData2);

        findDisplayedImage();
        circulatingImage();

        // change color of notification bar
        Miscellaneous.setNotificationBarColor(this.requireActivity());

        // on click listener for up button
        ImageView upButton = parentView.findViewById(R.id.activityOptionsBackArrow);
        upButton.setOnClickListener(view -> {
            SFXSound.playSFX(this.requireActivity(),R.raw.click2);
        });
        previousFragment(upButton);

        // links background music checkbox to background music class
        boolean value = SavedData.loadBoolean(SavedData.CHECKBOX_MUSIC, true, this.requireActivity());
        CheckBox musicCheckbox = parentView.findViewById(R.id.checkbox_music);
        musicCheckbox.setChecked(value);
        toggleBackgroundMusic(musicCheckbox, this.requireActivity());

        return parentView;
    }

    public void circulatingImage(){
        imageView1.setOnClickListener(view -> {
            SFXSound.playSFX(this.requireActivity(),R.raw.pop);
            // increment count
            count1 ++;
            // check if reset is needed
            if (count1 == GamePieceHelper.numberOfGamePieces()) {
                count1 = 0;
            }
            // check for duplicates and resolve if needed
            count1 = GamePieceHelper.checkForDuplicates(count1, count2);
            // get the resource
            int imageResource = GamePieceHelper.countToImageResource(count1);
            // check if we need to reset the count.


            Drawable drawable = ContextCompat.getDrawable(this.requireActivity(), imageResource);
            imageView1.setImageDrawable(drawable);
            Animation.bounceAnimation(imageView1, this.requireActivity());

            SavedData.saveInt(SavedData.PIECE_1_DATA, imageResource, this.requireActivity());
        });

        imageView2.setOnClickListener(view -> {
            SFXSound.playSFX(this.requireActivity(),R.raw.pop);
            count2 ++;
            if(count2 == GamePieceHelper.numberOfGamePieces()) {
                count2 = 0;
            }
            count2 = GamePieceHelper.checkForDuplicates(count2, count1);
            int imageResource = GamePieceHelper.countToImageResource(count2);
            Drawable drawable = ContextCompat.getDrawable(this.requireActivity(), imageResource);
            imageView2.setImageDrawable(drawable);
            Animation.bounceAnimation(imageView2, this.requireActivity());

            SavedData.saveInt(SavedData.PIECE_2_DATA, imageResource, this.requireActivity());
        });
    }

    public void findDisplayedImage() {
        count1 = GamePieceHelper.imageResourceToCount(pieceData1);
        count2 = GamePieceHelper.imageResourceToCount(pieceData2);
    }

    private void previousFragment(ImageView imageView) {
        imageView.setOnClickListener(view -> {
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction ft = getParentFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.enter_right, R.anim.exit_right, R.anim.enter_left, R.anim.exit_left);
            fm.popBackStack();
            ft.commit();
        });
    }

    private void toggleBackgroundMusic(CheckBox checkBox, Context context){
         checkBox.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 if (checkBox.isChecked() && MusicSoundService.isPaused) {
                     // if checkbox is checked at some point after app launch
                     MusicSoundService.onResume();
                     checked = true;
                 } else if (!checkBox.isChecked() && !MusicSoundService.isPaused){
                     // if checkbox is unchecked at some point after app launch
                     MusicSoundService.onPause();
                     checked = false;
                 } else if (checkBox.isChecked()) {
                     // if checkbox is checked on app launch
                     Intent intent = new Intent(context, MusicSoundService.class);
                     context.startService(intent);
                 }
                 SavedData.saveBoolean(SavedData.CHECKBOX_MUSIC, checked, context);
             }
         });
    }
}
