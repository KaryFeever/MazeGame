package model.game_object;

import interfaces.GameObject;
import model.Map;

public class Ghost implements GameObject {
    private int row;
    private int col;
    private Map map;


    public Ghost(Map map, int row, int col) {
        this.map = map;
        this.row = row;
        this.col = col;
    }

     @Override
    public boolean canMove(Directions direction) {
        int coordinates[] = direction.move(row, col);
        if(map.getMapField(coordinates[0], coordinates[1]).isPassable()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean move(Directions direction) {
        if(canMove(direction)) {
            map.getMapField(row, col).removeObject();
            this.row = direction.move(row, col)[0];
            this.col = direction.move(row, col)[1];
            map.getMapField(this.row, this.col ).putObject(this);
            return true;
        }
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
