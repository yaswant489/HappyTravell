/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.AdminProfileView;

/**
 *
 * @author Acer
 */
public class AdminProfileController {
    private AdminProfileView ProfileView;
    public AdminProfileController(AdminProfileView adminProfileView) {
        this.ProfileView = adminProfileView;
    }
    public void open(){
    this.ProfileView.setVisible(true);
    } 
    public void close(){
    this.ProfileView.dispose();
    }
}
