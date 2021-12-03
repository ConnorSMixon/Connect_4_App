package com.zybooks.connect4application.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zybooks.connect4application.GameLogic.Board;
import com.zybooks.connect4application.GameLogic.SavedData;
import com.zybooks.connect4application.R;
import com.zybooks.connect4application.utils.GamePieceHelper;
import com.zybooks.connect4application.utils.Miscellaneous;
import com.zybooks.connect4application.utils.SFXSound;

public class GameFragment extends Fragment {

    private ImageView[][] cells;
    private View boardView;
    private Board board;
    private ViewHolder viewHolder;
    private SFXSound sfx;
    private final int NUM_ROWS = 6, NUM_COLS = 7;
    private int piece1, piece2, textColor1, textColor2;

    private static class ViewHolder {
        public TextView winnerText;
        public ImageView arrowTurnIndicatorImageView1, arrowTurnIndicatorImageView2,
                pieceTurnIndicatorImageView1, pieceTurnIndicatorImageView2;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentView = inflater.inflate(R.layout.fragment_game, container, false);

        sfx = new SFXSound(this.requireActivity());

        // set piece color and text color according to sharedPreference from OptionsActivity
        piece1 = SavedData.loadInt(SavedData.PIECE_1_DATA, R.drawable.piece_red, this.requireActivity());
        piece2 = SavedData.loadInt(SavedData.PIECE_2_DATA, R.drawable.piece_yellow, this.requireActivity());

        textColor1 = GamePieceHelper.imageResourceToColor(piece1);
        textColor2 = GamePieceHelper.imageResourceToColor(piece2);

        // create board
        board = new Board(NUM_COLS, NUM_ROWS);
        boardView = parentView.findViewById(R.id.game_board);
        loadBoard();
        boardView.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_UP: {
                    int col = colAtX(motionEvent.getX());
                    if (col != -1)
                        drop(col);
                }
            }
            return true;
        });

        // change color of piece turn indicator
        viewHolder = new GameFragment.ViewHolder();
        viewHolder.pieceTurnIndicatorImageView1 = parentView.findViewById(R.id.indicator_piece1);
        viewHolder.pieceTurnIndicatorImageView2 = parentView.findViewById(R.id.indicator_piece2);
        resourceForPieceIndicator();

        // change visibility of arrow turn indicator
        viewHolder.arrowTurnIndicatorImageView1 = parentView.findViewById(R.id.turn_indicator_image_view1);
        viewHolder.arrowTurnIndicatorImageView2 = parentView.findViewById(R.id.turn_indicator_image_view2);
        visibilityForTurnIndicator();

        // change visibility of winner message
        viewHolder.winnerText = parentView.findViewById(R.id.winner_text);
        viewHolder.winnerText.setVisibility(View.GONE);

        // on click listener for up button
        ImageView upButton = parentView.findViewById(R.id.gameActivityBackArrow);
        previousFragment(upButton);

        //on click listener for pause button
        ImageView pauseButton = parentView.findViewById(R.id.pause_button);
        pauseMenu(pauseButton);

        // on click listener for reset button
        ImageButton resetButton = parentView.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(view -> {
            reset();
            sfx.playSFX(SFXSound.sfxClick, SFXSound.sfxClickCount, this.requireActivity());
        });

        // change color or notification bar
        Miscellaneous.setNotificationBarColor(this.requireActivity());

        return parentView;
    }

    private void drop(int col) {
        if (board.hasWinner)
            return;

        int row = board.lastAvailableRow(col);
        if (row == -1)
            return;

        final ImageView cell = cells[row][col];

        float move = -(cell.getHeight() * row + cell.getHeight() + 100);
        cell.setY(move);
        cell.setImageResource(resourceForPiece());
        saveBoard(row, col);
        TranslateAnimation anim = new TranslateAnimation(0, 0, 0, Math.abs(move));
        anim.setInterpolator(new BounceInterpolator());
        anim.setFillAfter(true);
        anim.setDuration(1100);
        cell.startAnimation(anim);
        cell.animate().alpha(1f).setDuration(0);
        board.occupyCell(col, row);

        if (board.checkForWin()) {
            win();
        } else {
            changeTurn();
        }
    }

    private void saveBoard(int row, int col) {
        String boardValue;
        if (cells[row][col].getDrawable().getConstantState() == getResources().getDrawable(piece1).getConstantState()) {
            boardValue = "1";
        } else if (cells[row][col].getDrawable().getConstantState() == getResources().getDrawable(piece2).getConstantState()) {
            boardValue = "2";
        } else {
            boardValue = "0";
        }
        String key = row + Integer.toString(col);
        SavedData.saveString(key, boardValue, this.requireActivity());
    }

    private void loadBoard() {
        cells = new ImageView[NUM_ROWS][NUM_COLS];
        for (int r=0; r<NUM_ROWS; r++) {
            ViewGroup row = (ViewGroup) ((ViewGroup) boardView).getChildAt(r);
            row.setClipChildren(false);
            for (int c=0; c<NUM_COLS; c++) {
                ImageView imageView = (ImageView) row.getChildAt(c);
                imageView.setImageResource(android.R.color.transparent);
                cells[r][c] = imageView;
            }
        }
    }
    private void win() {
        int color = board.turn == Board.Turn.FIRST ? getResources().getColor(textColor1) :
                getResources().getColor(textColor2);

        viewHolder.winnerText.animate().alpha(1f).setDuration(0);
        viewHolder.winnerText.setTextColor(color);
        viewHolder.winnerText.setVisibility(View.VISIBLE);
    }

    private void changeTurn() {
        board.toggleTurn();
        visibilityForTurnIndicator();
    }

    private int colAtX(float x) {
        float colWidth = cells[0][0].getWidth();
        int col = (int) x / (int) colWidth;
        if (col < 0 || col > 6)
            return -1;
        return col;
    }

    private int resourceForPiece() {
        if (board.turn == Board.Turn.FIRST) {
            return piece1;
        } else {
            return piece2;
        }
    }

    private void resourceForPieceIndicator() {
        viewHolder.pieceTurnIndicatorImageView1.setImageResource(piece1);
        viewHolder.pieceTurnIndicatorImageView2.setImageResource(piece2);
    }

    private void visibilityForTurnIndicator() {
        if (board.turn == Board.Turn.FIRST) {
            viewHolder.arrowTurnIndicatorImageView1.setVisibility(View.VISIBLE);
            viewHolder.arrowTurnIndicatorImageView2.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.arrowTurnIndicatorImageView2.setVisibility(View.VISIBLE);
            viewHolder.arrowTurnIndicatorImageView1.setVisibility(View.INVISIBLE);
        }
    }

    private void reset() {
        board.reset();
        viewHolder.winnerText.animate().alpha(0f).setDuration(500);
        visibilityForTurnIndicator();
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                ImageView cell = cells[r][c];
                cell.animate().alpha(0f).setDuration(500);
                String key = r + Integer.toString(c);
                SavedData.saveString(key, "0", this.requireActivity());
            }
        }
    }

    private void previousFragment(ImageView imageView) {
        imageView.setOnClickListener(view -> {
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction ft = getParentFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.enter_right, R.anim.exit_right, R.anim.enter_left, R.anim.exit_left);
            fm.popBackStack();
            ft.commit();
        });
    }

    private void pauseMenu(ImageView imageView) {
        imageView.setOnClickListener(view -> {
            FragmentTransaction ft = getParentFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.pop_open, R.anim.pop_open, R.anim.pop_close, R.anim.pop_close);
            ft.add(R.id.fragment_container, PauseFragment.class, null);
            ft.addToBackStack(null);
            ft.commit();
        });
    }
}
