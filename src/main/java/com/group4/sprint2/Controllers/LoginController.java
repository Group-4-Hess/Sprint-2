package com.group4.sprint2.Controllers;

import java.io.IOException;

import com.group4.sprint2.Managers.SceneManager;
import com.group4.sprint2.Managers.UserManager;
import com.group4.sprint2.Models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


/**
 * Controller for the login screen, responsible for authenticating users
 * and navigating to the appropriate screen upon successful login.
 * <p>
 * This controller is linked to {@code login-screen.fxml} and uses
 * {@link UserManager} to validate credentials against stored user data.
 * </p>
 *
 * @see UserManager
 * @see User
 * @see SceneManager
 */
public class LoginController {

    /**
     * Label used to display error messages to the user, such as
     * "User not found" or "Incorrect password".
     * Remains empty when no error has occurred.
     */
    @FXML
    private Label invalidLabel;

    /**
     * Text field where the user enters their username.
     */
    @FXML
    private TextField usernameField;

    /**
     * Password field where the user enters their password.
     * Input is masked for security.
     */
    @FXML
    private PasswordField passwordField;
    
/**
     * Handles the login button click event by validating the entered
     * username and password against stored user data.
     * <p>
     * Validation steps:
     * <ol>
     *   <li>Checks if the username exists in the user store.</li>
     *   <li>Checks if the entered password matches the stored password.</li>
     *   <li>If both checks pass, navigates to the appropriate screen
     *       based on the user's role.</li>
     * </ol>
     * </p>
     * <p>
     * If validation fails at any step, an appropriate error message
     * is displayed in {@code invalidLabel} and navigation is halted.
     * </p>
     *
     * @throws IOException if an error occurs while switching scenes
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
