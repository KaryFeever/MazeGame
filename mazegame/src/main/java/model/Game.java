package model;

import java.io.File;
import java.util.ArrayList;

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
    private ArrayList<Map> maps = new ArrayList<Map>();
    private Map selectedMap;
    private File directory = new File("data/maps");

    public Game() {
        configureMaps();
        printMap();
        GameObject pacMan = maps.get(0).getMapField(9, 1).getObject();
        pacMan.move(Directions.RIGHT);
        System.out.println();
        printMap();
        pacMan.move(Directions.RIGHT);
        System.out.println();
        printMap();
        pacMan.move(Directions.RIGHT);
        System.out.println();
    }

    
    private void printMap() {
        for(int row = 0; row < maps.get(0).getRows(); row++) {
            for(int col = 0; col < maps.get(0).getCols(); col++) {
                if(maps.get(0).getMapField(row, col).getClass() == Wall.class) {
                    System.out.print("#");
                } else if (maps.get(0).getMapField(row, col).getClass() == Field.class) {
                        if (maps.get(0).getMapField(row, col).getObject() == null) {
                            System.out.print(".");
                        } else { 
                            if(maps.get(0).getMapField(row, col).getObject().getClass() == PacMan.class) {
                            System.out.print("C");
                        } else if (maps.get(0).getMapField(row, col).getObject().getClass() == Ghost.class) {
                            System.out.print("^");
                        } else if (maps.get(0).getMapField(row, col).getObject().getClass() == Target.class) {
                            System.out.print("T");
                        } else if (maps.get(0).getMapField(row, col).getObject().getClass() == Key.class) {
                            System.out.print("K");
                        }
                    }
                }
            }
            System.out.println();
        }
    }

    private void configureMaps() {
        MapParser parser = new MapParser();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                Map map = parser.parseMap(file);
                if(map != null) {
                    maps.add(map);
                }
            }
        }
    }
    

}
