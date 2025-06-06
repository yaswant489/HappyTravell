/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.dao.AdminDao;
import happytravell.dao.TravellerDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import happytravell.model.TravellerData;
import happytravell.view.TravellerRegisterView;
import happytravell.view.SignupAsView;
import happytravell.view.TravellerRegisterView;
import happytravell.view.LoginPageView;

/**
 *
 * @author Acer
 */
public class TravellerRegisterController {
    private TravellerRegisterView travellerRegisterView = new TravellerRegisterView ();
    private TravellerData travellerData;
    public TravellerRegisterController(TravellerRegisterView travellerRegisterView) {
        this.travellerRegisterView = travellerRegisterView;
        this.travellerData = travellerData;
        this.travellerRegisterView.registerTraveller(new TravellerRegisterController.TravellerRegister());
        this.travellerRegisterView.mainpageBack(new TravellerRegisterController.MainpageBack());
        
    }
    public void open(){
        this.travellerRegisterView.setVisible(true);

    }
    public void close(){
        this.travellerRegisterView.dispose();
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
    
    class TravellerRegister implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = travellerRegisterView.getFirstNameTextField().getText();
            String lastName = travellerRegisterView.getLastNameTextField().getText();
            String username = travellerRegisterView.getUsernameTextField().getText();
            String phoneNumber = travellerRegisterView.getPhoneNumberTextField().getText();
            String address = travellerRegisterView.getAddressTextField().getText();
            String email = travellerRegisterView.getEmailTextField().getText();
            String setpassword = String.valueOf(travellerRegisterView.getSetPasswordTextField().getPassword());
            String confirmpassword = String.valueOf(travellerRegisterView.getConfirmPasswordTextField().getPassword());            
             //Validation
            if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || username.isEmpty() ||address.isEmpty() || email.isEmpty() ||setpassword.isEmpty()||confirmpassword.isEmpty())
            {   
                JOptionPane.showMessageDialog(travellerRegisterView, "All fields must be filled.");
                return;
            }
            if (!setpassword.equals(confirmpassword)){
                JOptionPane.showMessageDialog(travellerRegisterView,"Password not matched.Please try again!");
                return;
            }
            if (!Pattern.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$", email)) {
                JOptionPane.showMessageDialog(travellerRegisterView, "Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!Pattern.matches("^\\d{7,15}$", phoneNumber)) {
                JOptionPane.showMessageDialog(travellerRegisterView, "Please enter a valid phone number (7 to 15 digits).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            travellerData.setFirstName(firstName);
            travellerData.setLastName(lastName);
            travellerData.setUsername(username);
            travellerData.setEmail(email);
            travellerData.setAddress(address);
            travellerData.setPhoneNumber(phoneNumber);
            travellerData.setPassword(setpassword);
                 
            
            boolean success = new TravellerDao().Register(travellerData);
            if (success){
                JOptionPane.showMessageDialog(travellerRegisterView,"Registered successfully. Please Login to continue!");
                LoginPageView loginView = new LoginPageView();
                LoginController loginController = new LoginController(loginView);
                loginController.open();
                close();
            } else {
                JOptionPane.showMessageDialog(travellerRegisterView,"Registered failed. Please try again!");
            }
                        
            
        }
    }
    
}
