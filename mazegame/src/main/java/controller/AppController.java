package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.HomeView;
import view.NewGameView;
import view.SettingsView;
import view.TheEndView;
import view.PlaybackView;

public class AppController {
    private Stage stage;
    private MapParser mapParser;
    private int mapIndex = 0;
    private int replayIndex = 0;
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

    public void setDifficultyConfiguration(int pacmanLives, int ghostsSpeed) {
        this.pacManLives = pacmanLives;
        this.ghostsSpeed = ghostsSpeed;
    }
    
    // scene new game, settings atd
    public AppController (Stage stage) {
        this.stage = stage;
        logParser = new LogParser();
        logParser.configureLogs();

        mapParser = new MapParser();
        mapParser.configureMaps();

        homeView = new HomeView(this);
        settingsView = new SettingsView(1, this);
        newGameView = new NewGameView(this);
        playbackView = new PlaybackView(this);
        theEndView = new TheEndView(this);

        activeScene = homeView;
        
    } 

    public Scene getScene() {
        return activeScene;
    }

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
                activeScene = playbackView;
                break;
            case 4:
                activeScene = theEndView;
            default:
                break;
        }
        stage.setScene(activeScene);
        stage.show();
    }

    public MapParser getMapParser () {
       return mapParser; 
    }

    public int getPacManLives() {
        return pacManLives;
    }

    public int getGhostsSpeed() {
        return ghostsSpeed;
    }

    public void setScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

    public LogParser getLogParser() {
        return logParser;
    }

    public void closeScene() {
        stage.close();
    }
}
