package view;

import controller.AppController;
import controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Game;
import model.Map;

/**
 * NewGameView class represents the view to start a new game.
 */
public class NewGameView extends Scene {
    private int mapIndex = 0;

    /**
     * Constructor for NewGameView.
     *
     * @param appController The application's main controller object.
     */
    public NewGameView(AppController appController) {
        super(new VBox(), 1024, 720);
        VBox startLayout = (VBox) getRoot();
        // Set alignment of startLayout to center
        startLayout.setAlignment(Pos.CENTER);
        startLayout.setStyle("-fx-background-color: #ebebeb;"); // Set background color
        startLayout.setPrefSize(640, 480);
        startLayout.setAlignment(Pos.CENTER);

        // Create a ListView with sample map names
        ListView<String> mapListView = new ListView<>();
        mapListView.getItems().addAll(appController.getMapParser().getMapNames());
        mapListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Create a ScrollPane and set the ListView as its content
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mapListView);

        // Set the height of the ScrollPane and ListView to show only a few items at a time
        scrollPane.setPrefHeight(100);
        mapListView.setPrefHeight(80);

        // Add all sections to the main layout
        startLayout.getChildren().addAll(scrollPane);

        // Create buttons
        Button saveButton = new Button("Play");
        saveButton.setStyle("-fx-background-color: #2dc39d; -fx-text-fill: #FFFFFF;");
        saveButton.setOnAction(event_save -> {
            startGame(appController.getMapParser().getMap(mapIndex), appController); // Set the start screen layout as the scene
        });

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #c3522d; -fx-text-fill: #FFFFFF;");
        backButton.setOnAction(event_back -> {
            appController.setScene(0);
        });
        
        HBox buttonsBox = new HBox(saveButton, backButton);
        buttonsBox.setSpacing(20);
        buttonsBox.setAlignment(Pos.CENTER);

        // Add buttons to the bottom of the layout
        startLayout.getChildren().add(buttonsBox);
        VBox.setMargin(buttonsBox, new Insets(20, 0, 0, 0));

        // Get the selected item from the ListView
        mapListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.mapIndex = appController.getMapParser().getMapNames().indexOf(newValue);
        });
    }

    /**
     * Starts the game based on the selected map and application controller settings.
     * 
     * @param map           The selected map.
     * @param appController The application's main controller object.
     */
    private void startGame(Map map, AppController appController) {
        final int WIDTH = 1024;
        final int HEIGHT = 960;
    
        Game game = new Game(map);
        game.getPacMan().setLives(appController.getPacManLives());
        GameController gameController = new GameController(game, appController);
        Canvas GameCanvas = new Canvas(800, 800);
        Canvas HUDCanvas = new Canvas(800, 64);
        
        GameView gameView = new GameView(GameCanvas, HUDCanvas, map, gameController);
        gameView.setGhostsSpeed(appController.getGhostsSpeed());
        gameView.draw();
    
        VBox vbox = new VBox(HUDCanvas, GameCanvas);
        StackPane root = new StackPane(vbox);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 1024, 960);
        scene.setOnKeyPressed(gameController::handleKeyPress);
    
        gameView.updateGameView();
        appController.setScene(scene);
    }
}