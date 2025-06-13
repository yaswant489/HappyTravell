/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.model;

/**
 *
 * @author Acer
 */
public class PlaceData {

    public PlaceData(int aInt, String string, String string1, byte[] bytes) {
    }

    public String getPlaceName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public byte[] getPlaceImage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public class Place {
    private int id;
    private String placeName;
    private String description;
    private byte[] placeImage;
  
    // Constructor without ID (for new places)
    public Place(String placeName, String description, byte[] placeImage) {
        this.placeName = placeName;
        this.description = description;
        this.placeImage = placeImage;
    }
    
    // Constructor with ID (for existing places from database)
    public Place(int id, String placeName, String description, String photoPath) {
        this.id = id;
        this.placeName = placeName;
        this.description = description;
        this.placeImage = placeImage;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getPlaceName() {
        return placeName;
    }
    
    public void setPlaceName(String name) {
        this.placeName = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public byte[] getPlaceImage() {
        return placeImage;
    }
    
    public void setPlaceImage(byte[] placeImage) {
        this.placeImage = placeImage;
    }
    
   
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Place place = (Place) obj;
        return id == place.id && 
               placeName != null ? placeName.equals(place.placeName) : place.placeName == null;
    }
    
    
    
}
}
