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
import happytravell.view.ForgetPasswordView;
import happytravell.view.LoginPageView;
import happytravell.view.SignupAsView;
import happytravell.controller.ForgetPasswordController;
import happytravell.controller.SignupAsController;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
/**
 *
 * @author User
 */
public class LoginController {
    private final LoginPageView loginPageView;

    private boolean isPasswordVisible = false;
    public LoginController(LoginPageView loginPageView){
        this.loginPageView = loginPageView;
        
        this.loginPageView.addLoginListener(new Login());
        this.loginPageView.signUpNavigation(new SignupNav(loginPageView.getSignUplabel()));
        this.loginPageView.ForgetPasswordNavigation(new ForgetPasswordNav(loginPageView.getForgetPasswordLabel()));
        
        this.loginPageView.TogglePasswordVisibility(new TogglePasswordVisibility());
    }
    
    public void open(){
        this.loginPageView.setVisible(true);
    }
    public void close(){
        this.loginPageView.dispose();
    }
    
    
    class SignupNav implements MouseListener {
        private final JLabel signUplabel;
        
        public SignupNav(JLabel label){
            this.signUplabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            SignupAsView signupAsView = new SignupAsView();
            SignupAsController signupAsController = new SignupAsController(signupAsView);
            signupAsController.open();
            close();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            signUplabel.setForeground(Color.BLUE);
            signUplabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            signUplabel.setForeground(Color.BLACK);
            signUplabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
    
    class ForgetPasswordNav implements MouseListener{
        
        private final JLabel forgetPasswordLabel;
        
        public ForgetPasswordNav(JLabel label){
            this.forgetPasswordLabel = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            ForgetPasswordView forgetPasswordView = new ForgetPasswordView();
            ForgetPasswordController forgetPasswordController= new ForgetPasswordController(forgetPasswordView);
            forgetPasswordController.open();
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
            loginPageView.getPasswordField().setEchoChar('•'); // or '*'
            loginPageView.getShowButton().setText("Show");
        } else {
            loginPageView.getPasswordField().setEchoChar((char) 0); // show password
            loginPageView.getShowButton().setText("Hide");
        }
        isPasswordVisible = !isPasswordVisible;
    }
}

    class Login implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = loginPageView.getEmailTextField().getText();
            String password = String.valueOf(loginPageView.getPasswordField().getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(loginPageView, "All fields must be filled.");
                return;
            }

            LoginRequest loginRequest = new LoginRequest(email, password);

            // Try to log in as Admin first
            AdminData admin = new AdminDao().adminLogin(loginRequest);
            if (admin != null) {
                JOptionPane.showMessageDialog(loginPageView, "Admin login successful");
                close();
                AdmindashboardView adminDashboardView = new AdmindashboardView();
                AdminDashboardController adminController = new AdminDashboardController(adminDashboardView, admin.getId());
                adminController.open();
                return; // Exit after successful admin login
            }

            // If not an admin, try to log in as Traveller
            TravellerData traveller = new TravellerDao().travellerLogin(loginRequest);
            if (traveller != null) {
                JOptionPane.showMessageDialog(loginPageView, "Traveller login successful");
                close();
                 // Exit after successful traveller login
            }
            
            // If both logins fail
            JOptionPane.showMessageDialog(loginPageView, "Login failed. Please check your credentials.");
        }
    }
    
}
