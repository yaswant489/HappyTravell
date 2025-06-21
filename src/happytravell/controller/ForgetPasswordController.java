/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.dao.PasswordResetDao;
import happytravell.view.ForgetPasswordView;
import happytravell.view.LoginPageView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class ForgetPasswordController {
    private final ForgetPasswordView view;
    private final PasswordResetDao passwordResetDao;
    private String currentEmail;
    
    public ForgetPasswordController(ForgetPasswordView view) {
        this.view = view;
        this.passwordResetDao = new PasswordResetDao();
        
        setupEventListeners();
    }
    
    private void setupEventListeners() {
        view.addResetListener(new ContinueListener());
        view.addBackListener(new BackListener());
    }
    
    private class ContinueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentEmail = view.getEmail();
            
            if (currentEmail.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter your email address", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!passwordResetDao.emailExists(currentEmail)) {
                JOptionPane.showMessageDialog(view, "Email not found in our system", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String verificationCode = generateVerificationCode();
            passwordResetDao.storeVerificationCode(currentEmail, verificationCode);
            
            // For now, just show the verification code in a dialog
            // TODO: Implement proper email sending functionality
            JOptionPane.showMessageDialog(view, 
                "Verification code sent to your email: " + currentEmail + "\n" +
                "Code: " + verificationCode + "\n\n" +
                "Note: In a real application, this code would be sent via email.", 
                "Verification Code", JOptionPane.INFORMATION_MESSAGE);
            
            // TODO: Uncomment when SMTPSMailSender is implemented
            /*
            if (SMTPSMailSender.sendVerificationEmail(currentEmail, verificationCode)) {
                JOptionPane.showMessageDialog(view, "Verification code sent to your email: " + currentEmail, "Success", JOptionPane.INFORMATION_MESSAGE);
                // Here you can navigate to a verification code input page if needed
                // For now, we'll just show a success message
            } else {
                JOptionPane.showMessageDialog(view, "Failed to send verification code. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            */
        }
        
        private String generateVerificationCode() {
            Random random = new Random();
            return String.format("%06d", random.nextInt(999999));
        }
    }
    
    private class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            navigateToLogin();
        }
    }
    
    private void navigateToLogin() {
        view.dispose();
        LoginPageView loginView = new LoginPageView();
        new LoginController(loginView).open();
    }
    
    public void open() {
        view.setVisible(true);
    }
}