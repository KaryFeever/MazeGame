package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import interfaces.Terrain;
import model.Map;
import model.game_object.Ghost;
import model.game_object.Key;
import model.game_object.PacMan;
import model.game_object.Target;
import model.terrain.Field;
import model.terrain.Wall;

public class MapParser {
    private int rows;
    private int cols;
    private int rowIndex = 0;
    private Map map;

    /**
     * 
     * @param line
     * @return
     */
    private boolean parseSizes(String line) {
        Scanner scanner = new Scanner(line);
        if (scanner.hasNextInt()) {
            rows = scanner.nextInt();
            if (scanner.hasNextInt()) {
                cols = scanner.nextInt();
                scanner.close();
                this.map = new Map(rows,cols);
                return true;
            }
            scanner.close();
            return false;
        }
        scanner.close();
        return false;
    }

    /**
     * 
     * @param line
     * @return
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
                    newTerrain.putObject(new Ghost(map, rowIndex + 1, col + 1));
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
     * 
     * @param file
     * @return
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        return map;
    }
    
}