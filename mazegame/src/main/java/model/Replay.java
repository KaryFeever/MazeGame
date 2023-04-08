package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.GameObject.Directions;

public class Replay {
    private String name;
    private Game game;
    private List<Map<String, Object>> steps = new ArrayList<Map<String, Object>>();

    public Replay(String name, Game game) {
        this.name = name;
        this.game = game;
    }

    public void addStep(List<Integer> pacmanDir, List<List<Integer>> ghostsDirections) {
        Map<String, Object> step = new HashMap<String, Object>();
        step.put("PacMan", pacmanDir);
        step.put("Ghosts", ghostsDirections);
        steps.add(step);
    }

    // public void printSteps() {
    //     int stepCounter = 0;
    //     for(Map<String, Object> step : steps) {
    //         System.out.println("STEP" + stepCounter + "\n");
    //         List<Integer> pacMnDir = (List<Integer>)step.get("PacMan");
    //         System.out.println("PACMAN: " + pacMnDir.get(0) + " " + pacMnDir.get(1) + "\n");
    //     }
    // }

}
