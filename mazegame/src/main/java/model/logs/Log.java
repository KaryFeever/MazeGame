package model.logs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.game_object.Ghost;
import model.game_object.PacMan;

/**
 * Log class handles logging game state and interactions between game objects.
 * It stores and writes the logs to a file.
 */
public class Log {
    private String logID;
    private String path = "data/logs";
    private List<String> steps = new ArrayList<>();
    private String mapString;
    private String ghostsDirections;
    private String pacmanLives = "PACMANLIVES";
    private int stepCounter = 0;

    /**
     * Constructs a new Log instance.
     *
     * @param mapString The initial map representation.
     * @param ghostsDirections The initial directions of the ghosts.
     * @param pacmanLives The initial number of lives for Pac-Man.
     */
    public Log(String mapString, String ghostsDirections, int pacmanLives) {
        this.logID = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".log";
        this.mapString = mapString;
        this.ghostsDirections = ghostsDirections;
        this.pacmanLives += "\n" + pacmanLives + "\n";
    }

    /**
     * Adds a step to the log containing the current positions of Pac-Man and the ghosts.
     *
     * @param pacMan The Pac-Man object with the current position.
     * @param ghosts A list of Ghost objects with the current positions.
     */
    public void  addStep(PacMan pacMan, List<Ghost> ghosts) {
        String step = "STEP" + stepCounter + "\n";
        step += "pacman: " + pacMan.getRow() + " " + pacMan.getCol() + "\n";
        for(int i = 0; i < ghosts.size(); i++) {
            step += "ghost" + i + ": " + ghosts.get(i).getRow() + " " + ghosts.get(i).getCol() + "\n";
        }
        steps.add(step);
        stepCounter++;
    }

    /**
     * Saves the log to a file in the specified path.
     */
    public void saveLog() {
        try {
            File file = new File(path + "/" + logID);
            FileWriter writer = new FileWriter(file);
            
            writer.write(mapString);
            writer.write(ghostsDirections);
            writer.write(pacmanLives);
            for(String step : steps) {
                writer.write(step);
            }
            
            // Close the FileWriter to flush the buffer and release resources
            writer.close();
            System.out.println("File created successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}