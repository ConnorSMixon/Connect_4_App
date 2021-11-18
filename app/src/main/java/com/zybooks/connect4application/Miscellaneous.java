package com.zybooks.connect4application;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class Miscellaneous {
    public static void setNotificationBarColor(Activity activity) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(activity.getResources().getColor(R.color.theme2_black));
    }

    public static void previousActivity(ImageView imageView, Activity activity) {
        imageView.setOnClickListener(view -> {
            activity.finish();
        });
    }
}
