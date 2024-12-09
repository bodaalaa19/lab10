package com.mycompany.lab99;

import java.util.ArrayList;

public class FriendContent {
    
    public static ArrayList<Post> getFriendPosts(String userId){
        ArrayList<User> friends=Friends.viewFriends(userId);
        ArrayList<Post> friendPosts=new ArrayList<>();
        for(int i=0;i<friends.size();i++){
            User friend = friends.get(i);
            String friendId=friend.getUserId();
            ArrayList<Post> posts=Post.loadPostsForUser(friendId);
            friendPosts.addAll(posts);
        }
        return friendPosts;
    }
    
    
    public static ArrayList<Story> getFriendStories(String userId){
        ArrayList<User> friends=Friends.viewFriends(userId);
        ArrayList<Story> friendStories=new ArrayList<>();
        for(int i=0;i<friends.size();i++){
            User friend=friends.get(i);
            String friendId=friend.getUserId();
            ArrayList<Story> stories=Story.loadStoriesForUser(friendId);
            friendStories.addAll(stories);
        }
        return friendStories;
    }
}
