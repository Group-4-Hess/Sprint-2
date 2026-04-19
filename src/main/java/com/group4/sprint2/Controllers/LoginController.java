package com.group4.sprint2.Controllers;

import java.io.IOException;

import com.group4.sprint2.Managers.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {
    @FXML
    private Label loginText;

    @FXML
    protected void onLoginButtonClick() throws IOException {
        SceneManager.switchScene("host staff.fxml", 1500, 900);
    }
}
