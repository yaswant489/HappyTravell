/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.AdminTravellingDetailsView;

/**
 *
 * @author Acer
 */
public class AdminTravellingDetailsController {
    private AdminTravellingDetailsView TravellingView;
    public AdminTravellingDetailsController(AdminTravellingDetailsView adminTravellingDetailsView) {
        this.TravellingView = adminTravellingDetailsView;
    }
    public void open(){
    this.TravellingView.setVisible(true);
    } 
    public void close(){
    this.TravellingView.dispose();
    }
}
