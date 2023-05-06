package controller;

import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Replay;
import model.game_object.Ghost;

public class ReplayController {
    private Replay replay;
    private int invulnerabilityCounter = 5;
    private int keyPickedUpStepIndex = -1;
    private AppController appController;

    public ReplayController(Replay replay, AppController appController) {
        this.replay = replay;
        this.appController = appController;
        // replay.printSteps();
    }

    public Replay getReplay() {
        return this.replay;
    }


    public void next() {
        replay.moveStepIndex(1);
        updateState();
    }

    public void previous() {
        replay.moveStepIndex(-1);
        updateState();
    }

    public void updateState() {
        Map<String, Object> step = replay.getStep();
        List<Integer> pacMnDir = (List<Integer>)step.get("PacMan");
        // replay.printSteps();
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

    public void findKeyStep() {
        for(Map<String, Object> step : replay.getSteps()) {
            List<Integer> pacMnDir = (List<Integer>)step.get("PacMan");
            if(pacMnDir.get(0) == replay.getGame().getKey().getRow() && pacMnDir.get(1) == replay.getGame().getKey().getCol()) {
                keyPickedUpStepIndex = replay.getSteps().indexOf(step);
            }
        }
    }

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
