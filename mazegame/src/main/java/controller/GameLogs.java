package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.logs.Log;

public class GameLogs {
    private String path = "data/logs";
    private File directory = new File(path);
    private List<Log> logs = new ArrayList<>();
    private Log currentLog;

    public GameLogs() {

    }

    private void  configureLogs() {

    }

    public void createLog(String mapString) {
        currentLog = new Log(mapString);
    }

    public Log getLog() {
        return this.currentLog;
    }

    public void saveLog() {
        this.currentLog.saveLog(path);
    }

}
