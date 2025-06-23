/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.dao.AdminDao;
import happytravell.dao.TravellerDao;
import happytravell.model.AdminData;
import happytravell.model.LoginRequest;
import happytravell.model.TravellerData;
import happytravell.view.AdmindashboardView;
import happytravell.view.ForgetPasswordView;
import happytravell.view.LoginPageView;
import happytravell.view.SignupAsView;
import happytravell.view.TravellerdashboardView;
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
 * @author User
 */
public class LoginController {

    private LoginPageView loginView = new LoginPageView();


    private final LoginPageView loginView;
    private final AdminDao adminDao;
    private final TravellerDao travellerDao;
    private boolean isPasswordVisible = false;
public LoginController(LoginPageView view){
        this.loginView = view;
        this.loginView.LoginUser(new LoginUser());
        this.loginView.signUpNavigation(new SignUpNav(loginView.getSignUplabel()));
        this.loginView.ForgetPasswordNavigation(new ForgetPasswordNav(loginView.getForgetPasswordLabel()));
        
        this.loginView.TogglePasswordVisibility(new TogglePasswordVisibility());

    }
    
    public void open(){
        this.loginView.setVisible(true);
    }
    public void close(){
        this.loginView.dispose();
    }
    
    
    class SignUpNav implements MouseListener{
        
        private JLabel signUpLabel;
        
        public SignUpNav(JLabel label){
            this.signUpLabel = label;

        }
    }

    // Listener for the "Sign Up" navigation
    class SignupListener implements MouseListener {
        private final JLabel signupLabel;

        public SignupListener(JLabel label) {
            this.signupLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            SignupAsView signupAsView = new SignupAsView();
           SignupAsController signupAsController= new SignupAsController(signupAsView);
            signupAsController.open();

            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {

            signUpLabel.setForeground(Color.BLUE);
            signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        }

        @Override
        public void mouseExited(MouseEvent e) {

            signUpLabel.setForeground(Color.BLACK);
            signUpLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
    }
    
    class ForgetPasswordNav implements MouseListener{
        
        private JLabel forgetPasswordLabel;
        
        public ForgetPasswordNav(JLabel label){

            this.forgetPasswordLabel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            ForgetPasswordView forgetPasswordView = new ForgetPasswordView();
            new ForgetPasswordController(forgetPasswordView);
            forgetPasswordView.setVisible(true);
            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            forgetPasswordLabel.setForeground(Color.BLUE);
            forgetPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            forgetPasswordLabel.setForeground(Color.BLACK);
            forgetPasswordLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

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
                loginView.getPasswordField().setEchoChar((char) 0);
                loginView.getShowButton().setText("Hide");
            }

            return "Unknown";
        }
        
        
        
        private void navigateToUserDashboard(Object user, String userType) {
            // Close current login view
            close();
            
            // Navigate based on user type
            switch (userType) {
                case "Admin":
                    AdminData admin = (AdminData) user;
                    // Navigate to Admin dashboard Page
                    AdmindashboardView adminDashboardView = new AdmindashboardView();
                    AdminDashboardController admindashboardController = new AdminDashboardController(adminDashboardView,admin.getId());
                    admindashboardController.open();
                    break;
                    
               
                case "Traveller":
                    TravellerData traveller = (TravellerData) user;
                    // Navigate to traveller database Page
                    TravellerdashboardView travellerDashboardView = new TravellerdashboardView();
                    TravellerDashboardController tarvellerdashboardController = new TravellerDashboardController(travellerDashboardView, traveller.getTravellerID());
                    tarvellerdashboardController.open();
                    break;
                    
                    
                    
                   
                    
                default:
                    JOptionPane.showMessageDialog(loginView, "Unknown user type");
                    break;
            
             }
        
        }

    }
}
