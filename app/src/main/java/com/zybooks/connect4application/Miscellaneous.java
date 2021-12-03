package com.zybooks.connect4application;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.Fragment;

public class Miscellaneous extends Fragment {

    public static void setNotificationBarColor(Activity activity) {
        Window window = activity.getWindow();
        window.setStatusBarColor(activity.getResources().getColor(R.color.theme2_black));
    }
}
