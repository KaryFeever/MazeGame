// Project Title: IJA 2022/23: Pac-Man Game Design and Implementation
// Autors: Maksim Naumenko  (xnaume01)
//         Tatiana Fedorova (xfedor14)

package interfaces;

import java.util.List;

/**
 * Terrain interface represents a cell of the game map that can contain
 * game objects. It provides methods for determining if the cell is empty,
 * passable, adding or removing game objects, and getting a list of
 * objects currently in the cell.
 */
public interface Terrain {
    /**
     * Determines if the terrain cell is empty.
     *
     * @return true if the cell is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Determines if the terrain cell is passable, meaning game objects
     * can move through it.
     *
     * @return true if the cell is passable, false otherwise.
     */
    boolean isPassable();

    /**
     * Returns a list of game objects currently in the terrain cell.
     *
     * @return A list of GameObjects in the cell.
     */
    List<GameObject> getObject();

    /**
     * Adds a game object to the terrain cell.
     *
     * @param object The GameObject to add to the cell.
     * @return true if the object was successfully added, false otherwise.
     */
    boolean putObject(GameObject object);

    /**
     * Removes a game object from the terrain cell.
     *
     * @param object The GameObject to remove from the cell.
     * @return true if the object was successfully removed, false otherwise.
     */
    boolean removeObject(GameObject object);
}
