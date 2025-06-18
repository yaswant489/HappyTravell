/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.AdminBusTicketsView;
import happytravell.view.AdmindashboardView;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

/**
 *
 * @author Acer
 */
public class AdminBusTicketsController {
    private AdminBusTicketsView TicketsView;
    public AdminBusTicketsController(AdminBusTicketsView adminBusTicketsView) {
        this.TicketsView.DashboardNavigation(new AdminBusTicketsController.DashboardNav(adminBusTicketsView.getDashboardlabel()));
        this.TicketsView = adminBusTicketsView;
    }
    public void open(){
    this.TicketsView.setVisible(true);
    } 
    public void close(){
    this.TicketsView.dispose();
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


}

