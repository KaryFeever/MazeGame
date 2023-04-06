package model.terrain;

import java.util.ArrayList;
import java.util.List;

import interfaces.GameObject;
import interfaces.Terrain;
import model.Game;

public class Field implements Terrain {
    private boolean emptyFlag = true;
    private List<GameObject> objects = new ArrayList<GameObject>();


    @Override
    public boolean isEmpty() {
        return emptyFlag;
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public List<GameObject> getObject() {
        return this.objects;
    }

    @Override
    public boolean putObject(GameObject object) {
        this.objects.add(object);
        emptyFlag = false;
        return true;
    }

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
