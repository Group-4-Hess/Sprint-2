package com.group4.sprint2.Managers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.group4.sprint2.Models.User;

public class UserManager {
    private static final String FILE_PATH = "users.json";
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveUser(User user) throws IOException {
        List<User> users = loadUsers();

        if (users == null) users = new ArrayList<>();

        users.add(user);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(users, writer);
        }
    }

    public static List<User> loadUsers() {
        File file = new File(FILE_PATH);

        if (!file.exists() || file.length() == 0) return new ArrayList<>();

        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<User>>(){}.getType();
            List<User> users = gson.fromJson(reader, listType);
            return users != null ? users : new ArrayList<>();
        } catch (IOException | JsonSyntaxException e) {
            System.out.println("Warning: users.json corrupted, starting fresh.");
            return new ArrayList<>();
        }
    }

    public static User findUser(String username) {
        for (User user : loadUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // not found
    }

    public static void clearUsers() throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write("[]");
        }
    }
}