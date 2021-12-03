package com.C4.connect4application.utils;

import android.app.Activity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.C4.connect4application.R;

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
        final android.view.animation.Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.bounce_anim);
        Animation interpolator = new Animation(.1, 20);
        myAnim.setInterpolator(interpolator);
        imageView.startAnimation(myAnim);
    }
}

