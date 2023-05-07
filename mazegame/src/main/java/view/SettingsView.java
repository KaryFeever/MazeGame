package view;

import controller.AppController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class represents a SettingsView that displays a settings menu for the user
 * to configure the game's difficulty.
 */
public class SettingsView extends Scene {
    private AppController appController;
    RadioButton easyButton;
    RadioButton mediumButton;
    RadioButton hardButton;
    RadioButton insaneButton;

    /**
     * Creates a SettingsView object for configuring game difficulty.
     *
     * @param appController The AppController instance to interact with the game state.
     */
    public SettingsView(AppController appController) {
        super(new VBox(), 1024, 720);
        this.appController = appController;
        
        // Difficulty mode section
        Label difficultyModeLabel = new Label("Difficulty mode");

        easyButton = new RadioButton("Easy");
        mediumButton = new RadioButton("Medium");
        hardButton = new RadioButton("Hard");
        insaneButton = new RadioButton("Insane");

        ToggleGroup difficultyModeGroup = new ToggleGroup();
        easyButton.setToggleGroup(difficultyModeGroup);
        mediumButton.setToggleGroup(difficultyModeGroup);
        hardButton.setToggleGroup(difficultyModeGroup);
        insaneButton.setToggleGroup(difficultyModeGroup);

        this.setSelected();
        
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
            switch(((RadioButton)difficultyModeGroup.getSelectedToggle()).getText()) {
                case "Easy":
                    appController.setDifficultyConfiguration(0, 3, 1);
                    break;
                case "Medium":
                    appController.setDifficultyConfiguration(1, 2, 1);
                    break;
                case "Hard":
                    appController.setDifficultyConfiguration(2, 1, 1);
                    break;
                case "Insane":
                    appController.setDifficultyConfiguration(3, 1, 2);
                    break;
            }
            appController.setScene(0);
        });
    
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #c3522d; -fx-text-fill: #FFFFFF;");
        backButton.setOnAction(event_back -> {
            this.setSelected();
            appController.setScene(0);
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

    /**
     * Sets the selected difficulty RadioButton according to the current difficulty level.
     */
    private void setSelected() {
        switch(appController.getDifficultyLevel()) {
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
    }
}