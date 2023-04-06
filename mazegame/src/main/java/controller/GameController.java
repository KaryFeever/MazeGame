package controller;

import interfaces.GameObject.Directions;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Game;
import model.game_object.Ghost;

public class GameController {
    private Game game;

    public GameController(Game game) {
        this.game = game;
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
    }

    public void updateGameState() {
        for(Ghost ghost : game.getGhosts()) {
            ghost.move(null);
        }
    }
    
}
