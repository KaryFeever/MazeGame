// Project Title: IJA 2022/23: Pac-Man Game Design and Implementation
// Autors: Maksim Naumenko  (xnaume01)
//         Tatiana Fedorova (xfedor14)

package controller;

import interfaces.GameObject.Directions;
import javafx.scene.input.KeyEvent;
import model.Game;
import model.game_object.Ghost;
import model.logs.Log;

/**
 * GameController class is responsible for handling the game logic and user interactions
 * during the gameplay. It listens for user input and updates the game state accordingly.
 */
public class GameController {
    private Game game;
    private Log log;
    private int invulnerabilityCounter = 5;
    private boolean flag = true;
    private AppController appController;

    /**
     * Creates a GameController with the given game instance and AppController.
     * Initializes the Log object for storing game steps and interactions.
     *
     * @param game The game instance being controlled by this GameController.
     * @param appController The AppController instance for managing scene changes.
     */
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
    
    /**
     * Handles key press events for moving the PacMan character in the game.
     *
     * @param event The KeyEvent that occurred.
     */
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

    /**
     * Updates the game state by moving ghosts and checking for intersections.
     */
    public void updateGameState() {
        for(Ghost ghost : game.getGhosts()) {
            if(ghost.move(null)) {
                log.addStep(game.getPacMan(), game.getGhosts());
            }
            checkIntersection();
        }
    }

    /**
     * Checks for intersections between game objects and takes appropriate actions.
     */
    private void checkIntersection() {
        // Check intersection between ghosts and PacMan
        for(Ghost ghost : game.getGhosts()) {
            if((ghost.getCol() == game.getPacMan().getCol()) && (ghost.getRow() == game.getPacMan().getRow())) {
                if(invulnerabilityCounter >= 5){
                    game.getPacMan().reduceLives();
                    invulnerabilityCounter = 0;
                }
                if(game.getPacMan().getLives() <= 0) {
                    if(flag) {
                        log.saveLog();
                        flag = false;
                    }
                    appController.setLabel("GAME OVER");
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
                appController.setScene(4);
            }
        }
        invulnerabilityCounter++;
    }

    /**
     * Returns the number of remaining lives of the PacMan character.
     *
     * @return The number of remaining lives.
     */
    public int getPacManLives() {
        return game.getPacMan().getLives();
    }

    /**
     * Returns whether the PacMan character has collected the key.
     *
     * @return true if PacMan has collected the key, false otherwise.
     */
    public boolean getKeyFlag() {
        return game.getPacMan().isKeyFlag();
    }

    /**
     * Returns the game over flag, which indicates whether the game has ended.
     *
     * @return true if the game is over, false otherwise.
     */
    public boolean getGameOverFlag() {
        return flag;
    }
}
