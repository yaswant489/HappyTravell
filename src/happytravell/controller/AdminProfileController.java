/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.dao.AdminDao;
import happytravell.model.AdminData;
import happytravell.view.AdminBookingDetailsView;
import happytravell.view.AdminBusTicketsView;
import happytravell.view.AdminProfileView;
import happytravell.view.AdminRouteDetailsView;
import happytravell.view.AdminVehiclesDetailsView;
import happytravell.view.AdmindashboardView;
import happytravell.view.LoginPageView;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class AdminProfileController {
    private AdminProfileView profileView = new AdminProfileView();
    private int currentAdminId;
    private AdminDao adminDao = new AdminDao();
    
    private String firstName = "";
    private String lastName = "";
    private String username = "";
    private String email = "";
    private String phoneNumber = "";
    private String address = "";
    
    
    public AdminProfileController(AdminProfileView adminProfileView, int adminId) {
        this.profileView = adminProfileView;
        this.currentAdminId = adminId;
        this.profileView.DashboardNavigation(new AdminProfileController.DashboardNav(adminProfileView.getDashboardlabel()));
        this.profileView.BookingDetailsNavigation(new AdminProfileController.BookingDetailsNav(adminProfileView.getBookingDetailslabel()));
        this.profileView.BusTicketsNavigation(new AdminProfileController.BusTicketsNav(adminProfileView.getBusTicketslabel()));
        this.profileView.RouteDetailsNavigation(new AdminProfileController.RouteDetailsNav(adminProfileView.getRouteDetailslabel()));
        this.profileView.VehiclesDetailsNavigation(new AdminProfileController.VehiclesDetailsNav(adminProfileView.getVehiclesDetailslabel()));
        this.profileView.LogOutNavigation(new AdminProfileController.LogOutNav(adminProfileView.getLogOutlabel()));
        
        this.profileView.setUpdateProfileButtonAction(e -> handleUpdateProfile());
        
        loadAdminData();
        loadExistingProfilePicture();     
    }
    private void loadAdminData() {
        if (currentAdminId != -1) {
            
            AdminData admin = adminDao.getAdminById(currentAdminId);
            if (admin != null) {
                firstName = admin.getFirstName() != null ? admin.getFirstName() : "";
                lastName = admin.getLastName() != null ? admin.getLastName() : "";
                username = admin.getUsername() != null ? admin.getUsername() : "";
                phoneNumber = admin.getPhoneNumber() != null ? admin.getPhoneNumber() : "";
                email = admin.getEmail() != null ? admin.getEmail() : "";
                address = admin.getAddress() != null ? admin.getAddress() : "";
                
                profileView.getFirstNameTextField().setText(firstName);
                profileView.getLastNameTextField().setText(lastName);
                profileView.getUsernameTextField().setText(username);
                profileView.getPhoneNumberTextField().setText(phoneNumber);
                profileView.getEmailTextField().setText(email);
                profileView.getAddressTextField().setText(address);
            } else {
                JOptionPane.showMessageDialog(profileView, 
                    "Unable to load admin data. Please try logging in again.", 
                    "Error", 
                    JOptionPane.WARNING_MESSAGE);
            }
       }
    }
    
    private void handleUpdateProfile() {
        if (currentAdminId == -1) {
            JOptionPane.showMessageDialog(profileView, 
                "Error: Admin ID not set. Please login again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String changedFirstName = profileView.getFirstNameTextField().getText().trim();
        String changedLastName = profileView.getLastNameTextField().getText().trim();
        String changedUsername = profileView.getUsernameTextField().getText().trim();
        String changedPhoneNumber = profileView.getPhoneNumberTextField().getText().trim();
        String changedEmail = profileView.getEmailTextField().getText().trim();
        String changedAddress = profileView.getAddressTextField().getText().trim();
        
        
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || address.isEmpty())  {
            JOptionPane.showMessageDialog(profileView, 
                "Please fill in all fields", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean hasChanges = !changedFirstName.equals(firstName) ||
                           !changedLastName.equals(lastName) ||
                           !changedUsername.equals(username) ||
                           !changedPhoneNumber.equals(phoneNumber) ||
                           !changedEmail.equals(email) ||
                           !changedAddress.equals(address);
        
        if (!hasChanges) {
            JOptionPane.showMessageDialog(profileView, 
                "No changes made to update.", 
                "Message", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        
        AdminDao adminDao = new AdminDao();
        boolean success = adminDao.updateAdminProfile(currentAdminId, 
                                            changedFirstName, 
                                            changedLastName, 
                                            changedUsername, 
                                            changedPhoneNumber, 
                                            changedEmail, 
                                            changedAddress);

        if (success) {
            firstName = changedFirstName;
            lastName = changedLastName;
            username = changedUsername;
            phoneNumber = changedPhoneNumber;
            email = changedEmail;
            address = changedAddress;

            JOptionPane.showMessageDialog(profileView, 
                "Profile updated successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(profileView, 
                "Failed to update profile. Please try again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            }
    }

    public void setCurrentOwnerId(int ownerId) {
        this.currentAdminId = ownerId;
        loadExistingProfilePicture();
    }
    
    private void loadExistingProfilePicture() {
        if (currentAdminId != -1) {
            try {
                byte[] existingProfilePicture = adminDao.getProfilePicture(currentAdminId);
                if (existingProfilePicture != null && existingProfilePicture.length > 0) {
                    displayProfileImageInView(existingProfilePicture);
                } else {
                    profileView.setDefaultProfileImage();
                }
            } catch (Exception e) {
            }
        }
    }
    
    
    private void displayProfileImageInView(byte[] imageData) {
        try {
            if (imageData != null && imageData.length > 0) {
                ImageIcon originalIcon = new ImageIcon(imageData);
                
                int labelWidth = 130;
                int labelHeight = 130;
                
                Image scaledImage = originalIcon.getImage().getScaledInstance(
                    labelWidth, labelHeight, Image.SCALE_SMOOTH);
                
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                
                profileView.displayProfileImage(imageData);
               
            }
        } catch (Exception e) {
        }
    }
    
   
    
    public void open(){
    this.profileView.setVisible(true);
    } 
    public void close(){
    this.profileView.dispose();
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
            dashboardLabel.setForeground(Color.WHITE);
            dashboardLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            dashboardLabel.setForeground(Color.BLACK);
            dashboardLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    //    Booking Details Navigation
    class BookingDetailsNav implements MouseListener{
        
        private JLabel bookingDetailsLabel;
        
        public BookingDetailsNav(JLabel label){
            this.bookingDetailsLabel = label;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminBookingDetailsView adminBookingDetailsView = new AdminBookingDetailsView();
            AdminBookingDetailsController AdminBookingDetails= new AdminBookingDetailsController(adminBookingDetailsView);
            AdminBookingDetails.open();
            close();
        }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            bookingDetailsLabel.setForeground(Color.RED);
            bookingDetailsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            bookingDetailsLabel.setForeground(Color.BLACK);
            bookingDetailsLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
//  Route Details Navigation
    class RouteDetailsNav implements MouseListener{
        
        private JLabel routeDetailsLabel;      
        public RouteDetailsNav(JLabel label){
            this.routeDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminRouteDetailsView adminRouteDetailsView = new AdminRouteDetailsView();
            AdminRouteDetailsController AdminRouteDetails= new AdminRouteDetailsController(adminRouteDetailsView);
            AdminRouteDetails.open();
            close();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            routeDetailsLabel.setForeground(Color.WHITE);
            routeDetailsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            routeDetailsLabel.setForeground(Color.BLACK);
            routeDetailsLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
//  Bus Ticket Navigation  
    class BusTicketsNav implements MouseListener{
        
        private JLabel busTicketsLabel;
        public BusTicketsNav(JLabel label){
            this.busTicketsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminBusTicketsView adminBusTicketsView = new AdminBusTicketsView();
            AdminBusTicketsController AdminBusTickets= new AdminBusTicketsController(adminBusTicketsView);
            AdminBusTickets.open();
            close();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            busTicketsLabel.setForeground(Color.WHITE);
            busTicketsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            busTicketsLabel.setForeground(Color.BLACK);
            busTicketsLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
//  Vehicles Details Navigation
    class VehiclesDetailsNav implements MouseListener{
        
        private JLabel vehiclesDetailsLabel;
        public VehiclesDetailsNav(JLabel label){
            this.vehiclesDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminVehiclesDetailsView adminVehiclesDetailsView = new AdminVehiclesDetailsView();
            AdminVehiclesDetailsController  AdminVehiclesDetails= new  AdminVehiclesDetailsController(adminVehiclesDetailsView);
            AdminVehiclesDetails.open();
            close();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            vehiclesDetailsLabel.setForeground(Color.WHITE);
            vehiclesDetailsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            vehiclesDetailsLabel.setForeground(Color.BLACK);
            vehiclesDetailsLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    

    
//    LogOut Navigation
    class LogOutNav implements MouseListener{
        
        private JLabel logOutLabel;
        public LogOutNav(JLabel label){
            this.logOutLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                profileView.dispose();

                LoginPageView loginView = new LoginPageView();
                LoginController loginController = new LoginController(loginView);
                loginController.open();
            }
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            logOutLabel.setForeground(Color.WHITE);
            logOutLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            logOutLabel.setForeground(Color.BLACK);
            logOutLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    

    
    
}
