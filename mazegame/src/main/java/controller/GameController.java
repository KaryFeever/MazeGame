package controller;

import interfaces.GameObject.Directions;
import javafx.scene.input.KeyEvent;
import model.Game;
import model.game_object.Ghost;
import model.logs.Log;

public class GameController {
    private Game game;
    private Log log;
    private int invulnerabilityCounter = 5;
    private boolean flag = true;
    private AppController appController;

    public GameController(Game game, AppController appController) {
        this.game = game;
        this.appController = appController;

        String ghostsDirections = "GHOSTS DIRECTIONS\n";
        for(Ghost ghost : game.getGhosts()) {
            ghostsDirections += ghost.getDirection() + " ";
        }
        ghostsDirections += "\n";
        this.log = new Log(game.getMap().printMap(), ghostsDirections, appController.getPacManLives());

    }

    public void handleKeyPress(KeyEvent event) {
        switch(event.getCode()) {
            case W:
                game.movePacman(Directions.UP);
                break;
            case A:
                game.movePacman(Directions.LEFT);
                break;
            case S:
                game.movePacman(Directions.DOWN);
                break;
            case D:
                game.movePacman(Directions.RIGHT);
                break;
            default:
                break;

        }
        log.addStep(game.getPacMan(), game.getGhosts());
    }

    public void updateGameState() {
        for(Ghost ghost : game.getGhosts()) {
            if(ghost.move(null)) {
                log.addStep(game.getPacMan(), game.getGhosts());
            }
            checkIntersection();
        }
    }

    private void checkIntersection() {
        // Check intersection between ghosts and PacMan
        for(Ghost ghost : game.getGhosts()) {
            if((ghost.getCol() == game.getPacMan().getCol()) && (ghost.getRow() == game.getPacMan().getRow())) {
                if(invulnerabilityCounter >= 5){
                    game.getPacMan().reduceLives();
                    invulnerabilityCounter = 0;
                }
                if(game.getPacMan().getLives() <= 0) {
                    
                    //System.out.println("GAME OVER");
                    if(flag) {
                        log.saveLog();
                        flag = false;
                    }
                    appController.setLabel("GAME OVER");
                    appController.switchMode(true);
                    appController.setScene(4);
                }
                
            }
        }

        // Check intersection between PacMan and Key
        if((game.getPacMan().getCol() == game.getKey().getCol()) && (game.getPacMan().getRow() == game.getKey().getRow())) {
            game.getMap().getMapField(game.getKey().getRow(), game.getKey().getCol()).removeObject(game.getKey());
            game.getPacMan().setKeyFlag(true);
        }


        // Check intersection between PacMan and Target
        if((game.getPacMan().getCol() == game.getTarget().getCol()) && (game.getPacMan().getRow() == game.getTarget().getRow())) {
            if(game.getPacMan().isKeyFlag()) {
                // System.out.println("WIN");
                if(flag) {
                    log.saveLog();
                    flag = false;
                }
                appController.setLabel("WIN");
                appController.switchMode(true);
                appController.setScene(4);
            }
        }
        invulnerabilityCounter++;
    }

    public int getPacManLives() {
        return game.getPacMan().getLives();
    }

    public boolean getKeyFlag() {
        return game.getPacMan().isKeyFlag();
    }

    public boolean getGameOverFlag() {
        return flag;
    }
    
}
