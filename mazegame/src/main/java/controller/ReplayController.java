// Project Title: IJA 2022/23: Pac-Man Game Design and Implementation
// Autors: Maksim Naumenko  (xnaume01)
//         Tatiana Fedorova (xfedor14)

package controller;

import java.util.List;
import java.util.Map;
import model.Replay;
import model.game_object.Ghost;

/**
 * ReplayController class is responsible for controlling the playback of
 * a Replay object, allowing for next and previous step navigation and
 * updating the game state accordingly.
 */
public class ReplayController {
    private Replay replay;
    private int invulnerabilityCounter = 5;
    private int keyPickedUpStepIndex = -1;
    private AppController appController;

    /**
     * Constructor that initializes a new ReplayController with a specified
     * Replay object and an AppController reference.
     *
     * @param replay The Replay object to be controlled.
     * @param appController A reference to the AppController instance.
     */
    public ReplayController(Replay replay, AppController appController) {
        this.replay = replay;
        this.appController = appController;
    }

    /**
     * Returns the Replay object controlled by this controller.
     *
     * @return The Replay object.
     */
    public Replay getReplay() {
        return this.replay;
    }

    /**
     * Moves the current step index to the next step and updates the game state.
     */
    public void next() {
        replay.moveStepIndex(1);
        updateState();
    }

    /**
     * Moves the current step index to the previous step and updates the game state.
     */
    public void previous() {
        replay.moveStepIndex(-1);
        updateState();
    }

    /**
     * Updates the game state according to the current step index.
     */
    public void updateState() {
        Map<String, Object> step = replay.getStep();
        List<Integer> pacMnDir = (List<Integer>)step.get("PacMan");
        replay.getGame().getPacMan().move(pacMnDir.get(0), pacMnDir.get(1));
        List<List<Integer>> ghostsDir = (List<List<Integer>>)step.get("Ghosts");
        for(int i = 0; i < ghostsDir.size(); i++) {
            replay.getGame().getGhosts().get(i).move(ghostsDir.get(i).get(0), ghostsDir.get(i).get(1));
        }
        if(replay.getIndex() == keyPickedUpStepIndex - 1) {
            replay.getGame().getMap().getMapField(replay.getGame().getKey().getRow(), replay.getGame().getKey().getCol()).putObject(replay.getGame().getKey());
            replay.getGame().getPacMan().setKeyFlag(false);
        }
        checkIntersection();
    }

    /**
     * Searches for the step in which the PacMan picks up the key and
     * stores the index of that step.
     */
    public void findKeyStep() {
        for(Map<String, Object> step : replay.getSteps()) {
            List<Integer> pacMnDir = (List<Integer>)step.get("PacMan");
            if(pacMnDir.get(0) == replay.getGame().getKey().getRow() && pacMnDir.get(1) == replay.getGame().getKey().getCol()) {
                keyPickedUpStepIndex = replay.getSteps().indexOf(step);
            }
        }
    }

    /**
     * Checks for intersections between game objects (PacMan, ghosts, key, target)
     * and updates the game state accordingly.
     */
    private void checkIntersection() {
        // Check intersection between ghosts and PacMan
        for(Ghost ghost : replay.getGame().getGhosts()) {
            if((ghost.getCol() == replay.getGame().getPacMan().getCol()) && (ghost.getRow() == replay.getGame().getPacMan().getRow())) {
                if(invulnerabilityCounter >= 5){
                    replay.getGame().getPacMan().reduceLives();
                    invulnerabilityCounter = 0;
                }
                if(replay.getGame().getPacMan().getLives() <= 0) {
                    // game over
                }
            }
        }

        // Check intersection between PacMan and Key
        if((replay.getGame().getPacMan().getCol() == replay.getGame().getKey().getCol()) && (replay.getGame().getPacMan().getRow() == replay.getGame().getKey().getRow())) {
            replay.getGame().getMap().getMapField(replay.getGame().getKey().getRow(), replay.getGame().getKey().getCol()).removeObject(replay.getGame().getKey());
            replay.getGame().getPacMan().setKeyFlag(true);
            keyPickedUpStepIndex = replay.getIndex();
        }

        // Check intersection between PacMan and Target
        if((replay.getGame().getPacMan().getCol() == replay.getGame().getTarget().getCol()) && (replay.getGame().getPacMan().getRow() == replay.getGame().getTarget().getRow())) {
            if(replay.getGame().getPacMan().isKeyFlag()) {
                // win
            }
        }
        invulnerabilityCounter++;
    }
}