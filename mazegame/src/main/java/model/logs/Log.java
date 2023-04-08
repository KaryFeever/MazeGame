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

public class Log {
    private String logID;
    private String path = "data/logs";
    private List<String> steps = new ArrayList<>();
    private String mapString;
    private String ghostsDirections;
    
    private int stepCounter = 0;

    public Log(String mapString, String ghostsDirections) {
        this.logID = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".log";
        this.mapString = mapString;
        this.ghostsDirections = ghostsDirections;
    }

    public void  addStep(PacMan pacMan, List<Ghost> ghosts) {
        String step = "STEP" + stepCounter + "\n";
        step += "pacman: " + pacMan.getRow() + " " + pacMan.getCol() + "\n";
        for(int i = 0; i < ghosts.size(); i++) {
            step += "ghost" + i + ": " + ghosts.get(i).getRow() + " " + ghosts.get(i).getCol() + "\n";
        }
        steps.add(step);
        stepCounter++;
        
        
    }


    public void saveLog() {
        try {
            File file = new File(path + "/" + logID);
            FileWriter writer = new FileWriter(file);
            
            writer.write(mapString);
            writer.write(ghostsDirections);

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
