// Project Title: IJA 2022/23: Pac-Man Game Design and Implementation
// Autors: Maksim Naumenko  (xnaume01)
//         Tatiana Fedorova (xfedor14)

import controller.AppController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class represents the main application for the game, setting up the primary stage
 * and handling user interaction and game state changes.
 */
public class App extends Application {
    
    /**
     * The entry point of the JavaFX application.
     * Sets up the main stage and scene for the game.
     *
     * @param primaryStage The primary stage for this JavaFX application.
     * @throws Exception If there is an error during the initialization process.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        AppController appController = new AppController(primaryStage);
        primaryStage.setScene(appController.getScene());
        primaryStage.show();
    }

    /**
     * The main method of the application, launching the JavaFX application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
