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
public class TravellerBookingView extends javax.swing.JFrame {

    /**
     * Creates new form AdmindashboardView
     */
    public TravellerBookingView() {
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
        scaleImage11();
        scaleImage12();
        scaleImage13();
        scaleImage14();
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
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/car.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(carIcon.getWidth(), carIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        carIcon.setIcon(scaledIcon);
    }
    public void scaleImage11(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/jeep .png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(jeepIcon.getWidth(), jeepIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        jeepIcon.setIcon(scaledIcon);
    }
    public void scaleImage12(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/taxi.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(taxiIcon.getWidth(), taxiIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        taxiIcon.setIcon(scaledIcon);
    }
    public void scaleImage13(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/image/guide.png"));
        //scaling image to fit in the hlabel.
        Image img1 = icon1.getImage();
        Image imgScale = img1.getScaledInstance(guideIcon.getWidth(), guideIcon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        guideIcon.setIcon(scaledIcon);
    }
    public void scaleImage14(){
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

        jPanel11 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        notificationIcon = new javax.swing.JLabel();
        TravelerDetailsPanel = new javax.swing.JPanel();
        guideIcon = new javax.swing.JLabel();
        taxiIcon = new javax.swing.JLabel();
        jeepIcon = new javax.swing.JLabel();
        carIcon = new javax.swing.JLabel();
        travellersButton1 = new javax.swing.JButton();
        travellersButton2 = new javax.swing.JButton();
        travellersButton3 = new javax.swing.JButton();
        travellersButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        returnSpinner = new javax.swing.JSpinner();
        departureSpinner = new javax.swing.JSpinner();
        returnDateTimeTextField = new javax.swing.JTextField();
        pickupAddressTextField = new javax.swing.JTextField();
        dropAdressTextField = new javax.swing.JTextField();
        departureDateTimeTextField = new javax.swing.JTextField();
        numberOfPassenger = new javax.swing.JLabel();
        PaymentMethodComboBox = new javax.swing.JComboBox<>();
        VehiclesNumberComboBox = new javax.swing.JComboBox<>();
        driverNameComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel6 = new javax.swing.JLabel();
        bookButton = new javax.swing.JButton();
        bookingDetailsButton = new javax.swing.JButton();
        searchIcon = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        numberSpinner = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();
        DashDetailsPanel = new javax.swing.JPanel();
        logoutIcon = new javax.swing.JLabel();
        profileIcon = new javax.swing.JLabel();
        vehiclesIcon = new javax.swing.JLabel();
        busTicketIcon = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        routeIcon = new javax.swing.JLabel();
        bookingIcon = new javax.swing.JLabel();
        dashboardIcon = new javax.swing.JLabel();
        logoIcon = new javax.swing.JLabel();
        dashboardLabel = new javax.swing.JLabel();
        bookingLabel = new javax.swing.JLabel();
        routeDetailsLabel = new javax.swing.JLabel();
        busDetailsLabel = new javax.swing.JLabel();
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jLabel1.setText("Booking");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 6, 68, -1));

        notificationIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ringing.png"))); // NOI18N
        jPanel1.add(notificationIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 5, 20, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 510, 30));

        TravelerDetailsPanel.setBackground(new java.awt.Color(235, 189, 135));
        TravelerDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        guideIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/guide.png"))); // NOI18N
        TravelerDetailsPanel.add(guideIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 35, 20));

        taxiIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/taxi.png"))); // NOI18N
        TravelerDetailsPanel.add(taxiIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 35, 20));

        jeepIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/jeep .png"))); // NOI18N
        TravelerDetailsPanel.add(jeepIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 40, 25));

        carIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/car.png"))); // NOI18N
        TravelerDetailsPanel.add(carIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 25, 48, 35));

        travellersButton1.setBackground(new java.awt.Color(171, 106, 32));
        travellersButton1.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        travellersButton1.setText("           Guide");
        travellersButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        travellersButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                travellersButton1ActionPerformed(evt);
            }
        });
        TravelerDetailsPanel.add(travellersButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 23, 110, 35));

        travellersButton2.setBackground(new java.awt.Color(171, 106, 32));
        travellersButton2.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        travellersButton2.setText("              Car");
        travellersButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        travellersButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                travellersButton2ActionPerformed(evt);
            }
        });
        TravelerDetailsPanel.add(travellersButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 23, 110, 35));

        travellersButton3.setBackground(new java.awt.Color(171, 106, 32));
        travellersButton3.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        travellersButton3.setText("            Jeep");
        travellersButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        travellersButton3.setHideActionText(true);
        travellersButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                travellersButton3ActionPerformed(evt);
            }
        });
        TravelerDetailsPanel.add(travellersButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 23, 110, 35));

        travellersButton4.setBackground(new java.awt.Color(171, 106, 32));
        travellersButton4.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        travellersButton4.setText("       Taxi");
        travellersButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        travellersButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                travellersButton4ActionPerformed(evt);
            }
        });
        TravelerDetailsPanel.add(travellersButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 23, 110, 35));

        jLabel2.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jLabel2.setText("Choose Service");
        TravelerDetailsPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 110, -1));

        jPanel2.setBackground(new java.awt.Color(248, 219, 164));
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jLabel3.setText("Booking Details");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        returnSpinner.setModel(new javax.swing.SpinnerDateModel());
        returnSpinner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        returnSpinner.setFocusCycleRoot(true);
        jPanel2.add(returnSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 122, 24));

        departureSpinner.setModel(new javax.swing.SpinnerDateModel());
        departureSpinner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        departureSpinner.setFocusCycleRoot(true);
        jPanel2.add(departureSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, 24));

        returnDateTimeTextField.setBackground(new java.awt.Color(248, 219, 164));
        returnDateTimeTextField.setText("Return Date & Time");
        returnDateTimeTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(returnDateTimeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 250, 23));

        pickupAddressTextField.setBackground(new java.awt.Color(248, 219, 164));
        pickupAddressTextField.setText("Pickup Address");
        pickupAddressTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(pickupAddressTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 18, 250, 23));

        dropAdressTextField.setBackground(new java.awt.Color(248, 219, 164));
        dropAdressTextField.setText("Drop Address");
        dropAdressTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(dropAdressTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 250, 23));

        departureDateTimeTextField.setBackground(new java.awt.Color(248, 219, 164));
        departureDateTimeTextField.setText("Departure Date & Time");
        departureDateTimeTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(departureDateTimeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 250, 23));

        numberOfPassenger.setText("Number of passenger");
        numberOfPassenger.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(numberOfPassenger, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 200, 22));

        PaymentMethodComboBox.setBackground(new java.awt.Color(248, 219, 164));
        PaymentMethodComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Payment Method", "Cash ", "E-sewa", "Mobile Banking" }));
        PaymentMethodComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentMethodComboBoxActionPerformed(evt);
            }
        });
        jPanel2.add(PaymentMethodComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 250, -1));

        VehiclesNumberComboBox.setBackground(new java.awt.Color(248, 219, 164));
        VehiclesNumberComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vehicles Number", " ", " ", " " }));
        VehiclesNumberComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VehiclesNumberComboBoxActionPerformed(evt);
            }
        });
        jPanel2.add(VehiclesNumberComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 250, -1));

        driverNameComboBox.setBackground(new java.awt.Color(248, 219, 164));
        driverNameComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Driver name" }));
        driverNameComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driverNameComboBoxActionPerformed(evt);
            }
        });
        jPanel2.add(driverNameComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 250, -1));

        jScrollPane1.setViewportView(null);
        jScrollPane1.setViewportView(jLabel6);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 210, 160));

        bookButton.setBackground(new java.awt.Color(171, 106, 32));
        bookButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bookButton.setText(" Book");
        jPanel2.add(bookButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, 70, -1));

        bookingDetailsButton.setBackground(new java.awt.Color(171, 106, 32));
        bookingDetailsButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bookingDetailsButton.setText("Booking Details");
        jPanel2.add(bookingDetailsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 120, -1));

        searchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jPanel2.add(searchIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 27, 15, 15));

        jTextField1.setBackground(new java.awt.Color(252, 186, 107));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jTextField1.setText("Search");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 180, -1));

        numberSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 5, 1));
        numberSpinner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        numberSpinner.setFocusCycleRoot(true);
        jPanel2.add(numberSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 50, 24));

        jSpinner4.setModel(new javax.swing.SpinnerDateModel());
        jSpinner4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSpinner4.setFocusCycleRoot(true);
        jPanel2.add(jSpinner4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, 24));

        TravelerDetailsPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 490, 260));

        getContentPane().add(TravelerDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 510, 330));

        DashDetailsPanel.setBackground(new java.awt.Color(241, 215, 184));
        DashDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoutIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout.png"))); // NOI18N
        DashDetailsPanel.add(logoutIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 295, 18, 18));
        logoutIcon.getAccessibleContext().setAccessibleName("");

        profileIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user.png"))); // NOI18N
        profileIcon.setText("jLabel1");
        DashDetailsPanel.add(profileIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 255, 18, 18));

        vehiclesIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/front-of-bus.png"))); // NOI18N
        DashDetailsPanel.add(vehiclesIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 216, 18, 18));

        busTicketIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tickets.png"))); // NOI18N
        DashDetailsPanel.add(busTicketIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 178, 18, 18));

        jLabel4.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        jLabel4.setText("Happy Travels");
        DashDetailsPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 15, 80, -1));

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
        DashDetailsPanel.add(dashboardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 70, -1));

        bookingLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        bookingLabel.setText("Booking");
        DashDetailsPanel.add(bookingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 70, -1));

        routeDetailsLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        routeDetailsLabel.setText("Route Details");
        DashDetailsPanel.add(routeDetailsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        busDetailsLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        busDetailsLabel.setText("Bus Details");
        DashDetailsPanel.add(busDetailsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 70, -1));

        vehiclesDetailsLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        vehiclesDetailsLabel.setText("Vehicles Details");
        DashDetailsPanel.add(vehiclesDetailsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        profileLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        profileLabel.setText("Profile");
        DashDetailsPanel.add(profileLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 50, -1));

        logOutLabel.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        logOutLabel.setText("Logout");
        DashDetailsPanel.add(logOutLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 50, -1));

        getContentPane().add(DashDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 360));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void travellersButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_travellersButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_travellersButton1ActionPerformed

    private void travellersButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_travellersButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_travellersButton2ActionPerformed

    private void travellersButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_travellersButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_travellersButton3ActionPerformed

    private void travellersButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_travellersButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_travellersButton4ActionPerformed

    private void PaymentMethodComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentMethodComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PaymentMethodComboBoxActionPerformed

    private void VehiclesNumberComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VehiclesNumberComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VehiclesNumberComboBoxActionPerformed

    private void driverNameComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driverNameComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_driverNameComboBoxActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(TravellerBookingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TravellerBookingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TravellerBookingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TravellerBookingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TravellerBookingView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DashDetailsPanel;
    private javax.swing.JComboBox<String> PaymentMethodComboBox;
    private javax.swing.JPanel TravelerDetailsPanel;
    private javax.swing.JComboBox<String> VehiclesNumberComboBox;
    private javax.swing.JButton bookButton;
    private javax.swing.JButton bookingDetailsButton;
    private javax.swing.JLabel bookingIcon;
    private javax.swing.JLabel bookingLabel;
    private javax.swing.JLabel busDetailsLabel;
    private javax.swing.JLabel busTicketIcon;
    private javax.swing.JLabel carIcon;
    private javax.swing.JLabel dashboardIcon;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JTextField departureDateTimeTextField;
    private javax.swing.JSpinner departureSpinner;
    private javax.swing.JComboBox<String> driverNameComboBox;
    private javax.swing.JTextField dropAdressTextField;
    private javax.swing.JLabel guideIcon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel jeepIcon;
    private javax.swing.JLabel logOutLabel;
    private javax.swing.JLabel logoIcon;
    private javax.swing.JLabel logoutIcon;
    private javax.swing.JLabel notificationIcon;
    private javax.swing.JLabel numberOfPassenger;
    private javax.swing.JSpinner numberSpinner;
    private javax.swing.JTextField pickupAddressTextField;
    private javax.swing.JLabel profileIcon;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JTextField returnDateTimeTextField;
    private javax.swing.JSpinner returnSpinner;
    private javax.swing.JLabel routeDetailsLabel;
    private javax.swing.JLabel routeIcon;
    private javax.swing.JLabel searchIcon;
    private javax.swing.JLabel taxiIcon;
    private javax.swing.JButton travellersButton1;
    private javax.swing.JButton travellersButton2;
    private javax.swing.JButton travellersButton3;
    private javax.swing.JButton travellersButton4;
    private javax.swing.JLabel vehiclesDetailsLabel;
    private javax.swing.JLabel vehiclesIcon;
    // End of variables declaration//GEN-END:variables
}
