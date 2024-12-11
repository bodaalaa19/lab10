package com.mycompany.lab99;

import static com.mycompany.lab99.Content.getFormatter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

public class Group {
    private String groupName;
    private String groupId;
    private String photoPath;
    private ArrayList<String> adminIds;
    private ArrayList<String> userIds;
    private ArrayList<Post> posts;

    public Group(String groupName, String photoPath) {
        this.groupName = groupName;
        this.photoPath = photoPath;
        adminIds = new ArrayList<>();
        userIds = new ArrayList<>();
        posts = new ArrayList<>();
        Random random=new Random(); //generates random ids for groups
        setGroupId("group"+random.nextInt(Integer.MAX_VALUE));
    }

    public Group(String groupName, String groupId, String photoPath) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.photoPath = photoPath;
        adminIds = new ArrayList<>();
        userIds = new ArrayList<>();
        posts = new ArrayList<>();
    }
    
    

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public ArrayList<String> getAdminIds() {
        return adminIds;
    }

    public void setAdminIds(ArrayList<String> adminIds) {
        this.adminIds = adminIds;
    }

    public ArrayList<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(ArrayList<String> userIds) {
        this.userIds = userIds;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
    
    
    public static void saveGroups(ArrayList<Group> list) {
        JSONArray groupArray = new JSONArray();
        for (Group group : list) {
            JSONObject j = new JSONObject();
            j.put("groupName", group.getGroupName());
            j.put("groupId", group.getGroupId());
            j.put("groupPhoto", group.getPhotoPath());
            
            JSONArray adminArray=new JSONArray();
            ArrayList<String> admins=group.getAdminIds();
            if(admins!=null){
                for(String adminId : admins){
                    JSONObject aId = new JSONObject();
                    aId.put("adminId",adminId);
                    adminArray.put(aId);
                }
            }
            j.put("admins",adminArray);
            
            
            JSONArray userArray=new JSONArray();
            ArrayList<String> users=group.getUserIds();
            if(users!=null){
                for(String userId : users){
                    JSONObject uId =new JSONObject();
                    uId.put("userId",userId);
                    userArray.put(uId);
                }
            }
            j.put("users",userArray);
            
            
            JSONArray postArray=new JSONArray();
            ArrayList<Post> posts=group.getPosts();
            if(posts!=null){
                for(Post post : posts){
                    JSONObject postJson = new JSONObject();
                    postJson.put("postId",post.getContentId());
                    postJson.put("userId",post.getUserId());
                    postJson.put("content",post.getContent());
                    postJson.put("imagesrc",post.getImageSource());
                    postJson.put("timestamp",post.getTimeStamp().format(getFormatter()));
                    postArray.put(postJson);
                }
            }
            j.put("posts",postArray);

            groupArray.put(j);
        }

        try (FileWriter file = new FileWriter("groups.json")) {
            file.write(groupArray.toString(4)); // Print with an indentation of 4 spaces
            file.flush();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public static ArrayList<Group> loadGroups() {
        ArrayList<Group> groupList = new ArrayList<>();
        try (FileReader reader = new FileReader("groups.json")) {
            // Read the content of the file
            Scanner scanner = new Scanner(reader);
            StringBuilder jsonContent = new StringBuilder();

            while (scanner.hasNextLine()) {
                jsonContent.append(scanner.nextLine());
            }

            JSONArray groupArray = new JSONArray(jsonContent.toString());

            // Iterate through the JSONArray and create Group objects
            for (int i = 0; i < groupArray.length(); i++) {
                JSONObject groupJson = groupArray.getJSONObject(i);
                String groupName = groupJson.getString("groupName");
                String groupId = groupJson.getString("groupId");
                String groupPhoto = groupJson.getString("groupPhoto");


                // Create a Group object
                Group group=new Group(groupName,groupId,groupPhoto);

                
                if (groupJson.has("admins")) {
                    JSONArray adminsArray = groupJson.getJSONArray("admins");
                    ArrayList<String> adminIds = new ArrayList<>();
                    for (int j = 0; j < adminsArray.length(); j++) {
                        JSONObject adminJson = adminsArray.getJSONObject(j);
                        adminIds.add(adminJson.getString("adminId"));
                    }
                    group.setAdminIds(adminIds);
                }
                
                if (groupJson.has("users")) {
                    JSONArray usersArray = groupJson.getJSONArray("users");
                    ArrayList<String> userIds = new ArrayList<>();
                    for (int j = 0; j < usersArray.length(); j++) {
                        JSONObject userJson = usersArray.getJSONObject(j);
                        userIds.add(userJson.getString("userId"));
                    }
                    group.setUserIds(userIds);
                }
                
                if (groupJson.has("posts")) {
                    JSONArray postsArray = groupJson.getJSONArray("posts");
                    ArrayList<Post> posts = new ArrayList<>();
                    for (int j = 0; j < postsArray.length(); j++) {
                        JSONObject postJson = postsArray.getJSONObject(j);
                        Post post = new Post();
                        post.setContentId(postJson.getString("postId"));
                        post.setUserId(postJson.getString("userId"));
                        post.setContent(postJson.getString("content"));
                        post.setImageSource(postJson.getString("imagesrc"));
                        post.setTimeStamp(LocalDateTime.parse(postJson.getString("timestamp"),getFormatter()));
                        posts.add(post);
                    }
                    group.setPosts(posts);
                }
                
                groupList.add(group);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing the JSON or creating Group objects: " + e.getMessage());
        }

        return groupList;
    }
    
    public static void addToGroup(String groupId,String userId){
        ArrayList<Group> groups=loadGroups();
        ArrayList<User> users=User.loadUsers();
        
        boolean userExists=false;
        for(int i=0;i<users.size();i++){
            if(userId.equals(users.get(i).getUserId())){
                userExists=true;
                break;
            }
        }
        
        if(!userExists){
            JOptionPane.showMessageDialog(null, "User doesnt exist");
            return;
        }
        
        Group wantedGroup=null;
        for(int i=0;i<groups.size();i++){
            if(groupId.equals(groups.get(i).getGroupId())){
                wantedGroup=groups.get(i);
                break;
            }
        }
        
        if(wantedGroup==null){
            JOptionPane.showMessageDialog(null, "Group doesnt exist");
            return;
        }
        
        if(wantedGroup.getUserIds().contains(userId)){
            JOptionPane.showMessageDialog(null, "User already in group");
            return;
        }
        
        wantedGroup.getUserIds().add(userId);
        JOptionPane.showMessageDialog(null, "User added to the group.");

        Group.saveGroups(groups);
    }
    
    
    
}
