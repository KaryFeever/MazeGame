package model.game_object;

import interfaces.GameObject;

/**
 * Key class represents a key object in the game.
 * It implements the GameObject interface and provides methods
 * for interaction and position retrieval.
 */
public class Key implements GameObject {
    private int row;
    private int col;

    /**
     * Constructs a new Key instance with the specified initial row and column.
     *
     * @param row The initial row position of the key.
     * @param col The initial column position of the key.
     */
    public Key(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Checks if the key can move in the specified direction.
     * As keys are static objects, this method always returns false.
     *
     * @param direction The direction to check for possible movement.
     * @return false as keys cannot move.
     */
    @Override
    public boolean canMove(Directions direction) {
        return false;
    }

    /**
     * Moves the key in the specified direction.
     * As keys are static objects, this method always returns false.
     *
     * @param direction The direction to move the key in.
     * @return false as keys cannot move.
     */
    @Override
    public boolean move(Directions direction) {
        return false;
    }

    /**
     * Returns the row position of the key.
     *
     * @return The row position of the key.
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Returns the column position of the key.
     *
     * @return The column position of the key.
     */
    @Override
    public int getCol() {
        return col;
    }
}
