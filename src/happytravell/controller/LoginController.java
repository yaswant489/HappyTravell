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
    private LoginPageView loginView = new LoginPageView();

    private boolean isPasswordVisible = false;
    public LoginController(LoginPageView loginPageView){
        this.loginPageView = loginPageView;
        
        this.loginPageView.addLoginListener(new Login());
        this.loginPageView.addSignupListener(new Signup());
        this.loginPageView.ForgetPasswordNavigation(new ForgetPasswordNav(loginPageView.getForgetPasswordLabel()));
        
        this.loginView.TogglePasswordVisibility(new TogglePasswordVisibility());

        this.loginView.TogglePasswordVisibility(new TogglePasswordVisibility());
    }
    
    public void open(){
        this.loginView.setVisible(true);
    }
    public void close(){
        this.loginView.dispose();
    }
    
    
    class Signup implements ActionListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            SignupAsView signupAsView = new SignupAsView();
            SignupAsController signupAsController= new SignupAsController(signupAsView);
            signupAsController.open();
            close();
        }
    }
    
    class ForgetPasswordNav implements MouseListener {
    
    private JLabel forgetPasswordLabel;
    
    public ForgetPasswordNav(JLabel label){
        this.forgetPasswordLabel = label;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        ForgetPasswordView forgetPasswordView = new ForgetPasswordView();
        ForgetPasswordController forgetPasswordController = new ForgetPasswordController(forgetPasswordView);
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
            loginView.getPasswordField().setEchoChar('•'); // or '*'
            loginView.getShowButton().setText("Show");
        } else {
            loginView.getPasswordField().setEchoChar((char) 0); // show password
            loginView.getShowButton().setText("Hide");
        }
        isPasswordVisible = !isPasswordVisible;
    }
}


    class Login implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = loginView.getEmailTextField().getText();            
            String password = String.valueOf(loginView.getPasswordField().getPassword());
            
            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(loginView, "Please fill in all the fields");
                return;
            }
            
            TravellerDao travellerDao = new TravellerDao();
            AdminDao adminDao = new AdminDao();
            LoginRequest loginRequest = new LoginRequest(email, password);
            
            // Try admin login first
            AdminData admin = adminDao.adminLogin(loginRequest);
            if (admin != null) {
                JOptionPane.showMessageDialog(loginView, "Admin logged in successfully");
                AdmindashboardView dashboardView = new AdmindashboardView();
                AdminDashboardController controller = new AdminDashboardController(dashboardView, admin.getId());
                controller.open();
                close();
                return;
            }
            
            // If not admin, try traveller login
            TravellerData traveller = travellerDao.travellerLogin(loginRequest);
            if (traveller != null) {
                JOptionPane.showMessageDialog(loginView, "Traveller logged in successfully");
                TravellerdashboardView dashboardView = new TravellerdashboardView();
                TravellerDashboardController controller = new TravellerDashboardController(dashboardView, traveller.getTravellerID());
                controller.open();
                close();
                return;
            }
            
            // If neither worked
            JOptionPane.showMessageDialog(loginView, "Login failed. Invalid email or password");
        }
    }
}

