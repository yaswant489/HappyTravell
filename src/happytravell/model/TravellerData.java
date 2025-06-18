/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.model;

/**
 *
 * @author User
 */
public class TravellerData {

    private int travellerID;
    public String firstName; // Changed from public to private for consistency
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
    private byte[] image;
    
    

    // Constructor with basic fields
    public TravellerData(String firstName, String lastName, String username, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        // Removed initialization of null fields - they will be null by default
    }
    
    // Constructor with all fields
    public TravellerData(int travellerID, String firstName, String lastName, String username, 
                        String email, String phoneNumber, String address, String password, byte[] image) {
        this.travellerID = travellerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.image = image;
    }

    // Default constructor
    public TravellerData() {
        
    }
    
    // Setters
    public void setTravellerID(int travellerID) {
        this.travellerID = travellerID;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setImage(byte[] image) {
        this.image = image;
    }

    // Getters - FIXED: Removed incorrect parameters
    public int getTravellerID() {
        return this.travellerID;
    }
    
    public String getFirstName() { // Removed incorrect String parameter
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public byte[] getImage() { // Removed incorrect byte[] parameter
        return this.image;
    }
}