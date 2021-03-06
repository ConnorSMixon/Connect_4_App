package com.C4.connect4application.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.C4.connect4application.utils.Miscellaneous;
import com.C4.connect4application.R;
import com.C4.connect4application.utils.SFXSound;
import com.C4.connect4application.utils.FragmentHelper;

public class StartFragment extends Fragment {

    private SFXSound sfx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentView = inflater.inflate(R.layout.fragment_start, container, false);

        sfx = new SFXSound(this.requireActivity());

        ImageButton playButton = parentView.findViewById(R.id.play_button);
        ImageButton optionsButton = parentView.findViewById(R.id.options_button);
        ImageButton helpButton = parentView.findViewById(R.id.help_button);

        openLargeFragment(playButton, GameFragment.class, R.id.fragment_container);
        openLargeFragment(helpButton, HelpFragment.class, R.id.fragment_container);
        openSmallFragment(optionsButton, OptionsFragment.class, R.id.small_fragment_container);

        // allow user to close popup fragment by touching outside of layout
        parentView.setOnClickListener(view -> {
            if (OptionsFragment.isInflated) {
                FragmentManager fm;
                fm = getParentFragmentManager();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                fm.popBackStack();
                ft.commit();
                OptionsFragment.isInflated = false;
            }
        });

        // change color of notification bar
        Miscellaneous.setNotificationBarColor(this.requireActivity());

        return parentView;
    }

    private void openLargeFragment(ImageButton button, Class fragment, int container) {
        button.setOnClickListener(view -> {
            if(!GameFragment.isInflated && !OptionsFragment.isInflated && !HelpFragment.isInflated) {
                sfx.playSFX(SFXSound.sfxClick, SFXSound.sfxClickCount, this.requireActivity());
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                FragmentHelper.openLargeFragment(ft, fragment, container);
            }
        });
    }

    private void openSmallFragment(ImageButton button, Class fragment, int container) {
        button.setOnClickListener(view -> {
            if(!GameFragment.isInflated && !OptionsFragment.isInflated && !HelpFragment.isInflated) {
                sfx.playSFX(SFXSound.sfxClick, SFXSound.sfxClickCount, this.requireActivity());
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                FragmentHelper.openSmallFragment(ft, fragment, container);
            }
        });
    }
}