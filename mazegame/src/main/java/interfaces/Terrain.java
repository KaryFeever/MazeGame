package interfaces;

public interface Terrain {
    boolean isEmpty();
    boolean isPassable();
    GameObject getObject();
    boolean putObject(GameObject object);
    boolean removeObject();
}
