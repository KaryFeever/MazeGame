// Project Title: IJA 2022/23: Pac-Man Game Design and Implementation
// Autors: Maksim Naumenko  (xnaume01)
//         Tatiana Fedorova (xfedor14)

package view;

import controller.GameController;
import interfaces.GameObject;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Map;
import model.game_object.Ghost;
import model.game_object.Key;
import model.game_object.PacMan;
import model.game_object.Target;
import model.terrain.Wall;

/**
 * GameView class for rendering the game's graphical elements on a canvas.
 */
public class GameView {
    private static final String WALL_IMAGE_PATH = "/wall.jpg";
    private static final String FIELD_IMAGE_PATH = "/field.jpg";
    private static final String GHOST_IMAGE_PATH = "/ghost.png";
    private static final String KEY_IMAGE_PATH = "/key.png";
    private static final String TARGET_IMAGE_PATH = "/target.png";
    private static final String PACMAN_IMAGE_PATH = "/pacman.png";
    private static final String LIVES_IMAGE_PATH = "/heart.png";
    private static final int SPRITE_SIZE = 64;
    private int ghostsSpeed = 1;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private GraphicsContext hudGraphicsContext;
    private Map map;
    private GameController controller;
    private AnimationTimer animationTimer;

    /**
     * Constructor for GameView.
     *
     * @param canvas The canvas on which the game is rendered.
     * @param HUDCanvas The canvas for rendering the heads-up display (HUD) elements.
     * @param map The game's map object.
     * @param controller The GameController object.
     */
    public GameView(Canvas canvas, Canvas HUDCanvas, Map map, GameController controller) {
        this.canvas = canvas;
        this.map = map;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.hudGraphicsContext = HUDCanvas.getGraphicsContext2D();
        this.controller = controller;
    }

    /**
     * Draws the game objects on the canvas.
     */
    public void draw() {
        for(int row = 0; row < map.getRows(); row++) {
            for(int col = 0; col < map.getCols(); col++) {
                if(map.getMapField(row, col).getClass() == Wall.class) {
                    graphicsContext.drawImage(new Image(WALL_IMAGE_PATH), col*SPRITE_SIZE, row*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE);
                } else {
                    graphicsContext.drawImage(new Image(FIELD_IMAGE_PATH), col*SPRITE_SIZE, row*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE);
                    if(!map.getMapField(row, col).isEmpty()) {
                        for(GameObject object : map.getMapField(row, col).getObject()) {
                            if(object.getClass() == Target.class) {
                                graphicsContext.drawImage(new Image(TARGET_IMAGE_PATH), col*SPRITE_SIZE, row*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE);
                            }
                            if(object.getClass() == Key.class) {
                                graphicsContext.drawImage(new Image(KEY_IMAGE_PATH), col*SPRITE_SIZE, row*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE);
                            }
                            if(object.getClass() == PacMan.class) {
                                graphicsContext.drawImage(new Image(PACMAN_IMAGE_PATH), col*SPRITE_SIZE, row*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE);
                            }
                            if(object.getClass() == Ghost.class) {
                                graphicsContext.drawImage(new Image(GHOST_IMAGE_PATH), col*SPRITE_SIZE, row*SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Draws the Pac-Man lives in the HUD.
     */
    private void drawLives() {
        Image livesImage = new Image(LIVES_IMAGE_PATH);
        HBox livesBox = new HBox();
        for (int i = 0; i < controller.getPacManLives(); i++) {
            Image lifeImage = new Image(LIVES_IMAGE_PATH);
            livesBox.getChildren().add(new ImageView(lifeImage));
        }
        livesBox.setLayoutX(20);
        livesBox.setLayoutY(20);
        hudGraphicsContext.clearRect(0, 0, hudGraphicsContext.getCanvas().getWidth(), hudGraphicsContext.getCanvas().getHeight());
        hudGraphicsContext.drawImage(livesBox.snapshot(null, null), 0, 0, SPRITE_SIZE*controller.getPacManLives(), SPRITE_SIZE);
        if(controller.getKeyFlag()) {
            hudGraphicsContext.drawImage(new Image(KEY_IMAGE_PATH), 200, 0, SPRITE_SIZE, SPRITE_SIZE);
        }
    }

    /**
     * Updates the game view by redrawing objects on the canvas and updating the HUD.
     */
    public void updateGameView() {
        final long[] startTime = { System.nanoTime() };
        long INTERVAL = 200000000 / ghostsSpeed; // 50 milliseconds

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long elapsedTime = now - startTime[0];
                if (elapsedTime >= INTERVAL) {
                    // draw();
                    controller.updateGameState();
                    startTime[0] = now;
                }
                draw();
                drawLives();
                
                if(!controller.getGameOverFlag()) {
                    animationTimer.stop();
                }
            }
        };
        animationTimer.start();
    }
    
    /**
     * Sets the speed of the ghosts in the game.
     *
     * @param ghostsSpeed The speed value for ghosts.
     */
    public void setGhostsSpeed(int ghostsSpeed) {
        this.ghostsSpeed = ghostsSpeed;
    }
}