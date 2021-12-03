package com.C4.connect4application.Fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.C4.connect4application.R;
import com.C4.connect4application.utils.Miscellaneous;
import com.C4.connect4application.utils.SFXSound;

public class HelpFragment extends Fragment {
    private SFXSound sfx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflates parent view
        View parentView = inflater.inflate(R.layout.fragment_help, container, false);

        sfx = new SFXSound(this.requireActivity());
        final Button needHelp = parentView.findViewById(R.id.help_more_information);
        final Button noHelp = parentView.findViewById(R.id.no_help_needed);

        openFragmentOnClick(this.requireActivity(), R.id.small_fragment_container_help, needHelp, FragmentNeedInfo.class,
                R.anim.enter_left, R.anim.exit_left, R.anim.enter_right, R.anim.exit_right);
        openFragmentOnClick(this.requireActivity(), R.id.small_fragment_container_help, noHelp,
                FragmentDontNeedInfo.class, R.anim.pop_open, R.anim.pop_open, R.anim.pop_close, R.anim.pop_close);

        // outside container for clicking out
        parentView.setOnClickListener(view -> {
            FragmentManager fm;
            fm = getFragmentManager();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            fm.popBackStack();
            ft.commit();
        });
        // BUTTON ANIMATION NEED TO GET WORKING WITH FRAGMENTS
        //Animation bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_anim);
        // needHelp.setOnClickListener(view -> needHelp.startAnimation(bounceAnimation));

        Miscellaneous.setNotificationBarColor(this.requireActivity());
        return parentView;
    }

    private void openFragmentOnClick(Context context, int cont, Button Button, Class fragment,
                                     int anim1, int anim2, int anim3, int anim4) {
        Button.setOnClickListener(view -> {
            sfx.playSFX(SFXSound.sfxClick, SFXSound.sfxClickCount, context);
            FragmentTransaction ft = getParentFragmentManager().beginTransaction();
            ft.setCustomAnimations(anim1, anim2, anim3, anim4);
            ft.add(cont, fragment, null);
            ft.addToBackStack(null);
            ft.commit();
        });

    }
}