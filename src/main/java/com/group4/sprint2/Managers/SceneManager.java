package com.group4.sprint2.Managers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Manages scene transitions for the application's primary {@link Stage}.
 * <p>
 * This class provides a centralized mechanism for switching between FXML-defined
 * scenes within the main application window. It maintains a static reference to
 * the primary {@link Stage}, which must be set during application startup via
 * {@link #setStage(Stage)} before any scene transitions can occur.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     // In Sprint_2.java start() method:
 *     SceneManager.setStage(stage);
 *     SceneManager.switchScene("login-screen.fxml");
 *
 *     // In a controller:
 *     SceneManager.switchScene("wait-staff.fxml");
 * </pre>
 */
public class SceneManager {

    /**
     * The primary application {@link Stage} used for all scene transitions.
     * Must be set via {@link #setStage(Stage)} before calling
     * {@link #switchScene(String)}.
     */
    private static Stage stage;

    /**
     * Sets the primary application {@link Stage} to be used for scene transitions.
     * <p>
     * This method must be called once during application startup, typically
     * in the {@code start()} method of the main {@code Application} class,
     * before any calls to {@link #switchScene(String)} are made.
     * </p>
     *
     * @param stage the primary {@link Stage} of the JavaFX application;
     *              must not be {@code null}
     */
    public static void setStage(Stage stage) {
        SceneManager.stage = stage;
    }

    /**
     * Switches the current scene of the primary {@link Stage} to the
     * scene defined by the specified FXML file.
     * <p>
     * The FXML file is resolved from the classpath at the path
     * {@code /com/group4/sprint2/<fxmlFile>}. The new scene is displayed
     * at a fixed size of 1000x900 pixels with the title
     * "Restaurant Management System".
     * </p>
     *
     * @param fxmlFile the name of the FXML file to load (e.g., {@code "login-screen.fxml"});
     *                 must exist at {@code /com/group4/sprint2/<fxmlFile>} on the classpath
     * @throws IOException if the FXML file cannot be found or loaded
     * @throws NullPointerException if the resolved resource URL is {@code null},
     *                              indicating the file was not found on the classpath
     */
    public static void switchScene(String fxmlFile) throws IOException {
        var url = SceneManager.class.getResource("/com/group4/sprint2/" + fxmlFile);
        
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root, 1000, 900));
        stage.show();
        stage.setTitle("Restaurant Management System");
    }
}
