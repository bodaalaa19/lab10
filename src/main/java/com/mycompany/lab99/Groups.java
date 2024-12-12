/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.lab99;

import static com.mycompany.lab99.Group.getAllGroupsForUser;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Victus
 */
public class Groups extends javax.swing.JFrame {

    /**
     * Creates new form Groups
     */
    public Groups() {
        initComponents();
                        DefaultListModel<String> listModel = new DefaultListModel<>();
ArrayList<String> groups=getAllGroupsForUser(LoginScreen.activeUser.getUserId());
        for (String group : groups) {
            listModel.addElement(group);
        }
        MyGroupsList.setModel(listModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CreateGroupBtn = new javax.swing.JButton();
        back4Btn = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        GroupSearchList = new javax.swing.JList<>();
        GroupSearch = new javax.swing.JTextField();
        GroupSearchBtn = new javax.swing.JButton();
        LeaveGroupBtn = new javax.swing.JButton();
        ViewGroup = new javax.swing.JButton();
        JoinGroupBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        MyGroupsList = new javax.swing.JList<>();
        ViewMyGroup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        CreateGroupBtn.setBackground(new java.awt.Color(255, 0, 0));
        CreateGroupBtn.setText("Create Group");
        CreateGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateGroupBtnActionPerformed(evt);
            }
        });

        back4Btn.setText("back");
        back4Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back4BtnActionPerformed(evt);
            }
        });

        GroupSearchList.setBackground(new java.awt.Color(255, 51, 204));
        jScrollPane7.setViewportView(GroupSearchList);

        GroupSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GroupSearchActionPerformed(evt);
            }
        });

        GroupSearchBtn.setText("Search");
        GroupSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GroupSearchBtnActionPerformed(evt);
            }
        });

        LeaveGroupBtn.setBackground(new java.awt.Color(204, 204, 255));
        LeaveGroupBtn.setText("Leave Group");
        LeaveGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeaveGroupBtnActionPerformed(evt);
            }
        });

        ViewGroup.setBackground(new java.awt.Color(204, 204, 255));
        ViewGroup.setText("View Group");
        ViewGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewGroupActionPerformed(evt);
            }
        });

        JoinGroupBtn.setBackground(new java.awt.Color(204, 204, 255));
        JoinGroupBtn.setText("Join group");
        JoinGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JoinGroupBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Group search:");

        jLabel2.setText("My Groups:");

        MyGroupsList.setBackground(new java.awt.Color(255, 51, 204));
        jScrollPane8.setViewportView(MyGroupsList);

        ViewMyGroup.setBackground(new java.awt.Color(153, 153, 255));
        ViewMyGroup.setText("View Group");
        ViewMyGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewMyGroupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CreateGroupBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back4Btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(GroupSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(GroupSearchBtn)
                    .addComponent(LeaveGroupBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ViewGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JoinGroupBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ViewMyGroup))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(GroupSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(GroupSearchBtn)
                                    .addComponent(jLabel1)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(back4Btn)))
                        .addGap(5, 5, 5))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(JoinGroupBtn)
                                    .addGap(16, 16, 16)
                                    .addComponent(LeaveGroupBtn)
                                    .addGap(18, 18, 18)
                                    .addComponent(ViewGroup))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(CreateGroupBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(119, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ViewMyGroup)
                        .addGap(145, 145, 145))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CreateGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateGroupBtnActionPerformed
        // TODO add your handling code here:
        CreateGroup createGroup=new CreateGroup();
        createGroup.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_CreateGroupBtnActionPerformed

    private void back4BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back4BtnActionPerformed
        // TODO add your handling code here:
        NewsFeed newsFeed=new NewsFeed();
        newsFeed.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_back4BtnActionPerformed

    
    
    private void GroupSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GroupSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GroupSearchActionPerformed

    private void ViewGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewGroupActionPerformed
        // TODO add your handling code here:
Group l=null;
String s=GroupSearchList.getSelectedValue();
ArrayList<Group> groups=Group.loadGroups();
        for (Group group : groups) {
            if(s.equals(group.getGroupName())){
            l=group;
            break;
            }
        }
        ViewGroup viewGroup=new ViewGroup(l);
        viewGroup.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_ViewGroupActionPerformed

    private void GroupSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GroupSearchBtnActionPerformed
        // TODO add your handling code here:
        ArrayList<Group> g=Group.searchForGroup(GroupSearch.getText());
                DefaultListModel<String> listModel2 = new DefaultListModel<>();
        for (Group group : g) {
            listModel2.addElement(group.getGroupName());
        }
        GroupSearchList.setModel(listModel2);
    }//GEN-LAST:event_GroupSearchBtnActionPerformed

    private void LeaveGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeaveGroupBtnActionPerformed
        // TODO add your handling code here:
        Group l=null;
String s=GroupSearchList.getSelectedValue();
ArrayList<Group> groups=Group.loadGroups();
        for (Group group : groups) {
            if(s.equals(group.getGroupName())){
            l=group;
            break;
            }
        }
Group.leaveGroup(l.getGroupId(), LoginScreen.activeUser.getUserId());
    }//GEN-LAST:event_LeaveGroupBtnActionPerformed

    private void JoinGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JoinGroupBtnActionPerformed
        // TODO add your handling code here:
        Group l=null;
String s=GroupSearchList.getSelectedValue();
ArrayList<Group> groups=Group.loadGroups();
        for (Group group : groups) {
            if(s.equals(group.getGroupName())){
            l=group;
            break;
            }
        }
        Group.addToGroup(l.getGroupId(), LoginScreen.activeUser.getUserId());
    }//GEN-LAST:event_JoinGroupBtnActionPerformed

    private void ViewMyGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewMyGroupActionPerformed
        // TODO add your handling code here:
        Group l=null;
String s=MyGroupsList.getSelectedValue();
ArrayList<Group> groups=Group.loadGroups();
        for (Group group : groups) {
            if(s.equals(group.getGroupName())){
            l=group;
            break;
            }
        }
        NormalUserGroup normalUserGroup=new NormalUserGroup(l);
        normalUserGroup.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_ViewMyGroupActionPerformed

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
            java.util.logging.Logger.getLogger(Groups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Groups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Groups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Groups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Groups().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreateGroupBtn;
    private javax.swing.JTextField GroupSearch;
    private javax.swing.JButton GroupSearchBtn;
    private javax.swing.JList<String> GroupSearchList;
    private javax.swing.JButton JoinGroupBtn;
    private javax.swing.JButton LeaveGroupBtn;
    private javax.swing.JList<String> MyGroupsList;
    private javax.swing.JButton ViewGroup;
    private javax.swing.JButton ViewMyGroup;
    private javax.swing.JButton back4Btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    // End of variables declaration//GEN-END:variables
}
