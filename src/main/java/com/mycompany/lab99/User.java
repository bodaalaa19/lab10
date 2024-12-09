/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab99;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author al-aqsa
 */
public class User {
    private String userId;
    private String email;
    private String username;
    private String hashedPassword;
    private Date dateOfBirth;
    private String status;
    private String pass;
    private Profile profile;

    // Date formatter
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Constructor
    public User(String userId, String email, String username, String password, String dateOfBirth) throws NoSuchAlgorithmException, ParseException {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.pass=password;
        this.hashedPassword = hashPassword(password); // Hash the password
        this.dateOfBirth = dateFormat.parse(dateOfBirth); // Parse the date
        this.status = "offline";
        this.profile=new Profile();
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }
     public String getpass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String password) throws NoSuchAlgorithmException {
        this.hashedPassword = hashPassword(password); // Hash the password
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) throws ParseException {
        this.dateOfBirth = dateFormat.parse(dateOfBirth);
    }

    public String getFormattedDateOfBirth() {
        return dateFormat.format(dateOfBirth);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setProfile(Profile p) {
        this.profile=p;
    }
      public Profile getProfile() {
        return this.profile;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", dateOfBirth='" + getFormattedDateOfBirth() + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    // Static method to hash passwords
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedBytes);
    }
     public void setHashedPasswordDirectly(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
public static Void saveUsers(ArrayList<User> list) {
    JSONArray userArray = new JSONArray();
    for (User user : list) {
        JSONObject j = new JSONObject();
        j.put("userId", user.getUserId());
        j.put("email", user.getEmail());
        j.put("userName", user.getUsername());
        j.put("hashedPassword", user.getHashedPassword());
        j.put("dateOfBirth", user.getFormattedDateOfBirth());
        j.put("Status", user.getStatus());

        // Save the profile data
        Profile profile = user.getProfile();
        if (profile != null) {
            JSONObject profileJson = new JSONObject();
            profileJson.put("coverPhoto", profile.getCoverPhoto());
            profileJson.put("profilePhoto", profile.getProfilePhoto());
            profileJson.put("bio", profile.getBio());
            j.put("profile", profileJson);
        }

        userArray.put(j);
    }

    try (FileWriter file = new FileWriter("users.json")) {
        file.write(userArray.toString(4)); // Print with an indentation of 4 spaces
        file.flush();
    } catch (IOException e) {
        System.err.println("Error writing to file: " + e.getMessage());
    }
    return null;
}

public static ArrayList<User> loadUsers() {
    ArrayList<User> userList = new ArrayList<>();
    try (FileReader reader = new FileReader("users.json")) {
        // Read the content of the file
        Scanner scanner = new Scanner(reader);
        StringBuilder jsonContent = new StringBuilder();

        while (scanner.hasNextLine()) {
            jsonContent.append(scanner.nextLine());
        }

        JSONArray userArray = new JSONArray(jsonContent.toString());

        // Iterate through the JSONArray and create User objects
        for (int i = 0; i < userArray.length(); i++) {
            JSONObject userJson = userArray.getJSONObject(i);
            String userId = userJson.getString("userId");
            String email = userJson.getString("email");
            String username = userJson.getString("userName");
            String hashedPassword = userJson.getString("hashedPassword");
            String dateOfBirth = userJson.getString("dateOfBirth");
            String status = userJson.getString("Status");

            // Create a User object
  User user = new User(userId, email, username, hashedPassword, dateOfBirth);
            user.setHashedPasswordDirectly(hashedPassword);
            user.setStatus(status);
            // Load profile data if available
            if (userJson.has("profile")) {
                JSONObject profileJson = userJson.getJSONObject("profile");
                Profile profile = new Profile();
                profile.setCoverPhoto(profileJson.optString("coverPhoto", null));
                profile.setProfilePhoto(profileJson.optString("profilePhoto", null));
                profile.setBio(profileJson.optString("bio", null));
                user.setProfile(profile);
                
            }
            
            userList.add(user);
        }
    } catch (IOException e) {
        System.err.println("Error reading the file: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Error parsing the JSON or creating User objects: " + e.getMessage());
    }

    return userList;
}

public static int signUp(User newUser) {
    ArrayList<User> users = loadUsers();
    int flag = 1;  // Default to 1 (indicating success, user can be added)

    // Check if the userId or username already exists
    for (User u : users) {
        if (newUser.getUserId().equals(u.getUserId()) || newUser.getUsername().equals(u.getUsername())) {
            flag = 0;
                      JOptionPane.showMessageDialog(null,"The id or username alreade exists","message",JOptionPane.ERROR_MESSAGE);
            break;  // Exit the loop immediately after finding a duplicate
        }
    }

    // If no duplicate was found, add the new user and save
    if (flag == 1) {
                              JOptionPane.showMessageDialog(null,"user created succsessfully","message",JOptionPane.NO_OPTION);

        for (User u : users) {
            u.setStatus("offline");
        }
        newUser.setStatus("offline");
        users.add(newUser);  // Add the new user
        saveUsers(users);     // Save the updated list of users

    }

    return flag;  // Return the flag indicating success (1) or failure (0)
}
public static Boolean login(String name, String pass) throws NoSuchAlgorithmException {
    int x=8;
    ArrayList<User> users = loadUsers(); // Load users from the file
    String hashedInputPassword = hashPassword(pass); // Hash the input password
    
    // Iterate through the users to find a matching username and hashed password
    for (User u : users) {
        if (u.getUsername().equalsIgnoreCase(name) && u.getHashedPassword().equals(hashedInputPassword)) {
                        // Set the logged-in user to online
            u.setStatus("online");

            // Save the updated user list
            saveUsers(users);

            return true; // Login successful
        }
    }
    return  false;
    //
    // Login failed
} }