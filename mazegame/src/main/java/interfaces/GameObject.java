package interfaces;

/**
 * GameObject interface represents an object in the game world that can
 * interact with other game objects or the terrain.
 * It provides the necessary methods to determine if an object can move,
 * move the object in a specified direction, and get the object's row and
 * column coordinates.
 */
public interface GameObject {

    /**
     * Directions enumeration represents the four cardinal directions
     * (left, right, up, down) with associated vertical and horizontal move values.
     */
    public enum Directions{
        LEFT(0, -1),
        RIGHT(0, 1),
        UP(-1, 0),
        DOWN(1, 0);

        private int verticalMove;
        private int horizontalMove;

        /**
         * Constructs a Directions instance with the specified vertical and
         * horizontal move values.
         *
         * @param verticalMove The vertical move value.
         * @param horizontalMove The horizontal move value.
         */
        Directions(int verticalMove, int horizontalMove) {
            this.verticalMove = verticalMove;
            this.horizontalMove = horizontalMove;
        }

        /**
         * Moves the provided coordinates by the vertical and horizontal move
         * values associated with the Directions instance.
         *
         * @param coordinates An array containing the row and column coordinates.
         * @return The updated coordinates array.
         */
        public int[] move(int coordinates[]) {
            coordinates[0] += verticalMove;
            coordinates[1] += horizontalMove;
            return coordinates;
        }

        /**
         * This method should be implemented to move the object by the vertical
         * and horizontal move values associated with the Directions instance.
         *
         * @param row The current row coordinate.
         * @param col The current column coordinate.
         * @return The updated coordinates as an int array, or null if not implemented.
         */
        public int[] move(int row, int col) {
            return null;
        }
    }

    /**
     * Determines if the object can move in the specified direction.
     *
     * @param direction The direction in which to check for movement.
     * @return true if the object can move in the specified direction, false otherwise.
     */
    boolean canMove(Directions direction);

    /**
     * Moves the object in the specified direction.
     *
     * @param direction The direction in which to move the object.
     * @return true if the object was successfully moved, false otherwise.
     */
    boolean move(Directions direction);

    /**
     * Returns the row coordinate of the object.
     *
     * @return The row coordinate.
     */
    int getRow();

    /**
     * Returns the column coordinate of the object.
     *
     * @return The column coordinate.
     */
    int getCol();
}
