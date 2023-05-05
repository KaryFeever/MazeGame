package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsView extends Scene {
    public SettingsView(int difficultyLevel) {
        super(new VBox(), 1024, 720); // create a new VBox with a width of 400 and height of 300
        
        // Difficulty mode section
        Label difficultyModeLabel = new Label("Difficulty mode");


        RadioButton easyButton = new RadioButton("Easy");
        RadioButton mediumButton = new RadioButton("Medium");
        RadioButton hardButton = new RadioButton("Hard");
        RadioButton insaneButton = new RadioButton("Insane");


        ToggleGroup difficultyModeGroup = new ToggleGroup();
        easyButton.setToggleGroup(difficultyModeGroup);
        mediumButton.setToggleGroup(difficultyModeGroup);
        hardButton.setToggleGroup(difficultyModeGroup);
        insaneButton.setToggleGroup(difficultyModeGroup);

        switch(difficultyLevel) {
            case 0:
                easyButton.setSelected(true); 
                break;
            case 1:
                mediumButton.setSelected(true);
                break;
            case 2:
                hardButton.setSelected(true);
                break;
            case 3:
                insaneButton.setSelected(true);
                break;
        }
        
        
        HBox difficultyModeBox = new HBox(easyButton, mediumButton, hardButton, insaneButton);
        difficultyModeBox.setSpacing(20);
        difficultyModeBox.setAlignment(Pos.CENTER);
        VBox difficultyModeVBox = new VBox(difficultyModeLabel, difficultyModeBox);
        difficultyModeVBox.setSpacing(20);
        difficultyModeVBox.setAlignment(Pos.CENTER);
        difficultyModeVBox.setPadding(new Insets(0, 0, 50, 0));


        // Create buttons
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: #2dc39d; -fx-text-fill: #FFFFFF;");
        saveButton.setOnAction(event_save -> {
            // stage.setScene(startScene); // Set the start screen layout as the scene
            // switch(((RadioButton)difficultyModeGroup.getSelectedToggle()).getText()) {
            //     case "Easy":
            //          TODO: call function setDifficultyConfiguration()
            //         pacManLives = 3;
            //         easy = 0;
            //         break;
            //     case "Medium":
            //         pacManLives = 2;
            //         medium = 1;
            //         break;
            //     case "Hard":
            //         pacManLives = 1;
            //         hard = true;
            //         break;
            //     case "Insane":
            //         pacManLives = 1;
            //         ghostsSpeed = 2;
            //         insane = true;
            //         break;
            // }
        });
    
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #c3522d; -fx-text-fill: #FFFFFF;");
        backButton.setOnAction(event_back -> {
            // stage.setScene(startScene); // Set the start screen layout as the scene
        });

        HBox buttonsBox = new HBox(saveButton, backButton);
        buttonsBox.setSpacing(20);
        buttonsBox.setAlignment(Pos.CENTER);

        // add the label, radio buttons, and button HBox to the VBox
        VBox vbox = (VBox) getRoot(); // get the VBox from the scene's root
        vbox.getChildren().addAll(difficultyModeLabel, easyButton, mediumButton, hardButton, insaneButton, buttonsBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

    }
}
