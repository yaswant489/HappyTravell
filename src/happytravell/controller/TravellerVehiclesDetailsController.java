/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.TravellerVehiclesDetailsView;



/**
 *
 * @author Acer
 */
public class TravellerVehiclesDetailsController {
    private TravellerVehiclesDetailsView vehiclesDetailsView;
    private int currentTravellerId;
    public TravellerVehiclesDetailsController(TravellerVehiclesDetailsView vehiclesDetailsView ,int travellerId) {
        this.currentTravellerId =travellerId;
        this.vehiclesDetailsView = vehiclesDetailsView;
    }
    public void open(){
    this.vehiclesDetailsView.setVisible(true);
    } 
    public void close(){
    this.vehiclesDetailsView.dispose();
    }
}
