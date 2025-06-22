/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package happytravell.view;

import happytravell.UI.AdminBookingDetailsCardPanel;
import happytravell.controller.AdminBookingDetailsController;
import happytravell.dao.TravellerDao;
import happytravell.model.BookingData;
import happytravell.model.TravellerData;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Acer
 */
public class AdminBookingDetailsView extends javax.swing.JFrame {

    /**
     * Creates new form AdmindashboardView
     */
    public AdminBookingDetailsView() {
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel11 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        TravelerDetailsPanel = new javax.swing.JPanel();
        notificationIcon = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
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
        bookingDetailsLabel = new javax.swing.JLabel();
        routeDetailsLabel = new javax.swing.JLabel();
        busTicketsLabel = new javax.swing.JLabel();
        vehiclesDetailsLabel = new javax.swing.JLabel();
        profileLabel = new javax.swing.JLabel();
        logOutLabel = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        TravelerDetailsPanel.setBackground(new java.awt.Color(255, 242, 227));
        TravelerDetailsPanel.setLayout(null);

        notificationIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ringing.png"))); // NOI18N
        TravelerDetailsPanel.add(notificationIcon);
        notificationIcon.setBounds(470, 5, 25, 25);
        notificationIcon.getAccessibleContext().setAccessibleName("");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Constantia", 1, 14)); // NOI18N
        jLabel1.setText("Booking Details");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(201, 0, 124, 30);

        TravelerDetailsPanel.add(jPanel1);
        jPanel1.setBounds(0, 0, 510, 30);

        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel2.setBackground(new java.awt.Color(255, 242, 227));
        jPanel2.setLayout(null);
        scrollPane.setViewportView(jPanel2);

        TravelerDetailsPanel.add(scrollPane);
        scrollPane.setBounds(0, 30, 510, 330);

        getContentPane().add(TravelerDetailsPanel);
        TravelerDetailsPanel.setBounds(130, 0, 510, 360);

        DashDetailsPanel.setBackground(new java.awt.Color(241, 215, 184));
        DashDetailsPanel.setLayout(null);

        logoutIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout.png"))); // NOI18N
        DashDetailsPanel.add(logoutIcon);
        logoutIcon.setBounds(10, 295, 18, 18);
        logoutIcon.getAccessibleContext().setAccessibleName("");

        profileIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user.png"))); // NOI18N
        profileIcon.setText("jLabel1");
        DashDetailsPanel.add(profileIcon);
        profileIcon.setBounds(10, 255, 18, 18);

        vehiclesIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/front-of-bus.png"))); // NOI18N
        DashDetailsPanel.add(vehiclesIcon);
        vehiclesIcon.setBounds(10, 216, 18, 18);

        busTicketIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tickets.png"))); // NOI18N
        DashDetailsPanel.add(busTicketIcon);
        busTicketIcon.setBounds(10, 178, 18, 18);

        happyTravelLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        happyTravelLabel.setText("Happy Travels");
        DashDetailsPanel.add(happyTravelLabel);
        happyTravelLabel.setBounds(50, 15, 80, -1);

        routeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/directions.png"))); // NOI18N
        DashDetailsPanel.add(routeIcon);
        routeIcon.setBounds(10, 140, 18, 18);

        bookingIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/booking.png"))); // NOI18N
        DashDetailsPanel.add(bookingIcon);
        bookingIcon.setBounds(10, 97, 18, 18);
        bookingIcon.getAccessibleContext().setAccessibleName("");

        dashboardIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dashboard.png"))); // NOI18N
        dashboardIcon.setText("jLabel1");
        DashDetailsPanel.add(dashboardIcon);
        dashboardIcon.setBounds(10, 58, 18, 18);

        logoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/happy.png"))); // NOI18N
        logoIcon.setFocusCycleRoot(true);
        DashDetailsPanel.add(logoIcon);
        logoIcon.setBounds(-35, -5, 120, 50);
        logoIcon.getAccessibleContext().setAccessibleName("");

        dashboardLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        dashboardLabel.setText("  Dashboard");
        DashDetailsPanel.add(dashboardLabel);
        dashboardLabel.setBounds(30, 60, 80, -1);

        bookingDetailsLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        bookingDetailsLabel.setText("  Booking Details");
        DashDetailsPanel.add(bookingDetailsLabel);
        bookingDetailsLabel.setBounds(30, 100, 100, -1);

        routeDetailsLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        routeDetailsLabel.setText("  Route Details");
        DashDetailsPanel.add(routeDetailsLabel);
        routeDetailsLabel.setBounds(30, 140, 90, 20);

        busTicketsLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        busTicketsLabel.setText("  Bus Tickets");
        DashDetailsPanel.add(busTicketsLabel);
        busTicketsLabel.setBounds(36, 182, 60, -1);

        vehiclesDetailsLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        vehiclesDetailsLabel.setText("  Vehicles Details");
        DashDetailsPanel.add(vehiclesDetailsLabel);
        vehiclesDetailsLabel.setBounds(31, 220, 90, -1);

        profileLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        profileLabel.setText("  Profile");
        DashDetailsPanel.add(profileLabel);
        profileLabel.setBounds(40, 260, 50, -1);

        logOutLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        logOutLabel.setText("  Log Out");
        DashDetailsPanel.add(logOutLabel);
        logOutLabel.setBounds(40, 300, -1, -1);

        getContentPane().add(DashDetailsPanel);
        DashDetailsPanel.setBounds(0, 0, 130, 360);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(AdminBookingDetailsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminBookingDetailsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminBookingDetailsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminBookingDetailsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminBookingDetailsView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DashDetailsPanel;
    private javax.swing.JPanel TravelerDetailsPanel;
    private javax.swing.JLabel bookingDetailsLabel;
    private javax.swing.JLabel bookingIcon;
    private javax.swing.JLabel busTicketIcon;
    private javax.swing.JLabel busTicketsLabel;
    private javax.swing.JLabel dashboardIcon;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JLabel happyTravelLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel logOutLabel;
    private javax.swing.JLabel logoIcon;
    private javax.swing.JLabel logoutIcon;
    private javax.swing.JLabel notificationIcon;
    private javax.swing.JLabel profileIcon;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JLabel routeDetailsLabel;
    private javax.swing.JLabel routeIcon;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel vehiclesDetailsLabel;
    private javax.swing.JLabel vehiclesIcon;
    // End of variables declaration//GEN-END:variables

    
    public void scrollToTop() {
        SwingUtilities.invokeLater(() -> {
            scrollPane.getVerticalScrollBar().setValue(0);
        });
    }
    
   // Replace the displayBooking method in AdminBookingDetailsView with this debug version:

public void displayBooking(List<BookingData> bookings, AdminBookingDetailsController controller) {
    System.out.println("=== DEBUG: displayBooking called ===");
    System.out.println("Number of bookings received: " + (bookings != null ? bookings.size() : "null"));
    
    jPanel2.removeAll();
    jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));
    
    if (bookings == null || bookings.isEmpty()) {
        System.out.println("No bookings to display");
        JLabel noDataLabel = new JLabel("No booking data available");
        noDataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel2.add(noDataLabel);
        jPanel2.revalidate();
        jPanel2.repaint();
        return;
    }
    
    TravellerDao travellerDao = new TravellerDao();
    
    for (int i = 0; i < bookings.size(); i++) {
        BookingData bookingDetails = bookings.get(i);
        System.out.println("Processing booking " + (i+1) + "/" + bookings.size());
        System.out.println("Booking ID: " + bookingDetails.getBookingId());
        System.out.println("Traveller ID: " + bookingDetails.getTravellerId());
        
        try {
            // Get traveller data for this booking
            TravellerData traveller = travellerDao.getTravellerById(bookingDetails.getTravellerId());
            System.out.println("Traveller loaded: " + (traveller != null ? traveller.getFirstName() : "null"));
            
            // Create card panel
            AdminBookingDetailsCardPanel cardPanel = new AdminBookingDetailsCardPanel(bookingDetails);
            System.out.println("Card panel created");
            
            cardPanel.setTravellerData(traveller);
            cardPanel.populateData();
            System.out.println("Card panel populated");
            
            cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cardPanel.setPreferredSize(new java.awt.Dimension(480, 100)); // Set explicit size for testing
            cardPanel.setBackground(java.awt.Color.LIGHT_GRAY); // Temporary background for visibility
            cardPanel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED, 2)); // Temporary border
            
            // Use the controller's BookingCardListener
            cardPanel.addMouseListener(new AdminBookingDetailsController.BookingCardListener(bookingDetails, controller));
            System.out.println("Mouse listener added");
            
            jPanel2.add(cardPanel);
            System.out.println("Card panel added to jPanel2");
            
            if (i < bookings.size() - 1) {
                jPanel2.add(Box.createVerticalStrut(15));
            }
            
        } catch (Exception e) {
            System.err.println("Error creating card for booking " + bookingDetails.getBookingId());
            e.printStackTrace();
            
            // Add error card
            JPanel errorPanel = new JPanel();
            errorPanel.setBackground(java.awt.Color.PINK);
            errorPanel.add(new JLabel("Error loading booking: " + e.getMessage()));
            errorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            jPanel2.add(errorPanel);
        }
    }
    
    jPanel2.add(Box.createVerticalGlue());
    
    
   
}

    private JPanel createBookingCard(BookingData bookingDetails) {
        return new AdminBookingDetailsCardPanel(bookingDetails);
    }

    
//    navigation
    public void DashboardNavigation(MouseListener listener){
        dashboardLabel.addMouseListener(listener);
    }
    public JLabel getDashboardlabel(){
        return dashboardLabel;
    }
    public void RouteDetailsNavigation(MouseListener listener){
        routeDetailsLabel.addMouseListener(listener);
    }
    public JLabel getRouteDetailslabel(){
        return routeDetailsLabel;
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
