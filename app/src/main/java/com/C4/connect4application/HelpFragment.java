package com.C4.connect4application;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.C4.connect4application.utils.FragmentHelper;

public class HelpFragment extends Fragment {

    private SFXSound sfx;
    public static boolean isInflated = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflates parent view
        View parentView = inflater.inflate(R.layout.fragment_help, container, false);

        isInflated = true;
        sfx = new SFXSound(this.requireActivity());

        ImageButton helpButton = parentView.findViewById(R.id.question_mark_button);
        ImageButton upButton = parentView.findViewById(R.id.up_button);

        openFragmentOnClick(helpButton, InfoFragment.class, R.id.small_fragment_container);

        // listener for clicking out of fragment popup
        parentView.setOnClickListener(view -> {
            if (InfoFragment.isInflated) {
                FragmentManager fm;
                fm = getParentFragmentManager();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                fm.popBackStack();
                ft.commit();
                InfoFragment.isInflated = false;
            }
        });

        // on click listener for up button
        previousFragment(upButton);

        // question mark animation
        AlphaAnimation animation = new AlphaAnimation(.75f, 1f);
        animation.setDuration(1000);
        animation.setRepeatCount(Animation.INFINITE);
        helpButton.startAnimation(animation);

        // set notification bar color
        Miscellaneous.setNotificationBarColor(this.requireActivity());

        return parentView;
    }

    private void openFragmentOnClick(ImageButton Button, Class fragment, int container) {
        Button.setOnClickListener(view -> {
            if(!InfoFragment.isInflated) {
                sfx.playSFX(SFXSound.sfxClick, SFXSound.sfxClickCount, this.requireActivity());
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                FragmentHelper.openSmallFragment(ft, fragment, container);
            }
        });
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        isInflated = false;
    }
}