/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab99;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class NotifcationToGroupPosted extends Notification {
    private String groupId; // Field for the group ID
    private Content content; // Field for the content of the post
    private static final String NOTIFICATIONS_FILE = "NotifcationToGroupPosted.json"; // File for storing notifications

    // Constructor
    public NotifcationToGroupPosted(String groupId, String userId, Content content, String message) {
        super(userId, message); // Call parent constructor to set user ID and message
        this.groupId = groupId; // Initialize group ID
        this.content = content; // Initialize content
    }

    // Getter for groupId
    public String getGroupId() {
        return groupId;
    }

    // Getter for content
    public Content getContent() {
        return content;
    }

    // Save notifications to the file
    public static void saveNotifications(ArrayList<JSONObject> notifications) {
        JSONArray notificationsArray = new JSONArray(notifications);
        try (FileWriter file = new FileWriter(NOTIFICATIONS_FILE)) {
            file.write(notificationsArray.toString(4)); // Write with indentation
            file.flush();
        } catch (IOException e) {
            System.err.println("Error saving notifications: " + e.getMessage());
        }
    }

    // Load notifications from the file
    public static ArrayList<JSONObject> loadNotifications() {
        ArrayList<JSONObject> notifications = new ArrayList<>();
        File file = new File(NOTIFICATIONS_FILE);

        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("[]");
                writer.flush();
            } catch (IOException e) {
                System.err.println("Error creating notifications file: " + e.getMessage());
            }
        }

        try (FileReader reader = new FileReader(file)) {
            Scanner scanner = new Scanner(reader);
            StringBuilder jsonContent = new StringBuilder();

            while (scanner.hasNextLine()) {
                jsonContent.append(scanner.nextLine());
            }

            JSONArray notificationsArray = new JSONArray(jsonContent.toString());
            for (int i = 0; i < notificationsArray.length(); i++) {
                notifications.add(notificationsArray.getJSONObject(i));
            }
        } catch (IOException e) {
            System.err.println("Error reading notifications file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing JSON or loading notifications: " + e.getMessage());
        }

        return notifications;
    }

    // Function to notify a group about a new post
    public static boolean notifyGroupPost(String groupId, String userId, Content content, String message) {
        ArrayList<JSONObject> notifications = loadNotifications();

        JSONObject notification = new JSONObject();
        notification.put("groupId", groupId);
        notification.put("userId", userId);
        notification.put("content", content.toString());
        notification.put("message", message);

        notifications.add(notification);
        saveNotifications(notifications);
        return true;
    }

    // Function to retrieve all notifications for a specific group
    public static ArrayList<NotifcationToGroupPosted> getGroupNotifications(String groupId) {
        ArrayList<JSONObject> allNotifications = loadNotifications();
        ArrayList<NotifcationToGroupPosted> groupNotifications = new ArrayList<>();

        for (JSONObject notification : allNotifications) {
            if (notification.getString("groupId").equals(groupId)) {
                String userId = notification.getString("userId");
                String message = notification.getString("message");
                Content content = new Content(notification.getString("content"));
                groupNotifications.add(new NotifcationToGroupPosted(groupId, userId, content, message)); // Create notification objects
            }
        }

        return groupNotifications; // Return list of NotifcationToGroupPosted objects
    }

    // Function to remove a specific notification
    public static boolean removeNotification(String groupId, String userId) {
        ArrayList<JSONObject> notifications = loadNotifications();
        boolean notificationFound = false;

        for (int i = 0; i < notifications.size(); i++) {
            JSONObject notification = notifications.get(i);

            if (notification.has("groupId") && notification.has("userId") &&
                notification.getString("groupId").equals(groupId) &&
                notification.getString("userId").equals(userId)) {
                    
                notifications.remove(i); // Remove the notification
                notificationFound = true;
                break;
            }
        }

        if (notificationFound) {
            saveNotifications(notifications); // Save updated list
        }

        return notificationFound;
    }
}

