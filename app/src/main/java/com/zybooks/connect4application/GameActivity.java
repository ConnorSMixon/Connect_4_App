package com.zybooks.connect4application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
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
    private final int NUM_ROWS = 6;
    private final int NUM_COLS = 7;

    private static class ViewHolder {
        public TextView winnerText;
        public ImageView turnIndicatorImageView1;
        public ImageView turnIndicatorImageView2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
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
        viewHolder = new ViewHolder();
        viewHolder.turnIndicatorImageView1 = (ImageView) findViewById(R.id.turn_indicator_image_view1);
        viewHolder.turnIndicatorImageView2 = (ImageView) findViewById(R.id.turn_indicator_image_view2);
        resourceForIndicator();
        viewHolder.winnerText = (TextView) findViewById(R.id.winner_text);
        viewHolder.winnerText.setVisibility(View.GONE);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        cell.setImageResource(resourceForTurn());
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
        int color = board.turn == Board.Turn.FIRST ? getResources().getColor(R.color.primary_player)
                : getResources().getColor(R.color.secondary_player);
        viewHolder.winnerText.setTextColor(color);
        viewHolder.winnerText.setVisibility(View.VISIBLE);
    }

    private void changeTurn() {
        board.toggleTurn();
        resourceForIndicator();
    }

    private int colAtX(float x) {
        float colWidth = cells[0][0].getWidth();
        int col = (int) x / (int) colWidth;
        if (col < 0 || col > 6)
            return -1;
        return col;
    }

    private int resourceForTurn() {
        switch (board.turn) {
            case FIRST:
                return R.drawable.piece_one;
            case SECOND:
                return R.drawable.piece_two;
        }
        return R.drawable.piece_one;
    }

    private void resourceForIndicator() {
        if(board.turn == board.turn.FIRST) {
            viewHolder.turnIndicatorImageView1.setVisibility(View.VISIBLE);
            viewHolder.turnIndicatorImageView2.setVisibility(View.INVISIBLE);
        }
        else {
            viewHolder.turnIndicatorImageView2.setVisibility(View.VISIBLE);
            viewHolder.turnIndicatorImageView1.setVisibility(View.INVISIBLE);
        }
    }

    private void reset() {
        board.reset();
        viewHolder.winnerText.setVisibility(View.GONE);
        resourceForIndicator();
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                cells[r][c].setImageResource(android.R.color.transparent);
            }
        }
    }


}
