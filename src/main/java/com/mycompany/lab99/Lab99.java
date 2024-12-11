/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lab99;

import static com.mycompany.lab99.FriendRequest.deleteRequest;
import static com.mycompany.lab99.FriendRequest.sendRequest;
import static com.mycompany.lab99.Friends.acceptRequest;
import static com.mycompany.lab99.Friends.declineRequest;
import static com.mycompany.lab99.Friends.removeFriendship;
import static com.mycompany.lab99.Friends.viewRequestSenders;
import static com.mycompany.lab99.Post.loadPosts;
import static com.mycompany.lab99.Post.savePosts;
import static com.mycompany.lab99.Story.deleteStories;
import static com.mycompany.lab99.Story.loadStories;
import static com.mycompany.lab99.Story.saveStories;
import static com.mycompany.lab99.User.loadUsers;
import static com.mycompany.lab99.User.saveUsers;
import static com.mycompany.lab99.User.signUp;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author Victus
 */
public class Lab99 {

    public static void main(String[] args) throws ParseException, NoSuchAlgorithmException {
        /*ArrayList<String> group1Admins=new ArrayList<>();
        group1Admins.add("admin1");
        group1Admins.add("admin2");
        
        ArrayList<String> group1Users=new ArrayList<>();
        group1Users.add("user1");
        group1Users.add("user2");
        group1Users.add("user3");
        
        ArrayList<Post> groupPosts=Post.loadPostsForUser("best");
        
        Group group1=new Group("group1","testpath1");
        group1.setAdminIds(group1Admins);
        group1.setUserIds(group1Users);
        group1.setPosts(groupPosts);
        
        ArrayList<Group> groups=new ArrayList<>();
        groups.add(group1);
        
        Group.saveGroups(groups);*/
        
        
        
        /*ArrayList<Group> loadedGroups=Group.loadGroups();
            for(int i=0;i<loadedGroups.size();i++){
                System.out.println(loadedGroups.get(i).getGroupName());
                System.out.println(loadedGroups.get(i).getGroupId());
                System.out.println(loadedGroups.get(i).getPhotoPath());
    
                for(int j=0;j<loadedGroups.get(i).getAdminIds().size();j++){
                    System.out.println(loadedGroups.get(i).getAdminIds().get(j));
                }
    
                for(int j=0;j<loadedGroups.get(i).getUserIds().size();j++){
                    System.out.println(loadedGroups.get(i).getUserIds().get(j));
                }
    
                for(int j=0;j<loadedGroups.get(i).getPosts().size();j++){
                    System.out.println(loadedGroups.get(i).getPosts().get(j).getContentId());
                    System.out.println(loadedGroups.get(i).getPosts().get(j).getUserId());
                    System.out.println(loadedGroups.get(i).getPosts().get(j).getContent());
                    System.out.println(loadedGroups.get(i).getPosts().get(j).getImageSource());
                    System.out.println(loadedGroups.get(i).getPosts().get(j).getTimeStamp());
                }
            }*/
            
            //Group.addToGroup("group747358394", "best");
            //Group.removeUserFromGroup("group747358394", "j");
            
            //Group.removePostFromGroup("group747358394", "bestt", "post191806060");
            
            //Group.addPostToGroup("group747358394", "best", "testtesttest", LocalDateTime.now(), "image.jpg");
        }

}
