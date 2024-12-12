/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.lab99;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author Victus
 */
public class CreateGroup extends javax.swing.JFrame {

    /**
     * Creates new form CreateGroup
     */
                        String s="C:\\Users\\Victus\\Documents\\GitHub\\lab10\\src\\main\\java\\com\\mycompany\\lab99\\defaultCover.jpg";

    public CreateGroup() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        GroupNameText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        AddGroupPhoto = new javax.swing.JButton();
        CreateGroup = new javax.swing.JButton();
        back3Btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Enter the group name:");

        GroupNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GroupNameTextActionPerformed(evt);
            }
        });

        jLabel2.setText("Group photo:");

        AddGroupPhoto.setText("Add photo");
        AddGroupPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddGroupPhotoActionPerformed(evt);
            }
        });

        CreateGroup.setText("CreateGroup");
        CreateGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateGroupActionPerformed(evt);
            }
        });

        back3Btn.setText("back");
        back3Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back3BtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(GroupNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(AddGroupPhoto))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(CreateGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(back3Btn)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back3Btn)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GroupNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(AddGroupPhoto))
                .addGap(43, 43, 43)
                .addComponent(CreateGroup)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GroupNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GroupNameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GroupNameTextActionPerformed

    private void AddGroupPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddGroupPhotoActionPerformed
        // TODO add your handling code here:
         JFileChooser x = new JFileChooser();
        x.showOpenDialog(this);
        File f = x.getSelectedFile();
        s=f.getAbsolutePath();
        System.out.println(s);
    }//GEN-LAST:event_AddGroupPhotoActionPerformed

    private void CreateGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateGroupActionPerformed
        // TODO add your handling code here:
        ArrayList<Group> groups=Group.loadGroups();
        Group g=new Group(LoginScreen.activeUser.getUserId(),GroupNameText.getText(),s);
        groups.add(g);
        Group.saveGroups(groups);
        this.setVisible(false);
        Groups gg=new Groups();
        gg.setVisible(true);
    }//GEN-LAST:event_CreateGroupActionPerformed

    private void back3BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back3BtnActionPerformed
        // TODO add your handling code here:
        Groups g= new Groups();
        g.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_back3BtnActionPerformed

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
            java.util.logging.Logger.getLogger(CreateGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateGroup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddGroupPhoto;
    private javax.swing.JButton CreateGroup;
    private javax.swing.JTextField GroupNameText;
    private javax.swing.JButton back3Btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
