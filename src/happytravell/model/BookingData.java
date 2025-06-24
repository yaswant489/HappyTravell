/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.model;

import java.sql.Timestamp;
/**
 *
 * @author Acer
 */
public class BookingData {
    
    private int bookingId;   
    private int travellerId;
    private String travellerName; 
    private String pickupAddress;
    public String dropAddress;
    private String departureDateTime;  
    private String returnDateTime;
    private int passengerCount;
    private String vehicleNumber;
    private String vehicleType;    
    private String driverName;
    private String paymentMethod;         
             
    
    // Default constructor
    public BookingData() {
    }
    
    
     public BookingData(int travellerId, String pickupAddress, String dropAddress,
                      String departureDateTime, String returnDateTime,
                      int passengerCount, String vehicleType, String paymentMethod,
                      String vehicleNumber, String driverName) {
        this(travellerId, pickupAddress, dropAddress, departureDateTime, 
             returnDateTime, passengerCount, vehicleType, paymentMethod);
        this.vehicleNumber = vehicleNumber;
        this.driverName = driverName;
     }
    
    // Constructor for traveler booking
    public BookingData(int travellerId, String pickupAddress, String dropAddress, 
                      String departureDateTime, String returnDateTime, 
                      int passengerCount, String vehicleType, String paymentMethod) {
        this.travellerId = travellerId;
        this.pickupAddress = pickupAddress;
        this.dropAddress = dropAddress;
        this.departureDateTime = departureDateTime;
        this.returnDateTime = returnDateTime;
        this.passengerCount = passengerCount;
        this.vehicleType = vehicleType;
        this.paymentMethod = paymentMethod;
    }
    
    // Constructor for admin view
    public BookingData(int bookingId, int travellerId, String travellerName,
                      String pickupAddress, String dropAddress, 
                      String departureDateTime, String returnDateTime,
                      int passengerCount, String vehicleNumber, String vehicleType,
                      String driverName, String status, double fare) {
        this.bookingId = bookingId;
        this.travellerId = travellerId;
        this.travellerName = travellerName;
        this.pickupAddress = pickupAddress;
        this.dropAddress = dropAddress;
        this.departureDateTime = departureDateTime;
        this.returnDateTime = returnDateTime;
        this.passengerCount = passengerCount;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.driverName = driverName;
        
    }
    
    // Getters and Setters for all fields
    public int getBookingId() {
        return bookingId;
    }
    
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    
    public int getTravellerId() {
        return travellerId;
    }
    
    public void setTravellerId(int travellerId) {
        this.travellerId = travellerId;
    }
    
    public String getTravellerName() {
        return travellerName;
    }
    
    public void setTravellerName(String travellerName) {
        this.travellerName = travellerName;
    }
    
    public String getPickupAddress() {
        return pickupAddress;
    }
    
    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }
    
    public String getDropAddress() {
        return dropAddress;
    }
    
    public void setDropAddress(String dropAddress) {
        this.dropAddress = dropAddress;
    }
    
    public String getDepartureDateTime() {
        return departureDateTime;
    }
    
    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }
    
    public String getReturnDateTime() {
        return returnDateTime;
    }
    
    public void setReturnDateTime(String returnDateTime) {
        this.returnDateTime = returnDateTime;
    }
    
    public int getPassengerCount() {
        return passengerCount;
    }
    
    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }
    
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    
    public String getVehicleType() {
        return vehicleType;
    }
    
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    
    public String getDriverName() {
        return driverName;
    }
    
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    // Utility method for traveler view
    public String getFormattedBookingSummary() {
        return String.format("Booking #%d - %s from %s to %s", 
            bookingId, vehicleType, pickupAddress, dropAddress);
    }
    
    // Utility method for admin view
    public String getFormattedAdminSummary() {
        return String.format("Booking #%d - %s (%s) - %s - : ", 
            bookingId, travellerName, vehicleType, driverName );
    }
    
    
}
