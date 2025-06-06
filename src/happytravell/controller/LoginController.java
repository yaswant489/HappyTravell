/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import happytravell.dao.AdminDao;
import happytravell.dao.TravellerDao;
import happytravell.model.AdminData;
import happytravell.model.LoginRequest;
import happytravell.model.TravellerData;
import happytravell.view.TravellerdashboardView;
import happytravell.view.AdmindashboardView;

import happytravell.view.LoginPageView;
/**
 *
 * @author Acer
 */
public class LoginController {
    private LoginPageView loginView = new LoginPageView();
    public LoginController(LoginPageView view){
        this.loginView = view;
        this.loginView.Login(new LoginTraveller());
        this.loginView.Login(new LoginAdmin());
    }
    
    public void open(){
        this.loginView.setVisible(true);
    }
    public void close(){
        this.loginView.dispose();
    }
    
    class LoginTraveller implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         String email = loginView.getEmailTextField().getText();            
         String password = String.valueOf(loginView.getPasswordField().getPassword());
         if (email.isEmpty()||password.isEmpty()){
                JOptionPane.showMessageDialog(loginView, "Fill in all the fields");
            }
         else {
             TravellerDao travellerDao = new TravellerDao();
             LoginRequest loginRequest = new LoginRequest(email,password);
             TravellerData traveller = travellerDao.travellerLogin(loginRequest);
             if (traveller == null){
                    JOptionPane.showMessageDialog(loginView, "Incorrect username or password.Please try again!","Error",JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(loginView, "Logged in successfully");
                    TravellerdashboardView dashboardView = new TravellerdashboardView();
                    TravellerDashboardController controller = new TravellerDashboardController(dashboardView, traveller);
                    controller.open();
                    close();
                }
         }
        
        }
    }
    class LoginAdmin implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         String email = loginView.getEmailTextField().getText();            
         String password = String.valueOf(loginView.getPasswordField().getPassword());
         if (email.isEmpty()||password.isEmpty()){
                JOptionPane.showMessageDialog(loginView, "Fill in all the fields");
            }
         else {
             AdminDao adminDao = new AdminDao();
             LoginRequest loginRequest = new LoginRequest(email,password);
             AdminData admin = adminDao.adminLogin(loginRequest);
             if (admin == null){
                    JOptionPane.showMessageDialog(loginView, "Incorrect username or password.Please try again!","Error",JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(loginView, "Logged in successfully");
                    AdmindashboardView dashboardView = new AdmindashboardView();
                    AdminDashboardController controller = new AdminDashboardController(dashboardView, admin);
                    AdminDashboardController.open();
                    close();
                }
         }
        
        }
    }
}
