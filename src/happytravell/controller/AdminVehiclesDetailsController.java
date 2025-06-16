/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.AdminVehiclesDetailsView;

/**
 *
 * @author Acer
 */
public class AdminVehiclesDetailsController {
    private AdminVehiclesDetailsView VehiclesDetailsView;
    public AdminVehiclesDetailsController(AdminVehiclesDetailsView adminVehiclesDetailsView) {
        this.VehiclesDetailsView = adminVehiclesDetailsView;
    }
    public void open(){
    this.VehiclesDetailsView.setVisible(true);
    } 
    public void close(){
    this.VehiclesDetailsView.dispose();
    }
}
