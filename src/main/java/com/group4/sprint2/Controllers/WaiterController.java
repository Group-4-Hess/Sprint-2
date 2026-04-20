package com.group4.sprint2.Controllers;

import java.io.IOException;

import com.group4.sprint2.Managers.OrderManager;
import com.group4.sprint2.Managers.SceneManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WaiterController {

    private String tableName;
    private boolean changingStatus = false;
    
    private Button table;
    
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
        table = (Button) event.getSource();
        tableName = table.getText(); 

        if (changingStatus) {
            Stage newWindow = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group4/sprint2/status-screen.fxml"));
            Parent root = loader.load();
        
            StatusController statusController = loader.getController();
            statusController.setWaiterController(this);

            newWindow.setScene(new Scene(root, 500, 300));
            newWindow.setTitle("Set " + tableName + " Status");
            newWindow.show();
        }
        else {
            Stage newWindow = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group4/sprint2/order-screen.fxml"));
            Parent root = loader.load();
        
            OrderController orderController = loader.getController();
            orderController.setTable(tableName);
            orderController.setWaiterController(this); 

            newWindow.setScene(new Scene(root, 800, 900));
            newWindow.setTitle("Orders - " + tableName);
            newWindow.show();
        }
    }

    @FXML
    protected void clearOrders(){
        try {
            OrderManager.clearOrders();
        } catch (IOException e) {
            System.out.println("Error clearing orders: " + e.getMessage());
        }
    }

    @FXML
    protected void changeStatus(ActionEvent event) {
        Button button = (Button) event.getSource();
        changingStatus = !changingStatus;
    
        if (changingStatus) {
            button.setStyle("-fx-background-color: #303234; -fx-text-fill: #FFFFFF;");  // example of changing the button's background and text color
        } else {
            button.setStyle("");  
        }
    }

    public void setTableStatus(String status){
        switch (status) {
            case "Available":
                setAvailableTable();
                break;
            case "Occupied":
                setOccupiedTable();
                break;
            case "Dirty":
                setDirtyTable();
                break;
            default:
                System.out.println("Invalid status: " + status);
        }
    }

    @FXML
    protected void setAvailableTable(){
        table.getStyleClass().removeAll("occupied-table", "dirty-table");
        table.getStyleClass().add("empty-table");
    }

    @FXML
    protected void setOccupiedTable(){
        table.getStyleClass().removeAll("empty-table", "dirty-table");
        table.getStyleClass().add("occupied-table");
    }

    @FXML
    protected void setDirtyTable(){
        table.getStyleClass().removeAll("occupied-table", "empty-table");
        table.getStyleClass().add("dirty-table");
    }
    
    @FXML
    protected void sendToKitchen(){
        OrderManager.printKitchenOrders();
    }

    @FXML
    private void handleLogout() throws IOException {
        SceneManager.switchScene("login-screen.fxml");
    }

    /**
     * Receives the completed order string from {@link OrderController}
     * upon order completion.
     * <p>
     * Currently logs the order to the console. Can be extended to update
     * the UI or trigger additional workflows.
     * </p>
     *
     * @param order the completed order string containing all selected items
     */
    public void setOrder(String order) {
        System.out.println("Order received: " + order);
    }

}
