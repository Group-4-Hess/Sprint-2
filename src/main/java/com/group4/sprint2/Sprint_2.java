package com.group4.sprint2;

import java.io.IOException;

import com.group4.sprint2.Managers.SceneManager;

import javafx.application.Application;
import javafx.stage.Stage;

public class Sprint_2 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.setStage(stage);
        SceneManager.switchScene("login-screen.fxml", 1000, 900);
    }
}
