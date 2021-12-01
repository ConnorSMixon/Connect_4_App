package com.zybooks.connect4application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;

public class StartFragment extends Fragment {

    private SFXSound sfx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentView = inflater.inflate(R.layout.fragment_start, container, false);

        sfx = new SFXSound(this.requireActivity());



        ImageButton playButton = parentView.findViewById(R.id.play_button);
        ImageButton optionsButton = parentView.findViewById(R.id.options_button);

        openFragmentOnClick(this.requireActivity(), R.id.fragment_container, playButton, GameFragment.class,
                R.anim.enter_left, R.anim.exit_left, R.anim.enter_right, R.anim.exit_right);
        openFragmentOnClick(this.requireActivity(), R.id.small_fragment_container, optionsButton,
                OptionsFragment.class, R.anim.pop_open, R.anim.pop_open, R.anim.pop_close, R.anim.pop_close);

        // change color of notification bar
        Miscellaneous.setNotificationBarColor(this.requireActivity());

        return parentView;
    }
    private void openFragmentOnClick(Context context, int cont, ImageButton imageButton, Class fragment,
                                     int anim1, int anim2, int anim3, int anim4) {
        imageButton.setOnClickListener(view -> {
            sfx.playSFX(SFXSound.sfxClick, SFXSound.sfxClickCount, context);
            FragmentTransaction ft = getParentFragmentManager().beginTransaction();
            ft.setCustomAnimations(anim1, anim2, anim3, anim4);
            ft.add(cont, fragment, null);
            ft.addToBackStack(null);
            ft.commit();
        });
    }
}