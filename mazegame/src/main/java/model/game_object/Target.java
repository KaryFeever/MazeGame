package model.game_object;

import interfaces.GameObject;

/**
 * Target class represents a target or goal position in the game.
 * It implements the GameObject interface and provides methods
 * for position retrieval.
 */
public class Target implements GameObject {
    private int row;
    private int col;

    /**
     * Constructs a new Target instance with the specified row and column.
     *
     * @param row The row position of the target.
     * @param col The column position of the target.
     */
    public Target(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns whether the target can move in the specified direction.
     * The target cannot move, so this method always returns false.
     *
     * @param direction The direction to check for possible movement.
     * @return false, as the target cannot move.
     */
    @Override
    public boolean canMove(Directions direction) {
        return false;
    }

    /**
     * Attempts to move the target in the specified direction.
     * The target cannot move, so this method always returns false.
     *
     * @param direction The direction to attempt to move the target in.
     * @return false, as the target cannot move.
     */
    @Override
    public boolean move(Directions direction) {
        return false;
    }

    /**
     * Returns the row position of the target.
     *
     * @return The row position of the target.
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Returns the column position of the target.
     *
     * @return The column position of the target.
     */
    @Override
    public int getCol() {
        return col;
    }
}