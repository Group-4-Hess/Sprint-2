package com.group4.sprint2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class StatusController {
    private WaiterController waiterController; 


    public void setWaiterController(WaiterController waiterController) {
        this.waiterController = waiterController;
    }

    @FXML
    protected void handleAvailable() {
        waiterController.setTableStatus("Available");
    }
    @FXML
    protected void handleOccupied() {
        waiterController.setTableStatus("Occupied");
    }
    @FXML
    protected void handleDirty() {
        waiterController.setTableStatus("Dirty");
    }

    

    @FXML
    protected void handleExit(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }



}
