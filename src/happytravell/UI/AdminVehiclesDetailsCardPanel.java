/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.UI;

import happytravell.UI.PanelShadow;
import happytravell.model.VehiclesData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Acer
 */
public class AdminVehiclesDetailsCardPanel extends PanelShadow {
    private VehiclesData vehicleData;
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel numberLabel;
    private JLabel seatsLabel;
    private JLabel colorLabel;
    private JLabel agencyLabel;
    
    public AdminVehiclesDetailsCardPanel(VehiclesData vehicle) {
        this.vehicleData = vehicle;
        initializeComponents();
        setupLayout();
        populateData();
    }
    
    private void initializeComponents() {
        setLayout(new BorderLayout());

        setBackground(new Color(239, 204, 150));
        setFocusable(true);
        setRequestFocusEnabled(true);
        setRoundTopLeft(15);
        setRoundTopRight(15);
        setRoundBottomLeft(15);
        setRoundBottomRight(15);
        setShadowSize(8);
        setShadowOpacity(0.5f);
        setShadowColor(new Color(0, 0, 0, 100));

        setPreferredSize(new Dimension(1150, 220));
        setMaximumSize(new Dimension(1150, 220));
        setMinimumSize(new Dimension(1150, 220));
        

        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(250, 160));
        imageLabel.setBorder(null);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imageLabel.setBackground(new Color(239, 204, 150));
        imageLabel.setOpaque(false);
        
        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 24));
        nameLabel.setForeground(new Color(80, 50, 30));
        
        typeLabel = new JLabel();
        typeLabel.setFont(new Font("Mongolian Baiti", Font.PLAIN, 16));
        typeLabel.setForeground(new Color(100, 70, 50));
        
        numberLabel = new JLabel();
        numberLabel.setFont(new Font("Mongolian Baiti", Font.PLAIN, 16));
        numberLabel.setForeground(new Color(100, 70, 50));
        
        seatsLabel = new JLabel();
        seatsLabel.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
        seatsLabel.setForeground(new Color(100, 70, 50));
        
        colorLabel = new JLabel();
        colorLabel.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
        colorLabel.setForeground(new Color(100, 70, 50));
        
        agencyLabel = new JLabel();
        agencyLabel.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
        agencyLabel.setForeground(new Color(100, 70, 50));
    }
    
    private void setupLayout() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(239, 204, 150));
        contentPanel.setBorder(null);
        
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(239, 204, 150));
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.setPreferredSize(new Dimension(280, 170));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(239, 204, 150));
        infoPanel.setBorder(null);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 10));

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(8));
        infoPanel.add(typeLabel);
        infoPanel.add(Box.createVerticalStrut(6));
        infoPanel.add(numberLabel);
        infoPanel.add(Box.createVerticalStrut(6));
        infoPanel.add(seatsLabel);
        infoPanel.add(Box.createVerticalStrut(6));
        infoPanel.add(colorLabel);
        infoPanel.add(Box.createVerticalStrut(6));
        infoPanel.add(agencyLabel);
        infoPanel.add(Box.createVerticalGlue());

        contentPanel.add(imagePanel, BorderLayout.WEST);
        contentPanel.add(infoPanel, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
    }
    
    private void populateData() {
        
        if (vehicleData != null) {
            
            // Set vehicle name
            nameLabel.setText(vehicleData.getVehicleName() != null ? 
                            vehicleData.getVehicleName() : "Vehicle Name");
            
            // Set vehicle type
            typeLabel.setText("Type: " + (vehicleData.getVehicleType() != null ? 
                             vehicleData.getVehicleType() : "Type not specified"));
            
            // Set vehicle number
            numberLabel.setText("Number: " + (vehicleData.getVehicleNumber() != null ? 
                               vehicleData.getVehicleNumber() : "Number not available"));
            
            // Set number of seats
            seatsLabel.setText("Seats: " + vehicleData.getNumberOfSeats());
            
            // Set vehicle color
            colorLabel.setText("Color: " + (vehicleData.getVehicleColor() != null ? 
                              vehicleData.getVehicleColor() : "Color not specified"));
            
            // Set travel agency
            agencyLabel.setText("Agency: " + (vehicleData.getTravelAgency() != null ? 
                               vehicleData.getTravelAgency() : "Agency not specified"));
            
            // Set vehicle image
            byte[] imageData = vehicleData.getVehicleImage();
            if (imageData != null && imageData.length > 0) {
                try {
                    ImageIcon originalIcon = new ImageIcon(imageData);
                    if (originalIcon.getIconWidth() > 0 && originalIcon.getIconHeight() > 0) {
                        Image scaledImage = originalIcon.getImage().getScaledInstance(
                            250, 160, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                        imageLabel.setIcon(scaledIcon);
                        imageLabel.setText("");
                    } else {
                        imageLabel.setText("Invalid Image");
                        imageLabel.setIcon(null);
                    }
                } catch (Exception e) {
                    System.err.println("Error loading vehicle image: " + e.getMessage());
                    imageLabel.setText("Image Error");
                    imageLabel.setIcon(null);
                }
            } else {
                imageLabel.setText("No Image");
                imageLabel.setIcon(null);
            }
        }
    }
    
    public VehiclesData getVehicleData() {
        return vehicleData;
    }
    
    /**
     * Update the card with new vehicle data
     * @param vehicle Updated vehicle data
     */
    public void updateVehicleData(VehiclesData vehicle) {
        this.vehicleData = vehicle;
        populateData();
        repaint();
    }
}