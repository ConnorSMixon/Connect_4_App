package com.C4.connect4application;

import android.app.Activity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Animation extends android.view.animation.Animation implements android.view.animation.Interpolator{
    private final double mAmplitude;
    private final double mFrequency;

    Animation(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }

    public static void bounceAnimation(ImageView imageView, Activity activity) {
        final android.view.animation.Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.ripple_pop);
        com.C4.connect4application.Animation interpolator = new com.C4.connect4application.Animation(.1, 20);
        myAnim.setInterpolator(interpolator);
        imageView.startAnimation(myAnim);
    }
}

