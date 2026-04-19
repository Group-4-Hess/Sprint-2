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
import com.group4.sprint2.Order;


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
}
