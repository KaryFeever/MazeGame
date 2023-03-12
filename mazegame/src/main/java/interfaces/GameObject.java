package interfaces;

public interface GameObject {
    public enum Directions{
        LEFT(0, -1),
        RIGHT(0, 1),
        UP(-1, 0),
        DOWN(1, 0);

        private int verticalMove;
        private int horizontalMove;

        Directions(int verticalMove, int horizontalMove) {
            this.verticalMove = verticalMove;
            this.horizontalMove = horizontalMove;
        }

        public int[] move(int coordinates[]) {
            coordinates[0] += verticalMove;
            coordinates[1] += horizontalMove;
            return coordinates;
        }

        public int[] move(int row, int col) {
            return null;
        }
    }

    boolean canMove(Directions direction);
    boolean move(Directions direction);
    int getRow();
    int getCol();
}
