/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.dao.TravellerDao;
import happytravell.view.AdminBookingDetailsView;
import happytravell.view.AdminBusTicketsView;
import happytravell.view.AdminProfileView;
import happytravell.view.AdminRouteDetailsView;
import happytravell.view.AdminVehiclesDetailsView;
import happytravell.view.AdmindashboardView;
import happytravell.view.LoginPageView;
import happytravell.view.TravellerProfileAccountManagementView;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class TravellerProfileAccountManagementController {
    private TravellerProfileAccountManagementView accountView;
    private int currentAdminId;
    private TravellerDao adminDao = new TravellerDao();
    
    public TravellerProfileAccountManagementController(TravellerProfileAccountManagementView accountView, int TravellerId) {
        this.accountView = accountView;
        this.currentAdminId = TravellerId;
        
        // Set up navigation listeners
        this.accountView.DashboardNavigation(new DashboardNav(accountView.getDashboardlabel()));
        this.accountView.BookingNavigation(new BookingDetailsNav(accountView.getBookinglabel()));
        this.accountView.BusTicketsNavigation(new BusTicketsNav(accountView.getBusTicketslabel()));
        this.accountView.RouteNavigation(new RouteDetailsNav(accountView.getRoutelabel()));
        this.accountView.VehiclesDetailsNavigation(new VehiclesDetailsNav(accountView.getVehiclesDetailslabel()));
        this.accountView.LogOutNavigation(new LogOutNav(accountView.getLogOutlabel()));
        
        // Set up button actions
        this.accountView.setUpdateProfileButtonAction(new BackButtonListener());
        
    }
    
    public void open() {
        this.accountView.setVisible(true);
    }
    
    public void close() {
        this.accountView.dispose();
    }
    
    // Button listeners
    class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminProfileView profileView = new AdminProfileView();
            AdminProfileController profileController = new AdminProfileController(profileView, currentAdminId);
            profileController.open();
            close();
        }
    }
    
    
    
    // Navigation classes (similar to AdminProfileController)
    class DashboardNav implements MouseListener {
        private JLabel dashboardLabel;
        
        public DashboardNav(JLabel label) {
            this.dashboardLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdmindashboardView admindashboardView = new AdmindashboardView();
            AdminDashboardController adminDashboard = new AdminDashboardController(admindashboardView, currentAdminId);
            adminDashboard.open();
            close();
        }
        
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        
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
    
    class BookingDetailsNav implements MouseListener {
        private JLabel bookingDetailsLabel;
        
        public BookingDetailsNav(JLabel label) {
            this.bookingDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminBookingDetailsView adminBookingDetailsView = new AdminBookingDetailsView();
            AdminBookingDetailsController adminBookingDetails = new AdminBookingDetailsController(adminBookingDetailsView, currentAdminId);
            adminBookingDetails.open();
            close();
        }
        
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        
        @Override
        public void mouseEntered(MouseEvent e) {
            bookingDetailsLabel.setForeground(Color.WHITE);
            bookingDetailsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            bookingDetailsLabel.setForeground(Color.BLACK);
            bookingDetailsLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
    
    class RouteDetailsNav implements MouseListener {
        private JLabel routeDetailsLabel;
        
        public RouteDetailsNav(JLabel label) {
            this.routeDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminRouteDetailsView adminRouteDetailsView = new AdminRouteDetailsView();
            AdminRouteDetailsController adminRouteDetails = new AdminRouteDetailsController(adminRouteDetailsView, currentAdminId);
            adminRouteDetails.open();
            close();
        }
        
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        
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
    
    class BusTicketsNav implements MouseListener {
        private JLabel busTicketsLabel;
        
        public BusTicketsNav(JLabel label) {
            this.busTicketsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminBusTicketsView adminBusTicketsView = new AdminBusTicketsView();
            AdminBusTicketsController adminBusTickets = new AdminBusTicketsController(adminBusTicketsView, currentAdminId);
            adminBusTickets.open();
            close();
        }
        
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        
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
    
    class VehiclesDetailsNav implements MouseListener {
        private JLabel vehiclesDetailsLabel;
        
        public VehiclesDetailsNav(JLabel label) {
            this.vehiclesDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminVehiclesDetailsView adminVehiclesDetailsView = new AdminVehiclesDetailsView();
            AdminVehiclesDetailsController adminVehiclesDetails = new AdminVehiclesDetailsController(adminVehiclesDetailsView, currentAdminId);
            adminVehiclesDetails.open();
            close();
        }
        
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        
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
    
    class LogOutNav implements MouseListener {
        private JLabel logOutLabel;
        
        public LogOutNav(JLabel label) {
            this.logOutLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            int response = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to logout?", "Logout",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (response == JOptionPane.YES_OPTION) {
                accountView.dispose();
                LoginPageView loginView = new LoginPageView();
                LoginController loginController = new LoginController(loginView);
                loginController.open();
            }
        }
        
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        
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
