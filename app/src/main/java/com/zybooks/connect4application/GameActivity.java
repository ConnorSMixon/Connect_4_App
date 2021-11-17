package com.zybooks.connect4application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity {
    private ImageView[][] cells;
    private View boardView;
    private Board board;
    private ViewHolder viewHolder;
    private final int NUM_ROWS = 6, NUM_COLS = 7;
    private int piece1, piece2, textColor1, textColor2;

    private static class ViewHolder {
        public TextView winnerText;
        public ImageView arrowTurnIndicatorImageView1, arrowTurnIndicatorImageView2,
                pieceTurnIndicatorImageView1, pieceTurnIndicatorImageView2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // set piece color and text color according to sharedPreference from OptionsActivity
        piece1 = Settings.loadData(OptionsActivity.viewKey1, OptionsActivity.imageResource1, this);
        piece2 = Settings.loadData(OptionsActivity.viewKey2, OptionsActivity.imageResource2, this);

        textColor1 = imageResourceToColor(piece1);
        textColor2 = imageResourceToColor(piece2);

        // create board
        board = new Board(NUM_COLS, NUM_ROWS);
        boardView = findViewById(R.id.game_board);
        buildCells();
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
        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(view -> reset());

        // changes color of piece turn indicator
        viewHolder = new ViewHolder();
        viewHolder.pieceTurnIndicatorImageView1 = (ImageView) findViewById(R.id.indicator_piece1);
        viewHolder.pieceTurnIndicatorImageView2 = (ImageView) findViewById(R.id.indicator_piece2);
        resourceForPieceIndicator();

        // changes visibility of arrow turn indicator
        viewHolder.arrowTurnIndicatorImageView1 = (ImageView) findViewById(R.id.turn_indicator_image_view1);
        viewHolder.arrowTurnIndicatorImageView2 = (ImageView) findViewById(R.id.turn_indicator_image_view2);
        visibilityForTurnIndicator();

        // changes visibility of winner message
        viewHolder.winnerText = (TextView) findViewById(R.id.winner_text);
        viewHolder.winnerText.setVisibility(View.GONE);

        // miscellaneous tasks
        // change color or notification bar
        Miscellaneous.notificationBarColor(this);
    }

    private void buildCells() {
        cells = new ImageView[NUM_ROWS][NUM_COLS];
        for (int r = 0; r < NUM_ROWS; r++) {
            ViewGroup row = (ViewGroup) ((ViewGroup) boardView).getChildAt(r);
            row.setClipChildren(false);
            for (int c = 0; c < NUM_COLS; c++) {
                ImageView imageView = (ImageView) row.getChildAt(c);
                imageView.setImageResource(android.R.color.transparent);
                cells[r][c] = imageView;
            }
        }
    }

    private void drop(int col) {
        if (board.hasWinner)
            return;
        int row = board.lastAvailableRow(col);
        if (row == -1)
            return;
        final ImageView cell = cells[row][col];
        float move = -(cell.getHeight() * row + cell.getHeight() + 15);
        cell.setY(move);
        cell.setImageResource(resourceForPiece());
        TranslateAnimation anim = new TranslateAnimation(0, 0, 0, Math.abs(move));
        anim.setDuration(850);
        anim.setFillAfter(true);
        cell.startAnimation(anim);
        board.occupyCell(col, row);
        if (board.checkForWin(col, row)) {
            win();
        } else {
            changeTurn();
        }
    }

    private void win() {
        int color = board.turn == Board.Turn.FIRST ? getResources().getColor(textColor1) :
                getResources().getColor(textColor2);

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
        if (board.turn == board.turn.FIRST) {
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
        if (board.turn == board.turn.FIRST) {
            viewHolder.arrowTurnIndicatorImageView1.setVisibility(View.VISIBLE);
            viewHolder.arrowTurnIndicatorImageView2.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.arrowTurnIndicatorImageView2.setVisibility(View.VISIBLE);
            viewHolder.arrowTurnIndicatorImageView1.setVisibility(View.INVISIBLE);
        }
    }

    private void reset() {
        board.reset();
        viewHolder.winnerText.setVisibility(View.GONE);
        visibilityForTurnIndicator();
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                cells[r][c].setImageResource(android.R.color.transparent);
            }
        }
    }

    private int imageResourceToColor(int piece) {
        switch (piece) {
            case R.drawable.piece_yellow:
                return R.color.yellow;
            case R.drawable.piece_orange:
                return R.color.orange;
            case R.drawable.piece_green:
                return R.color.green;
            case R.drawable.piece_blue:
                return R.color.blue;
            case R.drawable.piece_purple:
                return R.color.purple;
            case R.drawable.piece_red:
            default:
                return R.color.red;
        }
    }
}

