/////*
//// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//// */
//package happytravell.controller;
//
//import happytravell.dao.PasswordResetDao;
//
//import happytravell.view.ForgetPasswordView;
//import happytravell.view.LoginPageView;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.Random;
//
////
////
/////**
//// *
//// * @author Acer
//// */
//
//
//
//
//
//public class ForgetPasswordController {
//    private final ForgetPasswordView view;
//    private final PasswordResetDao passwordResetDao;
//    private String currentEmail;
//    
//    public ForgetPasswordController(ForgetPasswordView view) {
//        this.view = view;
//        this.passwordResetDao = new PasswordResetDao();
//        
//        setupEventListeners();
//    }
//    
//    private void setupEventListeners() {
//        view.addSendCodeListener(new SendCodeListener());
//        view.addResetListener(new ResetPasswordListener());
//        view.addBackListener(new BackListener());
//        view.toggleResetPasswordVisibility(new TogglePasswordVisibility(view.getResetSetPasswordField(), view.getResetShowButton()));
//        view.toggleConfirmPasswordVisibility(new TogglePasswordVisibility(view.getConfirmPasswordField(), view.getConfirmShowButton()));
//    }
//    
//    private class SendCodeListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            currentEmail = view.getEmail();
//            
//            if (currentEmail.isEmpty()) {
//                view.showError("Please enter your email address");
//                return;
//            }
//            
//            if (!passwordResetDao.emailExists(currentEmail)) {
//                view.showError("Email not found in our system");
//                return;
//            }
//            
//            String verificationCode = generateVerificationCode();
//            passwordResetDao.storeVerificationCode(currentEmail, verificationCode);
//            
//            if (SMTPSMailSender.sendVerificationEmail(currentEmail, verificationCode)) {
//                view.showSuccess("Verification code sent to your email");
//                view.setVerificationFieldsEnabled(true);
//            } else {
//                view.showError("Failed to send verification code. Please try again.");
//            }
//        }
//        
//        private String generateVerificationCode() {
//            Random random = new Random();
//            return String.format("%06d", random.nextInt(999999));
//        }
//    }
//    
//    private class ResetPasswordListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String code = view.getVerificationCode();
//            String newPassword = view.getNewPassword();
//            String confirmPassword = view.getConfirmPassword();
//            
//            if (!validateInputs(code, newPassword, confirmPassword)) {
//                return;
//            }
//            
//            if (!passwordResetDao.verifyCode(currentEmail, code)) {
//                view.showError("Invalid or expired verification code");
//                return;
//            }
//            
//            if (passwordResetDao.resetPassword(currentEmail, newPassword)) {
//                view.showSuccess("Password reset successfully! You can now login with your new password.");
//                navigateToLogin();
//            } else {
//                view.showError("Failed to reset password. Please try again.");
//            }
//        }
//        
//        private boolean validateInputs(String code, String newPassword, String confirmPassword) {
//            if (code.isEmpty()) {
//                view.showError("Please enter verification code");
//                return false;
//            }
//            
//            if (newPassword.isEmpty()) {
//                view.showError("Please enter new password");
//                return false;
//            }
//            
//            if (confirmPassword.isEmpty()) {
//                view.showError("Please confirm your password");
//                return false;
//            }
//            
//            if (!newPassword.equals(confirmPassword)) {
//                view.showError("Passwords do not match");
//                return false;
//            }
//            
//            if (newPassword.length() < 6) {
//                view.showError("Password must be at least 6 characters");
//                return false;
//            }
//            
//            return true;
//        }
//    }
//    
//    private class BackListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            navigateToLogin();
//        }
//    }
//    
//    private class TogglePasswordVisibility implements ActionListener {
//        private final JPasswordField passwordField;
//        private final JToggleButton toggleButton;
//        private boolean isVisible = false;
//        
//        public TogglePasswordVisibility(JPasswordField passwordField, JToggleButton toggleButton) {
//            this.passwordField = passwordField;
//            this.toggleButton = toggleButton;
//        }
//        
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            isVisible = !isVisible;
//            if (isVisible) {
//                passwordField.setEchoChar((char) 0);
//                toggleButton.setText("Hide");
//            } else {
//                passwordField.setEchoChar('•');
//                toggleButton.setText("Show");
//            }
//        }
//    }
//    
//    private void navigateToLogin() {
//        view.dispose();
//        LoginPageView loginView = new LoginPageView();
//        new LoginController(loginView).open();
//    }
//    
//    public void open() {
//        view.setVisible(true);
//    }
//}