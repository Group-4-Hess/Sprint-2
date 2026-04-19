package com.group4.sprint2.Controllers;

import java.io.IOException;

import com.group4.sprint2.Managers.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private Label invalidLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    
    /**
     * Checks username and password to allow access to application
     * 
     * @param username | the username entered by the user
     * @param password | the password entered by the user
     * @return void
     * @throws IOException
     */
    @FXML 
    protected void onLoginButtonClick() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        //checks UN and PW with hardocded password for now (please replace when database is created)
        if(username.equals("admin") && password.equals("admin")) {
            SceneManager.switchScene("wait-staff.fxml");
        } 
        else {
            invalidLabel.setText("Invalid username or password.");
        }
    }
}
