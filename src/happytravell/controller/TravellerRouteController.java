/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.TravellerRouteView;

/**
 *
 * @author Acer
 */
public class TravellerRouteController {
    private TravellerRouteView routeView;
    private int currentTravellerId;
    public TravellerRouteController(TravellerRouteView routeView ,int travellerId) {
        this.currentTravellerId =travellerId;
        this.routeView = routeView;
    }
    public void open(){
    this.routeView.setVisible(true);
    } 
    public void close(){
    this.routeView.dispose();
    }
}
