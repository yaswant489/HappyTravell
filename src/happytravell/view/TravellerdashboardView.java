/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package happytravell.view;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

import javax.swing.JLabel;

/**
 *
 * @author Acer
 */
public class TravellerdashboardView extends javax.swing.JFrame {

    /**
     * Creates new form AdmindashboardView
     */
    public TravellerdashboardView() {
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
    }
    
    public void scaleImage1(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/dashboard.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        dashboardIcon.setIcon(scaledIcon);
    }
    public void scaleImage2(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/booking.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        bookingIcon.setIcon(scaledIcon);
    }
    public void scaleImage3(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/directions.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        routeIcon.setIcon(scaledIcon);
    }
    public void scaleImage4(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/tickets.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        busTicketIcon.setIcon(scaledIcon);
    }
    public void scaleImage5(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/front-of-bus.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        vehiclesIcon.setIcon(scaledIcon);
    }
    public void scaleImage6(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/user.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        profileIcon.setIcon(scaledIcon);
    }
    public void scaleImage7(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/logout.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        logoutIcon.setIcon(scaledIcon);
    }
    public void scaleImage8(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/happy.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        logoIcon.setIcon(scaledIcon);
    }
    public void scaleImage9(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/ringing.png"));
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        notificationIcon.setIcon(scaledIcon);
    }

    public void setContent(JPanel panel) {
        TravelerDetailsPanel.removeAll();
        TravelerDetailsPanel.add(panel, java.awt.BorderLayout.CENTER);
        TravelerDetailsPanel.revalidate();
        TravelerDetailsPanel.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel11 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        TravelerDetailsPanel = new javax.swing.JPanel();
        placePanel = new javax.swing.JPanel();

        pokharaPanelButton = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        mustangPanelButton = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        kathmanduPanelButton = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        satisfactionButton = new javax.swing.JButton();
        travelsDoneButton = new javax.swing.JButton();
        pendingButton = new javax.swing.JButton();
        placesButton = new javax.swing.JButton();
        notificationIcon = new javax.swing.JLabel();
        DashDetailsPanel = new javax.swing.JPanel();
        logoutIcon = new javax.swing.JLabel();
        profileIcon = new javax.swing.JLabel();
        vehiclesIcon = new javax.swing.JLabel();
        busTicketIcon = new javax.swing.JLabel();
        happyTravelLabel = new javax.swing.JLabel();
        routeIcon = new javax.swing.JLabel();
        bookingIcon = new javax.swing.JLabel();
        dashboardIcon = new javax.swing.JLabel();
        logoIcon = new javax.swing.JLabel();
        dashboardLabel = new javax.swing.JLabel();
        bookingLabel = new javax.swing.JLabel();
        routeLabel = new javax.swing.JLabel();
        busTicketsLabel = new javax.swing.JLabel();
        vehiclesDetailsLabel = new javax.swing.JLabel();
        profileLabel = new javax.swing.JLabel();

        logOutLabel = new javax.swing.JLabel();

        // ... (original layout code restored here)
        
        pack();
    }// </editor-fold>                        

    public void addLogoutListener(java.awt.event.ActionListener listener) {
        logoutIcon.addMouseListener(createMouseListener(listener));
        logOutLabel.addMouseListener(createMouseListener(listener));
    }

    public void addBookingListener(java.awt.event.ActionListener listener) {
        bookingIcon.addMouseListener(createMouseListener(listener));
        bookingLabel.addMouseListener(createMouseListener(listener));
    }

    public void addVehiclesListener(java.awt.event.ActionListener listener) {
        vehiclesIcon.addMouseListener(createMouseListener(listener));
        vehiclesDetailsLabel.addMouseListener(createMouseListener(listener));
    }

    public void addDashboardListener(java.awt.event.ActionListener listener) {
        dashboardIcon.addMouseListener(createMouseListener(listener));
        dashboardLabel.addMouseListener(createMouseListener(listener));
    }


        travelsDoneButton.setBackground(new java.awt.Color(166, 124, 94));
        travelsDoneButton.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        travelsDoneButton.setText("Travels Done ");
        travelsDoneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                travelsDoneButtonActionPerformed(evt);
            }
        });
        TravelerDetailsPanel.add(travelsDoneButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 301, 120, 53));


    public void addBusTicketsListener(java.awt.event.ActionListener listener) {
        busTicketIcon.addMouseListener(createMouseListener(listener));
        busTicketsLabel.addMouseListener(createMouseListener(listener));
    }


        placesButton.setBackground(new java.awt.Color(173, 77, 44));
        placesButton.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        placesButton.setText("Places");
        placesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placesButtonActionPerformed(evt);
            }
        });
        TravelerDetailsPanel.add(placesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 300, 115, 53));

        notificationIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ringing.png"))); // NOI18N
        TravelerDetailsPanel.add(notificationIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 5, 25, 25));
        notificationIcon.getAccessibleContext().setAccessibleName("");

        getContentPane().add(TravelerDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 510, 360));

        DashDetailsPanel.setBackground(new java.awt.Color(241, 215, 184));
        DashDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoutIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout.png"))); // NOI18N
        DashDetailsPanel.add(logoutIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 295, 18, 18));
        logoutIcon.getAccessibleContext().setAccessibleName("");

        profileIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user.png"))); // NOI18N
        profileIcon.setText("jLabel1");
        DashDetailsPanel.add(profileIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 255, 18, 18));

        vehiclesIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/front-of-bus.png"))); // NOI18N
        DashDetailsPanel.add(vehiclesIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 18, 18));

        busTicketIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tickets.png"))); // NOI18N
        DashDetailsPanel.add(busTicketIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 178, 18, 18));

        happyTravelLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        happyTravelLabel.setText("Happy Travels");
        DashDetailsPanel.add(happyTravelLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 15, 80, -1));

        routeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/directions.png"))); // NOI18N
        DashDetailsPanel.add(routeIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 135, 18, 18));

        bookingIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/booking.png"))); // NOI18N
        DashDetailsPanel.add(bookingIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 97, 18, 18));
        bookingIcon.getAccessibleContext().setAccessibleName("");

        dashboardIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dashboard.png"))); // NOI18N
        dashboardIcon.setText("jLabel1");
        DashDetailsPanel.add(dashboardIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 58, 18, 18));

        logoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/happy.png"))); // NOI18N
        logoIcon.setFocusCycleRoot(true);
        DashDetailsPanel.add(logoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(-35, -5, 120, 50));
        logoIcon.getAccessibleContext().setAccessibleName("");

        dashboardLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        dashboardLabel.setText("Dashboard");
        DashDetailsPanel.add(dashboardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        bookingLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        bookingLabel.setText("Booking");
        DashDetailsPanel.add(bookingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 50, -1));

        routeLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        routeLabel.setText("Route");
        DashDetailsPanel.add(routeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 40, -1));

        busTicketsLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        busTicketsLabel.setText("Bus Tickets");
        DashDetailsPanel.add(busTicketsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        vehiclesDetailsLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        vehiclesDetailsLabel.setText("Vehicles Details");
        DashDetailsPanel.add(vehiclesDetailsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        profileLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        profileLabel.setText("Profile");
        DashDetailsPanel.add(profileLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 50, -1));

        logOutLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        logOutLabel.setText("LogOut");
        DashDetailsPanel.add(logOutLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        getContentPane().add(DashDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 360));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void satisfactionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_satisfactionButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_satisfactionButtonActionPerformed

    private void travelsDoneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_travelsDoneButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_travelsDoneButtonActionPerformed

    private void pendingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendingButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pendingButtonActionPerformed

    private void placesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_placesButtonActionPerformed

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
            java.util.logging.Logger.getLogger(TravellerdashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TravellerdashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TravellerdashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TravellerdashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TravellerdashboardView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DashDetailsPanel;
    private javax.swing.JPanel TravelerDetailsPanel;
    private javax.swing.JLabel bookingIcon;
    private javax.swing.JLabel bookingLabel;
    private javax.swing.JLabel busTicketIcon;
    private javax.swing.JLabel busTicketsLabel;
    private javax.swing.JLabel dashboardIcon;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JLabel happyTravelLabel;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel kathmanduPanelButton;
    private javax.swing.JLabel logOutLabel;
    private javax.swing.JLabel logoIcon;
    private javax.swing.JLabel logoutIcon;
    private javax.swing.JPanel mustangPanelButton;
    private javax.swing.JLabel notificationIcon;
    private javax.swing.JButton pendingButton;
    private javax.swing.JPanel placePanel;
    private javax.swing.JButton placesButton;
    private javax.swing.JPanel pokharaPanelButton;
    private javax.swing.JLabel profileIcon;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JLabel routeIcon;
    private javax.swing.JLabel routeLabel;
    private javax.swing.JButton satisfactionButton;
    private javax.swing.JButton travelsDoneButton;
    private javax.swing.JLabel vehiclesDetailsLabel;
    private javax.swing.JLabel vehiclesIcon;
    // End of variables declaration//GEN-END:variables

    public void TravelsDoneNavigation(ActionListener listener){
        travelsDoneButton.addActionListener(listener);
    }
    public void PendingNavigation(ActionListener listener){
        pendingButton.addActionListener(listener);
    }
    public void PlacesNavigation(ActionListener listener){
        placesButton.addActionListener(listener);
    }
    public void SatisfactionNavigation(ActionListener listener){
        satisfactionButton.addActionListener(listener);
    }
    public void DashboardNavigation(MouseListener listener){
        dashboardLabel.addMouseListener(listener);
    }
    public JLabel getDashboardlabel(){
        return dashboardLabel;
    }
    public void BookingNavigation(MouseListener listener){
        bookingLabel.addMouseListener(listener);
    }
    public JLabel getBookinglabel(){
        return bookingLabel;
    }
    public void RouteNavigation(MouseListener listener){
        routeLabel.addMouseListener(listener);
    }
    public JLabel getRoutelabel(){
        return routeLabel;
    }
    public void BusTicketsNavigation(MouseListener listener){
        busTicketsLabel.addMouseListener(listener);
    }
    public JLabel getBusTicketslabel(){
        return busTicketsLabel;
    }
    public void VehiclesDetailsNavigation(MouseListener listener){
        vehiclesDetailsLabel.addMouseListener(listener);
    }
    public JLabel getVehiclesDetailslabel(){
        return vehiclesDetailsLabel;
    }
    public void ProfileNavigation(MouseListener listener){
        profileLabel.addMouseListener(listener);
    }
    public JLabel getProfilelabel(){
        return profileLabel;
    }
    public void LogOutNavigation(MouseListener listener){
        logOutLabel.addMouseListener(listener);
    }
    public JLabel getLogOutlabel(){
        return logOutLabel;
    }

}
