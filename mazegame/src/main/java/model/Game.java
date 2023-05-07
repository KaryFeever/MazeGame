// Project Title: IJA 2022/23: Pac-Man Game Design and Implementation
// Autors: Maksim Naumenko  (xnaume01)
//         Tatiana Fedorova (xfedor14)

package model;

import java.util.ArrayList;
import java.util.List;
import interfaces.GameObject;
import interfaces.GameObject.Directions;
import model.game_object.Ghost;
import model.game_object.Key;
import model.game_object.PacMan;
import model.game_object.Target;
import model.terrain.Field;

/**
 * Game class is the central class of the game. It contains the game map, characters, and other game objects.
 * This class also manages the game logic and object interactions.
 */
public class Game {
    private Map map;
    private PacMan pacMan;
    private List<Ghost> ghosts;
    private Key key;
    private Target target;

    /**
     * Constructor for Game class. Initializes the game map and game objects.
     *
     * @param map - the Map object that represents the game map.
     */
    public Game(Map map) {
        this.map = map;
        this.ghosts = new ArrayList<Ghost>();
        this.initializeObjects();
    }

    /**
     * Initializes game objects from the map fields.
     */
    private void initializeObjects() {
        for(int row = 0; row < map.getRows(); row++) {
            for(int col = 0; col < map.getCols(); col++) {
                if(map.getMapField(row, col).getClass() == Field.class) {
                    if(map.getMapField(row, col).isEmpty()) {
                        continue;
                    } else {
                        for(GameObject object : map.getMapField(row, col).getObject()) {
                            if(object.getClass() == PacMan.class) {
                                pacMan = (PacMan) object;
                            }
                            if(object.getClass() == Ghost.class) {
                                ghosts.add((Ghost) object); 
                            }
                            if(object.getClass() == Key.class) {
                                key = (Key) object;
                            }
                            if(object.getClass() == Target.class) {
                                target = (Target) object;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Moves the PacMan character according to the given direction.
     *
     * @param direction - the Directions object representing the direction in which the PacMan should move.
     */
    public void movePacman(Directions direction) {
        pacMan.move(direction);
    }

    /**
     * Gets the list of Ghost objects in the game.
     *
     * @return {@code List<Ghost>} - the list of ghosts in the game.
     */
    public List<Ghost> getGhosts() {
        return this.ghosts;
    }

    /**
     * Gets the PacMan object in the game.
     *
     * @return PacMan - the PacMan character in the game.
     */
    public PacMan getPacMan() {
        return this.pacMan;
    }

    /**
     * Gets the Key object in the game.
     *
     * @return Key - the key object in the game.
     */
    public Key getKey() {
        return this.key;
    }

    /**
     * Gets the Target object in the game.
     *
     * @return Target - the target object in the game.
     */
    public Target getTarget() {
        return this.target;
    }

    /**
     * Gets the Map object representing the game map.
     *
     * @return Map - the game map.
     */
    public Map getMap() {
        return this.map;
    }
}