/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;


import happytravell.view.AdmindashboardView;
import happytravell.view.LoginPageView;
import happytravell.view.PlacesView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;



/**
 *
 * @author Acer
 */
public class AdminDashboardController {
    private AdmindashboardView admindashboardView = new AdmindashboardView();
    public AdminDashboardController(AdmindashboardView admindashboardView){
        this.admindashboardView.LogOutNavigation(new LogOutNav());
        this.admindashboardView.PlacesNavigation(new PlacesNav());
    }
    
    public void open(){
        this.admindashboardView.setVisible(true);
    } 

    public void close(){
        this.admindashboardView.dispose();
    } 

    class LogOutNav implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                admindashboardView.dispose();

                LoginPageView loginView = new LoginPageView();
                LoginController loginController = new LoginController(loginView);
                loginController.open();
            }
        }   
    }

    class PlacesNav implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
                PlacesView placesView = new PlacesView();
                PlacesController placesController = new PlacesController(placesView);
                placesController.open();
        }
    }   
}
    





