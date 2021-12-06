package com.C4.connect4application;

import android.app.Activity;
import android.content.res.Resources;
import android.view.Window;

import androidx.fragment.app.Fragment;

public class Miscellaneous extends Fragment {

    public static void setNotificationBarColor(Activity activity) {
        Window window = activity.getWindow();
        window.setStatusBarColor(activity.getResources().getColor(R.color.theme2_black));
    }
}
