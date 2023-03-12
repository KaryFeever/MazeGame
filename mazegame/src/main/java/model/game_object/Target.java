package model.game_object;

import interfaces.GameObject;

public class Target implements GameObject {
    private int row;
    private int col;

    public Target(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean canMove(Directions direction) {
        return false;
    }

    @Override
    public boolean move(Directions direction) {
        return false;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }
}
