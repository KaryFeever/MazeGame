import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Game;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Label label = new Label("Hello, JavaFX!");
        // Scene scene = new Scene(new StackPane(label), 640, 480);
        // stage.setScene(scene);
        // stage.show();

        // Define the dimensions of your canvas or screen
        int screenWidth = 800;
        int screenHeight = 800;

        // Create a two-dimensional array to represent your map
        int[][] mapData = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
            {1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1},
            {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1},
            {1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1},
            {1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };

        // Create a JavaFX Group to hold your sprites
        Group spriteGroup = new Group();

        // Create a JavaFX Scene to display your sprites on
        Scene scene = new Scene(spriteGroup, screenWidth, screenHeight);

        // Load your sprite images into JavaFX Image objects
        Image wallImage = new Image("/wall.png");

        // Define the dimensions of your sprites
        int spriteWidth = 64;
        int spriteHeight = 64;

        // Iterate over the map data and place sprites on the canvas
        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[i].length; j++) {
                // Calculate the x and y coordinates for the sprite
                int x = j * spriteWidth;
                int y = i * spriteHeight;

                // Load the appropriate sprite image based on the map data
                Image spriteImage = (mapData[i][j] == 1) ? wallImage : null;

                // Create a JavaFX ImageView object for the sprite and set its position and size
                ImageView spriteView = new ImageView(spriteImage);
                spriteView.setX(x);
                spriteView.setY(y);
                spriteView.setFitWidth(spriteWidth);
                spriteView.setFitHeight(spriteHeight);

                // Add the sprite to the spriteGroup
                spriteGroup.getChildren().add(spriteView);
            }
        }

        // Display the scene on the screen
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        // System.out.println("test");
        Game newGame = new Game();
    }

}