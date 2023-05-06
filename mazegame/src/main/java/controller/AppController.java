package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.HomeView;
import view.NewGameView;
import view.SettingsView;
import view.TheEndView;
import view.PlaybackView;

/**
 * AppController class for handling scene navigation and application state.
 */
public class AppController {
    private Stage stage;
    private MapParser mapParser;
    private int pacManLives = 3;
    private int ghostsSpeed = 1;
    private int difficultyLevel = 0;
    private LogParser logParser;
    private Scene activeScene;
    private Scene homeView;
    private Scene settingsView;
    private Scene newGameView;
    private Scene playbackView;
    private Scene theEndView;

    /** 
     * Set the difficulty configuration for the game.
     *
     * @param difficultyLevel The difficulty level of the game.
     * @param pacmanLives The number of lives for PacMan.
     * @param ghostsSpeed The speed of the ghosts in the game.
     */
    public void setDifficultyConfiguration(int difficultyLevel, int pacmanLives, int ghostsSpeed) {
        this.difficultyLevel = difficultyLevel;
        this.pacManLives = pacmanLives;
        this.ghostsSpeed = ghostsSpeed;
    }
    
    /**
     * AppController constructor.
     *
     * @param stage The stage used for displaying scenes.
     */
    public AppController (Stage stage) {
        this.stage = stage;
        logParser = new LogParser();
        logParser.configureLogs();

        mapParser = new MapParser();
        mapParser.configureMaps();

        homeView = new HomeView(this);
        settingsView = new SettingsView(this);
        newGameView = new NewGameView(this);
        playbackView = new PlaybackView(this);
        theEndView = new TheEndView(this);
        activeScene = homeView;
    } 
    
    /** 
     * Get the current active scene.
     *
     * @return The active scene.
     */
    public Scene getScene() {
        return activeScene;
    }

    /** 
     * Set the active scene based on a scene index.
     *
     * @param scene The index of the scene to be displayed.
     */
    public void setScene(int scene) {
        switch (scene) {
            // start scene
            case 0:
                activeScene = homeView;
                break;
            // new game
            case 1:
                activeScene = newGameView;
                break;
            //settings 
            case 2:
                activeScene = settingsView;
                break;
            //playback
            case 3:
                logParser.configureLogs();
                ((PlaybackView) playbackView).updateReplayViews();
                activeScene = playbackView;
                break;
            case 4:
                mapParser.configureMaps();
                activeScene = theEndView;
            default:
                break;
        }
        stage.setScene(activeScene);
        stage.show();
    }

    /** 
     * Get the map parser.
     *
     * @return The map parser.
     */
    public MapParser getMapParser () {
       return mapParser; 
    }

    /** 
     * Get the number of PacMan lives.
     *
     * @return The number of PacMan lives.
     */
    public int getPacManLives() {
        return pacManLives;
    }

    /** 
     * Get the ghosts speed.
     *
     * @return The ghosts speed.
     */
    public int getGhostsSpeed() {
        return ghostsSpeed;
    }

    /** 
     * Set the active scene using a scene object.
     *
     * @param scene The scene object to be displayed.
     */
    public void setScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

    /** 
     * Get the log parser.
     *
     * @return The log parser.
     */
    public LogParser getLogParser() {
        return logParser;
    }

    /**
     * Close the active scene.
     */
    public void closeScene() {
        stage.close();
    }

    /** 
     * Set the label for the end view.
     *
     * @param string The label text.
     */
    public void setLabel(String string) {
        ((TheEndView) theEndView).setLabel(string);
    }
    
    /** 
     * Get the difficulty level.
     *
     * @return The difficulty level.
     */
    public int getDifficultyLevel() {
        return difficultyLevel;
    }
}
