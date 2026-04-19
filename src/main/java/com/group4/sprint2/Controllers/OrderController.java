package com.group4.sprint2.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OrderController {
 
    @FXML 
    private VBox foodOptionsPanel;

    @FXML
    private Label tableLabel; // add this label to your FXML

    private String tableName;

    public void setTable(String tableName) {
        this.tableName = tableName;
        tableLabel.setText("Order: " + tableName); // display it on screen
    }


    private String order = ""; 
    /**
     * opens and closes the food options panel when a category button is clicked
     * 
     * @param isVisible | the visibility state of the food options panel
     * @return void
     * @throws IOException
     */
    @FXML
    protected void categorySelected(ActionEvent event) throws IOException {
        Button clicked = (Button) event.getSource();
        String category = clicked.getText();
        if(category.equals("Soups")) {
            System.out.println("Soups selected");
            // add logic to populate food options panel with appetizers here
        }
        else if(category.equals("Salad")) {
            System.out.println("Salads selected");
            // add logic to populate food options panel with main courses here
        }
        else if(category.equals("Entrees")) {
            System.out.println("Entrees selected");
            // add logic to populate food options panel with main courses here
        }
        else if(category.equals("Drinks")) {
            System.out.println("Drinks selectead");
            // add logic to populate food options panel with main courses here
        }
        else if(category.equals("Desserts")) {
            System.out.println("Desserts selected");
            // add logic to populate food options panel with desserts here
        }

        boolean isVisible = foodOptionsPanel.isVisible();
        foodOptionsPanel.setVisible(!isVisible);   // toggle
        foodOptionsPanel.setManaged(!isVisible);   // toggle space too

    }


    /**
     * selects a food item and adds it to the order when it is clicked
     * 
     * @param clicked | the food item button clicked by the user
     * @return void
     * @throws IOException
     */
    @FXML
    private void onFoodItemClick(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        System.out.println("Selected: " + clicked.getText());
        order += clicked.getText() + "\n"; // add selected item to order

    }

    /**
     * completes order and sends it to kitchen
     * 
     * @param TBD | the logic for completing the order will be added here once the database is set up
     * @return void
     * @throws IOException
     */

    @FXML
    private Button completeButton;

    @FXML
    private void completeOrder() {
        // add logic to save order to database here

        System.out.println("-------------------------------------");
        System.out.println("Order completed!");
        System.out.println(tableName);
        System.out.println("Order details:\n" + order);
        System.out.println("-------------------------------------");

        Stage stage = (Stage) completeButton.getScene().getWindow();
        stage.close();

    }

}