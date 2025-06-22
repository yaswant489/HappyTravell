/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.TravellerBusTicketsView;

/**
 *
 * @author Acer
 */
public class TravellerBusTicketController {
    private TravellerBusTicketsView BusTicketsView;
    private int currentTravellerId;
    public TravellerBusTicketController(TravellerBusTicketsView BusTicketsView ,int travellerId) {
        this.currentTravellerId =travellerId;
        this.BusTicketsView = BusTicketsView;
    }
    public void open(){
    this.BusTicketsView.setVisible(true);
    } 
    public void close(){
    this.BusTicketsView.dispose();
    }
}
