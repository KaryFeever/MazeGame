package model;

import interfaces.GameObject;
import interfaces.Terrain;
import model.game_object.Ghost;
import model.game_object.Key;
import model.game_object.PacMan;
import model.game_object.Target;
import model.terrain.Field;
import model.terrain.Wall;

/**
 * The Map class represents the game map and contains information about the structure
 * and layout of the terrain and game objects within the game.
 */
public class Map {
    private String name;
    private int rows;
    private int cols;
    private Terrain[][] mapField;

    /**
     * Constructor for the Map class. Initializes the map with specified dimensions
     * and name, and creates a Wall border around the map.
     *
     * @param rows - the number of rows in the map.
     * @param cols - the number of columns in the map.
     * @param name - the name of the map.
     */
    public Map(int rows, int cols, String name) {
        this.rows = rows+2;
        this.cols = cols+2;
        this.name = name;
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

    /**
     * Sets the number of rows in the map.
     *
     * @param rows - the number of rows.
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Sets the number of columns in the map.
     * 
     * @param cols the number of columns
     */
    public void setCols(int cols) {
        this.cols = cols;
    }

    /**
     * Returns the number of rows in the map.
     * 
     * @return the number of rows in the map
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of columns in the map.
     * 
     * @return the number of columns
     */
    public int getCols() {
        return cols;
    }

    /**
     * Retrieves the Terrain object at the specified row and column in the map.
     *
     * @param row - the row index.
     * @param col - the column index.
     * @return Terrain - the Terrain object at the specified location.
     */
    public Terrain getMapField(int row, int col) {
        return mapField[row][col];
    }

    /**
     * Sets the terrain at the specified row and column coordinates in the mapField.
     * 
     * @param row the row coordinate of the mapField to set
     * @param col the column coordinate of the mapField to set
     * @param terrain the terrain to set at the specified row and column coordinates
     * @return true if the terrain was successfully set, false otherwise (if the mapField is already occupied)
     */
    public boolean setMapField(int row, int col, Terrain terrain) {
        if(mapField[row + 1][col + 1] == null) {
            mapField[row + 1][col + 1] = terrain;
            return true;
        }
        return false;
    }

    /**
     * Converts the map into a printable String representation.
     *
     * @return String - the printable String representation of the map.
     */
    public String printMap() {
        String mapString = "" + (rows - 2) + " " + (cols - 2) + "\n";
        for(int row = 1; row < rows - 1; row++) {
            for(int col = 1; col < cols - 1; col++) {
                if(mapField[row][col].getClass() == Wall.class) {
                    // System.out.print("#");
                    mapString += "X";
                } else if (mapField[row][col].getClass() == Field.class) {
                        if (mapField[row][col].getObject().isEmpty()) {
                            mapString += ".";
                        } else { 
                            for(GameObject object : mapField[row][col].getObject()) {
                                if(object.getClass() == PacMan.class) {
                                    mapString += "S";
                                } else if (object.getClass() == Ghost.class) {
                                    mapString += "G";
                                } else if (object.getClass() == Target.class) {
                                    mapString += "T";
                                } else if (object.getClass() == Key.class) {
                                    mapString += "K";
                                }
                            }
                        }
                }
            }
            mapString += "\n";
        }
        return mapString;
    }

    /**
     * Retrieves the name of the map.
     *
     * @return String - the name of the map.
     */
    public String getName() {
        return name;
    }
}