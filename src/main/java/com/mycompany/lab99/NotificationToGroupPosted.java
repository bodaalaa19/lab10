/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab99;

import static com.mycompany.lab99.Group.getGroupNameFromId;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class NotificationToGroupPosted extends Notification {

    private static final String NOTIFICATIONS_FILE = "NotificationToGroupPosted.json";
    private String groupId;
    private String contentId;
    private ArrayList<String> members;

    public NotificationToGroupPosted(String groupId, String userId, String message) {
        super(userId, message); // Call parent constructor to set user ID and message
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public static void saveNotifications(ArrayList<JSONObject> notifications) {
        JSONArray notificationsArray = new JSONArray(notifications);
        try (FileWriter file = new FileWriter(NOTIFICATIONS_FILE)) {
            file.write(notificationsArray.toString(4)); // Write with indentation
            file.flush();
        } catch (IOException e) {
            System.err.println("Error saving notifications: " + e.getMessage());
        }
    }
    // Load group notifications from the file

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
//notify

    public static void createGroupPostNotification(String groupId, String posterId, String contentId, ArrayList<String> membersid) {
        ArrayList<JSONObject> notifications = loadNotifications();
        for (String id : membersid) {
            if (!membersid.equals(posterId)) {
                JSONObject notification = new JSONObject();
                notification.put("groupId", groupId);
                notification.put("poster", posterId);
                notification.put("message", posterId+ " posted "+contentId+" in "+getGroupNameFromId(groupId));
                notification.put("adminId", id);
                notifications.add(notification);
            }
        }
        saveNotifications(notifications);

    }
    // Function to return notifications for a specific memberId

    public static ArrayList<NotificationToGroupPosted> getNotificationsForMember(String memberId) {
        ArrayList<NotificationToGroupPosted> memberNotifications = new ArrayList<>();
        ArrayList<JSONObject> notifications = loadNotifications();

        for (JSONObject notification : notifications) {
            String adminId = notification.optString("adminId", null);
            if (adminId != null && adminId.equals(memberId)) {
                // Create a new NotificationToGroupPosted object and add it to the list
                String groupId = notification.getString("groupId");
                String posterId = notification.getString("poster");
                String message = notification.getString("message");

                if (!memberId.equals(posterId)) {
                    NotificationToGroupPosted notificationToGroupPosted = new NotificationToGroupPosted(groupId, posterId, message);
                    memberNotifications.add(notificationToGroupPosted);
                }
            }
        }

        return memberNotifications;
    }
    
    
    
}
