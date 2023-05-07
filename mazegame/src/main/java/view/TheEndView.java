// Project Title: IJA 2022/23: Pac-Man Game Design and Implementation
// Autors: Maksim Naumenko  (xnaume01)
//         Tatiana Fedorova (xfedor14)

package view;

import controller.AppController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This class represents a TheEndView that displays a message upon the end of the game,
 * providing options for the user to start a new game or exit.
 */
public class TheEndView extends Scene{
    Label difficultyModeLabel = new Label();
    Button newGameButton;

    /**
     * Creates a TheEndView object that shows the end of the game message and options.
     *
     * @param appController The AppController instance to interact with the game state.
     */
    public TheEndView(AppController appController) {
        super(new VBox(), 1024, 720);
        Font font =  Font.font("Roboto", FontWeight.EXTRA_BOLD, 64);
        difficultyModeLabel.setFont(font);
        newGameButton = new Button("New game");
        newGameButton.setStyle("-fx-background-color: #2dc39d; -fx-text-fill: #FFFFFF;");
        newGameButton.setOnAction(event -> {
            appController.setScene(1);
        });

        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: #c3522d; -fx-text-fill: #FFFFFF;");
        exitButton.setOnAction(event -> {
            appController.setScene(0);
        });

        VBox vbox = (VBox) getRoot(); // get the VBox from the scene's root
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        hBox.getChildren().addAll(newGameButton, exitButton);
        vbox.getChildren().addAll(difficultyModeLabel, hBox);

        // center the VBox in the scene
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
    }
    
    /**
     * Sets the text for the end of the game message label.
     *
     * @param label The text to display in the end of the game message label.
     */
    public void setLabel(String label) {
        difficultyModeLabel.setText(label);
    }
}