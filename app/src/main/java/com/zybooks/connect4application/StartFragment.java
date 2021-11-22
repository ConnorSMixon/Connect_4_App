package com.zybooks.connect4application;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class StartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentView = inflater.inflate(R.layout.fragment_start, container, false);

        ImageButton playButton = (ImageButton) parentView.findViewById(R.id.play_button);
        playButton.setOnClickListener(view -> {
            SFXSound.playSFX(this.requireActivity(),R.raw.click2);
        });
        ImageButton optionsButton = (ImageButton) parentView.findViewById(R.id.options_button);
        optionsButton.setOnClickListener(view -> {
            SFXSound.playSFX(this.requireActivity(),R.raw.click2);
        });

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