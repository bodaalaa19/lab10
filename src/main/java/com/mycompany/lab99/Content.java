package com.mycompany.lab99;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Content {
    private String contentId;
    private String userId;
    private String content;
    private LocalDateTime timeStamp;
    private String imageSource;
    private static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Content() {
        
    }

    public Content(String userId, String content, LocalDateTime timeStamp, String imageSource) {
        this.userId = userId;
        this.content = content;
        this.timeStamp = LocalDateTime.parse(timeStamp.format(formatter), formatter);;
        this.imageSource = imageSource;
    }
    
    

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = LocalDateTime.parse(timeStamp.format(formatter), formatter);
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public static DateTimeFormatter getFormatter() {
        return formatter;
    }

    @Override
    public String toString() {
        return "Content{" + "contentId=" + contentId + ", userId=" + userId + ", content=" + content + ", timeStamp=" + timeStamp + ", imageSource=" + imageSource + '}';
    }
    
    
}
