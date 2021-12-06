package com.C4.connect4application.utils;

import android.app.Activity;
import android.view.Window;

import androidx.fragment.app.Fragment;

import com.C4.connect4application.R;

public class Miscellaneous extends Fragment {

    public static void setNotificationBarColor(Activity activity) {
        Window window = activity.getWindow();
        window.setStatusBarColor(activity.getResources().getColor(R.color.theme2_black));
    }
}
