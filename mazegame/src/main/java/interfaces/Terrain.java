package interfaces;

import java.util.List;

public interface Terrain {
    boolean isEmpty();
    boolean isPassable();
    List<GameObject> getObject();
    boolean putObject(GameObject object);
    boolean removeObject(GameObject object);
}
