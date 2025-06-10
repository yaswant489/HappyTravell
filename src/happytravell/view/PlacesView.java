/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package happytravell.view;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Acer
 */
public class PlacesView extends javax.swing.JFrame {

    /**
     * Creates new form AdmindashboardView
     */
    public PlacesView() {
        initComponents();
        scaleImage1();
        scaleImage2();
        scaleImage3();
        scaleImage4();
        scaleImage5();
        scaleImage6();
        scaleImage7();
        scaleImage8();
        scaleImage9();
        scaleImage10();

    }
    
    public void scaleImage1(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/dashboard.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(dashboardIcon.getWidth(), dashboardIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        dashboardIcon.setIcon(scaledIcon);
    }
    public void scaleImage2(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/booking.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(bookingIcon.getWidth(), bookingIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        bookingIcon.setIcon(scaledIcon);
    }
    public void scaleImage3(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/directions.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(routeIcon.getWidth(), routeIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        routeIcon.setIcon(scaledIcon);
    }
    public void scaleImage4(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/tickets.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(busTicketIcon.getWidth(), busTicketIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        busTicketIcon.setIcon(scaledIcon);
    }
    public void scaleImage5(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/front-of-bus.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(vehiclesIcon.getWidth(), vehiclesIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        vehiclesIcon.setIcon(scaledIcon);
    }
    public void scaleImage6(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/user.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(profileIcon.getWidth(), profileIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        profileIcon.setIcon(scaledIcon);
    }
    public void scaleImage7(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/logout.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(logoutIcon.getWidth(), logoutIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        logoutIcon.setIcon(scaledIcon);
    }
    public void scaleImage8(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/happy.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(logoIcon.getWidth(), logoIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        logoIcon.setIcon(scaledIcon);
    }
    public void scaleImage9(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/ringing.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(notificationIcon.getWidth(), notificationIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        notificationIcon.setIcon(scaledIcon);
    }
    public void scaleImage10(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/search.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(searchIcon.getWidth(), searchIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        searchIcon.setIcon(scaledIcon);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TravelerDetailsPanel = new javax.swing.JPanel();
        placePanel = new javax.swing.JPanel();
        searchIcon = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        addPlacesButton = new javax.swing.JButton();
        DashDetailsPanel = new javax.swing.JPanel();
        logoutIcon = new javax.swing.JLabel();
        profileIcon = new javax.swing.JLabel();
        vehiclesIcon = new javax.swing.JLabel();
        busTicketIcon = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        routeIcon = new javax.swing.JLabel();
        bookingIcon = new javax.swing.JLabel();
        dashboardIcon = new javax.swing.JLabel();
        vehiclesDetailsButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        busTicketsButton = new javax.swing.JButton();
        routeDetailsButton = new javax.swing.JButton();
        bookingDetailsButton = new javax.swing.JButton();
        dashboardButton = new javax.swing.JButton();
        profileButton = new javax.swing.JButton();
        logoIcon = new javax.swing.JLabel();
        headingPlaces = new javax.swing.JPanel();
        notificationIcon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TravelerDetailsPanel.setBackground(new java.awt.Color(255, 242, 227));
        TravelerDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        placePanel.setBackground(new java.awt.Color(200, 143, 75));
        placePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        TravelerDetailsPanel.add(placePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 54, 490, 260));

        searchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        TravelerDetailsPanel.add(searchIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 24, 20, 20));

        jTextField1.setBackground(new java.awt.Color(252, 186, 107));
        jTextField1.setText("Search");
        TravelerDetailsPanel.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 170, -1));

        backButton.setBackground(new java.awt.Color(252, 186, 107));
        backButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        TravelerDetailsPanel.add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        addPlacesButton.setBackground(new java.awt.Color(252, 186, 107));
        addPlacesButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addPlacesButton.setText("Add Places");
        addPlacesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlacesButtonActionPerformed(evt);
            }
        });
        TravelerDetailsPanel.add(addPlacesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, -1, -1));

        getContentPane().add(TravelerDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 36, 510, 330));

        DashDetailsPanel.setBackground(new java.awt.Color(241, 215, 184));
        DashDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoutIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout.png"))); // NOI18N
        DashDetailsPanel.add(logoutIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 295, 15, 15));
        logoutIcon.getAccessibleContext().setAccessibleName("");

        profileIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user.png"))); // NOI18N
        profileIcon.setText("jLabel1");
        DashDetailsPanel.add(profileIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 255, 15, 15));

        vehiclesIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/front-of-bus.png"))); // NOI18N
        DashDetailsPanel.add(vehiclesIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 216, 15, 15));

        busTicketIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tickets.png"))); // NOI18N
        DashDetailsPanel.add(busTicketIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 178, 15, 15));

        jLabel4.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        jLabel4.setText("Happy Travels");
        DashDetailsPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 15, 80, -1));

        routeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/directions.png"))); // NOI18N
        DashDetailsPanel.add(routeIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 135, 15, 15));

        bookingIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/booking.png"))); // NOI18N
        DashDetailsPanel.add(bookingIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 97, 15, 15));
        bookingIcon.getAccessibleContext().setAccessibleName("");

        dashboardIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dashboard.png"))); // NOI18N
        dashboardIcon.setText("jLabel1");
        DashDetailsPanel.add(dashboardIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 58, 15, 15));

        vehiclesDetailsButton.setBackground(new java.awt.Color(243, 219, 191));
        vehiclesDetailsButton.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        vehiclesDetailsButton.setText("          Vehicles Details");
        vehiclesDetailsButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        vehiclesDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehiclesDetailsButtonActionPerformed(evt);
            }
        });
        DashDetailsPanel.add(vehiclesDetailsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 130, 30));

        logoutButton.setBackground(new java.awt.Color(243, 219, 191));
        logoutButton.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        logoutButton.setText("               LogOut");
        logoutButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logoutButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        DashDetailsPanel.add(logoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 130, 30));

        busTicketsButton.setBackground(new java.awt.Color(243, 219, 191));
        busTicketsButton.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        busTicketsButton.setText("Bus Tickets");
        busTicketsButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        DashDetailsPanel.add(busTicketsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 130, 30));

        routeDetailsButton.setBackground(new java.awt.Color(243, 219, 191));
        routeDetailsButton.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        routeDetailsButton.setText("     Route Details");
        routeDetailsButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        routeDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                routeDetailsButtonActionPerformed(evt);
            }
        });
        DashDetailsPanel.add(routeDetailsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 130, 30));

        bookingDetailsButton.setBackground(new java.awt.Color(243, 219, 191));
        bookingDetailsButton.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        bookingDetailsButton.setText("          Booking Details");
        bookingDetailsButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bookingDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingDetailsButtonActionPerformed(evt);
            }
        });
        DashDetailsPanel.add(bookingDetailsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 130, 30));

        dashboardButton.setBackground(new java.awt.Color(243, 219, 191));
        dashboardButton.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        dashboardButton.setText("Dashboard");
        dashboardButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dashboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardButtonActionPerformed(evt);
            }
        });
        DashDetailsPanel.add(dashboardButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 130, 30));

        profileButton.setBackground(new java.awt.Color(243, 219, 191));
        profileButton.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        profileButton.setText("               Profile");
        profileButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        profileButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        profileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileButtonActionPerformed(evt);
            }
        });
        DashDetailsPanel.add(profileButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 130, 30));

        logoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/happy.png"))); // NOI18N
        logoIcon.setFocusCycleRoot(true);
        DashDetailsPanel.add(logoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(-35, -5, 120, 50));
        logoIcon.getAccessibleContext().setAccessibleName("");

        getContentPane().add(DashDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 360));

        headingPlaces.setBackground(new java.awt.Color(255, 255, 255));
        headingPlaces.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        notificationIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ringing.png"))); // NOI18N
        headingPlaces.add(notificationIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 25, 25));
        notificationIcon.getAccessibleContext().setAccessibleName("");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Places");
        headingPlaces.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 50, -1));

        getContentPane().add(headingPlaces, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 510, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void vehiclesDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehiclesDetailsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vehiclesDetailsButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void dashboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboardButtonActionPerformed

    private void routeDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_routeDetailsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_routeDetailsButtonActionPerformed

    private void profileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profileButtonActionPerformed

    private void bookingDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookingDetailsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookingDetailsButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backButtonActionPerformed

    private void addPlacesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlacesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addPlacesButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PlacesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlacesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlacesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlacesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlacesView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DashDetailsPanel;
    private javax.swing.JPanel TravelerDetailsPanel;
    private javax.swing.JButton addPlacesButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton bookingDetailsButton;
    private javax.swing.JLabel bookingIcon;
    private javax.swing.JLabel busTicketIcon;
    private javax.swing.JButton busTicketsButton;
    private javax.swing.JButton dashboardButton;
    private javax.swing.JLabel dashboardIcon;
    private javax.swing.JPanel headingPlaces;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel logoIcon;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel logoutIcon;
    private javax.swing.JLabel notificationIcon;
    private javax.swing.JPanel placePanel;
    private javax.swing.JButton profileButton;
    private javax.swing.JLabel profileIcon;
    private javax.swing.JButton routeDetailsButton;
    private javax.swing.JLabel routeIcon;
    private javax.swing.JLabel searchIcon;
    private javax.swing.JButton vehiclesDetailsButton;
    private javax.swing.JLabel vehiclesIcon;
    // End of variables declaration//GEN-END:variables
}
