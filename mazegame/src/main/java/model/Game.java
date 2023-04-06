package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import controller.MapParser;
import interfaces.GameObject;
import interfaces.GameObject.Directions;
import model.game_object.Ghost;
import model.game_object.Key;
import model.game_object.PacMan;
import model.game_object.Target;
import model.terrain.Field;
import model.terrain.Wall;

public class Game {
    private Map map;
    private PacMan pacMan;
    private List<Ghost> ghosts;
    private Key key;
    private Target target;

    public Game(Map map) {
        this.map = map;
        this.ghosts = new ArrayList<Ghost>();
        this.initializeObjects();

    }

    private void initializeObjects() {
        for(int row = 0; row < map.getRows(); row++) {
            for(int col = 0; col < map.getCols(); col++) {
                if(map.getMapField(row, col).getClass() == Field.class) {
                    if(map.getMapField(row, col).isEmpty()) {
                        continue;
                    }
                    if(map.getMapField(row, col).getObject().getClass() == PacMan.class) {
                        pacMan = (PacMan) map.getMapField(row, col).getObject();
                    }
                    if(map.getMapField(row, col).getObject().getClass() == Ghost.class) {
                        ghosts.add((Ghost) map.getMapField(row, col).getObject()); 
                    }
                    if(map.getMapField(row, col).getObject().getClass() == Key.class) {
                        key = (Key) map.getMapField(row, col).getObject();
                    }
                    if(map.getMapField(row, col).getObject().getClass() == Target.class) {
                        target = (Target) map.getMapField(row, col).getObject();
                    }
                }
            }
        }
    }

    public void movePacman(Directions direction) {
        pacMan.move(direction);
    }

    public List<Ghost> getGhosts() {
        return this.ghosts;
    }

}
