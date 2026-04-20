package com.group4.sprint2.Models;

import java.time.LocalDateTime;

public class Order {
    private String tableName;
    private String items;
    private String timestamp;

    public Order(String tableName, String items) {
        this.tableName = tableName;
        this.items = items;
        this.timestamp = LocalDateTime.now().toString();
    }

    public String getTableName() {
        return tableName;
    }
    public String getItems() {
        return items;
    }
    public String getTimestamp() {
        return timestamp;
    }
}