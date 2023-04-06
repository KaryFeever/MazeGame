package model;

import interfaces.GameObject;
import interfaces.Terrain;
import model.game_object.Ghost;
import model.game_object.Key;
import model.game_object.PacMan;
import model.game_object.Target;
import model.terrain.Field;
import model.terrain.Wall;

public class Map {
    private int rows;
    private int cols;
    private Terrain[][] mapField;

    public Map(int rows, int cols) {
        this.rows = rows+2;
        this.cols = cols+2;
        mapField = new Terrain[this.rows][this.cols];
        for(int row = 0; row < this.rows; row++) {
            mapField[row][0] = new Wall();
            mapField[row][this.cols - 1] = new Wall();
        }
        for(int col = 1; col < this.cols - 1; col++) {
            mapField[0][col] = new Wall();
            mapField[this.rows - 1][col] = new Wall();
        }
    }


    public void setRows(int rows) {
        this.rows = rows;
    }
    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
    public Terrain getMapField(int row, int col) {
        return mapField[row][col];
    }

    public boolean setMapField(int row, int col, Terrain terrain) {
        if(mapField[row + 1][col + 1] == null) {
            mapField[row + 1][col + 1] = terrain;
            return true;
        }
        return false;
    }

    public void printMap() {
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(mapField[row][col].getClass() == Wall.class) {
                    System.out.print("#");
                } else if (mapField[row][col].getClass() == Field.class) {
                        if (mapField[row][col].getObject().isEmpty()) {
                            System.out.print(".");
                        } else { 
                            for(GameObject object : mapField[row][col].getObject()) {
                                if(object.getClass() == PacMan.class) {
                                    System.out.print("C");
                                } else if (object.getClass() == Ghost.class) {
                                    System.out.print("^");
                                } else if (object.getClass() == Target.class) {
                                    System.out.print("T");
                                } else if (object.getClass() == Key.class) {
                                    System.out.print("K");
                                }
                            }
                        }
                }
            }
            System.out.println();
        }
    }

    
    
}
