/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.AdminBusTicketsView;

/**
 *
 * @author Acer
 */
public class AdminBusTicketsController {
    private AdminBusTicketsView TicketsView;
    public AdminBusTicketsController(AdminBusTicketsView adminBusTicketsView) {
        this.TicketsView = adminBusTicketsView;
    }
    public void open(){
    this.TicketsView.setVisible(true);
    } 
    public void close(){
    this.TicketsView.dispose();
    }
}
