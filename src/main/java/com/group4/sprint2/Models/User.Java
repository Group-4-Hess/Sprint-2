package com.group4.sprint2.Models;
/**
 * Represents a user within the system, containing credentials and access levels.
 */
public class User {
    //The unique identifier for the user's account
    private String username;

    //The user's secret authentication string
    private String password;

    //The access level or job function assigned to the user (e.g., "Management", "Wait Staff", "Host Staff")
    private String role;

    /**
     * Constructs a new User instance.
     * @param username the unique name used to identify the user
     * @param password the password used for authentication
     * @param role     the permission level assigned to the user
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * @return the current username of the user
     */
    public String getUsername() { return username; }

    /**
     * @return the user's password string
     */
    public String getPassword() { return password; }

    /**
     * @return the role/permission level assigned to this user
     */
    public String getRole() { return role; }
}
