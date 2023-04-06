package view;

import controller.GameController;
import interfaces.GameObject;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Map;
import model.game_object.Ghost;
import model.game_object.Key;
import model.game_object.PacMan;
import model.game_object.Target;
import model.terrain.Wall;

public class GameView {
    private static final String WALL_IMAGE_PATH = "/wall.png";
    private static final String FIELD_IMAGE_PATH = "/field.png";
    private static final String GHOST_IMAGE_PATH = "/ghost.png";
    private static final String KEY_IMAGE_PATH = "/key.png";
    private static final String TARGET_IMAGE_PATH = "/target.png";
    private static final String PACMAN_IMAGE_PATH = "/pacman.png";
    private static final int SPRITE_SIZE = 64;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Map map;
    private GameController controller;

    public GameView(Canvas canvas, Map map, GameController controller) {
        this.canvas = canvas;
        this.map = map;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.controller = controller;
    }

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

    public void updateGameView() {
        final long[] startTime = { System.nanoTime() };
        long INTERVAL = 150000000; // 50 milliseconds


        new AnimationTimer() {
            @Override
            public void handle(long now) {
                long elapsedTime = now - startTime[0];
                if (elapsedTime >= INTERVAL) {
                    // draw();
                    controller.updateGameState();
                    startTime[0] = now;
                }
                draw();
                // draw();
                // controller.updateGameState();
            }
        }.start();;
    }
}
