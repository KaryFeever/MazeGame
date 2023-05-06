package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import interfaces.GameObject.Directions;
import model.Game;
import model.Map;
import model.Replay;

public class LogParser {
    private String path = "data/logs";
    private File directory = new File(path);
    private Replay currentReplay;
    private List<Replay> replays = new ArrayList<>();

    public void configureLogs() {
        replays.clear();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                Replay replay = this.parseLog(file);
                if(replay != null) {
                    replays.add(replay);
                }
            }
        }
    }

    private Replay parseLog(File file) {
        MapParser mapParser = new MapParser();
        Map map = mapParser.parseMap(file);
        Game game = new Game(map);
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\\Z");
            String fileContent = scanner.next();
            int index = fileContent.indexOf("GHOSTS DIRECTIONS\n");
            String ghostDirections = fileContent.substring(index + "GHOSTS DIRECTIONS\n".length()).split("\n")[0];
            parseGhostDirections(game, ghostDirections);
            index = fileContent.indexOf("PACMANLIVES\n");
            String pacManLives = fileContent.substring(index + "PACMANLIVES\n".length()).split("\n")[0];
            parsePacManLives(game, pacManLives);
            currentReplay = new Replay(file.getName(), game);
            parseSteps(Arrays.copyOfRange(fileContent.substring(index + "PACMANLIVES\n".length()).split("\n"), 1, fileContent.substring(index + "PACMANLIVES\n".length()).split("\n").length));

            scanner.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return currentReplay;
    }

    private void parseGhostDirections(Game game, String directions) {
        for(int i = 0; i < directions.split(" ").length; i++) {
            switch(directions.split(" ")[i]) {
                case "RIGHT":
                    game.getGhosts().get(i).setDirection(Directions.RIGHT);
                    break;
                case "LEFT":
                    game.getGhosts().get(i).setDirection(Directions.LEFT);
                    break;
                case "UP":
                    game.getGhosts().get(i).setDirection(Directions.UP);
                    break;
                case "DOWN":
                    game.getGhosts().get(i).setDirection(Directions.DOWN);
                    break;
            }
        }
    }

    private void parsePacManLives(Game game, String pacManLives) {
        game.getPacMan().setLives(Integer.parseInt(pacManLives));
    }

    private void parseSteps(String[] steps) {
        int stepCounter = 0;
        List<Integer> pacmanDir = new ArrayList<>(); 
        List<List<Integer>> ghostsDirections = new ArrayList<>();
        for(String string : steps) {
            if(string.compareTo("STEP" + stepCounter) == 0) {
                if(stepCounter != 0) {
                    currentReplay.addStep(pacmanDir, ghostsDirections);
                    pacmanDir = new ArrayList<>();
                    ghostsDirections = new ArrayList<>();
                }
                stepCounter++;
                
            } else if(string.contains("pacman")) {
                pacmanDir.add(Integer.parseInt(string.split(":")[1].trim().split(" ")[0]));
                pacmanDir.add(Integer.parseInt(string.split(":")[1].trim().split(" ")[1]));
            } else if(string.contains("ghost")) {
                List<Integer> ghostDir = new ArrayList<>();
                ghostDir.add(Integer.parseInt(string.split(":")[1].trim().split(" ")[0]));
                ghostDir.add(Integer.parseInt(string.split(":")[1].trim().split(" ")[1]));
                ghostsDirections.add(ghostDir);
            }
            
        }
    }
    
    public List<Replay> getReplays() {
        return this.replays;
    }

    public List<String> getReplayNames() {
        List<String> replayNames = new ArrayList<>();
        for(Replay replay : replays) {
            replayNames.add(replay.getName());
        }

        return replayNames;
    }

}
