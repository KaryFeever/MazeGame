package model.game_object;

import java.util.Random;
import interfaces.GameObject;
import model.Map;

/**
 * Ghost class represents a ghost character in the game.
 * It implements the GameObject interface and provides methods
 * for movement and interacting with the game map.
 */
public class Ghost implements GameObject {
    private int row;
    private int col;
    private Map map;
    private Directions direction;
    private int stepCounter = 0;
    private int changeDirection = new Random().nextInt(4) + 1;

    /**
     * Constructs a Ghost object with the specified map, row, column, and direction.
     *
     * @param map       the map where the Ghost is placed
     * @param row       the row of the Ghost's initial position
     * @param col       the column of the Ghost's initial position
     * @param direction the initial direction of the Ghost
     */
    public Ghost(Map map, int row, int col, Directions direction) {
        this.map = map;
        this.row = row;
        this.col = col;
        this.direction = direction;
    }

    /**
     * Checks if the ghost can move in the specified direction.
     *
     * @param dir The direction to check for possible movement.
     * @return true if the ghost can move in the specified direction, false otherwise.
     */
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

    /**
     * Attempts to move the Ghost in the specified direction.
     * If the move is successful, returns true, otherwise, returns false.
     *
     * @param dir the direction to move the Ghost
     * @return true if the Ghost moved successfully, false otherwise
     */
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

    /**
     * Moves the Ghost to the specified row and column.
     *
     * @param row the new row for the Ghost
     * @param col the new column for the Ghost
     * @return true if the move is successful
     */
    public boolean move(int row, int col) {
        map.getMapField(this.row, this.col).removeObject(this);
        this.row = row;
        this.col = col;
        map.getMapField(this.row, this.col).putObject(this);
        return true;
    }

    /**
     * Returns the row position of the ghost.
     *
     * @return The row position of the ghost.
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Returns the column position of the ghost.
     *
     * @return The column position of the ghost.
     */
    @Override
    public int getCol() {
        return col;
    }

    /**
     * Gets the current direction of the Ghost.
     *
     * @return the current direction of the Ghost
     */
    public Directions getDirection() {
        return this.direction;
    }

    /**
     * Sets the direction of the Ghost.
     *
     * @param direction the new direction for the Ghost
     */
    public void setDirection(Directions direction) {
        this.direction = direction;
    }
}