/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.lab99;
import static com.mycompany.lab99.BlockedFriends.blockFriend;
import static com.mycompany.lab99.Friends.removeFriendship;
import static com.mycompany.lab99.Friends.viewFriends;
import com.mycompany.lab99.Post;
import com.mycompany.lab99.Story;
import com.mycompany.lab99.User;
import java.awt.Image;
import static java.awt.PageAttributes.MediaType.C;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author shams
 */
public class ProfilePage extends javax.swing.JFrame {
    changePassword changepassword;
    private User user;


    /**
     * Creates new form ProfilePage
     */
    private static final String DEFAULT_COVER_PICTURE_PATH = "C:\\Users\\Victus\\Documents\\GitHub\\lab9\\src\\main\\java\\com\\mycompany\\lab99\\defaultCover.jpg";
    
    private static final String DEFAULT_PROFILE_PICTURE_PATH
            = "C:\\Users\\Victus\\Documents\\GitHub\\lab9\\src\\main\\java\\com\\mycompany\\lab99\\defaultProfile.jpg";

    public ProfilePage(User user) {
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        initComponents();
        this.user = user;
        this.changepassword = null;
                    ArrayList<Post> posts=Post.loadPostsForUser(user.getUserId());
                    Story.deleteStories();
                    ArrayList<Story> stories=Story.loadStoriesForUser(user.getUserId());

        setProfilePicture();
        setCoverPhoto();
        setBio();
        
                DefaultListModel<String> listModel = new DefaultListModel<>();
                       for (Post p : posts) {
                                        listModel.addElement(p.getContent());

        }

                            myPostsList.setModel(listModel);
                            DefaultListModel<String> listModel2 = new DefaultListModel<>();
                       for (Story s : stories) {
                                        listModel2.addElement(s.getContent());

        }

                            myStoriesList.setModel(listModel2);
                                        DefaultListModel<String> listModel3 = new DefaultListModel<>();
    ArrayList<User> a=viewFriends(LoginScreen.activeUser.getUserId());
        for (User user1 : a) {
            listModel3.addElement(user1.getUserId()+" "+user1.getStatus());
        }
myFriendsList.setModel(listModel3);
    }
    
    private void setProfilePicture() {
        String path;

        //fetches stored profile picture
        if (this.user.getProfile().getProfilePhoto().equals("ghvh")) {
            path = DEFAULT_PROFILE_PICTURE_PATH;
        } else {
            path = this.user.getProfile().getProfilePhoto();
        }
        
        
        File file = new File(path);
        if (file.exists()) {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(92, 85, Image.SCALE_DEFAULT);
            this.profilePhoto.setIcon(new ImageIcon(scaledImg));
        } else {
            System.err.println("Profile picture not found at: " + path);
        }
    }
    
    private void setCoverPhoto() {
        String path;

        //fetches stored cover picture
        if (this.user.getProfile().getCoverPhoto().equals("hhh")) {
            path = DEFAULT_COVER_PICTURE_PATH;
        } else {
            path = this.user.getProfile().getCoverPhoto();
        }
        
        File file = new File(path);
        if (file.exists()) {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(302, 85, Image.SCALE_DEFAULT);
            this.coverPhoto.setIcon(new ImageIcon(scaledImg));
        } else {
            System.err.println("Profile picture not found at: " + path);
        }
    }
    
    private void setBio() {
        if (this.user.getProfile().getBio().equals("hfc")) {
            this.bio.setText("add bio");
        } else {
            this.bio.setText(this.user.getProfile().getBio());
        }
    }
    
    private ProfilePage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        profilePhoto = new javax.swing.JLabel();
        coverPhoto = new javax.swing.JLabel();
        bio = new javax.swing.JLabel();
        editProfileButton = new javax.swing.JButton();
        LogoutButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        myPostsList = new javax.swing.JList<>();
        viewMyPostBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        myStoriesList = new javax.swing.JList<>();
        viewMyStoryBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        AddMyPostBtn = new javax.swing.JButton();
        AddMyStory = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        myFriendsList = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        NewsfeedBtn = new javax.swing.JButton();
        RemoveFriendBtn = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        BlockFriendBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        profilePhoto.setBackground(new java.awt.Color(255, 255, 255));
        profilePhoto.setOpaque(true);
        profilePhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeProfilePhoto(evt);
            }
        });

        coverPhoto.setBackground(new java.awt.Color(255, 255, 255));
        coverPhoto.setOpaque(true);
        coverPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeCoverPhoto(evt);
            }
        });

        bio.setBackground(new java.awt.Color(255, 255, 255));
        bio.setOpaque(true);
        bio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeBio(evt);
            }
        });

        editProfileButton.setText("Change password");
        editProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editProfileButtonActionPerformed(evt);
            }
        });

        LogoutButton.setBackground(new java.awt.Color(255, 153, 153));
        LogoutButton.setText("Logout");
        LogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutButtonActionPerformed(evt);
            }
        });

        myPostsList.setBackground(new java.awt.Color(255, 51, 204));
        myPostsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(myPostsList);

        viewMyPostBtn.setBackground(new java.awt.Color(255, 102, 153));
        viewMyPostBtn.setText("view post");
        viewMyPostBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewMyPostBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Bio:");

        jLabel2.setText("posts:");

        myStoriesList.setBackground(new java.awt.Color(255, 51, 204));
        myStoriesList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(myStoriesList);

        viewMyStoryBtn.setBackground(new java.awt.Color(255, 102, 153));
        viewMyStoryBtn.setText("view Story");
        viewMyStoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewMyStoryBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("Stories:");

        AddMyPostBtn.setBackground(new java.awt.Color(255, 102, 153));
        AddMyPostBtn.setText("Add post");
        AddMyPostBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMyPostBtnActionPerformed(evt);
            }
        });

        AddMyStory.setBackground(new java.awt.Color(255, 102, 153));
        AddMyStory.setText("Add story");
        AddMyStory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMyStoryActionPerformed(evt);
            }
        });

        myFriendsList.setBackground(new java.awt.Color(255, 51, 204));
        myFriendsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(myFriendsList);

        jLabel4.setText("friends:");

        NewsfeedBtn.setBackground(new java.awt.Color(0, 51, 255));
        NewsfeedBtn.setText("Newsfeed");
        NewsfeedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewsfeedBtnActionPerformed(evt);
            }
        });

        RemoveFriendBtn.setBackground(new java.awt.Color(255, 102, 153));
        RemoveFriendBtn.setText("Remove Friend");
        RemoveFriendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveFriendBtnActionPerformed(evt);
            }
        });

        refresh.setText("refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        BlockFriendBtn.setBackground(new java.awt.Color(255, 102, 153));
        BlockFriendBtn.setText("Block Friend");
        BlockFriendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockFriendBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(profilePhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(coverPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bio, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NewsfeedBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 154, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(editProfileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LogoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refresh)
                        .addGap(39, 39, 39))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(RemoveFriendBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BlockFriendBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewMyStoryBtn)
                    .addComponent(AddMyStory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(viewMyPostBtn)
                            .addComponent(AddMyPostBtn)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(profilePhoto, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                            .addComponent(coverPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editProfileButton)
                            .addComponent(refresh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LogoutButton)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(bio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(NewsfeedBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(AddMyPostBtn)
                                .addGap(22, 22, 22)
                                .addComponent(viewMyPostBtn))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(AddMyStory)
                        .addGap(18, 18, 18)
                        .addComponent(viewMyStoryBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(BlockFriendBtn)
                                .addGap(19, 19, 19)
                                .addComponent(RemoveFriendBtn)
                                .addGap(17, 17, 17)))))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changeProfilePhoto(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeProfilePhoto
        JFileChooser x = new JFileChooser();
        x.showOpenDialog(this);
        File f = x.getSelectedFile();
        ImageIcon i = new ImageIcon(f.getAbsolutePath());
        Image img = i.getImage();
        Image scaledImg = img.getScaledInstance(92, 85, Image.SCALE_DEFAULT);
        
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        this.profilePhoto.setIcon(scaledIcon);
        
        ArrayList<User> users = User.loadUsers();
        
        for (User user : users) {
            if (user.getUsername().equals(this.user.getUsername())) {
                user.getProfile().setProfilePhoto(f.getAbsolutePath());
            }
        }
        
        User.saveUsers(users);
    }//GEN-LAST:event_changeProfilePhoto

    private void changeCoverPhoto(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeCoverPhoto
        JFileChooser x = new JFileChooser();
        x.showOpenDialog(this);
        File f = x.getSelectedFile();
        ImageIcon i = new ImageIcon(f.getAbsolutePath());
        Image img = i.getImage();
        
        Image scaledImg = img.getScaledInstance(303, 85, Image.SCALE_DEFAULT);
        
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        this.coverPhoto.setIcon(scaledIcon);
        
        ArrayList<User> users = User.loadUsers();
        
        for (User user : users) {
            if (user.getUsername().equals(this.user.getUsername())) {
                user.getProfile().setCoverPhoto(f.getAbsolutePath());
            }
        }
        
        User.saveUsers(users);
    }//GEN-LAST:event_changeCoverPhoto

    private void changeBio(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeBio
        String info;
        info = JOptionPane.showInputDialog(null, "Enter new bio:", "Input Dialog", JOptionPane.PLAIN_MESSAGE);
        
        if (info != null) {
            this.bio.setText(info);
            
            ArrayList<User> users = User.loadUsers();
            
            for (User user : users) {
                if (user.getUsername().equals(this.user.getUsername())) {
                    user.getProfile().setBio(info);
                }
            }
            
            User.saveUsers(users);
        } else {
            System.out.println("No input provided or dialog was canceled.");
        }
    }//GEN-LAST:event_changeBio

    private void editProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editProfileButtonActionPerformed
if(this.changepassword==null)
    this.changepassword=new changePassword(this.user);
this.changepassword.setVisible(true);

    }//GEN-LAST:event_editProfileButtonActionPerformed

    private void LogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutButtonActionPerformed
        // TODO add your handling code here:
                    ArrayList<User> users = User.loadUsers();

    for (User u : users) {
        if (u.getUsername().equals(user.getUsername())) {
            u.setStatus("offline");
            break;
        }
    }
        User.saveUsers(users);
        MainScreen m=MainScreen.getInstance();
        m.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_LogoutButtonActionPerformed

    private void viewMyPostBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewMyPostBtnActionPerformed
        // TODO add your handling code here:
            ArrayList<Post> posts2=Post.loadPostsForUser(user.getUserId());

        int index=myPostsList.getSelectedIndex();
        Post p=posts2.get(index);
        ViewPost viewPost=new ViewPost(p);
        viewPost.setVisible(true);
       
    }//GEN-LAST:event_viewMyPostBtnActionPerformed

    private void viewMyStoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewMyStoryBtnActionPerformed
        // TODO add your handling code here:
                    ArrayList<Story> stories2=Story.loadStoriesForUser(user.getUserId());
                    int index=myStoriesList.getSelectedIndex();
                    Story s=stories2.get(index);
                    ViewStory viewStory=new ViewStory(s);
                    viewStory.setVisible(true);
    }//GEN-LAST:event_viewMyStoryBtnActionPerformed

    private void AddMyPostBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMyPostBtnActionPerformed
        // TODO add your handling code here:
        AddPost addPost=new AddPost();
        addPost.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AddMyPostBtnActionPerformed

    private void AddMyStoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMyStoryActionPerformed
        // TODO add your handling code here:
        AddStory addStory=new AddStory();
        addStory.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AddMyStoryActionPerformed

    private void NewsfeedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewsfeedBtnActionPerformed
        // TODO add your handling code here:
        NewsFeed newsFeed=new NewsFeed();
        newsFeed.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_NewsfeedBtnActionPerformed

    private void RemoveFriendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveFriendBtnActionPerformed
        // TODO add your handling code here:
        String f=myFriendsList.getSelectedValue();
        String f1=f.split(" ")[0];
        removeFriendship(LoginScreen.activeUser.getUserId(),f1);
    }//GEN-LAST:event_RemoveFriendBtnActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
       ArrayList<Post> posts = Post.loadPostsForUser(user.getUserId());
    ArrayList<Story> stories = Story.loadStoriesForUser(user.getUserId());

    // Reload friends list
    ArrayList<User> friends = viewFriends(user.getUserId());

    // Update posts list
    DefaultListModel<String> postListModel = new DefaultListModel<>();
    for (Post p : posts) {
        postListModel.addElement(p.getContent());
    }
    myPostsList.setModel(postListModel);

    // Update stories list
    DefaultListModel<String> storyListModel = new DefaultListModel<>();
    for (Story s : stories) {
        storyListModel.addElement(s.getContent());
    }
    myStoriesList.setModel(storyListModel);

    // Update friends list
    DefaultListModel<String> friendsListModel = new DefaultListModel<>();
    for (User friend : friends) {
        friendsListModel.addElement(friend.getUserId() + " " + friend.getStatus());
    }
    myFriendsList.setModel(friendsListModel);

    // Update profile picture, cover photo, and bio
    setProfilePicture();
    setCoverPhoto();
    setBio();

    JOptionPane.showMessageDialog(this, "Page refreshed successfully!", "Refresh", JOptionPane.INFORMATION_MESSAGE);
  
    }//GEN-LAST:event_refreshActionPerformed

    private void BlockFriendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BlockFriendBtnActionPerformed
        // TODO add your handling code here:
        String f=myFriendsList.getSelectedValue();
        String f1=f.split(" ")[0];
        blockFriend(LoginScreen.activeUser.getUserId(),f1);
        JOptionPane.showMessageDialog(this, "blocked");
    }//GEN-LAST:event_BlockFriendBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProfilePage().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddMyPostBtn;
    private javax.swing.JButton AddMyStory;
    private javax.swing.JButton BlockFriendBtn;
    private javax.swing.JButton LogoutButton;
    private javax.swing.JButton NewsfeedBtn;
    private javax.swing.JButton RemoveFriendBtn;
    private javax.swing.JLabel bio;
    private javax.swing.JLabel coverPhoto;
    private javax.swing.JButton editProfileButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> myFriendsList;
    private javax.swing.JList<String> myPostsList;
    private javax.swing.JList<String> myStoriesList;
    private javax.swing.JLabel profilePhoto;
    private javax.swing.JButton refresh;
    private javax.swing.JButton viewMyPostBtn;
    private javax.swing.JButton viewMyStoryBtn;
    // End of variables declaration//GEN-END:variables

}
