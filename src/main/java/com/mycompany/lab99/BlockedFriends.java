package com.mycompany.lab99;

import static com.mycompany.lab99.Content.getFormatter;
import static com.mycompany.lab99.Friends.loadFriends;
import static com.mycompany.lab99.User.loadUsers;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class BlockedFriends {
    
    private static final String BLOCKEDFRIENDS_FILE = "Blocked.json";
    public static void saveBlockedFriends(ArrayList<JSONObject> blockedFriendsList) {
        JSONArray blockedFriendsArray = new JSONArray(blockedFriendsList);
        try (FileWriter file = new FileWriter(BLOCKEDFRIENDS_FILE)) {
            file.write(blockedFriendsArray.toString(4));
            file.flush();
        } catch (IOException e) {
            System.err.println("Error saving blocked friends: " + e.getMessage());
        }
    }
    public static ArrayList<JSONObject> loadBlockedFriends() {
        ArrayList<JSONObject> blockedFriendsList = new ArrayList<>();
        File file = new File(BLOCKEDFRIENDS_FILE);

        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("[]");
                writer.flush();
            } catch (IOException e) {
                System.err.println("Error creating blocked friends file: " + e.getMessage());
            }
        }
        try (FileReader reader = new FileReader(file)) {
            Scanner scanner = new Scanner(reader);
            StringBuilder jsonContent = new StringBuilder();

            while (scanner.hasNextLine()) {
                jsonContent.append(scanner.nextLine());
            }

            JSONArray blockedFriendsArray = new JSONArray(jsonContent.toString());
            for (int i = 0; i < blockedFriendsArray.length(); i++) {
                blockedFriendsList.add(blockedFriendsArray.getJSONObject(i));
            }
            
        } catch (IOException e) {
            System.err.println("Error reading blocked friends file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing JSON or loading blocked friends: " + e.getMessage());
        }

        return blockedFriendsList;
    }
    
    
    public static ArrayList<User> viewBlockedFriends(String userId) {
    ArrayList<User> blockedFriends = new ArrayList<>();
    ArrayList<JSONObject> blockedFriendsList = loadBlockedFriends(); 
    ArrayList<User> allUsers = loadUsers(); 

        for (JSONObject friendship : blockedFriendsList) {
            String user1 = friendship.getString("user");
            String blockedUser = friendship.getString("blockeduser");

            if (user1.equals(userId)) {
                for (User user : allUsers) {
                    if (user.getUserId().equals(blockedUser)) {
                        blockedFriends.add(user);
                        break;
                    }
                }}
        }
        return blockedFriends; 
    }
    
    public static boolean blockFriend(String userId,String blockedUserId){
        ArrayList<User> users =loadUsers();
        ArrayList<JSONObject> blockedList =loadBlockedFriends();
        boolean blockedFlag=false;
        
        for(int i=0;i<users.size();i++){
            if(blockedUserId.equals(users.get(i).getUserId())){
                JSONObject blocked=new JSONObject();
                blocked.put("user",userId);
                blocked.put("blockeduser", users.get(i).getUserId());
                blockedList.add(blocked);
                Friends.removeFriendship(userId, blockedUserId);
                blockedFlag=true;
                break;
            }
        }
        
        if(blockedFlag){
            saveBlockedFriends(blockedList);
        }
        return blockedFlag;
    }
    
    public static boolean unblockFriend(String userId,String blockedUserId){
        ArrayList<JSONObject> blockedList=loadBlockedFriends();
        boolean unblocked=false;
        
        for(int i=0;i<blockedList.size();i++){
            JSONObject blocked=blockedList.get(i);
            String user=blocked.getString("user");
            String blockedUser=blocked.getString("blockeduser");
            
            if(userId.equals(user) && blockedUserId.equals(blockedUser)){
                blockedList.remove(i);
                unblocked=true;
                break;
            }
        }
        
        if(unblocked){
            saveBlockedFriends(blockedList);
        }
        return unblocked;
    }
    
    public static boolean isBlocked(String userId,String blockedUserId){
        ArrayList<JSONObject> blockedList=loadBlockedFriends();
        
        for(int i=0;i<blockedList.size();i++){
            JSONObject blocked=blockedList.get(i);
            String user=blocked.getString("user");
            String blockedUser=blocked.getString("blockeduser");
            
            if(userId.equals(user) && blockedUserId.equals(blockedUser)){
                return true;
            }
        }
        return false;
    }
}
