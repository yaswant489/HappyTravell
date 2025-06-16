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
import happytravell.view.ForgetView;

import happytravell.view.LoginPageView;
import happytravell.view.SignupAsView;
/**
 *
 * @author Acer
 */
public class LoginController {
    private LoginPageView loginView = new LoginPageView();

    private boolean isPasswordVisible = false;
    public LoginController(LoginPageView view){
        this.loginView = view;
        this.loginView.LoginUser(new LoginUser());
        this.loginView.CreateAccountNav(new CreateAccountNav());
        this.loginView.ForgetPasswordNav(new ForgetPasswordNav());

        this.loginView.TogglePasswordVisibility(new TogglePasswordVisibility());
    }
    
    public void open(){
        this.loginView.setVisible(true);
    }
    public void close(){
        this.loginView.dispose();
    }
    
    class CreateAccountNav implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            SignupAsView signupAsView = new SignupAsView();
            SignupAsController signupAsController = new SignupAsController(signupAsView);
            signupAsController.open();
            
        }
        
    }


    class ForgetPasswordNav implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           ForgetView forgetView = new ForgetView();
           ForgetPasswordController forgetPasswordController = new ForgetPasswordController(forgetView);
           forgetPasswordController.open();
        }
        
    }


   class TogglePasswordVisibility implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isPasswordVisible) {
            loginView.getPasswordField().setEchoChar('•'); // or '*'
            loginView.getShowButton().setText("Show");
        } else {
            loginView.getPasswordField().setEchoChar((char) 0); // show password
            loginView.getShowButton().setText("Hide");
        }
        isPasswordVisible = !isPasswordVisible;
    }
}

    class LoginUser implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         String email = loginView.getEmailTextField().getText();            
         String password = String.valueOf(loginView.getPasswordField().getPassword());
         if (email.isEmpty()||password.isEmpty()){
            JOptionPane.showMessageDialog(loginView, "Fill in all the fields");
            return;
            }
         LoginRequest loginRequest = new LoginRequest(email,password);
         
         Object authenticatedUser = authenticateUser(loginRequest);
            
            if (authenticatedUser == null) {
                JOptionPane.showMessageDialog(loginView, 
                    "Incorrect username or password. Please try again!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String userType = getUserType(authenticatedUser);
                JOptionPane.showMessageDialog(loginView, 
                    "Logged in successfully as " + userType);
                
                // Navigate to appropriate dashboard based on user type
                navigateToUserDashboard(authenticatedUser, userType);
            }
        }
        
        private Object authenticateUser(LoginRequest loginRequest) {
            // Try admin first
            AdminDao adminDao = new AdminDao();
            AdminData admin= adminDao.adminLogin(loginRequest);
            if (admin != null) {
                return admin;
            }
            
            
            
            // Try traveller last
            TravellerDao travellerDao = new TravellerDao();
            TravellerData traveller = travellerDao.travellerLogin(loginRequest);
            if (traveller != null) {
                return traveller;
            }
            
            // No user found
            return null;
        }
        
        private String getUserType(Object user) {
            if (user instanceof AdminData) {
                return "Admin";
            
            } else if (user instanceof TravellerData) {
                return "Traveller";
            }
            return "Unknown";
        }
        
        private void navigateToUserDashboard(Object user, String userType) {
            // Close current login view
            close();
            
            // Navigate based on user type
            switch (userType) {
                case "Admin":
                    // Navigate to Admin dashboard Page
                    AdmindashboardView adminDashboardView = new AdmindashboardView();
                    AdminDashboardController admindashboardController = new AdminDashboardController(adminDashboardView);
                    admindashboardController.open();
                    break;
                    
               
                case "Traveller":
                    // Navigate to traveller database Page
                    TravellerdashboardView travellerDashboardView = new TravellerdashboardView();
                    TravellerDashboardController tarvellerdashboardController = new TravellerDashboardController(travellerDashboardView);
                    tarvellerdashboardController.open();
                    break;
                    
                    
                    
                   
                    
                default:
                    JOptionPane.showMessageDialog(loginView, "Unknown user type");
                    break;
            
             }
        
        }
    }
    
}
