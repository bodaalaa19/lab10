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
import static com.mycompany.lab99.Group.addToGroup;
import static com.mycompany.lab99.Group.removeUserFromGroup;
import static com.mycompany.lab99.NotifyAddedToGroup.getUserGroupNotifications;
import static com.mycompany.lab99.Post.loadPosts;
import static com.mycompany.lab99.Post.savePosts;
import static com.mycompany.lab99.RequestNotifications.UserRequestsNotifications;
import static com.mycompany.lab99.RequestNotifications.removeRequestNotification;
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
                System.out.println(loadedGroups.get(i).getGroupCreator());
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
            
            /*ArrayList<Group> groups=new ArrayList<>();
            Group group=new Group("j","group1","photo1");
            groups.add(group);
            Group.saveGroups(groups);*/
            
            //Group.addToGroup("group1645596773", "best");
            //Group.addToGroup("group1645596773", "oo");
            //Group.addToGroup("group1645596773", "k");
            //Group.addToGroup("group1645596773", "ahmed");
            //Group.removeUserFromGroup("group1645596773", "j", "oo");
            //Group.addPostToGroup("group1645596773", "best", "bestpost1ingroup", LocalDateTime.now(), "image1");
            //Group.addPostToGroup("group1645596773", "best", "bestPost2", LocalDateTime.now(), "img2");
            //Group.removePostFromGroup("group1645596773", "j", "post1291850372");
            
            //Group.editGroupPost("group1645596773", "j", "post1822775443", "bestEditedPost1byAdmin");
            
            //Group.promoteToAdmin("group1645596773", "j", "ahmed");
            //Group.demoteFromAdmin("group1645596773", "ahmed", "best");
            
            //System.out.println(Group.isCreator("group1645596773", "j"));
            /*ArrayList<Group> groups=Group.searchForGroup("group");
            System.out.println(groups.size()+"\n");
            for(int i=0;i<groups.size();i++){
                System.out.println(groups.get(i).getGroupName());
            }*/
            
            
        }
}


