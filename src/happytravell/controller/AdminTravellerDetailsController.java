/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.AdminTravellersDetailsView;

/**
 *
 * @author Acer
 */
public class AdminTravellerDetailsController {
    private AdminTravellersDetailsView TravellerView;
    public AdminTravellerDetailsController(AdminTravellersDetailsView adminTravellerDetailsView) {
        this.TravellerView = adminTravellerDetailsView;
    }
    public void open(){
    this.TravellerView.setVisible(true);
    } 
    public void close(){
    this.TravellerView.dispose();
    }
}
