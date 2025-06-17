/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.dao.TravellerDao;
import happytravell.model.BookingData;
import happytravell.model.TravellerData;
import happytravell.view.AdminBookingDetailsView;
import happytravell.view.AdmindashboardView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 *
 * @author Acer
 */
public class AdminBookingDetailsController {
    private AdminBookingDetailsView bookingView = new AdminBookingDetailsView();
    private List<BookingData>allBooking;
    private List<BookingData>filteredBooking;
    
    public AdminBookingDetailsController(AdminBookingDetailsView adminBookingDetailsView) {
        this.bookingView.DashboardNavigation(new AdminBookingDetailsController.DashboardNav(adminBookingDetailsView.getDashboardlabel()));
//        this.bookingView = adminBookingDetailsView;
        
        this.allBooking = new ArrayList<>();
        this.filteredBooking = new ArrayList<>();
        
        
    }
    public void open(){
    this.bookingView.setVisible(true);
    } 
    public void close(){
    this.bookingView.dispose();
    } 
    
    
    private void loadRestaurants() {
        try {
            TravellerDao travellerDao = new TravellerDao();
            allBooking = travellerDao.getImage();
            filteredBooking = new ArrayList<>(allBookingWithImage);
            displayAllRestaurants();
        } catch (Exception e) {
        }
    }
    
    //    Dashboard Navigation
    class DashboardNav implements MouseListener{
        
        private JLabel dashboardLabel;
        
        public DashboardNav(JLabel label){
            this.dashboardLabel = label;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            AdmindashboardView admindashboardView = new AdmindashboardView();
            AdminDashboardController AdminDashboard= new AdminDashboardController(admindashboardView);
            AdminDashboard.open();
            close();
        }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            dashboardLabel.setForeground(Color.RED);
            dashboardLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            dashboardLabel.setForeground(Color.BLACK);
            dashboardLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    public class BookingDetailsPopup extends JDialog {
    private BookingData bookingData;
    private TravellerData travellerData;
    private JLabel customerImageLabel;
    private JLabel nameValueLabel;
    private JLabel destinationValueLabel;
    private JLabel pickupAddressValueLabel;
    private JLabel dropAddressValueLabel;
    private JLabel departureDateValueLabel;
    private JLabel departureTimeValueLabel;
    private JLabel returnDateValueLabel;
    private JLabel returnTimeValueLabel;
    private JLabel passengerCountValueLabel;
    private JLabel vehicleNumberValueLabel;
    private JLabel driverNameValueLabel;
    private JLabel statusValueLabel;
    private JButton showRouteButton;
    private JButton closeButton;
    
    public BookingDetailsPopup(BookingData booking) {
        super((JFrame) null, "Booking Details", true);
        this.bookingData = booking;
        initializeComponents();
        setupLayout();
        populateData();
        setupEventHandlers();
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void initializeComponents() {
        setSize(800, 600);
        setBackground(new Color(255, 248, 235));
        
        customerImageLabel = new JLabel();
        customerImageLabel.setPreferredSize(new Dimension(120, 120));
        customerImageLabel.setBorder(BorderFactory.createLineBorder(new Color(241, 215, 184), 3));
        customerImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        customerImageLabel.setVerticalAlignment(SwingConstants.CENTER);
        customerImageLabel.setBackground(Color.WHITE);
        customerImageLabel.setOpaque(true);
        
        // Initialize value labels
        nameValueLabel = createValueLabel();
        destinationValueLabel = createValueLabel();
        pickupAddressValueLabel = createValueLabel();
        dropAddressValueLabel = createValueLabel();
        departureDateValueLabel = createValueLabel();
        departureTimeValueLabel = createValueLabel();
        returnDateValueLabel = createValueLabel();
        returnTimeValueLabel = createValueLabel();
        passengerCountValueLabel = createValueLabel();
        vehicleNumberValueLabel = createValueLabel();
        driverNameValueLabel = createValueLabel();
        statusValueLabel = createValueLabel();
        
        showRouteButton = new JButton("Show Route");
        showRouteButton.setBackground(new Color(241, 215, 184));
        showRouteButton.setForeground(new Color(80, 50, 30));
        showRouteButton.setFont(new Font("Candara", Font.BOLD, 14));
        showRouteButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        showRouteButton.setFocusPainted(false);
        showRouteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        closeButton = new JButton("✕");
        closeButton.setBackground(new Color(244, 67, 54));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        closeButton.setFocusPainted(false);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.setPreferredSize(new Dimension(40, 40));
    }    
        private JLabel createValueLabel() {
        JLabel label = new JLabel();
        label.setFont(new Font("Candara", Font.PLAIN, 14));
        label.setForeground(new Color(60, 40, 20));
        return label;
    }
    
    private JLabel createFieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Candara", Font.BOLD, 14));
        label.setForeground(new Color(80, 50, 30));
        return label;
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("Booking Details", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Constantia", Font.BOLD, 18));
        titleLabel.setForeground(new Color(80, 50, 30));
        
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(closeButton, BorderLayout.EAST);
        
        // Main Content Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 248, 235));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JPanel customerPanel = new JPanel(new BorderLayout());
        customerPanel.setBackground(new Color(255, 248, 235));
        customerPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(241, 215, 184), 2),
            "Customer Information",
            0, 0,
            new Font("Candara", Font.BOLD, 16),
            new Color(80, 50, 30)
        ));
        
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(new Color(255, 248, 235));
        imagePanel.add(customerImageLabel);
        
        customerPanel.add(imagePanel, BorderLayout.CENTER);
        customerPanel.setPreferredSize(new Dimension(200, 300));
        
        // Details Panel (Right side)
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBackground(new Color(255, 248, 235));
        detailsPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(241, 215, 184), 2),
            "Booking Information",
            0, 0,
            new Font("Candara", Font.BOLD, 16),
            new Color(80, 50, 30)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(8, 10, 8, 10);
        
        // Add fields to details panel
        addField(detailsPanel, "Name:", nameValueLabel, gbc, 0);
        addField(detailsPanel, "Destination:", destinationValueLabel, gbc, 1);
        addField(detailsPanel, "Pickup Address:", pickupAddressValueLabel, gbc, 2);
        addField(detailsPanel, "Drop Address:", dropAddressValueLabel, gbc, 3);
        addField(detailsPanel, "Departure Date:", departureDateValueLabel, gbc, 4);
        addField(detailsPanel, "Departure Time:", departureTimeValueLabel, gbc, 5);
        addField(detailsPanel, "Return Date:", returnDateValueLabel, gbc, 6);
        addField(detailsPanel, "Return Time:", returnTimeValueLabel, gbc, 7);
        addField(detailsPanel, "Passengers:", passengerCountValueLabel, gbc, 8);
        addField(detailsPanel, "Vehicle Number:", vehicleNumberValueLabel, gbc, 9);
        addField(detailsPanel, "Driver Name:", driverNameValueLabel, gbc, 10);
        addField(detailsPanel, "Status:", statusValueLabel, gbc, 11);
        
        mainPanel.add(customerPanel, BorderLayout.WEST);
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 248, 235));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        buttonPanel.add(showRouteButton);
        
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void addField(JPanel parent, String labelText, JLabel valueLabel, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.0;
        parent.add(createFieldLabel(labelText), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        parent.add(valueLabel, gbc);
        gbc.fill = GridBagConstraints.NONE;
    }
    
    private void populateData() {
        if (bookingData != null) {
            nameValueLabel.setText(travellerData.getFirstName(resultSet.getString("first_name")) != null ? 
                                 travellerData.getFirstName(resultSet.getString("first_name")) : "Not specified");
            destinationValueLabel.setText(bookingData.getDropAddress(resultSet.getString("drop_address")) != null ? 
                                        bookingData.getDropAddress(resultSet.getString("drop_address")) : "Not specified");
            pickupAddressValueLabel.setText(bookingData.getPickupAddress() != null ? 
                                          bookingData.getPickupAddress() : "Not specified");
            dropAddressValueLabel.setText(bookingData.getDepartureDateTime() != null ? 
                                        bookingData.getDepartureDateTime() : "Not specified");
            departureDateValueLabel.setText(bookingData.getReturnDateTime() != null ? 
                                          bookingData.getReturnDateTime() : "Not specified");
            passengerCountValueLabel.setText(String.valueOf(bookingData.getPassengerCount()));
            vehicleNumberValueLabel.setText(bookingData.getVehicleNumber() != null ? 
                                          bookingData.getVehicleNumber() : "Not assigned");
            driverNameValueLabel.setText(bookingData.getDriverName() != null ? 
                                       bookingData.getDriverName() : "Not assigned");
            
            
            
            // Set customer image
            byte[] imageData = travellerData.getImage(resultSet.getBytes("image"));
            if (imageData != null && imageData.length > 0) {
                try {
                    ImageIcon originalIcon = new ImageIcon(imageData);
                    if (originalIcon.getIconWidth() > 0 && originalIcon.getIconHeight() > 0) {
                        Image scaledImage = originalIcon.getImage().getScaledInstance(
                            110, 110, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                        customerImageLabel.setIcon(scaledIcon);
                        customerImageLabel.setText("");
                    } else {
                        setDefaultCustomerImage();
                    }
                } catch (Exception e) {
                    System.err.println("Error loading customer image: " + e.getMessage());
                    setDefaultCustomerImage();
                }
            } else {
                setDefaultCustomerImage();
            }
        }
    }
    
    private void setDefaultCustomerImage() {
        customerImageLabel.setText("👤");
        customerImageLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        customerImageLabel.setForeground(new Color(158, 158, 158));
        customerImageLabel.setIcon(null);
    }
    
    private void setupEventHandlers() {
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        showRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement route showing functionality here
                System.out.println("Show route for booking: " + travellerData.getFirstName(resultSet.getString("first_name")));
                // You can integrate with map API or route display functionality
            }
        });
        
        // Add hover effects
        showRouteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                showRouteButton.setBackground(new Color(230, 190, 150));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                showRouteButton.setBackground(new Color(241, 215, 184));
            }
        });
        
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setBackground(new Color(220, 50, 40));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                closeButton.setBackground(new Color(244, 67, 54));
            }
        });
    }
    
    public void showPopup() {
        setVisible(true);
    }
    
    public void BookingData(BookingData newBookingData) {
        this.bookingData = newBookingData;
        populateData();
    }
    
    public BookingData getBookingData() {
        return bookingData;
    }

    
    
    
}
}
