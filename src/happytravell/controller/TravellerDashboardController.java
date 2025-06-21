/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.model.TravellerData;
import happytravell.view.TravellerdashboardView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import happytravell.view.LoginPageView;
import happytravell.view.TravellerBookingView;
import happytravell.view.TravellerVechilesDetailsView;

/**
 *
 * @author Acer
 */
public class TravellerDashboardController {

    private TravellerdashboardView travellerdashboardView;
    private TravellerData travellerData;
    
    public TravellerDashboardController(TravellerdashboardView view, TravellerData data) {
        this.travellerdashboardView = view;
        this.travellerData = data;
        
        // Add listeners to all side panel buttons
        this.travellerdashboardView.addLogoutListener(new LogoutListener());
        this.travellerdashboardView.addBookingListener(new BookingListener());
        this.travellerdashboardView.addVehiclesListener(new VehiclesListener());
        this.travellerdashboardView.addDashboardListener(new DashboardListener());
        this.travellerdashboardView.addRouteListener(new RouteListener());
        this.travellerdashboardView.addBusTicketsListener(new BusTicketsListener());
        this.travellerdashboardView.addProfileListener(new ProfileListener());

        // Set initial content - removed DashboardHomePanel reference
        // The dashboard will show its default content
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

    class ProfileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            javax.swing.JOptionPane.showMessageDialog(travellerdashboardView, "Profile functionality not yet implemented!");
        }
    }
}
    
    
