import controller.GameController;
import controller.MapParser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

import model.Game;
import model.Map;
import view.GameView;

public class App extends Application {

    private Stage stage;
    private MapParser mapParser;
    private Scene startScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        // Process Maps from the file
        mapParser = new MapParser();
        mapParser.configureMaps();

        // Create the start screen
        Button startButton = new Button("New game");
        startButton.setOnAction(event -> {
            // startGame(mapParser.getMap(0));
            // Create the playback screen
            VBox startLayout = new VBox();
            // Set alignment of startLayout to center
            startLayout.setAlignment(Pos.CENTER);
            startLayout.setStyle("-fx-background-color: #FFFFFF;"); // Set background color
            startLayout.setPrefSize(640, 480);
            startLayout.setAlignment(Pos.CENTER);

            // Create a ListView with sample map names
            ListView<String> mapListView = new ListView<>();
            mapListView.getItems().addAll("Map 1.txt", "Map 2.txt", "Map 3.txt",
            "Map 4.txt", "Map 5.txt", "Map 6.txt","Map 7.txt", "Map 8.txt", "Map 9.txt");

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
            saveButton.setOnAction(event_save -> {
                startGame(mapParser.getMap(0)); // Set the start screen layout as the scene
            });

            Button backButton = new Button("Back");
            backButton.setOnAction(event_back -> {
                stage.setScene(startScene); // Set the start screen layout as the scene
            });

            HBox buttonsBox = new HBox(saveButton, backButton);
            buttonsBox.setSpacing(20);
            buttonsBox.setAlignment(Pos.CENTER);

            // Add buttons to the bottom of the layout
            startLayout.getChildren().add(buttonsBox);
            VBox.setMargin(buttonsBox, new Insets(20, 0, 0, 0));

            // Create a Scene for the settings screen and set it on the stage
            Scene settingsScene = new Scene(startLayout);
            stage.setScene(settingsScene);

        });

        Button settingsButton = new Button("Settings");
            settingsButton.setOnAction(event -> {
            // Create the settings screen
            VBox settingsLayout = new VBox();
            settingsLayout.setStyle("-fx-background-color: #FFFFFF;"); // Set background color
            settingsLayout.setPrefSize(640, 480);
            settingsLayout.setAlignment(Pos.CENTER);

            // Control set-up section
            Label controlSetUpLabel = new Label("Control set-up");
            ToggleGroup controlSetUpGroup = new ToggleGroup();
            RadioButton mouseButton = new RadioButton("Mouse");
            RadioButton keyboardButton = new RadioButton("Keyboard");
            mouseButton.setToggleGroup(controlSetUpGroup);
            keyboardButton.setToggleGroup(controlSetUpGroup);
            mouseButton.setSelected(true); // Set default selection
            keyboardButton.setSelected(false);
            HBox controlSetUpBox = new HBox(mouseButton, keyboardButton);
            controlSetUpBox.setSpacing(20);
            controlSetUpBox.setAlignment(Pos.CENTER);
            VBox controlSetUpVBox = new VBox(controlSetUpLabel, controlSetUpBox);
            controlSetUpVBox.setSpacing(20);
            controlSetUpVBox.setAlignment(Pos.CENTER);
            controlSetUpVBox.setPadding(new Insets(0, 0, 30, 0));

            // Gameplay mode section
            Label gameplayModeLabel = new Label("Gameplay mode");
            ToggleGroup gameplayModeGroup = new ToggleGroup();
            RadioButton staticButton = new RadioButton("Static");
            RadioButton dynamicButton = new RadioButton("Dynamic");
            staticButton.setToggleGroup(gameplayModeGroup);
            dynamicButton.setToggleGroup(gameplayModeGroup);
            staticButton.setSelected(true); // Set default selection
            dynamicButton.setSelected(false);
            HBox gameplayModeBox = new HBox(staticButton, dynamicButton);
            gameplayModeBox.setSpacing(20);
            gameplayModeBox.setAlignment(Pos.CENTER);
            VBox gameplayModeVBox = new VBox(gameplayModeLabel, gameplayModeBox);
            gameplayModeVBox.setSpacing(20);
            gameplayModeVBox.setAlignment(Pos.CENTER);
            gameplayModeVBox.setPadding(new Insets(0, 0, 30, 0));

            // Difficulty mode section
            Label difficultyModeLabel = new Label("Difficulty mode");
            ToggleGroup difficultyModeGroup = new ToggleGroup();
            RadioButton easyButton = new RadioButton("Easy");
            RadioButton mediumButton = new RadioButton("Medium");
            RadioButton hardButton = new RadioButton("Hard");
            RadioButton insaneButton = new RadioButton("Insane");
            easyButton.setToggleGroup(difficultyModeGroup);
            mediumButton.setToggleGroup(difficultyModeGroup);
            hardButton.setToggleGroup(difficultyModeGroup);
            insaneButton.setToggleGroup(difficultyModeGroup);
            easyButton.setSelected(true); // Set default selection
            mediumButton.setSelected(false);
            hardButton.setSelected(false);
            insaneButton.setSelected(false);
            HBox difficultyModeBox = new HBox(easyButton, mediumButton, hardButton, insaneButton);
            difficultyModeBox.setSpacing(20);
            difficultyModeBox.setAlignment(Pos.CENTER);
            VBox difficultyModeVBox = new VBox(difficultyModeLabel, difficultyModeBox);
            difficultyModeVBox.setSpacing(20);
            difficultyModeVBox.setAlignment(Pos.CENTER);
            difficultyModeVBox.setPadding(new Insets(0, 0, 50, 0));

            // Add all sections to the main layout
            settingsLayout.getChildren().addAll(controlSetUpVBox, gameplayModeVBox, difficultyModeVBox);

            // Create buttons
            Button saveButton = new Button("Save");
            saveButton.setOnAction(event_save -> {
                stage.setScene(startScene); // Set the start screen layout as the scene
            });
    
            Button backButton = new Button("Back");
            backButton.setOnAction(event_back -> {
                stage.setScene(startScene); // Set the start screen layout as the scene
            });

            HBox buttonsBox = new HBox(saveButton, backButton);
            buttonsBox.setSpacing(20);
            buttonsBox.setAlignment(Pos.CENTER);

            // Add buttons to the bottom of the layout
            settingsLayout.getChildren().add(buttonsBox);
            VBox.setMargin(buttonsBox, new Insets(20, 0, 0, 0));

            // Create a Scene for the settings screen and set it on the stage
            Scene settingsScene = new Scene(settingsLayout);
            stage.setScene(settingsScene);
        });

        Button playbackButton = new Button("Playback");
        playbackButton.setOnAction(event -> {
            // Create the playback screen
            VBox playbackLayout = new VBox();
            // Set alignment of playbackLayout to center
            playbackLayout.setAlignment(Pos.CENTER);
            playbackLayout.setStyle("-fx-background-color: #FFFFFF;"); // Set background color
            playbackLayout.setPrefSize(640, 480);
            playbackLayout.setAlignment(Pos.CENTER);

            // Create a ListView with sample map names
            ListView<String> mapListView = new ListView<>();
            mapListView.getItems().addAll("Map 1.txt", "Map 2.txt", "Map 3.txt",
            "Map 4.txt", "Map 5.txt", "Map 6.txt","Map 7.txt", "Map 8.txt", "Map 9.txt");

            // Create a ScrollPane and set the ListView as its content
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(mapListView);

            // Set the height of the ScrollPane and ListView to show only a few items at a time
            scrollPane.setPrefHeight(100);
            mapListView.setPrefHeight(80);

            // Control Playback mode section
            Label playbackModeLabel = new Label("Playback mode");
            ToggleGroup playbackModeGroup = new ToggleGroup();
            RadioButton stepbystepButton = new RadioButton("Step-by-step");
            RadioButton fastButton = new RadioButton("Fast");
            stepbystepButton.setToggleGroup(playbackModeGroup);
            fastButton.setToggleGroup(playbackModeGroup);
            stepbystepButton.setSelected(true); // Set default selection
            fastButton.setSelected(false);
            HBox playbackModeBox = new HBox(stepbystepButton, fastButton);
            playbackModeBox.setSpacing(20);
            playbackModeBox.setAlignment(Pos.CENTER);
            VBox playbackModeVBox = new VBox(playbackModeLabel, playbackModeBox);
            
            playbackModeVBox.setSpacing(20);
            playbackModeVBox.setAlignment(Pos.CENTER);
            playbackModeVBox.setPadding(new Insets(0, 0, 30, 0));

            // Add all sections to the main layout
            playbackLayout.getChildren().addAll(scrollPane, playbackModeVBox);

            // Create buttons
            Button saveButton = new Button("Play");
            saveButton.setOnAction(event_save -> {
                stage.setScene(startScene); // Set the start screen layout as the scene
            });
    
            Button backButton = new Button("Back");
            backButton.setOnAction(event_back -> {
                stage.setScene(startScene); // Set the start screen layout as the scene
            });

            HBox buttonsBox = new HBox(saveButton, backButton);
            buttonsBox.setSpacing(20);
            buttonsBox.setAlignment(Pos.CENTER);

            // Add buttons to the bottom of the layout
            playbackLayout.getChildren().add(buttonsBox);
            VBox.setMargin(buttonsBox, new Insets(20, 0, 0, 0));

            // Create a Scene for the settings screen and set it on the stage
            Scene settingsScene = new Scene(playbackLayout);
            stage.setScene(settingsScene);
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            stage.close();
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(startButton, settingsButton, playbackButton, exitButton);
        vbox.setSpacing(20); // Add spacing between buttons
        vbox.setAlignment(Pos.CENTER); // Center VBox

        // Add spacing between "Exit" and "Playback" buttons
        VBox.setMargin(exitButton, new Insets(40, 0, 0, 0));

        // Create a StackPane to hold the VBox and center it
        StackPane startLayout = new StackPane(vbox);
        startLayout.setStyle("-fx-background-color: #FFFFFF;"); // Set background color
        startLayout.setPrefSize(640, 480);

        // Save the start screen layout as a scene
        startScene = new Scene(startLayout);

        // Show the start screen
        primaryStage.setScene(startScene);
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
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(root, WIDTH, HEIGHT);
        } else {
            scene.setRoot(root);
        }

        scene.setOnKeyPressed(gameController::handleKeyPress);

        gameView.updateGameView();

        // display the UI
        stage.setScene(scene);
        stage.setTitle("GAME");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
