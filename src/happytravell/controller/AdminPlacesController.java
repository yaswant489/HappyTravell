/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

//import happytravell.view.PlacesView;



import happytravell.dao.PlaceDao;
import happytravell.model.PlaceData;
import happytravell.view.AdminBookingDetailsView;
import happytravell.view.AdminBusTicketsView;
import happytravell.view.AdminPlacesView;
import happytravell.view.AdminProfileView;
import happytravell.view.AdminRouteDetailsView;
import happytravell.view.AdminVehiclesDetailsView;
import happytravell.view.AdmindashboardView;
import happytravell.view.LoginPageView;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
        this.placesView.BookingDetailsNavigation(new AdminPlacesController.BookingDetailsNav(view.getBookingDetailslabel()));
        this.placesView.BusTicketsNavigation(new AdminPlacesController.BusTicketsNav(view.getBusTicketslabel()));
        this.placesView.RouteDetailsNavigation(new AdminPlacesController.RouteDetailsNav(view.getRouteDetailslabel()));
        this.placesView.VehiclesDetailsNavigation(new AdminPlacesController.VehiclesDetailsNav(view.getVehiclesDetailslabel()));
        this.placesView.ProfileNavigation(new AdminPlacesController.ProfileNav(view.getProfilelabel()));
        this.placesView.LogOutNavigation(new AdminPlacesController.LogOutNav(view.getLogOutlabel()));
        this.placesView.DashboardNavigation(new AdminPlacesController.DashboardNav(view.getDashboardlabel()));
        
    }
    public void open(){
    this.placesView.setVisible(true);
    } 

    public void close(){
    this.placesView.dispose();
    } 
    
    //    Dashboard Navigation
    class DashboardNav implements MouseListener{
        
        private JLabel dashboardLabel;
        
        public DashboardNav(JLabel label){
            this.dashboardLabel = label;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            AdmindashboardView adminDashboardView = new AdmindashboardView();
            AdminDashboardController adminController = new AdminDashboardController(adminDashboardView, currentAdminId);
            adminController.open();
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
            AdminBookingDetailsController AdminBookingDetails= new AdminBookingDetailsController(adminBookingDetailsView, currentAdminId);
            AdminBookingDetails.open();
            close();
        }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

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
    
//  Route Details Navigation
    class RouteDetailsNav implements MouseListener{
        
        private JLabel routeDetailsLabel;      
        public RouteDetailsNav(JLabel label){
            this.routeDetailsLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminRouteDetailsView adminRouteDetailsView = new AdminRouteDetailsView();
            AdminRouteDetailsController AdminRouteDetails= new AdminRouteDetailsController(adminRouteDetailsView , currentAdminId);
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
            AdminBusTicketsController AdminBusTickets= new AdminBusTicketsController(adminBusTicketsView, currentAdminId);
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
            AdminVehiclesDetailsController  AdminVehiclesDetails= new  AdminVehiclesDetailsController(adminVehiclesDetailsView, currentAdminId);
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
    
//    Profile Navigation
    class ProfileNav implements MouseListener{
        
        private JLabel profileLabel;
        public ProfileNav(JLabel label){
            this.profileLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            AdminProfileView adminProfileView = new AdminProfileView();
            AdminProfileController  AdminProfile= new  AdminProfileController(adminProfileView , currentAdminId);
            AdminProfile.open();
            close();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            profileLabel.setForeground(Color.WHITE);
            profileLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            profileLabel.setForeground(Color.BLACK);
            profileLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
                placesView.dispose();

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
 
