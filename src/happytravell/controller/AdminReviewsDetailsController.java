/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.AdminReviewsDetailsView;
import happytravell.dao.ReviewDao;
import happytravell.model.ReviewData;
import happytravell.view.AdmindashboardView;
import happytravell.controller.AdminDashboardController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Acer
 */
public class AdminReviewsDetailsController {
    private AdminReviewsDetailsView reviewView;
    private ReviewDao reviewDao;
    private JPanel reviewsContainer;
    
    public AdminReviewsDetailsController(AdminReviewsDetailsView adminReviewsDetailsView) {
        this.reviewView = adminReviewsDetailsView;
        this.reviewDao = new ReviewDao();
        this.reviewsContainer = (JPanel) reviewView.getScrollPane().getViewport().getView();
        
        setupNavigation();
        setupBackButton();
        loadReviews();
    }
    
    private void setupNavigation() {
        // Setup navigation listeners
        reviewView.DashboardNavigation(new DashboardNav());
        reviewView.RouteDetailsNavigation(new RouteDetailsNav());
        reviewView.BusTicketsNavigation(new BusTicketsNav());
        reviewView.VehiclesDetailsNavigation(new VehiclesDetailsNav());
        reviewView.ProfileNavigation(new ProfileNav());
        reviewView.LogOutNavigation(new LogOutNav());
    }
    
    private void setupBackButton() {
        reviewView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdmindashboardView dashboardView = new AdmindashboardView();
                AdminDashboardController dashboardController = new AdminDashboardController(dashboardView, 1);
                dashboardController.open();
                close();
            }
        });
    }
    
    private void loadReviews() {
        // Clear existing content
        reviewsContainer.removeAll();
        reviewsContainer.setLayout(new BoxLayout(reviewsContainer, BoxLayout.Y_AXIS));
        reviewsContainer.setBackground(new Color(255, 242, 227));
        
        // Get all reviews from database
        List<ReviewData> reviews = reviewDao.getAllReviews();
        
        if (reviews.isEmpty()) {
            // Show no reviews message
            JLabel noReviewsLabel = new JLabel("No reviews found");
            noReviewsLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            noReviewsLabel.setForeground(new Color(100, 100, 100));
            noReviewsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            noReviewsLabel.setBorder(new EmptyBorder(50, 0, 0, 0));
            reviewsContainer.add(noReviewsLabel);
        } else {
            // Add each review as a card
            for (ReviewData review : reviews) {
                reviewsContainer.add(createReviewCard(review));
                reviewsContainer.add(Box.createVerticalStrut(15));
            }
        }
        
        // Refresh the scroll pane
        reviewsContainer.revalidate();
        reviewsContainer.repaint();
    }
    
    private JPanel createReviewCard(ReviewData review) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(10, 5));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        card.setMaximumSize(new Dimension(650, 200));
        card.setPreferredSize(new Dimension(650, 200));
        
        // Header panel with traveller info and rating
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        
        // Traveller info
        JPanel travellerInfoPanel = new JPanel(new BorderLayout(5, 2));
        travellerInfoPanel.setBackground(Color.WHITE);
        
        JLabel nameLabel = new JLabel(review.getTravellerName());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setForeground(new Color(50, 50, 50));
        
        JLabel emailLabel = new JLabel(review.getTravellerEmail());
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        emailLabel.setForeground(new Color(100, 100, 100));
        
        travellerInfoPanel.add(nameLabel, BorderLayout.NORTH);
        travellerInfoPanel.add(emailLabel, BorderLayout.SOUTH);
        
        // Rating stars
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        ratingPanel.setBackground(Color.WHITE);
        
        int rating = review.getRating();
        for (int i = 0; i < 5; i++) {
            JLabel starLabel = new JLabel(i < rating ? "★" : "☆");
            starLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 20));
            starLabel.setForeground(i < rating ? new Color(255, 193, 7) : new Color(200, 200, 200));
            ratingPanel.add(starLabel);
        }
        
        JLabel ratingText = new JLabel(" (" + rating + "/5)");
        ratingText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        ratingText.setForeground(new Color(100, 100, 100));
        ratingPanel.add(ratingText);
        
        headerPanel.add(travellerInfoPanel, BorderLayout.WEST);
        headerPanel.add(ratingPanel, BorderLayout.EAST);
        
        // Review content
        JPanel contentPanel = new JPanel(new BorderLayout(10, 5));
        contentPanel.setBackground(Color.WHITE);
        
        // Review text
        JTextArea reviewTextArea = new JTextArea(review.getReviewText());
        reviewTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        reviewTextArea.setLineWrap(true);
        reviewTextArea.setWrapStyleWord(true);
        reviewTextArea.setEditable(false);
        reviewTextArea.setBackground(new Color(248, 248, 248));
        reviewTextArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JScrollPane textScrollPane = new JScrollPane(reviewTextArea);
        textScrollPane.setPreferredSize(new Dimension(400, 80));
        textScrollPane.setBorder(null);
        
        // Additional details
        JPanel detailsPanel = new JPanel(new GridLayout(3, 1, 5, 2));
        detailsPanel.setBackground(Color.WHITE);
        
        if (review.getBookingReference() != null && !review.getBookingReference().isEmpty()) {
            JLabel bookingLabel = new JLabel("Booking: " + review.getBookingReference());
            bookingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            bookingLabel.setForeground(new Color(100, 100, 100));
            detailsPanel.add(bookingLabel);
        }
        
        if (review.getVehicleType() != null && !review.getVehicleType().isEmpty()) {
            JLabel vehicleLabel = new JLabel("Vehicle: " + review.getVehicleType());
            vehicleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            vehicleLabel.setForeground(new Color(100, 100, 100));
            detailsPanel.add(vehicleLabel);
        }
        
        if (review.getDriverName() != null && !review.getDriverName().isEmpty()) {
            JLabel driverLabel = new JLabel("Driver: " + review.getDriverName());
            driverLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            driverLabel.setForeground(new Color(100, 100, 100));
            detailsPanel.add(driverLabel);
        }
        
        contentPanel.add(textScrollPane, BorderLayout.CENTER);
        contentPanel.add(detailsPanel, BorderLayout.EAST);
        
        // Status and actions panel
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(Color.WHITE);
        
        JLabel statusLabel = new JLabel("Status: " + review.getStatus());
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        statusLabel.setForeground(review.getStatus().equals("APPROVED") ? 
            new Color(76, 175, 80) : 
            review.getStatus().equals("REJECTED") ? 
            new Color(244, 67, 54) : 
            new Color(255, 152, 0));
        
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        actionPanel.setBackground(Color.WHITE);
        
        if (review.getStatus().equals("PENDING")) {
            JButton approveButton = new JButton("Approve");
            approveButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
            approveButton.setBackground(new Color(76, 175, 80));
            approveButton.setForeground(Color.WHITE);
            approveButton.setFocusPainted(false);
            approveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    approveReview(review.getReviewId());
                }
            });
            
            JButton rejectButton = new JButton("Reject");
            rejectButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
            rejectButton.setBackground(new Color(244, 67, 54));
            rejectButton.setForeground(Color.WHITE);
            rejectButton.setFocusPainted(false);
            rejectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rejectReview(review.getReviewId());
                }
            });
            
            actionPanel.add(approveButton);
            actionPanel.add(rejectButton);
        }
        
        statusPanel.add(statusLabel, BorderLayout.WEST);
        statusPanel.add(actionPanel, BorderLayout.EAST);
        
        // Add all panels to card
        card.add(headerPanel, BorderLayout.NORTH);
        card.add(contentPanel, BorderLayout.CENTER);
        card.add(statusPanel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private void approveReview(int reviewId) {
        if (reviewDao.updateReviewStatus(reviewId, "APPROVED")) {
            JOptionPane.showMessageDialog(reviewView, 
                "Review approved successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            loadReviews(); // Refresh the display
        } else {
            JOptionPane.showMessageDialog(reviewView, 
                "Failed to approve review. Please try again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void rejectReview(int reviewId) {
        if (reviewDao.updateReviewStatus(reviewId, "REJECTED")) {
            JOptionPane.showMessageDialog(reviewView, 
                "Review rejected successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            loadReviews(); // Refresh the display
        } else {
            JOptionPane.showMessageDialog(reviewView, 
                "Failed to reject review. Please try again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void open() {
        this.reviewView.setVisible(true);
    }
    
    public void close() {
        this.reviewView.dispose();
    }
    
    // Navigation classes
    class DashboardNav implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            AdmindashboardView dashboardView = new AdmindashboardView();
            AdminDashboardController dashboardController = new AdminDashboardController(dashboardView,1);
            dashboardController.open();
            close();
        }
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    }
    
    class RouteDetailsNav implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Add route details navigation
        }
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    }
    
    class BusTicketsNav implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Add bus tickets navigation
        }
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    }
    
    class VehiclesDetailsNav implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Add vehicles details navigation
        }
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    }
    
    class ProfileNav implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Add profile navigation
        }
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    }
    
    class LogOutNav implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Add logout navigation
        }
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    }
}