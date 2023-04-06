import controller.GameController;
import controller.MapParser;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Game;
import model.Map;
import view.GameView;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;



public class App extends Application {
    private boolean started = false;

@Override
public void start(Stage primaryStage) throws Exception {
    // Process Maps from the file
    MapParser mapParser = new MapParser();
    mapParser.configureMaps();


    // Create the start screen
    Button startButton = new Button("Start");
    startButton.setOnAction(event -> {
        startGame(mapParser.getMap(0));
    });

    Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            primaryStage.close();
        });
    

    // StackPane startLayout = new StackPane(startButton, exitButton);
    // Scene startScene = new Scene(startLayout, 640, 480);
     // Create a VBox to hold the buttons
     VBox vbox = new VBox();
     vbox.getChildren().addAll(startButton, exitButton);
     vbox.setSpacing(20); // Add spacing between buttons
 
     // Create a StackPane to hold the VBox and center it
     StackPane startLayout = new StackPane(vbox);
     startLayout.setStyle("-fx-background-color: #FFFFFF;"); // Set background color
     startLayout.setPrefSize(640, 480);

    // Show the start screen
    primaryStage.setScene(new Scene(startLayout));
    primaryStage.show();
}

private void startGame(Map map) {
    final int WIDTH = 640;
    final int HEIGHT = 480;

    Game game = new Game(map);
    GameController gameController = new GameController(game);
    Canvas canvas = new Canvas(800, 800);
    GameView gameView = new GameView(canvas, map, gameController);
    gameView.draw();
    map.printMap();

    // set up the UI
    BorderPane root = new BorderPane();
    root.setCenter(canvas);
    Scene scene = new Scene(root, WIDTH, HEIGHT);

    scene.setOnKeyPressed(gameController::handleKeyPress);

    gameView.updateGameView();

    // display the UI
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("GAME");
    stage.show();
    
    // // Define the dimensions of your canvas or screen
    // int screenWidth = 800;
    // int screenHeight = 800;

    // // Create a two-dimensional array to represent your map
    // int[][] mapData = {
    //     {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    //     {1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1},
    //     {1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1},
    //     {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
    //     {1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1},
    //     {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1},
    //     {1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1},
    //     {1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1},
    //     {1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1},
    //     {1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1},
    //     {1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1},
    //     {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    // };

    // // Create a JavaFX Group to hold your sprites
    // Group spriteGroup = new Group();

    // // Create a JavaFX Scene to display your sprites on
    // Scene scene = new Scene(spriteGroup, screenWidth, screenHeight);

    // // Load your sprite images into JavaFX Image objects
    // Image wallImage = new Image("/wall.png");

    // // Define the dimensions of your sprites
    // int spriteWidth = 64;
    // int spriteHeight = 64;

    // // Iterate over the map data and place sprites on the canvas
    // for (int i = 0; i < mapData.length; i++) {
    //     for (int j = 0; j < mapData[i].length; j++) {
    //         // Calculate the x and y coordinates for the sprite
    //         int x = j * spriteWidth;
    //         int y = i * spriteHeight;

    //         // Load the appropriate sprite image based on the map data
    //         Image spriteImage = (mapData[i][j] == 1) ? wallImage : null;

    //         // Create a JavaFX ImageView object for the sprite and set its position and size
    //         ImageView spriteView = new ImageView(spriteImage);
    //         spriteView.setX(x);
    //         spriteView.setY(y);
    //         spriteView.setFitWidth(spriteWidth);
    //         spriteView.setFitHeight(spriteHeight);

    //         // Add the sprite to the spriteGroup
    //         spriteGroup.getChildren().add(spriteView);
    //     }
    // }

    // // Display the scene on the screen
    // Stage stage = new Stage();
    // stage.setScene(scene);
    // stage.show();
}


public static void main(String[] args) {
    launch(args);
}
}