package model.game_object;

import interfaces.GameObject;
import model.Map;

public class PacMan implements GameObject {
    private int row;
    private int col;
    private boolean keyFlag = false;
    private Map map;
    private int lives = 3;

    public PacMan(Map map, int row, int col) {
        this.map = map;
        this.row = row;
        this.col = col;
    }


    @Override
    public boolean canMove(Directions direction) {
        int rowMove = 0;
        int colMove = 0;
        switch(direction) {
            case LEFT:
                colMove = -1;
                break;
            case RIGHT:
                colMove = 1;
                break;
            case UP:
                rowMove = -1;
                break;
            case DOWN:
                rowMove = 1;
                break;
        }
        if(map.getMapField(row + rowMove, col + colMove).isPassable()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean move(Directions direction) {
        if(canMove(direction)) {
            int rowMove = 0;
            int colMove = 0;
            switch(direction) {
                case LEFT:
                    colMove = -1;
                    break;
                case RIGHT:
                    colMove = 1;
                    break;
                case UP:
                    rowMove = -1;
                    break;
                case DOWN:
                    rowMove = 1;
                    break;
            }
            map.getMapField(row, col).removeObject(this);
            this.row += rowMove;
            this.col += colMove;
            map.getMapField(this.row, this.col ).putObject(this);
            return true;
        }
        return false;
    }


    public boolean move(int row, int col) {
        map.getMapField(this.row, this.col).removeObject(this);
        this.row = row;
        this.col = col;
        map.getMapField(this.row, this.col ).putObject(this);
        return true;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    public boolean isKeyFlag() {
        return keyFlag;
    }

    public void setKeyFlag(boolean keyFlag) {
        this.keyFlag = keyFlag;
    }

    public void reduceLives() {
        this.lives--;
    }

    public int getLives() {
        return this.lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    
}
