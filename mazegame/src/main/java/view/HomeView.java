package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class HomeView  extends Scene{
    public HomeView() {
        super(new VBox(), 1024, 720); // create a new VBox with a width of 400 and height of 300

        // create the four buttons
        Button startButton = new Button("New game");
        startButton.setStyle("-fx-background-color: #2dc39d; -fx-text-fill: #FFFFFF;");

        Button settingsButton = new Button("Settings");
        // add SetOnAction()
            // Call setScene(settingsMarker) from App.java
            

        Button playbackButton = new Button("Playback");

        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: #c3522d; -fx-text-fill: #FFFFFF;");

        // add the buttons to the VBox
        VBox vbox = (VBox) getRoot(); // get the VBox from the scene's root
        vbox.getChildren().addAll(startButton, settingsButton, playbackButton, exitButton);

        // center the VBox in the scene
        vbox.setAlignment(Pos.CENTER);

        vbox.setSpacing(20); // Add spacing between buttons
    }
}
