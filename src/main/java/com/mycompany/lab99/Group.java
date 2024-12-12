package com.mycompany.lab99;

import static com.mycompany.lab99.Content.getFormatter;
import static com.mycompany.lab99.NotifyAddedToGroup.notifyGroupAddition;
import static com.mycompany.lab99.NotifyAddedToGroup.removeGroupNotification;
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
    private String groupCreator;
    private ArrayList<String> adminIds;
    private ArrayList<String> userIds;
    private ArrayList<Post> posts;

    //constructor to create new group
    public Group(String userId, String groupName, String photoPath) {
        this.groupCreator=userId;
        this.groupName = groupName;
        this.photoPath = photoPath;
        adminIds = new ArrayList<>();
        adminIds.add(this.groupCreator);
        userIds = new ArrayList<>();
        userIds.add(this.groupCreator);
        posts = new ArrayList<>();
        Random random=new Random(); //generates random ids for groups
        setGroupId("group"+random.nextInt(Integer.MAX_VALUE));
    }

    //constructor to load groups from json file
    public Group(String groupCreator,String groupName, String groupId, String photoPath) {
        this.groupCreator=groupCreator;
        this.groupName = groupName;
        this.groupId = groupId;
        this.photoPath = photoPath;
        adminIds = new ArrayList<>();
        adminIds.add(this.groupCreator);
        userIds = new ArrayList<>();
        userIds.add(this.groupCreator);
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

    public String getGroupCreator() {
        return groupCreator;
    }

    public void setGroupCreator(String groupCreator) {
        this.groupCreator = groupCreator;
    }
    
    
    public static void saveGroups(ArrayList<Group> list) {
        JSONArray groupArray = new JSONArray();
        for (Group group : list) {
            JSONObject j = new JSONObject();
            j.put("groupName", group.getGroupName());
            j.put("groupId", group.getGroupId());
            j.put("groupPhoto", group.getPhotoPath());
            j.put("groupCreator", group.getGroupCreator());
            
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
                String groupCreator=groupJson.getString("groupCreator");


                // Create a Group object
                Group group=new Group(groupCreator,groupName,groupId,groupPhoto);

                
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
        notifyGroupAddition( groupId, userId);
        JOptionPane.showMessageDialog(null, "User added to the group.");

        Group.saveGroups(groups);
    }
    
    public static void removeUserFromGroup(String groupId, String adminId, String userId){
        ArrayList<Group> groups=loadGroups();
        //ArrayList<User> users=User.loadUsers();
        
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
        
        boolean removeMemberAccess=false;
        if(wantedGroup.getAdminIds().contains(adminId)){
            removeMemberAccess=true;
        }
        
        
        if(removeMemberAccess){
            if(wantedGroup.getUserIds().contains(userId)){
                if(userId.equals(wantedGroup.getGroupCreator())){
                    JOptionPane.showMessageDialog(null, "Cannot remove Group Creator!");
                    return;
                }else{
                  //  removeGroupNotification( groupId,  userId);
                    wantedGroup.getUserIds().remove(userId);
                    JOptionPane.showMessageDialog(null, "User removed from the group!");
                    Group.saveGroups(groups);
                    return;
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Only admins can remove users!");
                    return;
        }
        
        JOptionPane.showMessageDialog(null, "User doesn't exist in the group!");
        
        
    }
    
    public static void addPostToGroup(String groupId, String userId, String content,LocalDateTime timeStamp, String imageSource){
        ArrayList<Group> groups=loadGroups();
        //ArrayList<User> users=User.loadUsers();
        
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
        
        if (!wantedGroup.getUserIds().contains(userId)) {
            JOptionPane.showMessageDialog(null, "User is not a member of the group.");
            return;
        }
        
        Post post=new Post(userId,content,timeStamp,imageSource);
        wantedGroup.getPosts().add(post);
        JOptionPane.showMessageDialog(null, "Post added!");
        Group.saveGroups(groups);
        
    }
    
    public static void removePostFromGroup(String groupId, String userId,String postId){
        ArrayList<Group> groups=loadGroups();
        //ArrayList<User> users=User.loadUsers();
        
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
        
        ArrayList<Post> groupPosts=wantedGroup.getPosts();
        Post post=null;
        for(int i=0;i<groupPosts.size();i++){
            if(groupPosts.get(i).getContentId().equals(postId)){
                post=groupPosts.get(i);
                break;
            }
        }
        
        boolean removePostAccess=false;
        if(post==null){
            JOptionPane.showMessageDialog(null, "Post doesnt exist");
            return;
        }else{
            if(wantedGroup.getAdminIds().contains(userId) || post.getUserId().equals(userId)){
                removePostAccess=true;
            }
        }
        
        if(removePostAccess){
            if(post!=null){
                groupPosts.remove(post);
                JOptionPane.showMessageDialog(null, "Post removed!");
            }
        }
        
        Group.saveGroups(groups);
    }
    
    public static void editGroupPost(String groupId,String userId,String postId, String content){
        ArrayList<Group> groups=loadGroups();
        //ArrayList<User> users=User.loadUsers();
        
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
        
        ArrayList<Post> groupPosts=wantedGroup.getPosts();
        Post post=null;
        for(int i=0;i<groupPosts.size();i++){
            if(groupPosts.get(i).getContentId().equals(postId)){
                post=groupPosts.get(i);
                break;
            }
        }
        
        boolean editPostAccess=false;
        if(post==null){
            JOptionPane.showMessageDialog(null, "Post doesnt exist");
            return;
        }else{
            if(wantedGroup.getAdminIds().contains(userId) || post.getUserId().equals(userId)){
                editPostAccess=true;
            }
        }
        
        if(editPostAccess){
            post.setContent(content);
            JOptionPane.showMessageDialog(null, "Post Edited");
        }
        Group.saveGroups(groups);
    }
    
    public static void promoteToAdmin(String groupId,String adminId,String userId){
        ArrayList<Group> groups=loadGroups();
        //ArrayList<User> users=User.loadUsers();
        
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
        
        if (!wantedGroup.getUserIds().contains(userId)) {
            JOptionPane.showMessageDialog(null, "User is not a member of the group.");
            return;
        }
        
        if (wantedGroup.getAdminIds().contains(adminId) && !(wantedGroup.getGroupCreator().equals(adminId))) {
            JOptionPane.showMessageDialog(null, "Only Primary Admin can promote!");
            return;
        }
        
        boolean promote=false;
        if(wantedGroup.getGroupCreator().equals(adminId)){
            promote=true;
        }
        
        if(promote){
            if(!wantedGroup.getAdminIds().contains(userId)){
                wantedGroup.getAdminIds().add(userId);
                JOptionPane.showMessageDialog(null, "User promoted to Admin!");
            }else{
                JOptionPane.showMessageDialog(null, "User is already an Admin!");
            }
        }
            
        Group.saveGroups(groups);
    }
    
    public static void demoteFromAdmin(String groupId, String adminId, String userId){
        ArrayList<Group> groups=loadGroups();
        //ArrayList<User> users=User.loadUsers();
        
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
        
        if (!wantedGroup.getUserIds().contains(userId)) {
            JOptionPane.showMessageDialog(null, "User is not a member of the group.");
            return;
        }
        
        if (!wantedGroup.getAdminIds().contains(userId)) {
            JOptionPane.showMessageDialog(null, "User is not an Admin!");
            return;
        }
        
        if (userId.equals(wantedGroup.getGroupCreator())) {
            JOptionPane.showMessageDialog(null, "Cannot demote the group creator!");
            return;
        }
        
        if (wantedGroup.getAdminIds().contains(adminId) && !(wantedGroup.getGroupCreator().equals(adminId))) {
            JOptionPane.showMessageDialog(null, "Only Primary Admin can demote!");
            return;
        }
        
        boolean demote=false;
        if(wantedGroup.getGroupCreator().equals(adminId)){
            demote=true;
        }
        
        if(demote){
            if(wantedGroup.getAdminIds().contains(userId)){
                wantedGroup.getAdminIds().remove(userId);
                JOptionPane.showMessageDialog(null, "User demoted from Admin!");
            }else{
                JOptionPane.showMessageDialog(null, "User is not an Admin!");
            }
        }
        Group.saveGroups(groups);
    }
    
    public static boolean isAdmin(String groupId, String adminId){
        ArrayList<Group> groups=loadGroups();
        //ArrayList<User> users=User.loadUsers();
        
        Group wantedGroup=null;
        for(int i=0;i<groups.size();i++){
            if(groupId.equals(groups.get(i).getGroupId())){
                wantedGroup=groups.get(i);
                break;
            }
        }
        
        boolean isAdmin=false;
        if(wantedGroup.getAdminIds().contains(adminId)){
            isAdmin=true;
        }
        return isAdmin;
    }
    
    public static boolean isCreator(String groupId, String adminId){
        ArrayList<Group> groups=loadGroups();
        //ArrayList<User> users=User.loadUsers();
        
        Group wantedGroup=null;
        for(int i=0;i<groups.size();i++){
            if(groupId.equals(groups.get(i).getGroupId())){
                wantedGroup=groups.get(i);
                break;
            }
        }
        
        boolean isCreator=false;
        if(wantedGroup.getGroupCreator().equals(adminId)){
            isCreator=true;
        }
        return isCreator;
    }
    
    public static ArrayList<Group> searchForGroup(String groupName){
        ArrayList<Group> groups=loadGroups();
        ArrayList<Group> result = new ArrayList<>();
        
        for(Group group : groups){
            if(group.getGroupName().contains(groupName)){
                result.add(group);
            }
        }
        return result;
    }
}
