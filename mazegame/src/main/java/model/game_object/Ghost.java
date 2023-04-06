package model.game_object;

import java.util.Random;

import interfaces.GameObject;
import model.Map;

public class Ghost implements GameObject {
    private int row;
    private int col;
    private Map map;
    private Directions direction;
    private int stepCounter = 0;
    private int changeDirection = new Random().nextInt(4) + 1;


    public Ghost(Map map, int row, int col, Directions direction) {
        this.map = map;
        this.row = row;
        this.col = col;
        this.direction = direction;
    }

     @Override
    public boolean canMove(Directions dir) {
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
    public boolean move(Directions dir) {
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
            stepCounter++;
            if(stepCounter == changeDirection) {
                this.direction = Directions.values()[new Random().nextInt(Directions.values().length)];        
                stepCounter = 0;
            }
            return true;
        }
        this.direction = Directions.values()[new Random().nextInt(Directions.values().length)];
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
