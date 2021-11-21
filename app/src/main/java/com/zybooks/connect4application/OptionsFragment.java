package com.zybooks.connect4application;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zybooks.connect4application.utils.GamePieceHelper;

public class OptionsFragment extends Fragment {
    public static int count1 = 0;
    public static int count2 = 0;

    public static int pieceData1, pieceData2;
    private ImageView imageView1, imageView2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this.requireActivity() fragment
        View parentView = inflater.inflate(R.layout.fragment_options, container, false);

        imageView1 = parentView.findViewById(R.id.piece1_selector);
        imageView2 = parentView.findViewById(R.id.piece2_selector);

        pieceData1 = SavedData.loadInt(SavedData.PIECE_1_DATA, R.drawable.piece_red, this.requireActivity());
        pieceData2 = SavedData.loadInt(SavedData.PIECE_2_DATA, R.drawable.piece_yellow, this.requireActivity());

        imageView1.setImageResource(pieceData1);
        imageView2.setImageResource(pieceData2);

        findDisplayedImage();
        circulatingImage();

        // change color of notification bar
        Miscellaneous.setNotificationBarColor(this.requireActivity());

        // on click listener for up button
        ImageView upButton = parentView.findViewById(R.id.activityOptionsBackArrow);
        Miscellaneous.previousActivity(upButton, this.requireActivity());

        return parentView;
    }

    public void circulatingImage(){
        imageView1.setOnClickListener(view -> {
            // increment count
            count1 ++;
            // check if reset is needed
            if (count1 == GamePieceHelper.numberOfGamePieces()) {
                count1 = 0;
            }
            // check for duplicates and resolve if needed
            count1 = GamePieceHelper.checkForDuplicates(count1, count2);
            // get the resource
            int imageResource = GamePieceHelper.countToImageResource(count1);
            // check if we need to reset the count.


            Drawable drawable = ContextCompat.getDrawable(this.requireActivity(), imageResource);
            imageView1.setImageDrawable(drawable);
            Animation.bounceAnimation(imageView1, this.requireActivity());

            SavedData.saveInt(SavedData.PIECE_1_DATA, imageResource, this.requireActivity());
        });

        imageView2.setOnClickListener(view -> {
            count2 ++;
            if(count2 == GamePieceHelper.numberOfGamePieces()) {
                count2 = 0;
            }
            count2 = GamePieceHelper.checkForDuplicates(count2, count1);
            int imageResource = GamePieceHelper.countToImageResource(count2);
            Drawable drawable = ContextCompat.getDrawable(this.requireActivity(), imageResource);
            imageView2.setImageDrawable(drawable);
            Animation.bounceAnimation(imageView2, this.requireActivity());

            SavedData.saveInt(SavedData.PIECE_2_DATA, imageResource, this.requireActivity());
        });
    }

    public void findDisplayedImage() {
        count1 = GamePieceHelper.imageResourceToCount(pieceData1);
        count2 = GamePieceHelper.imageResourceToCount(pieceData2);
    }
}
