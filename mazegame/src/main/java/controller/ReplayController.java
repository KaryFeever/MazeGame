package controller;

import java.util.List;
import java.util.Map;

import model.Replay;
import model.game_object.Ghost;

public class ReplayController {
    private Replay replay;
    private int invulnerabilityCounter = 5;

    public ReplayController(Replay replay) {
        this.replay = replay;
        // replay.printSteps();
    }

    public Replay getReplay() {
        return this.replay;
    }


    public void next() {
        replay.moveStepIndex(1);
        Map<String, Object> step = replay.getStep();
        List<Integer> pacMnDir = (List<Integer>)step.get("PacMan");
        // replay.printSteps();
        replay.getGame().getPacMan().move(pacMnDir.get(0), pacMnDir.get(1));
        List<List<Integer>> ghostsDir = (List<List<Integer>>)step.get("Ghosts");
        for(int i = 0; i < ghostsDir.size(); i++) {
            replay.getGame().getGhosts().get(i).move(ghostsDir.get(i).get(0), ghostsDir.get(i).get(1));
        }
        checkIntersection();
    }

    public void previous() {
        replay.moveStepIndex(-1);
        Map<String, Object> step = replay.getStep();
        List<Integer> pacMnDir = (List<Integer>)step.get("PacMan");
        // replay.printSteps();
        replay.getGame().getPacMan().move(pacMnDir.get(0), pacMnDir.get(1));
        List<List<Integer>> ghostsDir = (List<List<Integer>>)step.get("Ghosts");
        for(int i = 0; i < ghostsDir.size(); i++) {
            replay.getGame().getGhosts().get(i).move(ghostsDir.get(i).get(0), ghostsDir.get(i).get(1));
        }
        checkIntersection();
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
                    System.out.println("replay.getGame() OVER");
                    
                }
                
            }
        }

        // Check intersection between PacMan and Key
        if((replay.getGame().getPacMan().getCol() == replay.getGame().getKey().getCol()) && (replay.getGame().getPacMan().getRow() == replay.getGame().getKey().getRow())) {
            replay.getGame().getMap().getMapField(replay.getGame().getKey().getRow(), replay.getGame().getKey().getCol()).removeObject(replay.getGame().getKey());
            replay.getGame().getPacMan().setKeyFlag(true);
        }


        // Check intersection between PacMan and Target
        if((replay.getGame().getPacMan().getCol() == replay.getGame().getTarget().getCol()) && (replay.getGame().getPacMan().getRow() == replay.getGame().getTarget().getRow())) {
            if(replay.getGame().getPacMan().isKeyFlag()) {
                System.out.println("WIN");
               
            }
        }
        invulnerabilityCounter++;
    }


}
