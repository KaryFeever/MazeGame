// Project Title: IJA 2022/23: Pac-Man Game Design and Implementation
// Autors: Maksim Naumenko  (xnaume01)
//         Tatiana Fedorova (xfedor14)

package view;

import controller.AppController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * HomeView class represents the home screen of the application.
 */
public class HomeView  extends Scene{

    /**
     * Constructor for HomeView.
     *
     * @param appController The application's main controller object.
     */
    public HomeView(AppController appController) {
        super(new VBox(), 1024, 720); // create a new VBox with a width of 400 and height of 300

        // create the four buttons
        Button startButton = new Button("New game");
        startButton.setStyle("-fx-background-color: #2dc39d; -fx-text-fill: #FFFFFF;");
        startButton.setOnAction(event -> {
            appController.setScene(1);
        });
        Button settingsButton = new Button("Settings");
        settingsButton.setOnAction(event -> {
            appController.setScene(2);
        });

        Button playbackButton = new Button("Playback");
        playbackButton.setOnAction(event -> {
            appController.setScene(3);
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            appController.closeScene();
        });
        exitButton.setStyle("-fx-background-color: #c3522d; -fx-text-fill: #FFFFFF;");

        // add the buttons to the VBox
        VBox vbox = (VBox) getRoot(); // get the VBox from the scene's root
        vbox.getChildren().addAll(startButton, settingsButton, playbackButton, exitButton);

        // center the VBox in the scene
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20); // Add spacing between buttons
    }
}