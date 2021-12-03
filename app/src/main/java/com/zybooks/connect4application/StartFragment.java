package com.zybooks.connect4application;

import android.graphics.Path;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.zybooks.connect4application.utils.FragmentHelper;

public class StartFragment extends Fragment {

    private SFXSound sfx;
    private Fragment frag;
    private ImageButton playButton;
    private ImageButton optionsButton;
    private ImageButton helpButton;


    // initializes the buttons and sets them so it can be used outside methods.
    public ImageButton getPlayButton() {
        return playButton;
    }
    public void setPlayButton(ImageButton playButton) {
        this.playButton = playButton;
    }
    public ImageButton getOptionsButton() {
        return optionsButton;
    }
    public void setOptionsButton(ImageButton optionsButton) {
        this.optionsButton = optionsButton;
    }
    public ImageButton getHelpButton() {
        return optionsButton;
    }
    public void setHelpButton(ImageButton helpButton) {
        this.helpButton = helpButton;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentView = inflater.inflate(R.layout.fragment_start, container, false);

        sfx = new SFXSound(this.requireActivity());

        playButton = parentView.findViewById(R.id.play_button);
        setPlayButton(playButton);
        optionsButton = parentView.findViewById(R.id.options_button);
        setOptionsButton(optionsButton);
        helpButton = parentView.findViewById(R.id.help_button);
        setHelpButton(helpButton);

        openLargeFragment(playButton, GameFragment.class, R.id.fragment_container);
        openLargeFragment(helpButton, HelpFragment.class, R.id.fragment_container);
        openSmallFragment(optionsButton, OptionsFragment.class, R.id.small_fragment_container);


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