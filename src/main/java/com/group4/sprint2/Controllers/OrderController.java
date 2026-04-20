package com.group4.sprint2.Controllers;

import java.io.IOException;

import com.group4.sprint2.Managers.OrderManager;
import com.group4.sprint2.Models.Order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Controller for the order screen, responsible for managing food category
 * selection, food item selection, and order completion for a specific table.
 * <p>
 * This controller is linked to {@code order-screen.fxml} and communicates
 * back to the {@link WaiterController} upon order completion.
 * </p>
 *
 * @see WaiterController
 * @see OrderManager
 * @see Order
 */
public class OrderController {

    /**
     * Reference to the {@link WaiterController} that opened this order window.
     * Used to communicate order completion back to the waiter screen.
     */
    private WaiterController waiterController; 

    /** Accumulates the selected food items as a newline-separated string. */
    private String order = ""; 

    /** Label displaying the current table name at the top of the order screen. */
    @FXML
    private Label tableLabel;
    
    /** The name of the table this order belongs to. */
    private String tableName;

    
    /**
     * Sets the reference to the parent {@link WaiterController}.
     *
     * @param waiterController the {@link WaiterController} that opened this window
     */
    public void setWaiterController(WaiterController waiterController) {
        this.waiterController = waiterController;
    }


   
    /**
    * Sets the table name for this order and updates the on-screen label.
    *
    * @param tableName the name of the table (e.g., "Table 1")
    */
    public void setTable(String tableName) {
        this.tableName = tableName;
        tableLabel.setText("Order: " + tableName); // display it on screen
    }
    


    /** The panel containing food item buttons, shown when a category is selected. */
    @FXML 
    private VBox foodOptionsPanel;

    /** Food item button 1 - dynamically updated based on selected category */
    @FXML
    private Button button1;
    
    /** Food item button 2 - dynamically updated based on selected category */
    @FXML
    private Button button2;
    
    /** Food item button 3 - dynamically updated based on selected category */
    @FXML
    private Button button3;
    
    /** Food item button 4 - dynamically updated based on selected category */
    @FXML
    private Button button4;


    /**
     * Handles category button clicks, showing the food options panel and
     * populating it with menu items relevant to the selected category.
     * <p>
     * Supported categories: Soups, Salads, Entrees, Drinks, Desserts.
     * </p>
     *
     * @param event the {@link ActionEvent} triggered by clicking a category button
     * @throws IOException if an I/O error occurs
     */        
    @FXML
    protected void categorySelected(ActionEvent event) throws IOException {
        
        foodOptionsPanel.setVisible(true); 
        foodOptionsPanel.setManaged(true);   


        Button clicked = (Button) event.getSource();
        String category = clicked.getText();


    if(category.equals("Soups")) {
            System.out.println("Soups selected");

            button1.setText("Chicken Noodle Soup");
            button2.setText("Tomato Soup");
            button3.setText("Minestrone Soup");
            button4.setText("Gazpacho Soup");
        }
        else if(category.equals("Salads")) {
            System.out.println("Salads selected");
            button1.setText("Caesar Salad");
            button2.setText("Greek Salad");
            button3.setText("Garden Salad");
            button4.setText("Cobb Salad");
        }
        else if(category.equals("Entrees")) {
            System.out.println("Entrees selected");
            button1.setText("Grilled Chicken");
            button2.setText("Steak");
            button3.setText("Salmon");
            button4.setText("Vegetarian Pasta");
        }
        else if(category.equals("Drinks")) {
            System.out.println("Drinks selected");
            button1.setText("Coke");
            button2.setText("Sprite");
            button3.setText("Lemonade");
            button4.setText("Iced Tea");
        }
        else if(category.equals("Desserts")) {
            System.out.println("Desserts selected");
            button1.setText("Chocolate Cake");
            button2.setText("Cheesecake");
            button3.setText("Ice Cream");
            button4.setText("Apple Pie");
        }
    }


/**
     * Handles food item button clicks, appending the selected item to the order.
     * <p>
     * Each clicked item is added to the running order string separated by newlines.
     * </p>
     *
     * @param event the {@link ActionEvent} triggered by clicking a food item button
     */
    @FXML
    private void onFoodItemClick(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        System.out.println("Selected: " + clicked.getText());
        order += clicked.getText() + "\n"; // add selected item to order

    }


    private Button completeButton;

    /**
     * Completes the current order by saving it to the JSON file via
     * {@link OrderManager}, notifying the {@link WaiterController},
     * and closing the order window.
     * <p>
     * Uses the {@link ActionEvent} source to retrieve the current
     * {@link Stage} and close it without requiring a direct button reference.
     * </p>
     *
     * @param event the {@link ActionEvent} triggered by clicking the complete order button
     */
    @FXML
    private void completeOrder() {
        
        Order order = new Order(tableName, this.order);
        try {
            OrderManager.saveOrder(order);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) completeButton.getScene().getWindow();
        stage.close();

    }

}