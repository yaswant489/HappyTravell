/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.TravellerBookingView;

/**
 *
 * @author Acer
 */
public class TravellerBookingController {
    private TravellerBookingView BookingView;
    public TravellerBookingController(TravellerBookingView travellerBookingView) {
        this.BookingView = travellerBookingView;
    }
    public void open(){
    this.BookingView.setVisible(true);
    } 
    public void close(){
    this.BookingView.dispose();
    }
}
