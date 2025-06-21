/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.ForgetPasswordView;

/**
 *
 * @author Acer
 */
public class ForgetPasswordController {
    private ForgetPasswordView forgetView;
    public ForgetPasswordController(ForgetPasswordView forgetView) {
        this.forgetView = forgetView;
    }
    public void open(){
    this.forgetView.setVisible(true);
    } 
    public void close(){
    this.forgetView.dispose();
    }
}
