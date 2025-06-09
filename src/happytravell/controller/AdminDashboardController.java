/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.model.AdminData;
import happytravell.view.AdmindashboardView;


/**
 *
 * @author Acer
 */
public class AdminDashboardController {
    private AdmindashboardView admindashboardView = new AdmindashboardView();
    public AdminDashboardController(AdmindashboardView admindashboardView){
        
    
    }

public void open(){
    this.admindashboardView.setVisible(true);
} 

public void close(){
    this.admindashboardView.dispose();
} 


}

