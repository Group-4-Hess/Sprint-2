package com.group4.sprint2.Controllers;

import java.io.IOException;

import com.group4.sprint2.Managers.SceneManager;
import com.group4.sprint2.Managers.UserManager;
import com.group4.sprint2.Models.User;

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
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        User user = UserManager.findUser(username);
        if (user == null) {
            invalidLabel.setText("User not found.");
            return;
        }
    
        if (!user.getPassword().equals(password)) {
            invalidLabel.setText("Incorrect password.");
            return;
        }

        SceneManager.switchScene("wait-staff.fxml");
    }
}
