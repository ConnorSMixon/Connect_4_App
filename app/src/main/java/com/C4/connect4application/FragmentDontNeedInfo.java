package com.C4.connect4application;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentDontNeedInfo extends Fragment {

    public static boolean isInflated = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inflates parent view
        View parentView = inflater.inflate(R.layout.fragment_dont_need_info, container, false);

        isInflated = true;

        Miscellaneous.setNotificationBarColor(this.requireActivity());
        return parentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isInflated = false;
    }
}
