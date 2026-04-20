package com.group4.sprint2.Models;

import java.time.LocalDateTime;

/**
 * Represents a customer order within the system.
 * This model captures the table location, the ordered items,
 * and automatically generates a timestamp upon creation.
 */
public class Order {
    // The identifier for the table where the order was placed
    private String tableName;

    // A string representation of the items included in the order
    private String items;

    // The formatted date and time when the order object was instantiated
    private String timestamp;

    /**
     * Constructs a new Order.
     * * @param tableName The name or number of the table (e.g., "Table 5")
     * @param items A description or list of items being ordered
     */
    public Order(String tableName, String items) {
        this.tableName = tableName;
        this.items = items;
        this.timestamp = LocalDateTime.now().toString();
    }

    /**
     * @return The name of the table associated with this order
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @return The string containing the ordered items
     */
    public String getItems() {
        return items;
    }

    /**
     * @return The timestamp of when the order was created
     */
    public String getTimestamp() {
        return timestamp;
    }
}