package com.mycompany.lab99;

import static com.mycompany.lab99.Post.loadPosts;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class Story extends Content{
    private ArrayList<Story> stories;
    
    public Story() {
    }

    public Story(String userId, String content, LocalDateTime timeStamp, String imageSource) {
        super(userId, content, timeStamp, imageSource);
        stories=new ArrayList<>();
        Random random=new Random(); //generates random ids for stories
        setContentId("story"+random.nextInt(Integer.MAX_VALUE));
    }
    
    
    public static void saveStories(ArrayList<Story> stories){
        JSONArray storyArray=new JSONArray();
        for(Story story : stories){
           JSONObject j=new JSONObject();
            j.put("storyId",story.getContentId());
            j.put("userId",story.getUserId());
            j.put("content",story.getContent());
            j.put("timestamp",story.getTimeStamp().format(getFormatter()));
            j.put("imagesrc",story.getImageSource());
            storyArray.put(j);
        }
           try (FileWriter file = new FileWriter("stories.json")) {
            file.write(storyArray.toString(4)); //  print with an indentation of 4 spaces
            file.close();
            System.out.println("Stories successfully saved to stories.json!");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    
    public static ArrayList<Story> loadStories() {
        ArrayList<Story> storyList = new ArrayList<>();
        try (FileReader reader = new FileReader("stories.json")) {
            // Read the content of the file
            Scanner scanner = new Scanner(reader);
            StringBuilder jsonContent = new StringBuilder();

            while (scanner.hasNextLine()) {
                jsonContent.append(scanner.nextLine());
            }

            JSONArray storyArray = new JSONArray(jsonContent.toString());

            // Iterate through the JSONArray and create Post objects
            for (int i = 0; i < storyArray.length(); i++) {
                JSONObject userJson = storyArray.getJSONObject(i);
                String storyId = userJson.getString("storyId");
                String userId = userJson.getString("userId");
                String content = userJson.getString("content");
                LocalDateTime timestamp = LocalDateTime.parse(userJson.getString("timestamp"),getFormatter());
                String imagesrc = userJson.getString("imagesrc");

                // Create a Post object
                Story story = new Story(userId, content, timestamp, imagesrc);
                story.setContentId(storyId);
            
                storyList.add(story);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing the JSON or creating User objects: " + e.getMessage());
        }

        return storyList;
    }
    
    
    public static ArrayList<Story> loadStoriesForUser(String userId){
        deleteStories(); //deletes expired stories before showing user stories
        ArrayList<Story> allStories=loadStories(); //arraylist containing all stories
        ArrayList<Story> userStories=new ArrayList<>(); //empty arraylist for user stories
        for(int i=0;i<allStories.size();i++){
            //adds story in arraylist if userid matchs userid stored in story
            if(allStories.get(i).getUserId().equals(userId)){
                userStories.add(allStories.get(i));
            }
        }
        return userStories;
    }
    
    
    public static void deleteStories(){
        ArrayList<Story> stories=loadStories(); //arraylist containing all stories
        ArrayList<Story> updated=new ArrayList<>(); //empty arraylist 
        for(Story s: stories){
            //checks if story time exceeds 24 hours
            Duration duration=Duration.between(s.getTimeStamp(),LocalDateTime.now() );
            long hrs=duration.toHours();
            if(hrs<24){
                //adds story in new array if its time doesnt exceed 34 hours
                updated.add(s);
            }
        }
        saveStories(updated);
    }
    
    
}
