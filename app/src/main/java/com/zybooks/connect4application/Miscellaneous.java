package com.zybooks.connect4application;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class Miscellaneous {

    public static void notificationBarColor(Activity context) {
            Window window = context.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(context.getResources().getColor(R.color.theme2_black));

    }
}
