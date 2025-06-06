/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.dao.AdminDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import happytravell.model.AdminData;
import happytravell.view.SignupAsView;
import happytravell.view.AdminRegisterView;
import happytravell.view.LoginPageView;
/**
 *
 * @author Acer
 */
public class AdminRegisterController {
    private AdminRegisterView adminRegisterView = new AdminRegisterView ();
    private AdminData adminData;
    public AdminRegisterController(AdminRegisterView adminRegisterView) {
        this.adminRegisterView = adminRegisterView;
        this.adminData = adminData;
        this.adminRegisterView.registerAdmin(new AdminRegister());
        this.adminRegisterView.mainpageBack(new MainpageBack());
        
    }
    public void open(){
        this.adminRegisterView.setVisible(true);

    }
    public void close(){
        this.adminRegisterView.dispose();
    }
    
    class MainpageBack implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            SignupAsView signupAsView = new SignupAsView();
            SignupAsController signupAsController = new SignupAsController(signupAsView);
            signupAsController.open();
            close();
        }
        
    }
    
    class AdminRegister implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = adminRegisterView.getFirstNameTextField().getText();
            String lastName = adminRegisterView.getLastNameTextField().getText();
            String username = adminRegisterView.getUsernameTextField().getText();
            String phoneNumber = adminRegisterView.getPhoneNumberTextField().getText();
            String address = adminRegisterView.getAddressTextField().getText();
            String email = adminRegisterView.getEmailTextField().getText();
            String setpassword = String.valueOf(adminRegisterView.getSetPasswordTextField().getPassword());
            String confirmpassword = String.valueOf(adminRegisterView.getConfirmPasswordTextField().getPassword());            
             //Validation
            if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || username.isEmpty() ||address.isEmpty() || email.isEmpty() ||setpassword.isEmpty()||confirmpassword.isEmpty())
            {   
                JOptionPane.showMessageDialog(adminRegisterView, "All fields must be filled.");
                return;
            }
            if (!setpassword.equals(confirmpassword)){
                JOptionPane.showMessageDialog(adminRegisterView,"Password not matched.Please try again!");
                return;
            }
            if (!Pattern.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$", email)) {
                JOptionPane.showMessageDialog(adminRegisterView, "Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!Pattern.matches("^\\d{7,15}$", phoneNumber)) {
                JOptionPane.showMessageDialog(adminRegisterView, "Please enter a valid phone number (7 to 15 digits).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            adminData.setFirstName(firstName);
            adminData.setLastName(lastName);
            adminData.setUsername(username);
            adminData.setEmail(email);
            adminData.setAddress(address);
            adminData.setPhoneNumber(phoneNumber);
            adminData.setPassword(setpassword);
                 
            
            boolean success = new AdminDao().Register(adminData);
            if (success){
                JOptionPane.showMessageDialog(adminRegisterView,"Registered successfully. Please Login to continue!");
                LoginPageView loginView = new LoginPageView();
                LoginController loginController = new LoginController(loginView);
                loginController.open();
                close();
            } else {
                JOptionPane.showMessageDialog(adminRegisterView,"Registered failed. Please try again!");
            }
                        
            
        }
    }
}
