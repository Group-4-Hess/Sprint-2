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

/**
 * Controller for the waiter dashboard screen, responsible for managing
 * table interactions, order creation, table status updates, and navigation.
 * <p>
 * This controller is linked to {@code wait-staff.fxml} and serves as the
 * central hub for waiter operations. It supports two interaction modes:
 * <ul>
 *   <li><b>Order mode</b> — clicking a table opens the order screen</li>
 *   <li><b>Status mode</b> — clicking a table opens the status screen</li>
 * </ul>
 * The active mode is toggled via the "Change Table Status" button.
 * </p>
 *
 * @see OrderController
 * @see StatusController
 * @see OrderManager
 * @see SceneManager
 */
public class WaiterController {

    /**
     * The name of the most recently clicked table button (e.g., "Table 1").
     * Updated each time a table button is clicked.
     */
    private String tableName;

    /**
     * Tracks whether the controller is in status-changing mode.
     * When {@code true}, clicking a table opens the status screen.
     * When {@code false}, clicking a table opens the order screen.
     */
    private boolean changingStatus = false;
    
    /**
     * Reference to the most recently clicked table {@link Button}.
     * Used to apply style class changes when updating table status.
     */
    private Button table;
    
    /**
     * Handles table button click events, opening either the order screen
     * or the status screen depending on the current mode.
     * <p>
     * In order mode, opens {@code order-screen.fxml} in a new window and
     * passes the table name and controller reference to {@link OrderController}.
     * </p>
     * <p>
     * In status mode, opens {@code status-screen.fxml} in a new window and
     * passes the controller reference to {@link StatusController}.
     * </p>
     *
     * @param event the {@link ActionEvent} triggered by clicking a table button
     * @throws IOException if the FXML file cannot be loaded
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

    /**
     * Handles the "Clear Orders" button click, permanently deleting all
     * saved orders from the JSON file via {@link OrderManager#clearOrders()}.
     * <p>
     * If an error occurs during deletion, a message is printed to the console.
     * </p>
     */
    @FXML
    protected void clearOrders(){
        try {
            OrderManager.clearOrders();
        } catch (IOException e) {
            System.out.println("Error clearing orders: " + e.getMessage());
        }
    }
    
    /**
     * Handles the "Change Table Status" button click, toggling between
     * order mode and status-changing mode.
     * <p>
     * When status mode is activated, the button turns dark to indicate
     * the active mode. When deactivated, the button returns to its default style.
     * </p>
     *
     * @param event the {@link ActionEvent} triggered by clicking the change status button
     */
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

    /**
     * Updates the visual status of the most recently selected table button
     * by delegating to the appropriate status method.
     * <p>
     * Valid status values are {@code "Available"}, {@code "Occupied"}, and
     * {@code "Dirty"}. Any other value is logged to the console.
     * </p>
     *
     * @param status the new status to apply to the selected table;
     *               must be one of {@code "Available"}, {@code "Occupied"}, or {@code "Dirty"}
     */
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

    /**
     * Sets the selected table's visual style to available by applying
     * the {@code empty-table} CSS class and removing conflicting classes.
     */
    @FXML
    protected void setAvailableTable(){
        table.getStyleClass().removeAll("occupied-table", "dirty-table");
        table.getStyleClass().add("empty-table");
    }

    /**
     * Sets the selected table's visual style to occupied by applying
     * the {@code occupied-table} CSS class and removing conflicting classes.
     */
    @FXML
    protected void setOccupiedTable(){
        table.getStyleClass().removeAll("empty-table", "dirty-table");
        table.getStyleClass().add("occupied-table");
    }

    /**
     * Sets the selected table's visual style to dirty by applying
     * the {@code dirty-table} CSS class and removing conflicting classes.
     */
    @FXML
    protected void setDirtyTable(){
        table.getStyleClass().removeAll("occupied-table", "empty-table");
        table.getStyleClass().add("dirty-table");
    }
    
    /**
     * Handles the "Send to Kitchen" button click, printing a consolidated
     * summary of all current orders grouped by table to the console via
     * {@link OrderManager#printKitchenOrders()}.
     */
    @FXML
    protected void sendToKitchen(){
        OrderManager.printKitchenOrders();
    }
    
    /**
     * Handles the logout button click, navigating back to the login screen.
     * <p>
     * Switches the current scene to {@code login-screen.fxml} via
     * {@link SceneManager#switchScene(String)}.
     * </p>
     *
     * @throws IOException if an error occurs while switching scenes
     */ 
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
