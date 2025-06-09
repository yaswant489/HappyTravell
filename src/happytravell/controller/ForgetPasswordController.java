/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.ForgetView;
import happytravell.view.LoginPageView;
import happytravell.view.SignupAsView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author Acer
 */
public class ForgetPasswordController {
    private ForgetView forgetView = new ForgetView();

    public ForgetPasswordController(ForgetView forgetView) {
        this.forgetView = forgetView;
        this.forgetView.BackNav(new BackNav());
        this.forgetView.ResetNav(new ResetNav());
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
    
    




}



