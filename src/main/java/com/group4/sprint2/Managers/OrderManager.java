package com.group4.sprint2.Managers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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
 * <p>
 * Orders are maintained in FIFO (first-in, first-out) queue order, meaning
 * the earliest orders are processed first. This ensures kitchen staff
 * always see the oldest outstanding orders at the top.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     Order order = new Order("Table 1", "Burger\nCoke");
 *     OrderManager.saveOrder(order);
 *     OrderManager.printKitchenOrders();
 *     OrderManager.clearOrders();
 * </pre>
 *
 * @see Order
 */
public class OrderManager {

    /**
     * The file path where order data is stored in JSON format.
     */
    private static final String FILE_PATH = "orders.json";

    /**
     * Gson instance configured with pretty printing for human-readable JSON output.
     */
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Saves a new {@link Order} to the JSON file.
     * <p>
     * Loads any existing orders from the file, appends the new order
     * to the end of the queue, and writes the updated list back to the file.
     * If the file does not exist or is empty, a new queue is created.
     * </p>
     *
     * @param order the {@link Order} object to be saved; must not be {@code null}
     * @throws IOException if an error occurs while writing to the file
     */
    public static void saveOrder(Order order) throws IOException {
        Queue<Order> orders = loadOrderQueue();
        orders.add(order); // add to end of queue (FIFO)

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(new ArrayList<>(orders), writer);
        }
    }

    /**
     * Loads all {@link Order} objects from the JSON file as a FIFO queue.
     * <p>
     * Orders are returned in the order they were saved, with the oldest
     * order at the front of the queue. If the file does not exist or is
     * empty, an empty queue is returned. If the file is corrupted or cannot
     * be parsed, a warning is printed and an empty queue is returned.
     * </p>
     *
     * @return a {@link Queue} of {@link Order} objects in FIFO order;
     *         never {@code null}, returns an empty queue if no orders exist
     */
    public static Queue<Order> loadOrderQueue() {
        File file = new File(FILE_PATH);

        if (!file.exists() || file.length() == 0) return new LinkedList<>();

        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Order>>(){}.getType();
            List<Order> orders = gson.fromJson(reader, listType);
            return orders != null ? new LinkedList<>(orders) : new LinkedList<>();
        } catch (IOException | JsonSyntaxException e) {
            System.out.println("Warning: orders.json corrupted, starting fresh.");
            return new LinkedList<>();
        }
    }

    /**
     * Loads all {@link Order} objects from the JSON file as a list.
     * <p>
     * Convenience method that returns orders as a {@link List} while
     * preserving FIFO queue order. Oldest orders appear first.
     * </p>
     *
     * @return a {@link List} of {@link Order} objects in FIFO order;
     *         never {@code null}, returns an empty list if no orders exist
     */
    public static List<Order> loadOrders() {
        return new ArrayList<>(loadOrderQueue());
    }

    /**
     * Groups all loaded orders by their associated table name, preserving
     * FIFO order within each table's order list.
     * <p>
     * Iterates through all orders in queue order and organizes them into a
     * {@link Map} where each key is a table name and each value is a list
     * of orders placed at that table, oldest first.
     * </p>
     *
     * @return a {@link Map} where the key is the table name ({@link String})
     *         and the value is a {@link List} of {@link Order} objects for
     *         that table in FIFO order; returns an empty map if no orders exist
     */
    public static Map<String, List<Order>> getOrdersByTable() {
        Queue<Order> orders = loadOrderQueue();
        Map<String, List<Order>> groupedOrders = new LinkedHashMap<>(); // preserves insertion order

        for (Order order : orders) {
            String table = order.getTableName();

            if (!groupedOrders.containsKey(table)) {
                groupedOrders.put(table, new ArrayList<>());
            }

            groupedOrders.get(table).add(order);
        }

        return groupedOrders;
    }

    /**
     * Prints a formatted summary of all orders grouped by table to the console,
     * in FIFO order — the earliest orders are printed first.
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
     * Clears all orders by overwriting the JSON file with an empty array.
     * <p>
     * This operation is irreversible. All previously saved orders will be
     * permanently deleted from the file.
     * </p>
     *
     * @throws IOException if an error occurs while writing to the file
     */
    public static void clearOrders() throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write("[]");
        }
    }
}