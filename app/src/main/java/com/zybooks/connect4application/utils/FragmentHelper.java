package com.zybooks.connect4application.utils;

import androidx.fragment.app.FragmentTransaction;

import com.zybooks.connect4application.R;

public class FragmentHelper {

    public static void openLargeFragment(FragmentTransaction ft, Class fragment, int container) {
        ft.setCustomAnimations(R.anim.enter_left,R.anim.exit_left,R.anim.enter_right, R.anim.exit_right);
        ft.add(container, fragment, null);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static void openSmallFragment(FragmentTransaction ft, Class fragment, int container) {
        ft.setCustomAnimations(R.anim.pop_open, R.anim.pop_open, R.anim.pop_close, R.anim.pop_close);
        ft.add(container, fragment, null);
        ft.addToBackStack(null);
        ft.commit();
    }
}
