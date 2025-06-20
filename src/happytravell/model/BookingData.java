/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.model;

/**
 *
 * @author Acer
 */
public class BookingData {
    private int bookingId;   
    private int travellerId;
    private String pickupAddress;
    public String dropAddress; // Made private for proper encapsulation
    private String departureDateTime;
    private String returnDateTime;
    private int  passengerCount;
    private String vehicleNumber;
    private String driverName;
    
    
    
    // Default constructor
    public BookingData() {
    }
    
    // Constructor with essential fields
    public BookingData(int bookingId, int travellerId,int passengerCount, String pickupAddress, String dropAddress, 
                      String departureDateTime, String returnDateTime, String vehicleNumber, String driverName) {
        this.bookingId = bookingId;
        this.travellerId = travellerId;
        this.dropAddress = dropAddress;
        this.pickupAddress = pickupAddress;
        this.departureDateTime = departureDateTime;
        this.returnDateTime = returnDateTime;
        this.passengerCount = passengerCount;
        this.vehicleNumber = vehicleNumber;
        this.driverName = driverName;
    }
    
    
    
    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }
    
    public int getTravellerId() {
    return travellerId;
    }
    public void setTravellerId(int travellerId) {
    this.travellerId = travellerId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    
    public String getDepartureDateTime() {
        return departureDateTime;
    }
    
    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }
    
    // Fixed getDropAddress method - removed parameter and implemented properly
    public String getDropAddress() {
        return dropAddress;
    }
    
    public void setDropAddress(String dropAddress) {
        this.dropAddress = dropAddress;
    }
    
    public String getPickupAddress() {
        return pickupAddress;
    }
    
    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
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
    
    public String getDriverName() {
        return driverName;
    }
    
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}