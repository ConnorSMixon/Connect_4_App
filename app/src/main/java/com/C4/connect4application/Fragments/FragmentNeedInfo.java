package com.C4.connect4application.Fragments;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.C4.connect4application.R;
import com.C4.connect4application.utils.Miscellaneous;

public class FragmentNeedInfo extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inflates parent view
        View parentView = inflater.inflate(R.layout.fragment_need_info, container, false);

        Miscellaneous.setNotificationBarColor(this.requireActivity());
        return parentView;

    }
}