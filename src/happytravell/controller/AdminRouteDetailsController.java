/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.AdminRouteDetailsView;

/**
 *
 * @author Acer
 */
public class AdminRouteDetailsController {
    private AdminRouteDetailsView routeView;
    public AdminRouteDetailsController(AdminRouteDetailsView adminRouteDetailsView) {
        this.routeView = adminRouteDetailsView;
    }
    public void open(){
    this.routeView.setVisible(true);
    } 
    public void close(){
    this.routeView.dispose();
    } 
}
