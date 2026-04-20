package com.group4.sprint2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Controller for the status screen, responsible for allowing staff to
 * update the status of a selected table and close the status window.
 * <p>
 * This controller is linked to {@code status-screen.fxml} and communicates
 * back to the {@link WaiterController} to update the visual state of the
 * selected table button on the waiter dashboard.
 * </p>
 * <p>
 * Supported table statuses:
 * <ul>
 *   <li><b>Available</b> — table is clean and ready for new guests</li>
 *   <li><b>Occupied</b> — table is currently in use</li>
 *   <li><b>Dirty</b> — table needs to be cleaned before next use</li>
 * </ul>
 * </p>
 *
 * @see WaiterController
 */

public class StatusController {

    /**
     * Reference to the {@link WaiterController} that opened this status window.
     * Used to update the table status on the waiter dashboard.
     */
    private WaiterController waiterController; 

    /**
     * Sets the reference to the parent {@link WaiterController}.
     * Must be called immediately after loading this controller via
     * {@code FXMLLoader} before any status buttons are clicked.
     *
     * @param waiterController the {@link WaiterController} that opened this window
     */
    public void setWaiterController(WaiterController waiterController) {
        this.waiterController = waiterController;
    }
    
    /**
     * Handles the "Available" button click, setting the selected table's
     * status to available on the waiter dashboard.
     * <p>
     * Delegates to {@link WaiterController#setTableStatus(String)} with
     * the value {@code "Available"}.
     * </p>
     */
    @FXML
    protected void handleAvailable() {
        waiterController.setTableStatus("Available");
    }

    /**
     * Handles the "Occupied" button click, setting the selected table's
     * status to occupied on the waiter dashboard.
     * <p>
     * Delegates to {@link WaiterController#setTableStatus(String)} with
     * the value {@code "Occupied"}.
     * </p>
     */
    @FXML
    protected void handleOccupied() {
        waiterController.setTableStatus("Occupied");
    }

    /**
     * Handles the "Dirty" button click, setting the selected table's
     * status to dirty on the waiter dashboard.
     * <p>
     * Delegates to {@link WaiterController#setTableStatus(String)} with
     * the value {@code "Dirty"}.
     * </p>
     */
    @FXML
    protected void handleDirty() {
        waiterController.setTableStatus("Dirty");
    }

    /**
     * Handles the exit button click, closing the status window.
     * <p>
     * Retrieves the current {@link Stage} from the event source and
     * closes it without affecting the waiter dashboard window.
     * </p>
     *
     * @param event the {@link ActionEvent} triggered by clicking the exit button
     */
    @FXML
    protected void handleExit(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
