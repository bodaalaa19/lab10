/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab99;

/**
 *
 * @author shams
 */
public class Profile {
    private String coverPhoto;
    private String profilePhoto;
    private String bio;

    public Profile() {
        this.coverPhoto = "hhh";
        this.profilePhoto = "ghvh";
        this.bio = "hfc";
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public String getBio() {
        return bio;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    
    
}
