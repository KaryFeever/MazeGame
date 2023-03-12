package model.terrain;

import interfaces.GameObject;
import interfaces.Terrain;

public class Wall implements Terrain {

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public GameObject getObject() {
        return null;
    }

    @Override
    public boolean putObject(GameObject object) {
        return false;
    }

    @Override
    public boolean removeObject() {
        return false;
    }
    
}
