/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.AdminBookingDetailsView;


/**
 *
 * @author Acer
 */
public class AdminBookingDetailsController {
    private AdminBookingDetailsView bookingView;
    public AdminBookingDetailsController(AdminBookingDetailsView adminBookingDetailsView) {
        this.bookingView = adminBookingDetailsView;
    }
    public void open(){
    this.bookingView.setVisible(true);
    } 
    public void close(){
    this.bookingView.dispose();
    } 
}
