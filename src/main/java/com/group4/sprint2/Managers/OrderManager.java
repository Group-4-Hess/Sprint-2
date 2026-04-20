package com.group4.sprint2.Managers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.group4.sprint2.Models.Order;

/**
 * Manages the persistence and retrieval of {@link Order} objects using a JSON file.
 * <p>
 * This class provides static utility methods for saving, loading, grouping,
 * printing, and clearing orders. All order data is stored in a local JSON file
 * defined by {@code FILE_PATH}. Serialization and deserialization is handled
 * using the Gson library.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     Order order = new Order("Table 1", "Burger\nCoke", LocalDateTime.now().toString());
 *     OrderManager.saveOrder(order);
 *     OrderManager.printKitchenOrders();
 *     OrderManager.clearOrders();
 * </pre>
 *
 * @see Order
 */
public class OrderManager {
    private static final String FILE_PATH = "orders.json";
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveOrder(Order order) throws IOException {
        List<Order> orders = loadOrders(); 

        if (orders == null) orders = new ArrayList<>(); // add this null check

        orders.add(order);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(orders, writer);
        }
    }

    /**
     * Saves a new {@link Order} to the JSON file.
     * <p>
     * Loads any existing orders from the file, appends the new order,
     * and writes the updated list back to the file. If the file does not
     * exist or is empty, a new list is created.
     * </p>
     *
     * @param order the {@link Order} object to be saved; must not be {@code null}
     * @throws IOException if an error occurs while writing to the file
     */
    public static List<Order> loadOrders() {
        File file = new File(FILE_PATH);
        
        if (!file.exists()) return new ArrayList<>();
        
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Order>>(){}.getType();
            List<Order> orders = gson.fromJson(reader, listType);
            
            return orders != null ? orders : new ArrayList<>();
        } catch (IOException | JsonSyntaxException e) {
            System.out.println("Warning: orders.json corrupted, starting fresh.");
            return new ArrayList<>();
        }
    }


    /**
     * Loads all {@link Order} objects from the JSON file.
     * <p>
     * If the file does not exist or is empty, an empty list is returned.
     * If the file is corrupted or cannot be parsed, a warning is printed
     * to the console and an empty list is returned.
     * </p>
     *
     * @return a {@link List} of {@link Order} objects loaded from the file;
     *         never {@code null}, returns an empty list if no orders exist
     */
    public static Map<String, List<Order>> getOrdersByTable() {
        List<Order> orders = loadOrders();
        Map<String, List<Order>> groupedOrders = new HashMap<>();

        for(Order order : orders){
            String table = order.getTableName();
        

            if(!groupedOrders.containsKey(table)){
                groupedOrders.put(table, new ArrayList<>());
            }

            groupedOrders.get(table).add(order);
        }

        return groupedOrders;
    }
    /**
     * Groups all loaded orders by their associated table name.
     * <p>
     * Iterates through all orders and organizes them into a {@link Map}
     * where each key is a table name and each value is a list of orders
     * placed at that table.
     * </p>
     *
     * @return a {@link Map} where the key is the table name ({@link String})
     *         and the value is a {@link List} of {@link Order} objects for that table;
     *         returns an empty map if no orders exist
     */
    public static void printKitchenOrders() {
        Map<String, List<Order>> groupedOrders = getOrdersByTable();
    
        for (Map.Entry<String, List<Order>> entry : groupedOrders.entrySet()) {
            String table = entry.getKey();
            List<Order> orders = entry.getValue();
    
            System.out.println("--------------------------------");
            System.out.println("Table: " + table);
            System.out.println("Items:");
    
            for (Order order : orders) {
                System.out.println(order.getItems());
            }
    
            System.out.println("--------------------------------");
        }
    }
    /**
     * Prints a formatted summary of all orders grouped by table to the console.
     * <p>
     * Intended for kitchen staff use. Each table's orders are printed in a
     * clearly separated block showing the table name and all ordered items.
     * </p>
     *
     * <p>Example output:</p>
     * <pre>
     *     --------------------------------
     *     Table: Table 1
     *     Items:
     *     Burger
     *     Coke
     *     --------------------------------
     * </pre>
     */
    public static void clearOrders() throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write("[]"); 
        }
    }
    
}
