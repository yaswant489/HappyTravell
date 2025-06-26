/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.UI.AdminGuideDetailsCardPanel;
import happytravell.dao.GuideDao;
import happytravell.model.GuideData;
import happytravell.popup.AddGuidePopup;
import happytravell.popup.GuidePopup;
import happytravell.view.AdminGuideDetailsView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Acer
 */
public class GuideController {
    private AdminGuideDetailsView guideView;
    private GuideDao guideDao;
    private int currentAdminId;
    private List<GuideData> guides;
    
    public GuideController(AdminGuideDetailsView guideView, int adminId) {
        this.guideView = guideView;
        this.currentAdminId = adminId;
        this.guideDao = new GuideDao();
        
        initializeController();
    }
    public void open(){
    this.guideView.setVisible(true);
    } 
    public void close(){
    this.guideView.dispose();
    }
    
    
    
    
    private void initializeController() {
        setupEventListeners();
        loadGuides();
    }
    
    private void setupEventListeners() {
        // Add Guide button listener
        guideView.getAddGuideButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddGuideDialog();
            }
        });
        
        // Back button listener
        guideView.getAddVeheclesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
    
        }
  

    
    private void loadGuides() {
        try {
            guides = guideDao.getAllGuides();
            displayGuides();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(guideView, 
                "Error loading guides: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void displayGuides() {
        guideView.getGuideContainerPanel().removeAll();
        guideView.getGuideContainerPanel().setLayout(new BoxLayout(guideView.getGuideContainerPanel(), BoxLayout.Y_AXIS));
        
        if (guides == null || guides.isEmpty()) {
            // Show message when no guides available
            javax.swing.JLabel noGuidesLabel = new javax.swing.JLabel("No guides available. Click 'Add Guide' to add new guides.");
            noGuidesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            noGuidesLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.ITALIC, 14));
            noGuidesLabel.setForeground(new java.awt.Color(128, 128, 128));
            guideView.getGuideContainerPanel().add(Box.createVerticalStrut(50));
            guideView.getGuideContainerPanel().add(noGuidesLabel);
        } else {
            for (GuideData guide : guides) {
                AdminGuideDetailsCardPanel card = new AdminGuideDetailsCardPanel(guide);
                
                // Set click listener for each card
                card.setClickListener(new AdminGuideDetailsCardPanel.GuideCardClickListener() {
                    @Override
                    public void onGuideCardClicked(GuideData selectedGuide) {
                        showEditGuideDialog(selectedGuide);
                    }
                });
                
                guideView.getGuideContainerPanel().add(card);
                guideView.getGuideContainerPanel().add(Box.createVerticalStrut(10));
            }
        }
        
        guideView.getGuideContainerPanel().revalidate();
        guideView.getGuideContainerPanel().repaint();
    }
    
    private void showAddGuideDialog() {
    AddGuidePopup addForm = new AddGuidePopup((JFrame) SwingUtilities.getWindowAncestor(guideView));
    addForm.setVisible(true);
    
    // Check if the form was saved
    if (addForm.isSaved()) {
        GuideData newGuide = addForm.getGuideData();
        
        // Check if email already exists before saving
        if (guideDao.emailExists(newGuide.getEmail(), 0)) {
            JOptionPane.showMessageDialog(guideView, 
                "This email is already registered with another guide.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Save to database
        if (guideDao.addGuide(newGuide)) {
            JOptionPane.showMessageDialog(guideView, 
                "Guide added successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            loadGuides(); // Refresh the list
        } else {
            JOptionPane.showMessageDialog(guideView, 
                "Failed to add guide. Please try again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
    
    private void showEditGuideDialog(GuideData guide) {
        GuidePopup popup = new GuidePopup(guideView, guide);
        popup.setVisible(true);
        
        // Check if guide was updated or deleted
        if (popup.isUpdated() || popup.isDeleted()) {
            loadGuides(); // Refresh the guide list
        }
    }
    
    
    
    
   
    
    public void refreshGuides() {
        loadGuides();
    }
    
    public GuideData getGuideById(int guideId) {
        return guideDao.getGuideById(guideId);
    }
    
    public List<GuideData> getAllGuides() {
        return guides;
    }
    
    
    public AdminGuideDetailsView getView() {
        return guideView;
    }
    
  
}
