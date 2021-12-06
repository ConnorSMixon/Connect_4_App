package com.C4.connect4application.fragments;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.C4.connect4application.utils.Miscellaneous;
import com.C4.connect4application.R;

public class InfoFragment extends Fragment {

    public static boolean isInflated = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inflates parent view
        View parentView = inflater.inflate(R.layout.fragment_info, container, false);

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