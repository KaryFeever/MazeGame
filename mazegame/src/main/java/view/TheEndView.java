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

public class TheEndView extends Scene{
    Label difficultyModeLabel = new Label();
    Button newGameButton;
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
     * @param label
     */
    public void setLabel(String label) {
        difficultyModeLabel.setText(label);
    }
}
