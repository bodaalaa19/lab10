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

public class RequestNotifications extends Notification{
 private String senderId; // Add a field for sender ID
    private static final String NOTIFICATIONS_FILE = "requestsNotifications.json";

      public RequestNotifications(String senderId, String userId, String message) {
        super(userId, message); // Call parent constructor to set receiver ID and message
        this.senderId = senderId; // Initialize sender ID
    }

    // Getter for senderId
    public String getSenderId() {
        return senderId;
    }

    public static void saveRequests(ArrayList<JSONObject> requests) {
        JSONArray requestsArray = new JSONArray(requests);
        try (FileWriter file = new FileWriter(NOTIFICATIONS_FILE)) {
            file.write(requestsArray.toString(4));
            file.flush();
        } catch (IOException e) {
            System.err.println("Error saving requests: " + e.getMessage());
        }
    }
//load notifications
    public static ArrayList<JSONObject> loadRequests() {
        ArrayList<JSONObject> requests = new ArrayList<>();
        File file = new File(NOTIFICATIONS_FILE);

        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("[]");
                writer.flush();
            } catch (IOException e) {
                System.err.println("Error creating requests file: " + e.getMessage());
            }
        }
        try (FileReader reader = new FileReader(file)) {
            Scanner scanner = new Scanner(reader);
            StringBuilder jsonContent = new StringBuilder();

            while (scanner.hasNextLine()) {
                jsonContent.append(scanner.nextLine());
            }

            JSONArray requestsArray = new JSONArray(jsonContent.toString());
            for (int i = 0; i < requestsArray.length(); i++) {
                requests.add(requestsArray.getJSONObject(i));
            }
        } catch (IOException e) {
            System.err.println("Error reading requests file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing JSON or loading requests: " + e.getMessage());
        }

        return requests;
    }
//function takes ids of sender and reciever it will be used after sending request 
   public static boolean notifyRequest(String senderId, String receiverId) {
    ArrayList<JSONObject> requests = loadRequests();

    JSONObject request = new JSONObject();
    request.put("sender", senderId);
    request.put("receiver", receiverId);
    request.put("message", "You received a friend request from " + senderId); // Add the message

    requests.add(request);
    saveRequests(requests);
    return true;
}

//this function returns all request notifications as Notification objects
public static ArrayList<RequestNotifications> UserRequestsNotifications(String userId) {
    ArrayList<JSONObject> allRequests = loadRequests();
    ArrayList<RequestNotifications> userNotifications = new ArrayList<>();

    for (JSONObject request : allRequests) {
        if (request.getString("receiver").equals(userId)) {
            String senderId = request.getString("sender");
            String message = request.getString("message");
            userNotifications.add(new RequestNotifications(senderId, userId, message)); // Use new constructor
        }
    }

    return userNotifications; // Return list of RequestNotifications objects
}




   public static boolean removeRequestNotification(String senderId, String receiverId) {
    ArrayList<JSONObject> requests = loadRequests(); // Load current notifications
    boolean requestFound = false;

    for (int i = 0; i < requests.size(); i++) {
        JSONObject request = requests.get(i);

        // Check both sender and receiver for a match
        if (request.has("sender") && request.has("receiver") && 
            request.getString("sender").equals(senderId) && 
            request.getString("receiver").equals(receiverId)) {
                
            requests.remove(i); // Remove notification
            requestFound = true;
            break;
        }
    }

    // Save updated list if a notification was removed
    if (requestFound) {
        saveRequests(requests);
    }

    return requestFound;
}


   
}
