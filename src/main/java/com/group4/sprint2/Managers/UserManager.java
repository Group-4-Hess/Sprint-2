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

/**
 * Manages the persistence and retrieval of {@link User} objects using a JSON file.
 * <p>
 * This class provides static utility methods for saving, loading, finding,
 * and clearing users. All user data is stored in a local JSON file defined
 * by {@code FILE_PATH}. Serialization and deserialization is handled using
 * the Gson library.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     // Save a new user
 *     User user = new User("john", "password123", "waiter");
 *     UserManager.saveUser(user);
 *
 *     // Find a user by username
 *     User found = UserManager.findUser("john");
 *
 *     // Clear all users
 *     UserManager.clearUsers();
 * </pre>
 *
 * @see User
 */
public class UserManager {

    /**
    * The file path where user data is stored in JSON format.
    */
    private static final String FILE_PATH = "users.json";

    /**
     * Gson instance configured with pretty printing for human-readable JSON output.
     */
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    /**
     * Saves a new {@link User} to the JSON file.
     * <p>
     * Loads any existing users from the file, appends the new user,
     * and writes the updated list back to the file. If the file does not
     * exist or is empty, a new list is created.
     * </p>
     *
     * @param user the {@link User} object to be saved; must not be {@code null}
     * @throws IOException if an error occurs while writing to the file
     */
    public static void saveUser(User user) throws IOException {
        List<User> users = loadUsers();

        if (users == null) users = new ArrayList<>();

        users.add(user);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(users, writer);
        }
    }

    /**
     * Loads all {@link User} objects from the JSON file.
     * <p>
     * If the file does not exist or is empty, an empty list is returned.
     * If the file is corrupted or cannot be parsed, a warning is printed
     * to the console and an empty list is returned.
     * </p>
     *
     * @return a {@link List} of {@link User} objects loaded from the file;
     *         never {@code null}, returns an empty list if no users exist
     */
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

    /**
     * Searches for a {@link User} by their username.
     * <p>
     * Performs a case-sensitive search through all loaded users.
     * Returns the first matching user found, or {@code null} if no
     * user with the given username exists.
     * </p>
     *
     * @param username the username to search for; must not be {@code null}
     * @return the matching {@link User} object if found, or {@code null}
     *         if no user with the given username exists
     */
    public static User findUser(String username) {
        for (User user : loadUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // not found
    }


    /**
     * Clears all users by overwriting the JSON file with an empty array.
     * <p>
     * This operation is irreversible. All previously saved users will be
     * permanently deleted from the file.
     * </p>
     *
     * @throws IOException if an error occurs while writing to the file
     */
    public static void clearUsers() throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write("[]");
        }
    }
}