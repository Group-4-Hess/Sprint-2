package com.group4.sprint2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {
    @FXML
    private Label loginText;

    @FXML
    protected void onHelloButtonClick() {
        loginText.setText("the button works!");
    }
}
