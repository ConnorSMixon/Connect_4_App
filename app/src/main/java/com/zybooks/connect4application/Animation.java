package com.zybooks.connect4application;

import android.app.Activity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class Animation extends android.view.animation.Animation implements android.view.animation.Interpolator{
    private double mAmplitude = 1;
    private double mFrequency = 10;

    Animation(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }

    public static void bounceAnimation(ImageView imageView, Activity activity) {
        final android.view.animation.Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.bounce);
        com.zybooks.connect4application.Animation interpolator = new com.zybooks.connect4application.Animation(.1, 20);
        myAnim.setInterpolator(interpolator);
        imageView.startAnimation(myAnim);
    }
}

