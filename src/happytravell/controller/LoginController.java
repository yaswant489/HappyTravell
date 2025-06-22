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

    private final LoginPageView loginView;
    private final AdminDao adminDao;
    private final TravellerDao travellerDao;
    private boolean isPasswordVisible = false;

    public LoginController(LoginPageView loginView) {
        this.loginView = loginView;
        this.adminDao = new AdminDao();
        this.travellerDao = new TravellerDao();

        // Attach listeners
        this.loginView.addLoginListener(new LoginListener());
        this.loginView.addSignupListener(new SignupListener(loginView.getSignupLabel()));
        this.loginView.addForgetPasswordListener(new ForgetPasswordListener(loginView.getForgetPasswordLabel()));
        this.loginView.addTogglePasswordVisibilityListener(new TogglePasswordVisibilityListener());
    }

    public void open() {
        loginView.setVisible(true);
    }

    public void close() {
        loginView.dispose();
    }

    // Listener for the login button
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = loginView.getEmail();
            String password = loginView.getPassword();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(loginView, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LoginRequest loginRequest = new LoginRequest(email, password);

            // Try to log in as admin first
            AdminData admin = adminDao.adminLogin(loginRequest);
            if (admin != null) {
                JOptionPane.showMessageDialog(loginView, "Admin login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                AdmindashboardView adminDashboard = new AdmindashboardView();
                new AdminDashboardController(adminDashboard, admin.getId());
                adminDashboard.setVisible(true);
                close();
                return;
            }

            // If not an admin, try to log in as a traveller
            TravellerData traveller = travellerDao.travellerLogin(loginRequest);
            if (traveller != null) {
                JOptionPane.showMessageDialog(loginView, "Traveller login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                TravellerdashboardView travellerDashboard = new TravellerdashboardView();
                new TravellerDashboardController(travellerDashboard, traveller.getTravellerID());
                travellerDashboard.setVisible(true);
                close();
                return;
            }

            // If neither login is successful
            JOptionPane.showMessageDialog(loginView, "Invalid email or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
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
            new SignupAsController(signupAsView);
            signupAsView.setVisible(true);
            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            signupLabel.setForeground(Color.BLUE);
            signupLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            signupLabel.setForeground(Color.BLACK);
            signupLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    // Listener for the "Forget Password" label
    class ForgetPasswordListener implements MouseListener {
        private final JLabel forgetPasswordLabel;

        public ForgetPasswordListener(JLabel label) {
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

    // Listener to toggle password visibility
    class TogglePasswordVisibilityListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isPasswordVisible) {
                loginView.getPasswordField().setEchoChar('•');
                loginView.getShowButton().setText("Show");
            } else {
                loginView.getPasswordField().setEchoChar((char) 0);
                loginView.getShowButton().setText("Hide");
            }
            isPasswordVisible = !isPasswordVisible;
        }
    }
}

