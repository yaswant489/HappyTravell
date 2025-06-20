/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.UI;

import happytravell.model.BookingData;
import happytravell.model.TravellerData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



/**
 *
 * @author Acer
 */
public class AdminBookingDetailsCardPanel extends PanelShadow {
    private BookingData bookingData;
    private TravellerData travellerData;
    
    // UI Components
    private JLabel profileImageLabel;
    private JLabel nameLabel;
    private JLabel destinationLabel;
    private JLabel dateTimeLabel;
    private JLabel statusLabel;
    private JPanel mainContentPanel;
    
    // Colors matching the Happy Travels theme
    private static final Color CARD_BACKGROUND = new Color(241, 215, 184); // Warm beige
    private static final Color TEXT_PRIMARY = new Color(80, 50, 30);       // Dark brown
    private static final Color TEXT_SECONDARY = new Color(100, 70, 50);    // Medium brown
    private static final Color STATUS_ACTIVE = new Color(76, 175, 80);     // Green
    private static final Color STATUS_PENDING = new Color(255, 193, 7);    // Amber
    private static final Color STATUS_CANCELLED = new Color(244, 67, 54);  // Red
    private TravellerData traveller;
    
    public AdminBookingDetailsCardPanel(BookingData booking ) {
        this.bookingData = booking;
        this.travellerData = traveller;
        initializeComponents();
        setupLayout();
        populateData();
        setupEventListeners();
    }
    
    private void initializeComponents() {
        // Set up the shadow panel properties
        setBackground(CARD_BACKGROUND);
        setRoundTopLeft(15);
        setRoundTopRight(15);
        setRoundBottomLeft(15);
        setRoundBottomRight(15);
        setShadowSize(8);
        setShadowOpacity(0.3f);
        setShadowColor(new Color(0, 0, 0, 80));
        
        // Set card dimensions
        setPreferredSize(new Dimension(480, 160));
        setMaximumSize(new Dimension(480, 160));
        setMinimumSize(new Dimension(480, 160));
        
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Initialize profile image label
        profileImageLabel = new JLabel();
        profileImageLabel.setPreferredSize(new Dimension(80, 80));
        profileImageLabel.setBackground(Color.WHITE);
        profileImageLabel.setOpaque(true);
        profileImageLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(2, 2, 2, 2)
        ));
        profileImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profileImageLabel.setVerticalAlignment(SwingConstants.CENTER);
        
        // Set default profile icon if no image is available
        try {
            ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/image/user.png"));
            if (defaultIcon.getIconWidth() > 0) {
                profileImageLabel.setIcon(defaultIcon);
            } else {
                profileImageLabel.setText("👤");
                profileImageLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
            }
        } catch (Exception e) {
            profileImageLabel.setText("👤");
            profileImageLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        }
        
        // Initialize text labels
        nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Candara", Font.BOLD, 18));
        nameLabel.setForeground(TEXT_PRIMARY);
        
        destinationLabel = new JLabel("Destination:");
        destinationLabel.setFont(new Font("Candara", Font.PLAIN, 14));
        destinationLabel.setForeground(TEXT_SECONDARY);
        
        dateTimeLabel = new JLabel("Date and Time:");
        dateTimeLabel.setFont(new Font("Candara", Font.PLAIN, 14));
        dateTimeLabel.setForeground(TEXT_SECONDARY);
        
        statusLabel = new JLabel("Active");
        statusLabel.setFont(new Font("Candara", Font.BOLD, 12));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBackground(STATUS_ACTIVE);
        statusLabel.setOpaque(true);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        mainContentPanel = new JPanel(new GridBagLayout());
        mainContentPanel.setBackground(CARD_BACKGROUND);
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Profile image on the left
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 0, 15);
        mainContentPanel.add(profileImageLabel, gbc);
        
        // Name label
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 8, 0);
        mainContentPanel.add(nameLabel, gbc);
        
        // Destination label
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 6, 0);
        mainContentPanel.add(destinationLabel, gbc);
        
        // Date and time label
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainContentPanel.add(dateTimeLabel, gbc);
        
        // Status label (bottom right)
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        mainContentPanel.add(statusLabel, gbc);
        
        add(mainContentPanel, BorderLayout.CENTER);
    }
    
    private void populateData() {
        if (bookingData != null && travellerData != null) {
            // Populate name
            String firstName = getFirstName();
            String lastName = getLastName();
            String fullName = (firstName != null ? firstName : "") + 
                             (lastName != null ? " " + lastName : "");
            nameLabel.setText("Name: " + (fullName.trim().isEmpty() ? "Customer" : fullName.trim()));
            
            // Populate destination
            String destination = getDropAddress();
            destinationLabel.setText("Destination: " + (destination != null ? destination : "Not specified"));
            
            // Populate date and time
            String departureDateTime = bookingData.getDepartureDateTime();
            dateTimeLabel.setText("Date and Time: " + (departureDateTime != null ? departureDateTime : "Not specified"));
            
            // Set status based on booking data (you can customize this logic)
            updateStatusLabel("Active"); // Default status
        }
    }
    
    private void setupEventListeners() {
        // Add hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(235, 205, 170)); // Slightly darker on hover
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(CARD_BACKGROUND);
                repaint();
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle card click - you can add popup or navigation logic here
                System.out.println("Booking card clicked for booking ID: " + 
                                 (bookingData != null ? bookingData.getBookingId() : "N/A"));
            }
        });
    }
    
    // Helper methods to safely get data (fixing the parameter issues from original code)
    private String getFirstName() {
        if (travellerData != null) {
            try {
                // Using reflection or direct field access since the original getter has wrong parameter
                return travellerData.firstName; // Direct field access if available
            } catch (Exception e) {
                return "Customer";
            }
        }
        return "Customer";
    }
    
    private String getLastName() {
        if (travellerData != null) {
            return travellerData.getLastName();
        }
        return "";
    }
    
    private String getDropAddress() {
        if (bookingData != null) {
            try {
                // Similar fix for drop address getter
                return bookingData.dropAddress; // Direct field access if available
            } catch (Exception e) {
                return "Not specified";
            }
        }
        return "Not specified";
    }
    
    // Method to update status and its appearance
    public void updateStatusLabel(String status) {
        statusLabel.setText(status);
        
        switch (status.toLowerCase()) {
            case "active":
            case "confirmed":
                statusLabel.setBackground(STATUS_ACTIVE);
                break;
            case "pending":
            case "waiting":
                statusLabel.setBackground(STATUS_PENDING);
                break;
            case "cancelled":
            case "canceled":
                statusLabel.setBackground(STATUS_CANCELLED);
                break;
            default:
                statusLabel.setBackground(STATUS_PENDING);
        }
        repaint();
    }
    
    // Method to set profile image
    public void setProfileImage(ImageIcon image) {
        if (image != null) {
            // Scale image to fit the label
            profileImageLabel.setIcon(image);
        }
    }
    
    // Getters for accessing the data
    public BookingData getBookingData() {
        return bookingData;
    }
    
    public TravellerData getTravellerData() {
        return travellerData;
    }
    
    // Method to refresh the card data
    public void refreshData() {
        populateData();
        repaint();
    }
}