// Project Title: IJA 2022/23: Pac-Man Game Design and Implementation
// Autors: Maksim Naumenko  (xnaume01)
//         Tatiana Fedorova (xfedor14)

package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import interfaces.Terrain;
import interfaces.GameObject.Directions;
import model.Map;
import model.game_object.Ghost;
import model.game_object.Key;
import model.game_object.PacMan;
import model.game_object.Target;
import model.terrain.Field;
import model.terrain.Wall;

/**
 * MapParser class is responsible for parsing and creating Map instances
 * from files stored in the specified directory.
 */
public class MapParser {
    private int rows;
    private int cols;
    private int rowIndex = 0;
    private Map map;
    private String currentFileName;
    private ArrayList<Map> maps = new ArrayList<Map>();
    private File directory = new File("data/maps");

    /**
     * Reads all map files in the configured directory and generates a list
     * of Map objects.
     */
    public void configureMaps() {
        maps.clear();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                currentFileName = file.getName();
                Map map = this.parseMap(file);
                if(map != null) {
                    maps.add(map);
                    map = null;
                    rowIndex = 0;
                }
            }
        }
    }

    /**
     * Parses map sizes (rows and columns) from the given input line and creates
     * a new Map instance with these dimensions.
     *
     * @param line A string containing the map sizes (rows and columns).
     * @return True if map sizes were successfully parsed and a Map instance was created, false otherwise.
     */
    private boolean parseSizes(String line) {
        Scanner scanner = new Scanner(line);
        if (scanner.hasNextInt()) {
            rows = scanner.nextInt();
            if (scanner.hasNextInt()) {
                cols = scanner.nextInt();
                scanner.close();
                this.map = new Map(rows,cols,currentFileName);
                return true;
            }
            scanner.close();
            return false;
        }
        scanner.close();
        return false;
    }

    /**
     * Parses a line from the input file and creates Terrain objects and game objects
     * according to the characters in the line.
     *
     * @param line A string containing the map objects for a row of the map.
     * @return True if the line was successfully parsed and the objects were created, false otherwise.
     */
    private boolean parseFieldLine(String line) {
        if(line.length() != cols) {
            return false;
        }
        
        Scanner scanner = new Scanner(line);
        for(int col = 0; col < cols; col++) {
            Terrain newTerrain = null;
            switch(line.charAt(col)) {
                case 'T': // target
                    newTerrain = new Field();
                    newTerrain.putObject(new Target(rowIndex + 1, col + 1));
                    break;
                case 'X': // wall
                    newTerrain = new Wall();
                    break;
                case 'G': // ghost
                    newTerrain = new Field();
                    newTerrain.putObject(new Ghost(map, rowIndex + 1, col + 1, Directions.RIGHT));
                    break;
                case 'K': // key
                    newTerrain = new Field();
                    newTerrain.putObject(new Key(rowIndex + 1, col + 1));
                    break;
                case '.': // empty field
                    newTerrain = new Field();
                    break;
                case 'S': // start 
                    newTerrain = new Field();
                    newTerrain.putObject(new PacMan(map, rowIndex + 1, col + 1));
                    break;
                default:
                    return false;
            }
            map.setMapField(rowIndex, col, newTerrain);
        }
        scanner.close();
        return true;
    }

    /**
     * Parses a map file to create a Map object representing the game map.
     *
     * @param file The map file to be parsed.
     * @return The Map object representing the parsed map file.
     */
    public Map parseMap(File file) {
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            if(scanner.hasNextLine()) {
                parseSizes(scanner.nextLine());
            }
            for(int row = 0; row < rows; row++) {
                if(scanner.hasNextLine()) {
                    parseFieldLine(scanner.nextLine());
                }
                rowIndex++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Returns a specific Map object from the list of parsed maps.
     *
     * @param mapIndex The index of the desired Map object in the list.
     * @return The Map object at the specified index.
     */
    public Map getMap(int mapIndex) {
        return this.maps.get(mapIndex);
    }

    /**
     * Returns a list of map names extracted from the list of Map objects.
     *
     * @return The list of map names.
     */
    public List<String> getMapNames() {
        List<String> mapNames = new ArrayList<>();
        for(Map map : maps) {
            mapNames.add(map.getName());
        }
        return mapNames;
    }
}