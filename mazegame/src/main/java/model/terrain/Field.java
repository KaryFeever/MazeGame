package model.terrain;

import java.util.ArrayList;
import java.util.List;
import interfaces.GameObject;
import interfaces.Terrain;

/**
 * Field class represents a passable terrain in the game. This terrain can contain game objects
 * and can be traversed by game characters.
 */
public class Field implements Terrain {
    private boolean emptyFlag = true;
    private List<GameObject> objects = new ArrayList<GameObject>();

    /**
     * Check if the field is empty or not.
     *
     * @return boolean - true if the field is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return emptyFlag;
    }

    /**
     * Check if the field is passable or not.
     *
     * @return boolean - true if the field is passable, false otherwise.
     */
    @Override
    public boolean isPassable() {
        return true;
    }

    /**
     * Get the list of game objects on the field.
     *
     * @return List<GameObject> - the list of game objects on the field.
     */
    @Override
    public List<GameObject> getObject() {
        return this.objects;
    }

    /**
     * Add a game object to the field.
     *
     * @param object - the game object to be added to the field.
     * @return boolean - true if the object is added successfully, false otherwise.
     */
    @Override
    public boolean putObject(GameObject object) {
        this.objects.add(object);
        emptyFlag = false;
        return true;
    }

    /**
     * Remove a game object from the field.
     *
     * @param object - the game object to be removed from the field.
     * @return boolean - true if the object is removed successfully, false otherwise.
     */
    @Override
    public boolean removeObject(GameObject object) {
        if(!isEmpty()) {
            this.objects.remove(object);
            if(this.objects.isEmpty()) {
                emptyFlag = true;
            }
            return true;
        }
        return false;
    }
}