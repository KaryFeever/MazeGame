package view;

import controller.ReplayController;
import interfaces.GameObject;
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
 * This class represents a ReplayView responsible for rendering the state of a replayed game.
 */
public class ReplayView {
    private static final String WALL_IMAGE_PATH = "/wall.jpg";
    private static final String FIELD_IMAGE_PATH = "/field.jpg";
    private static final String GHOST_IMAGE_PATH = "/ghost.png";
    private static final String KEY_IMAGE_PATH = "/key.png";
    private static final String TARGET_IMAGE_PATH = "/target.png";
    private static final String PACMAN_IMAGE_PATH = "/pacman.png";
    private static final String LIVES_IMAGE_PATH = "/heart.png";
    private static final int SPRITE_SIZE = 64;
    private ReplayController controller;
    private Map map;
    private GraphicsContext graphicsContext;
    private GraphicsContext hudGraphicsContext;

    /**
     * Creates a ReplayView object for rendering the state of a replayed game.
     *
     * @param canvas The main game canvas for drawing game objects.
     * @param HUDCanvas The canvas for drawing the HUD, such as lives and key status.
     * @param controller The ReplayController instance to interact with the replayed game state.
     */
    public ReplayView(Canvas canvas, Canvas HUDCanvas, ReplayController controller) {
        this.controller = controller;
        this.map = controller.getReplay().getGame().getMap();
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.hudGraphicsContext = HUDCanvas.getGraphicsContext2D();
    }

    /**
     * Draws the current state of the game objects on the canvas.
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
        drawLives();
    }

    /**
     * Draws the lives and key status on the HUD canvas.
     */
    private void drawLives() {
        Image livesImage = new Image(LIVES_IMAGE_PATH);
        HBox livesBox = new HBox();
        for (int i = 0; i < controller.getReplay().getGame().getPacMan().getLives(); i++) {
            Image lifeImage = new Image(LIVES_IMAGE_PATH);
            livesBox.getChildren().add(new ImageView(lifeImage));
        }
        livesBox.setLayoutX(20);
        livesBox.setLayoutY(20);
        hudGraphicsContext.clearRect(0, 0, hudGraphicsContext.getCanvas().getWidth(), hudGraphicsContext.getCanvas().getHeight());
        hudGraphicsContext.drawImage(livesBox.snapshot(null, null), 0, 0, SPRITE_SIZE*controller.getReplay().getGame().getPacMan().getLives(), SPRITE_SIZE);
        if(controller.getReplay().getGame().getPacMan().isKeyFlag()) {
            hudGraphicsContext.drawImage(new Image(KEY_IMAGE_PATH), 200, 0, SPRITE_SIZE, SPRITE_SIZE);
        }
    }
}