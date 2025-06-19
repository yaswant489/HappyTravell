///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package happytravell.controller;

//import happytravell.dao.EmailDao;
import happytravell.view.ForgetPasswordView;
import happytravell.view.LoginPageView;
import happytravell.view.SignupAsView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
//
//
///**
// *
// * @author Acer
// */
public class ForgetPasswordController {
//    private final EmailDao emailDao;
//    private final EmailService emailService;
//    
//    private String generatedCode;
//    private String userEmail;
//    private boolean isCodeVerified = false;
//    private Timer codeExpirationTimer;
//    private static final int CODE_EXPIRATION_TIME = 10 * 60 * 1000;  
    private ForgetPasswordView forgetView = new ForgetPasswordView();
    private boolean isPasswordVisible = false;
    public ForgetPasswordController(ForgetPasswordView forgetView) {
        this.forgetView = forgetView;
        this.forgetView.BackNav(new BackNav());
        this.forgetView.ResetNav(new ResetNav());
        this.forgetView.toggleResetPasswordVisibility(new ToggleResetPasswordVisibility());
        this.forgetView.toggleConfirmPasswordVisibility(new ToggleConfirmPasswordVisibility());
    }
    void open() {
        this.forgetView.setVisible(true);
    }

    void close() {
        this.forgetView.dispose();
    }

    class BackNav implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginPageView LoginView = new LoginPageView();
            LoginController loginController = new LoginController(LoginView);
            loginController.open();
            
        }
        
    }
    class ResetNav implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginPageView loginView = new LoginPageView();
            LoginController loginController = new LoginController(loginView);
            loginController.open();
            
        }
        
    }
    
    class ToggleResetPasswordVisibility implements ActionListener{
           
        @Override
            public void actionPerformed(ActionEvent e) {
                if (isPasswordVisible) {
                    forgetView. getResetSetPasswordField().setEchoChar('•'); 
                    forgetView.getResetShowButton().setText("Show");
                } 
                else{
                     forgetView.getResetSetPasswordField().setEchoChar((char) 0);
                     forgetView.getResetShowButton().setText("Hide");
                    }
                isPasswordVisible = !isPasswordVisible;
            }
           
    }
    
    class ToggleConfirmPasswordVisibility implements ActionListener{
           
        @Override
            public void actionPerformed(ActionEvent e) {
                if (isPasswordVisible) {
                    forgetView. getConfirmPasswordField().setEchoChar('•'); 
                    forgetView.getConfirmShowButton().setText("Show");
                } 
                else{
                     forgetView.getConfirmPasswordField().setEchoChar((char) 0);
                     forgetView.getConfirmShowButton().setText("Hide");
                    }
                isPasswordVisible = !isPasswordVisible;
            }
           
    }
    
    
//    
//    
//
//
//

}
//
//
//
