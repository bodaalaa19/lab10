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
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class FriendRequest {

    // Load all requests from the JSON file
    public static ArrayList<JSONObject> loadRequests() {
        ArrayList<JSONObject> requests = new ArrayList<>();
        File file = new File("FriendRequest.json");

        if (!file.exists()) {
            
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("[]");
                writer.flush();
            } catch (IOException e) {
                System.err.println("Error creating the file: " + e.getMessage());
            }
        }

        // Read the file
        try (FileReader reader = new FileReader(file)) {
            Scanner scanner = new Scanner(reader);
            StringBuilder jsonContent = new StringBuilder();

            while (scanner.hasNextLine()) {
                jsonContent.append(scanner.nextLine());
            }

            JSONArray requestArray = new JSONArray(jsonContent.toString());
            for (int i = 0; i < requestArray.length(); i++) {
                requests.add(requestArray.getJSONObject(i));
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing JSON or loading requests: " + e.getMessage());
        }

        return requests;
    }

    // Save all requests to the JSON file
    public static void saveRequests(ArrayList<JSONObject> requests) {
        JSONArray requestArray = new JSONArray(requests);

        try (FileWriter file = new FileWriter("FriendRequest.json")) {
            file.write(requestArray.toString(4)); 
            file.flush();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Send a new friend request using senderId and receiverId
public static boolean sendRequest(String senderId, String receiverId) {
    ArrayList<User> users = User.loadUsers();
    ArrayList<JSONObject> requests = loadRequests();
    boolean senderExists = false;
    boolean receiverExists = false;
    for (User user : users) {
        if (user.getUserId().equals(senderId)) {
            senderExists = true;
        }
        if (user.getUserId().equals(receiverId)) {
            receiverExists = true;
        }
        
        if (senderExists && receiverExists) {
            break;
        }
    }
    if (!senderExists || !receiverExists || senderId.equals(receiverId)) {
        return false;
    }
    if(BlockedFriends.isBlocked(receiverId,senderId)){
        JOptionPane.showMessageDialog(null, "Cannot add user");
        return false;
    }
    for (JSONObject request : requests) {
        if (request.getString("sender").equals(senderId) && request.getString("receiver").equals(receiverId)) {
            return false;
        }
    }

    // Create a new friend request
    JSONObject newRequest = new JSONObject();
    newRequest.put("sender", senderId);
    newRequest.put("receiver", receiverId);

    // Add the new request and save to the file
    requests.add(newRequest);
    saveRequests(requests);
           JOptionPane.showMessageDialog(null, "request sent", "request", JOptionPane.INFORMATION_MESSAGE);

    return true;
}
//function to delete requests
public static boolean deleteRequest(String senderId, String receiverId) {
    ArrayList<JSONObject> requests = loadRequests(); 
    boolean requestFound = false;

    
    for (int i = 0; i < requests.size(); i++) {
        JSONObject request = requests.get(i);
        if (request.getString("sender").equals(senderId) && request.getString("receiver").equals(receiverId)) {
            requests.remove(i); 

            requestFound = true;
            break; 
        }
    }

    // Save the updated list of requests to the file
    if (requestFound) {
        saveRequests(requests);
    }

    return requestFound; 
}



}





