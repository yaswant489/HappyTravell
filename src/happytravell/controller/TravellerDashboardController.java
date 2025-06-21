/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.model.TravellerData;
import happytravell.view.AdminProfileView;
import happytravell.view.LoginPageView;
import happytravell.view.TravellerBookingView;
import happytravell.view.TravellerProfileView;
import happytravell.view.TravellerdashboardView;
import happytravell.view.TravellerVechilesDetailsView;
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
public class TravellerDashboardController {

    private TravellerdashboardView travellerdashboardView = new TravellerdashboardView();
    private TravellerData currentTraveller;
    
    public TravellerDashboardController(TravellerdashboardView travellerdashboardView, TravellerData traveller){
        this.travellerdashboardView = travellerdashboardView;
        this.currentTraveller = traveller;
       
        this.travellerdashboardView.BookingNavigation(new TravellerDashboardController.BookingNav(travellerdashboardView.getBookinglabel()));       
        this.travellerdashboardView.ProfileNavigation(new TravellerDashboardController.ProfileNav(travellerdashboardView.getProfilelabel()));
        this.travellerdashboardView.LogOutNavigation(new TravellerDashboardController.LogOutNav(travellerdashboardView.getLogOutlabel()));

    }
    
    public void open(){
        this.travellerdashboardView.setVisible(true);
    } 
    public void close(){
        this.travellerdashboardView.dispose();
    }
    
    class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginController(new LoginPageView()).open();
            close();
        }
    }
    
    class BookingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            javax.swing.JOptionPane.showMessageDialog(travellerdashboardView, "Booking functionality not yet implemented!");
        }
    }
    
    class VehiclesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new TravellerVechilesDetailsView().setVisible(true);
            close();
        }
    }

    class DashboardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Removed DashboardHomePanel reference - show default dashboard content
            javax.swing.JOptionPane.showMessageDialog(travellerdashboardView, "Dashboard home view not available!");
        }
    }

    class RouteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            javax.swing.JOptionPane.showMessageDialog(travellerdashboardView, "Route functionality not yet implemented!");
        }
    }

    class BusTicketsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            javax.swing.JOptionPane.showMessageDialog(travellerdashboardView, "Bus Tickets functionality not yet implemented!");
        }
    }


    //    Booking Details Navigation
    class BookingNav implements MouseListener{
        
        private JLabel bookingDetailsLabel;
        
        public BookingNav(JLabel label){
            this.bookingDetailsLabel = label;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            TravellerBookingView travellerBookingDetailsView = new TravellerBookingView();
            TravellerBookingController TravellerBooking= new TravellerBookingController(travellerBookingDetailsView);
            TravellerBooking.open();
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
    

    
//    Profile Navigation
    class ProfileNav implements MouseListener{
        
        private JLabel profileLabel;
        public ProfileNav(JLabel label){
            this.profileLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO: Create TravellerProfileController
            JOptionPane.showMessageDialog(travellerdashboardView, "Profile functionality not yet implemented!");
            // TravellerProfileView travellerProfileView = new TravellerProfileView();
            // TravellerProfileController  travellerProfile= new  TravellerProfileController(travellerProfileView );
            // travellerProfile.open();
            // close();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            profileLabel.setForeground(Color.RED);
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
                travellerdashboardView.dispose();

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
    
    
