import controller.AppController;
import controller.GameController;
import controller.LogParser;
import controller.MapParser;
import controller.ReplayController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import model.Game;
import model.Map;
import model.Replay;
import view.GameView;
import view.HomeView;
import view.ReplayView;

/**
 * This class represents the main application for the game, setting up the primary stage
 * and handling user interaction and game state changes.
 */
public class App extends Application {

    private Stage stage;
    private MapParser mapParser;
    private Scene startScene;
    private int mapIndex = 0;
    private int replayIndex = 0;
    private int pacManLives = 3;
    private int ghostsSpeed = 1;
    private boolean easy = true;
    private boolean medium = false;
    private boolean hard = false;
    private boolean insane = false;
    private int difficultyLevel = 0;
    
    /**
     * Sets the configuration for the game difficulty by specifying the lives
     * for Pac-Man and the speed of the ghosts.
     *
     * @param pacmanLives The number of lives for Pac-Man.
     * @param ghostsSpeed The speed of the ghosts.
     */
    public void setDifficultyConfiguration(int pacmanLives, int ghostsSpeed) {
        this.pacManLives = pacmanLives;
        this.ghostsSpeed = ghostsSpeed;
    }
    
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
