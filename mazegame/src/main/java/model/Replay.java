// Project Title: IJA 2022/23: Pac-Man Game Design and Implementation
// Autors: Maksim Naumenko  (xnaume01)
//         Tatiana Fedorova (xfedor14)

package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Replay class to store and manage a replay of a game.
 */
public class Replay {
    private String name;
    private Game game;
    private List<Map<String, Object>> steps = new ArrayList<Map<String, Object>>();
    private int stepIndex = -1;

    /**
     * Constructor to create a new Replay object.
     *
     * @param name The name of the replay.
     * @param game The Game object for which this replay is being created.
     */
    public Replay(String name, Game game) {
        this.name = name;
        this.game = game;
    }
    
    /** 
     * Adds a new step to the replay.
     *
     * @param pacmanDir The direction of Pac-Man in this step.
     * @param ghostsDirections A list of directions for each ghost in this step.
     */
    public void addStep(List<Integer> pacmanDir, List<List<Integer>> ghostsDirections) {
        Map<String, Object> step = new HashMap<String, Object>();
        step.put("PacMan", pacmanDir);
        step.put("Ghosts", ghostsDirections);
        steps.add(step);
    }

    
    /**
     * Returns the game associated with this replay.
     *
     * @return Game The game object for which this replay was created.
     */
    public Game getGame(){
        return this.game;
    }

    /**
     * Changes the current step index by the specified amount.
     *
     * @param move The number of steps to move the index. Can be positive or negative.
     */
    public void moveStepIndex(int move) {
        stepIndex += move;
        if(stepIndex > steps.size() - 1) {
            stepIndex = steps.size() - 1;
        }
        if(stepIndex < 0) {
            stepIndex = 0;
        }
    }

    /**
     * Returns the step at the current step index.
     *
     * @return {@code Map<String, Object>} A map containing the Pac-Man and ghost directions for this step.
     */
    public Map<String, Object> getStep() {
        return this.steps.get(stepIndex);
    }

    /**
     * Prints all steps stored in this replay.
     */
    public void printSteps() {
        int stepCounter = 0;
        for(Map<String, Object> step : steps) {
            System.out.println("STEP" + stepCounter + "\n");
            List<Integer> pacMnDir = (List<Integer>)step.get("PacMan");
            System.out.println("PACMAN: " + pacMnDir.get(0) + " " + pacMnDir.get(1) + "\n");
        }
    }

    /**
     * Returns the name of the replay.
     *
     * @return String The name of the replay.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns the current step index.
     *
     * @return int The current step index.
     */
    public int getIndex() {
        return this.stepIndex;
    }

    /**
     * Sets the step index to the specified value.
     *
     * @param index The new step index value.
     */
    public void setIndex(int index) {
        this.stepIndex = index;
    }

    /**
     * Returns the list of steps in the replay.
     *
     * @return {@code List<Map<String, Object>>} The list of steps, where each step is a map containing Pac-Man and ghost directions.
     */
    public List<Map<String, Object>> getSteps() {
        return this.steps;
    }
}