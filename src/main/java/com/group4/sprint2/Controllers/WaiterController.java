package com.group4.sprint2.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WaiterController {

    
    /**
     * Checks opens order screen when a table button is clicked
     * 
     * @param table | the table button clicked by the user
     * @param newWindow | the new window that will open when a table button is clicked
     * @return void
     * @throws IOException
     */
    @FXML 
    protected void handleTable(ActionEvent event) throws IOException {
        Button table = (Button) event.getSource();
        String tableName = table.getText(); 
        Stage newWindow = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group4/sprint2/order-screen.fxml"));
        Parent root = loader.load();
    
        OrderController orderController = loader.getController();
        orderController.setTable(tableName);
    
        newWindow.setScene(new Scene(root, 800, 900));
        newWindow.setTitle("Orders - " + tableName);
        newWindow.show();
    
    }

}
