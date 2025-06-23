/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

//import happytravell.view.PlacesView;



import happytravell.dao.PlaceDao;
import happytravell.model.PlaceData;
import happytravell.view.AdminPlacesView;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 *
 * @author Acer
 */
public class AdminPlacesController {
     private AdminPlacesView placesView;
    private PlaceDao placeDao;
    private int currentAdminId;
    
    public AdminPlacesController(AdminPlacesView view, int adminId) {
        this.placesView = view;
        this.placeDao = new PlaceDao();
        this.currentAdminId = adminId;
        initializeEventHandlers();
    }
    public void open(){
    this.placesView.setVisible(true);
    } 

    public void close(){
    this.placesView.dispose();
    } 
    
    /**
     * Initialize event handlers for the places view
     */
    private void initializeEventHandlers() {
        placesView.getAddPlacesButton().addActionListener(e -> showAddPlaceDialog());
    }    
        
    /**
     * Show the Add Places popup dialog
     */
    public void showAddPlaceDialog() {
        JDialog addPlaceDialog = createAddPlaceDialog();
        addPlaceDialog.setVisible(true);
    }
    
    /**
     * Create the Add Places dialog with form components
     * @return JDialog for adding places
     */
    private JDialog createAddPlaceDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add Places");
        dialog.setModal(true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(placesView);
        dialog.setResizable(false);
        
        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new java.awt.Color(252,186,107));
        // Photo section
        JPanel photoPanel = new JPanel();
        photoPanel.setLayout(new BoxLayout(photoPanel, BoxLayout.Y_AXIS));
        photoPanel.setBorder(BorderFactory.createTitledBorder("Photo"));
        
        JButton addPhotoButton = new JButton("Add Photo");
        addPhotoButton.setPreferredSize(new java.awt.Dimension(200,100));
        addPhotoButton.setBackground(java.awt.Color.LIGHT_GRAY);
        
        JLabel selectedPhotoLabel = new JLabel("No photo selected");
        selectedPhotoLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        
        photoPanel.add(addPhotoButton);
//        photoPanel.add(Box.createVerticalStrut(10));
//        photoPanel.add(selectedPhotoLabel);
        
        // Name section
        JPanel namePanel = new JPanel(new GridBagLayout());
        JLabel nameLabel = new JLabel("Name:");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx =-5; gbc.gridy =2 ;
        namePanel.setBackground(new java.awt.Color(252,186,107));
        nameLabel.setFont(nameLabel.getFont().deriveFont(12f));
        JTextField nameField = new JTextField();
        namePanel.add(nameLabel,gbc);
        
        nameField.setPreferredSize(new java.awt.Dimension(200,20));
        
        namePanel.add(nameLabel);
        namePanel.add(Box.createVerticalStrut(5));
        namePanel.add(nameField);
        
        // Description section
        JPanel descPanel = new JPanel(new GridBagLayout());
        JLabel descLabel = new JLabel("Description:");
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.WEST;
        gbc2.gridx =-5; gbc2.gridy =2 ;
        descPanel.setBackground(new java.awt.Color(252,186,107));
        descLabel.setFont(descLabel.getFont().deriveFont(12f));
        
        JTextArea descArea = new JTextArea(3, 20);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        JScrollPane descScrollPane = new JScrollPane(descArea);
      
        
//        gbc.gridx = 1; gbc.fill = GridBagConstraints.BOTH;
//        JTextArea descArea = new JTextArea(3, 20);
//        descArea.setFont(new Font("Arial", Font.PLAIN, 14));
//        descArea.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(new Color(227, 143, 11), 2),
//            BorderFactory.createEmptyBorder(5, 5, 5, 5)
//      
        
        descPanel.add(descLabel);
        descPanel.add(Box.createVerticalStrut(5));
        descPanel.add(descScrollPane);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new java.awt.Color(252,186,107));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        
        JButton addButton = new JButton("Add");
        addButton.setBackground(new java.awt.Color(173,98,44)); // Orange color from UI
        addButton.setForeground(java.awt.Color.BLACK);
        addButton.setPreferredSize(new java.awt.Dimension(80, 35));
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new java.awt.Dimension(80, 35));
        cancelButton.setBackground(new java.awt.Color(173,98,44));
        cancelButton.setForeground(java.awt.Color.BLACK);
        
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(5));
        buttonPanel.add(addButton);
        
        // Add components to main panel
        mainPanel.add(photoPanel);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(namePanel);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(descPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(buttonPanel);
        
        dialog.add(mainPanel);
        
        // Event handlers for dialog
        setupDialogEventHandlers(dialog, addPhotoButton, selectedPhotoLabel, 
                                nameField, descArea, addButton, cancelButton);
        
        return dialog;
    }
    
    /**
     * Setup event handlers for the add place dialog
     */
    private void setupDialogEventHandlers(JDialog dialog, JButton addPhotoButton, 
                                        JLabel selectedPhotoLabel, JTextField nameField, 
                                        JTextArea descArea, JButton addButton, 
                                        JButton cancelButton) {
        
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String description = descArea.getText().trim();
            
            if (name.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, 
                    "Please fill in all required fields.", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // For now, we'll add the place without an image
            PlaceData newPlace = new PlaceData(0, name, description, null);
            
            if (placeDao.addPlace(newPlace)) {
                JOptionPane.showMessageDialog(dialog, "Place added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Error adding place.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
    }    
        // Add button handler
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String name = nameField.getText().trim();
//                String description = descArea.getText().trim();
//                
//                if (validateInput(name, description)) {
//                    Place newPlace = new Place(name, description, selectedPhotoPath[0]);
//                    if (addPlace(newPlace)) {
//                        JOptionPane.showMessageDialog(dialog, 
//                            "Place added successfully!", 
//                            "Success", 
//                            JOptionPane.INFORMATION_MESSAGE);
//                        dialog.dispose();
//                        refreshPlacesView();
//                    } else {
//                        JOptionPane.showMessageDialog(dialog, 
//                            "Error adding place. Please try again.", 
//                            "Error", 
//                            JOptionPane.ERROR_MESSAGE);
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(dialog, 
//                        "Please fill in all required fields (Name and Description).", 
//                        "Validation Error", 
//                        JOptionPane.WARNING_MESSAGE);
//                }
//            }
//        });
        
        // Cancel button handler
//        cancelButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dialog.dispose();
//            }
//        });
//        
//        // Close button (X) handler
//        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//    }
//    
//    /**
//     * Validate input fields
//     * @param name Place name
//     * @param description Place description
//     * @return true if valid, false otherwise
//     */
//    private boolean validateInput(String name, String description) {
//        return !name.isEmpty() && !description.isEmpty();
//    }
//    
//    /**
//     * Add a new place using the place DAO
//     * @param place Place to add
//     * @return true if successful, false otherwise
//     */
//    private boolean addPlace(Place place) {
//        try {
//            return placeDao.addPlace(place);
//        } catch (Exception e) {
//            System.err.println("Error adding place: " + e.getMessage());
//            return false;
//        }
//    }
//    
//    /**
//     * Refresh the places view to show updated data
//     */
//    private void refreshPlacesView() {
//        placesView.refreshPlacesList();
//    }
//    
//    /**
//     * Search for places based on query
//     * @param query Search query
//     */
//    private void searchPlaces(String query) {
//        if (query.trim().isEmpty()) {
//            placesView.showAllPlaces();
//        } else {
//            placesView.filterPlaces(query);
//        }
//    }
//    
//    /**
//     * Get the places view
//     * @return PlacesView instance
//     */
//    public PlacesView getPlacesView() {
//        return placesView;
//    }
//    
//    /**
//     * Set the places view
//     * @param placesView PlacesView instance
//     */
//    public void setPlacesView(PlacesView placesView) {
//        this.placesView = placesView;
//    }

    
}           
 
