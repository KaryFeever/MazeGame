import controller.GameController;
import controller.GameLogs;
import controller.MapParser;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Game;
import model.Map;
import view.GameView;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;



public class App extends Application {

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
    final int WIDTH = 1024;
    final int HEIGHT = 960;

    Game game = new Game(map);
    GameLogs gameLogs = new GameLogs();
    GameController gameController = new GameController(game, gameLogs);
    Canvas GameCanvas = new Canvas(800, 800);
    Canvas HUDCanvas = new Canvas(800, 64);
    
    GameView gameView = new GameView(GameCanvas, HUDCanvas, map, gameController);
    gameView.draw();

    VBox vbox = new VBox(HUDCanvas, GameCanvas);

    StackPane root = new StackPane(vbox);
    root.setAlignment(Pos.CENTER);
    Scene scene = new Scene(root, 800, 864);

    scene.setOnKeyPressed(gameController::handleKeyPress);

    gameView.updateGameView();

    // display the UI
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("GAME");
    stage.show();
    
}


public static void main(String[] args) {
    launch(args);
}
}