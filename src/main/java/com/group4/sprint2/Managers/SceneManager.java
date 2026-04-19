package com.group4.sprint2.Managers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static Stage stage;

    public static void setStage(Stage stage) {
        SceneManager.stage = stage;
    }

    public static void switchScene(String fxmlFile) throws IOException {
        var url = SceneManager.class.getResource("/com/group4/sprint2/" + fxmlFile);
        
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root, 1000, 900));
        stage.show();
    }


}
