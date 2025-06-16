/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.model.TravellerData;
import happytravell.view.AdmindashboardView;
import happytravell.view.TravellerdashboardView;

/**
 *
 * @author Acer
 */
class TravellerDashboardController {

    private TravellerdashboardView travellerdashboardView = new TravellerdashboardView();
    public TravellerDashboardController(TravellerdashboardView travellerdashboardView){
    }
    public void open(){
    this.travellerdashboardView.setVisible(true);
    } 

    public void close(){
    this.travellerdashboardView.dispose();
    } 

    }
    
    
