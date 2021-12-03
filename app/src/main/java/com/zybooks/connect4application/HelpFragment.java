package com.zybooks.connect4application;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HelpFragment extends Fragment {

    private SFXSound sfx;
    public static boolean isInflated = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflates parent view
        View parentView = inflater.inflate(R.layout.fragment_help, container, false);

        isInflated = true;
        sfx = new SFXSound(this.requireActivity());
        final Button needHelp = parentView.findViewById(R.id.help_more_information);
        final Button noHelp = parentView.findViewById(R.id.no_help_needed);

        openFragmentOnClick(this.requireActivity(), needHelp, FragmentNeedInfo.class, R.id.small_fragment_container);

        openFragmentOnClick(this.requireActivity(), noHelp, FragmentDontNeedInfo.class, R.id.small_fragment_container);

        // outside container for clicking out
        parentView.setOnClickListener(view -> {
            if (FragmentNeedInfo.isInflated || FragmentDontNeedInfo.isInflated) {
                FragmentManager fm;
                fm = getParentFragmentManager();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                fm.popBackStack();
                ft.commit();
                FragmentNeedInfo.isInflated = false;
                FragmentDontNeedInfo.isInflated = false;
            }
        });
        // BUTTON ANIMATION NEED TO GET WORKING WITH FRAGMENTS
        //Animation bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_anim);
        // needHelp.setOnClickListener(view -> needHelp.startAnimation(bounceAnimation));

        Miscellaneous.setNotificationBarColor(this.requireActivity());
        return parentView;
    }

    private void openFragmentOnClick(Context context, Button Button, Class fragment, int container) {
        Button.setOnClickListener(view -> {
            if (!FragmentDontNeedInfo.isInflated && !FragmentNeedInfo.isInflated) {
                sfx.playSFX(SFXSound.sfxClick, SFXSound.sfxClickCount, context);
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.pop_open, R.anim.pop_open, R.anim.pop_close, R.anim.pop_close);
                ft.add(container, fragment, null);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isInflated = false;
    }
}