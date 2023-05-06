package view;

import controller.AppController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class TheEndView extends Scene{

    public TheEndView(AppController appController) {
        super(new VBox(), 1024, 720);

        Button newGameButton = new Button("New game");
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
        vbox.getChildren().addAll(newGameButton, exitButton);

        // center the VBox in the scene
        vbox.setAlignment(Pos.CENTER);

        vbox.setSpacing(20);
    }
    
}
