package com.zybooks.connect4application;

public class Cell {
    public boolean empty;
    public Board.Turn player;

    public Cell() {
        empty = true;
}

    public void setPlayer(Board.Turn player) {
        this.player = player;
        empty = false;
    }
}
