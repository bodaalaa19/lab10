package com.mycompany.lab99;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class Post extends Content{
    private ArrayList<Post> posts;
    
    public Post() {
        posts=new ArrayList<>();
    }

    public Post(String userId, String content, LocalDateTime timeStamp, String imageSource) {
        super(userId, content, timeStamp, imageSource);
        posts=new ArrayList<>();
        Random random=new Random(); //generates random ids for posts
        setContentId("post"+random.nextInt(Integer.MAX_VALUE));
    }
    
    
    public static void savePosts(ArrayList<Post> posts){
        JSONArray postArray=new JSONArray();
        for(Post post : posts){
           JSONObject j=new JSONObject();
            j.put("postId",post.getContentId());
            j.put("userId",post.getUserId());
            j.put("content",post.getContent());
            j.put("timestamp",post.getTimeStamp().format(getFormatter()));
            j.put("imagesrc",post.getImageSource());
            postArray.put(j);
        }
           try (FileWriter file = new FileWriter("posts.json")) {
            file.write(postArray.toString(4)); //  print with an indentation of 4 spaces
            file.close();
            System.out.println("Posts successfully saved to posts.json!");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public static ArrayList<Post> loadPosts() {
        ArrayList<Post> postList = new ArrayList<>();
        try (FileReader reader = new FileReader("posts.json")) {
            // Read the content of the file
            Scanner scanner = new Scanner(reader);
            StringBuilder jsonContent = new StringBuilder();

            while (scanner.hasNextLine()) {
                jsonContent.append(scanner.nextLine());
            }

            JSONArray postArray = new JSONArray(jsonContent.toString());

            // Iterate through the JSONArray and create Post objects
            for (int i = 0; i < postArray.length(); i++) {
                JSONObject userJson = postArray.getJSONObject(i);
                String postId = userJson.getString("postId");
                String userId = userJson.getString("userId");
                String content = userJson.getString("content");
                LocalDateTime timestamp = LocalDateTime.parse(userJson.getString("timestamp"),getFormatter());
                String imagesrc = userJson.getString("imagesrc");

                // Create a Post object
                Post post = new Post(userId, content, timestamp, imagesrc);
                post.setContentId(postId);
            
                postList.add(post);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing the JSON or creating User objects: " + e.getMessage());
        }

        return postList;
    }
    
    public static ArrayList<Post> loadPostsForUser(String userId){
        ArrayList<Post> allPosts=loadPosts(); //arraylist containing all posts
        ArrayList<Post> userPosts=new ArrayList<>(); //empty arraylist for user posts
        for(int i=0;i<allPosts.size();i++){
             //adds post in arraylist if userid matchs userid stored in post
            if(allPosts.get(i).getUserId().equals(userId)){
                userPosts.add(allPosts.get(i));
            }
        }
        return userPosts;
    }
    
    
    
}
