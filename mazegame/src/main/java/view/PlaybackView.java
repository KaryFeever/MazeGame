// Project Title: IJA 2022/23: Pac-Man Game Design and Implementation
// Autors: Maksim Naumenko  (xnaume01)
//         Tatiana Fedorova (xfedor14)

package view;

import controller.AppController;
import controller.ReplayController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Replay;

/**
 * This class represents a PlaybackView scene that allows the user to select and play back recorded games.
 */
public class PlaybackView extends Scene{
    private int replayIndex = 0;
    ListView<String> mapListView = new ListView<>();
    AppController appController;

    /**
     * Creates a PlaybackView layout to display the list of recorded games.
     * 
     * @param appController The AppController instance to interact with the application.
     */
    public PlaybackView(AppController appController) {
        super(new VBox(), 1024, 720);
        this.appController = appController;
        VBox playbackLayout = (VBox) getRoot();

        // Set alignment of playbackLayout to center
        playbackLayout.setAlignment(Pos.CENTER);
        playbackLayout.setStyle("-fx-background-color: #ebebeb;"); // Set background color
        playbackLayout.setPrefSize(640, 480);
        playbackLayout.setAlignment(Pos.CENTER);

        // Create a ListView with sample map names
        mapListView.getItems().addAll(appController.getLogParser().getReplayNames());
        mapListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Create a ScrollPane and set the ListView as its content
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mapListView);

        // Set the height of the ScrollPane and ListView to show only a few items at a time
        scrollPane.setPrefHeight(100);
        mapListView.setPrefHeight(80);

        // Control Playback mode section
        Label playbackModeLabel = new Label("Playback mode");
        ToggleGroup playbackModeGroup = new ToggleGroup();
        ToggleGroup playbackModeGroup2 = new ToggleGroup();
        RadioButton stepbystepButton = new RadioButton("Step-by-step");
        RadioButton fastButton = new RadioButton("Fast");
        RadioButton start = new RadioButton("From the start");
        RadioButton end = new RadioButton("From the end");
        stepbystepButton.setToggleGroup(playbackModeGroup);
        fastButton.setToggleGroup(playbackModeGroup);
        start.setToggleGroup(playbackModeGroup2);
        end.setToggleGroup(playbackModeGroup2);
        stepbystepButton.setSelected(true); // Set default selection
        fastButton.setSelected(false);
        start.setSelected(true);
        end.setSelected(false);
        HBox playbackModeBox1 = new HBox(stepbystepButton, fastButton);
        HBox playbackModeBox2 = new HBox(start, end);
        VBox playbackModeBox = new VBox(playbackModeBox1, playbackModeBox2);
        playbackModeBox1.setSpacing(20);
        playbackModeBox1.setAlignment(Pos.CENTER);
        playbackModeBox2.setSpacing(20);
        playbackModeBox2.setAlignment(Pos.CENTER);
        playbackModeBox.setSpacing(20);
        VBox playbackModeVBox = new VBox(playbackModeLabel, playbackModeBox);
        
        playbackModeVBox.setSpacing(20);
        playbackModeVBox.setAlignment(Pos.CENTER);
        playbackModeVBox.setPadding(new Insets(0, 0, 30, 0));

        // Add all sections to the main layout
        playbackLayout.getChildren().addAll(scrollPane, playbackModeVBox);

        // Create buttons
        Button saveButton = new Button("Play");
        saveButton.setStyle("-fx-background-color: #2dc39d; -fx-text-fill: #FFFFFF;");
        saveButton.setOnAction(event_save -> {
            int mode = 0;
            int from = 0;
            if( ((RadioButton)playbackModeGroup.getSelectedToggle()).getText().compareTo("Step-by-step") != 0) {
                mode = 1;
            }
            if(((RadioButton)playbackModeGroup2.getSelectedToggle()).getText().compareTo("From the start") != 0) {
                from = 1;
            }
            StartReplay(appController.getLogParser().getReplays().get(replayIndex), mode, from, appController);
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
        playbackLayout.getChildren().add(buttonsBox);
        VBox.setMargin(buttonsBox, new Insets(20, 0, 0, 0));

        // Get the selected item from the ListView
        mapListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.replayIndex = appController.getLogParser().getReplayNames().indexOf(newValue);
        });
    }

    /**
     * Starts a replay of the selected game.
     * 
     * @param replay The replay instance to be played back.
     * @param mode The playback mode: 0 for step-by-step, 1 for fast.
     * @param from The starting point of the replay: 0 for the beginning, 1 for the end.
     * @param appController The AppController instance to interact with the application.
     */
    private void StartReplay(Replay replay, int mode, int from, AppController appController) {
        ReplayController replayController = new ReplayController(replay, appController);
        Canvas GameCanvas = new Canvas(800, 800);
        Canvas HUDCanvas = new Canvas(800, 64);
    
        ReplayView replayView = new ReplayView(GameCanvas, HUDCanvas, replayController);
        
        VBox vbox = new VBox(HUDCanvas, GameCanvas);
        VBox vboxButtons = new VBox(); // Vbox for buttons
        vboxButtons.setSpacing(10);
        Button exit = new Button("Exit");
        exit.setStyle("-fx-background-color: #c3522d; -fx-text-fill: #FFFFFF;");
        exit.setOnAction(event -> {
            appController.setScene(3);
        });
        if(mode == 0) {
            Button next = new Button("Next");
            Button previous = new Button("Previous");
            next.setOnAction(event -> {
                replayController.next();
                replayView.draw();
            });
            previous.setOnAction(event -> {
                replayController.previous();
                replayView.draw();
            });
            vboxButtons.getChildren().addAll(previous, next, exit);
        }
        else {
            vbox.getChildren().add(exit);
        }
        
        if(from == 0) {
            replay.setIndex(-1);
        } else {
            replayController.findKeyStep();
            replayController.getReplay().setIndex(replayController.getReplay().getSteps().size() - 1);
            replayController.updateState();
            replay.getGame().getMap().getMapField(replay.getGame().getKey().getRow(), replay.getGame().getKey().getCol()).removeObject(replay.getGame().getKey());
            replay.getGame().getPacMan().setKeyFlag(true);
        }
        replayView.draw();
        VBox vboxButtonsWrapper = new VBox(vboxButtons);
        vboxButtonsWrapper.setAlignment(Pos.CENTER);
        HBox root = new HBox(vbox, vboxButtonsWrapper);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 1024, 960);
    
        // display the UI
        appController.setScene(scene);
        if(mode == 1) {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    for(int i = 0; i < replayController.getReplay().getSteps().size(); i++) {
                        if(from == 0) {
                            replayController.next();
                        } else {
                            replayController.previous();
                        }
                        Platform.runLater(() -> replayView.draw());
                        Thread.sleep(200); // sleep for 1 second after each iteration
                    } 
                    return null;
                }
            };
            new Thread(task).start();
        }
    }

    /**
     * Updates the list of recorded games displayed in the ListView.
     */
    public void updateReplayViews() {
        mapListView.getItems().clear();
        mapListView.getItems().addAll(appController.getLogParser().getReplayNames());
    }
}