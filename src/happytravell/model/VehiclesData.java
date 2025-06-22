/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.model;

/**
 *
 * @author Acer
 */
public class VehiclesData {
   
    private int vehicleId;
    private String vehicleType;
    private String vehicleNumber;
    private int numberOfSeats;
    private String vehicleName;
    private String vehicleColor;
    private String travelAgency;
    private boolean isActive;
    private byte[] vehicleImage;

   

    // Constructor with parameters
    public VehiclesData(String vehicleType, String vehicleNumber, int numberOfSeats, 
                   String vehicleName, String vehicleColor, String travelAgency) {
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.numberOfSeats = numberOfSeats;
        this.vehicleName = vehicleName;
        this.vehicleColor = vehicleColor;
        this.travelAgency = travelAgency;
        this.isActive = true;
    }
    
    public VehiclesData( int vehicleId,String vehicleType, String vehicleNumber, int numberOfSeats, 
                   String vehicleName, String vehicleColor, String travelAgency, boolean isActive,byte[] vehicleImage) {
        this.vehicleId =vehicleId;
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.numberOfSeats = numberOfSeats;
        this.vehicleName = vehicleName;
        this.vehicleColor = vehicleColor;
        this.travelAgency = travelAgency;
        this.isActive = true;
        this.vehicleImage = vehicleImage;
    }

    // Constructor with all parameters including ID
    public VehiclesData(int vehicleId, String vehicleType, String vehicleNumber, 
                   int numberOfSeats, String vehicleName, String vehicleColor, 
                   String travelAgency, boolean isActive) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.numberOfSeats = numberOfSeats;
        this.vehicleName = vehicleName;
        this.vehicleColor = vehicleColor;
        this.travelAgency = travelAgency;
        this.isActive = isActive;
    }

    // Getters and Setters
    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getTravelAgency() {
        return travelAgency;
    }

    public void setTravelAgency(String travelAgency) {
        this.travelAgency = travelAgency;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    
    public byte[] getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(byte[] vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

   
}
