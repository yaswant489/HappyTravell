/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.UI;

import happytravell.controller.AdminBookingDetailsController.BookingDetailsPopup;
import happytravell.model.BookingData;
import happytravell.model.TravellerData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel destinationLabel;
    private JLabel dateTimeLabel;
    private JLabel statusLabel;
    private JLabel vehicleLabel;
    private BookingDetailsPopup popup;
    
    public AdminBookingDetailsCardPanel(BookingData booking , TravellerData traveller) {
        this.bookingData = booking;
        this.travellerData = traveller;      
        initializeComponents();
        setupLayout();
        populateData();   
    }
    
    private void initializeComponents() {
        setLayout(new BorderLayout());

        setBackground(new Color(255, 242, 227));
        setFocusable(true);
        setRequestFocusEnabled(true);
        setRoundTopLeft(15);
        setRoundTopRight(15);
        setRoundBottomLeft(15);
        setRoundBottomRight(15);
        setShadowSize(8);
        setShadowOpacity(0.5f);
        setShadowColor(new Color(0, 0, 0, 100));

        setPreferredSize(new Dimension(1150, 180));
        setMaximumSize(new Dimension(1150, 180));
        setMinimumSize(new Dimension(1150, 180));
        
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(150, 120));
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(241, 215, 184), 2));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imageLabel.setBackground(new Color(255, 255, 255));
        imageLabel.setOpaque(true);
        
        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Candara", Font.BOLD, 22));
        nameLabel.setForeground(new Color(80, 50, 30));
        
        destinationLabel = new JLabel();
        destinationLabel.setFont(new Font("Candara", Font.PLAIN, 16));
        destinationLabel.setForeground(new Color(100, 70, 50));
        
        dateTimeLabel = new JLabel();
        dateTimeLabel.setFont(new Font("Candara", Font.PLAIN, 14));
        dateTimeLabel.setForeground(new Color(100, 70, 50));
        
        statusLabel = new JLabel();
        statusLabel.setFont(new Font("Candara", Font.BOLD, 14));
        statusLabel.setOpaque(true);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        
        vehicleLabel = new JLabel();
        vehicleLabel.setFont(new Font("Candara", Font.PLAIN, 14));
        vehicleLabel.setForeground(new Color(100, 70, 50));
    }
    
    private void setupLayout() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(255, 242, 227));
        contentPanel.setBorder(null);
        
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(255, 242, 227));
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.setPreferredSize(new Dimension(180, 140));
        imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(255, 242, 227));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 20));

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(8));
        infoPanel.add(destinationLabel);
        infoPanel.add(Box.createVerticalStrut(6));
        infoPanel.add(dateTimeLabel);
        infoPanel.add(Box.createVerticalStrut(6));
        infoPanel.add(vehicleLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(statusLabel);
        infoPanel.add(Box.createVerticalGlue());

        contentPanel.add(imagePanel, BorderLayout.WEST);
        contentPanel.add(infoPanel, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
    }
    
    private void populateData() {
        if (bookingData != null) {
            // Set customer name
            nameLabel.setText(travellerData.getFirstName(resultSet.getString("first_name")) != null ? 
                    
                            travellerData.getFirstName(resultSet.getString("first_name")) : "Customer Name");
            
            // Set destination
            destinationLabel.setText("Destination: " + (bookingData.getDropAddress(resultSet.getString("drop_address")) != null ? 
                               bookingData.getDropAddress(resultSet.getString("drop_address")) : "Not specified"));
            
            // Set date and time
            String dateTime = "";
            if (bookingData.getDepartureDateTime() != null) {
                dateTime = "DateTime: " + bookingData.getDepartureDateTime();
                
            }
            dateTimeLabel.setText(dateTime.isEmpty() ? "Date/Time not specified" : dateTime);
            
            // Set vehicle info
            vehicleLabel.setText("Vehicle: " + (bookingData.getVehicleNumber() != null ? 
                             bookingData.getVehicleNumber() : "Not assigned"));
            
        }
    }
    
    
}