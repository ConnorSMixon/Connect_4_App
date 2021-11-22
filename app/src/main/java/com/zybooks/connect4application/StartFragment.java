package com.zybooks.connect4application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class StartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentView = inflater.inflate(R.layout.fragment_start, container, false);

        ImageButton playButton = parentView.findViewById(R.id.play_button);
        ImageButton optionsButton = parentView.findViewById(R.id.options_button);

        openFragmentOnClick(playButton, GameFragment.class, this.requireActivity());
        openFragmentOnClick(optionsButton, OptionsFragment.class, this.requireActivity());

        // change color of notification bar
        Miscellaneous.setNotificationBarColor(this.requireActivity());

        return parentView;
    }

    private void openFragmentOnClick(ImageButton imageButton, Class fragment, Context context) {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SFXSound.playSFX(context, R.raw.click2);

                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_left, R.anim.exit_left, R.anim.enter_right, R.anim.exit_right);
                ft.replace(R.id.fragment_container, fragment, null);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }
}