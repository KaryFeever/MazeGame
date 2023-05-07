// Project Title: IJA 2022/23: Pac-Man Game Design and Implementation
// Autors: Maksim Naumenko  (xnaume01)
//         Tatiana Fedorova (xfedor14)

package model.terrain;

import java.util.List;
import interfaces.GameObject;
import interfaces.Terrain;

/**
 * Wall class represents an impassable terrain in the game. This terrain cannot contain game objects
 * and cannot be traversed by game characters.
 */
public class Wall implements Terrain {
    /**
     * Check if the wall is empty or not.
     *
     * @return boolean - always returns false as the wall cannot contain any game objects.
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Check if the wall is passable or not.
     *
     * @return boolean - always returns false as the wall is not passable.
     */
    @Override
    public boolean isPassable() {
        return false;
    }

    /**
     * Get the list of game objects on the wall.
     *
     * @return {@code List<GameObject>} - always returns null as the wall cannot contain any game objects.
     */
    @Override
    public List<GameObject> getObject() {
        return null;
    }

    /**
     * Add a game object to the wall.
     *
     * @param object - the game object to be added to the wall.
     * @return boolean - always returns false as the wall cannot contain any game objects.
     */
    @Override
    public boolean putObject(GameObject object) {
        return false;
    }

    /**
     * Remove a game object from the wall.
     *
     * @param object - the game object to be removed from the wall.
     * @return boolean - always returns false as the wall cannot contain any game objects.
     */
    @Override
    public boolean removeObject(GameObject object) {
        return false;
    }
}