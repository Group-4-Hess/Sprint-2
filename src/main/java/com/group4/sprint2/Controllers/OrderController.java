package com.group4.sprint2.Controllers;

import java.io.IOException;

import com.group4.sprint2.Managers.OrderManager;
import com.group4.sprint2.Order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OrderController {

    private WaiterController waiterController; 

    public void setWaiterController(WaiterController waiterController) {
        this.waiterController = waiterController;
    }


    @FXML
    private Label tableLabel;

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
    private VBox foodOptionsPanel;

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;



    @FXML
    protected void categorySelected(ActionEvent event) throws IOException {
        
        foodOptionsPanel.setVisible(true);   // toggle
        foodOptionsPanel.setManaged(true);   // toggle space too


        Button clicked = (Button) event.getSource();
        String category = clicked.getText();

        //change button text to actual food options from database (probably find a way to spawn a button in per order instead of having a set number?)
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
     * prints order details into the console and closes order window when complete order button is clicked
     * 
     * @param stage | represents the current order window stage to be closed after order completion
     * @return void
     * @throws IOException
     */

    @FXML
    private Button completeButton;

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