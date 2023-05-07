// Project Title: IJA 2022/23: Pac-Man Game Design and Implementation
// Autors: Maksim Naumenko  (xnaume01)
//         Tatiana Fedorova (xfedor14)

package model.game_object;

import interfaces.GameObject;
import model.Map;

/**
 * PacMan class represents the main character of the game.
 * It implements the GameObject interface and provides methods
 * for interaction, movement, and position retrieval.
 */
public class PacMan implements GameObject {
    private int row;
    private int col;
    private boolean keyFlag = false;
    private Map map;
    private int lives = 3;

    /**
     * Constructs a new PacMan instance with the specified initial row and column.
     *
     * @param map The game map.
     * @param row The initial row position of PacMan.
     * @param col The initial column position of PacMan.
     */
    public PacMan(Map map, int row, int col) {
        this.map = map;
        this.row = row;
        this.col = col;
    }

    /**
     * Checks if PacMan can move in the specified direction.
     *
     * @param direction The direction to check for possible movement.
     * @return true if PacMan can move in the specified direction, false otherwise.
     */
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

    /**
     * Moves PacMan in the specified direction.
     *
     * @param direction The direction to move PacMan in.
     * @return true if PacMan successfully moved in the specified direction, false otherwise.
     */
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

    /**
     * Moves PacMan to the specified row and column.
     *
     * @param row The new row position of PacMan.
     * @param col The new column position of PacMan.
     * @return true if PacMan successfully moved to the specified row and column.
     */
    public boolean move(int row, int col) {
        map.getMapField(this.row, this.col).removeObject(this);
        this.row = row;
        this.col = col;
        map.getMapField(this.row, this.col ).putObject(this);
        return true;
    }

    /**
     * Returns the row position of PacMan.
     *
     * @return The row position of PacMan.
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Returns the column position of PacMan.
     *
     * @return The column position of PacMan.
     */
    @Override
    public int getCol() {
        return col;
    }

    /**
     * Returns whether PacMan has picked up the key.
     *
     * @return true if PacMan has picked up the key, false otherwise.
     */
    public boolean isKeyFlag() {
        return keyFlag;
    }

    /**
     * Sets the key flag for PacMan.
     *
     * @param keyFlag The new value of the key flag.
     */
    public void setKeyFlag(boolean keyFlag) {
        this.keyFlag = keyFlag;
    }

    /**
     * Reduces the number of lives of PacMan by one.
     */
    public void reduceLives() {
        this.lives--;
    }

    /**
     * Returns the number of lives PacMan has.
     *
     * @return The number of lives of PacMan.
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * Sets the number of lives for PacMan.
     *
     * @param lives The new number of lives for PacMan.
     */
    public void setLives(int lives) {
        this.lives = lives;
    }
}