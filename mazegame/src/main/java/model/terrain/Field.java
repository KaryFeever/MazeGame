package model.terrain;

import interfaces.GameObject;
import interfaces.Terrain;

public class Field implements Terrain {
    private boolean emptyFlag = true;
    private GameObject object;


    @Override
    public boolean isEmpty() {
        return emptyFlag;
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public GameObject getObject() {
        return object;
    }

    @Override
    public boolean putObject(GameObject object) {
        if(isEmpty()) {
            this.object = object;
            emptyFlag = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeObject() {
        if(!isEmpty()) {
            this.object = null;
            emptyFlag = true;
            return true;
        }
        return false;
    }
    
}
