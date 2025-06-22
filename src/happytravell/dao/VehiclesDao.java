/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.dao;

import java.sql.*;
import happytravell.database.MysqlConnection;
import happytravell.model.VehiclesData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class VehiclesDao {
 
    MysqlConnection mySql = new MysqlConnection();

    public boolean addVehicle(VehiclesData vehicle) {
        Connection conn = mySql.openConnection();
        String sql = "INSERT INTO vehicles (vehicle_type, vehicle_number, number_of_seats, " +
                    "vehicle_name, vehicle_color, travel_agency, vehicles_image, is_active) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, vehicle.getVehicleType());
            stmt.setString(2, vehicle.getVehicleNumber());
            stmt.setInt(3, vehicle.getNumberOfSeats());
            stmt.setString(4, vehicle.getVehicleName());
            stmt.setString(5, vehicle.getVehicleColor());
            stmt.setString(6, vehicle.getTravelAgency());
            stmt.setBoolean(7, vehicle.isActive());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Get the generated vehicle ID
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        vehicle.setVehicleId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error adding vehicle: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Update an existing vehicle
     * @param vehicle Vehicle object with updated information
     * @return true if successful, false otherwise
     */
    public boolean updateVehicle(VehiclesData vehicle){
        Connection conn = mySql.openConnection();
        String sql = "UPDATE vehicles SET vehicle_type = ?, vehicle_number = ?, number_of_seats = ?, " +
                    "vehicle_name = ?, vehicle_color = ?, travel_agency = ?, vehicles_image,is_active = ? WHERE vehicle_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, vehicle.getVehicleType());
            stmt.setString(2, vehicle.getVehicleNumber());
            stmt.setInt(3, vehicle.getNumberOfSeats());
            stmt.setString(4, vehicle.getVehicleName());
            stmt.setString(5, vehicle.getVehicleColor());
            stmt.setString(6, vehicle.getTravelAgency());
            stmt.setBoolean(7, vehicle.isActive());
            stmt.setInt(8, vehicle.getVehicleId());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating vehicle: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Delete a vehicle (soft delete - set isActive to false)
     * @param vehicleId ID of the vehicle to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteVehicle(int vehicleId) {
        Connection conn = mySql.openConnection();
        String sql = "UPDATE vehicles SET is_active = false WHERE vehicle_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting vehicle: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Get a vehicle by ID
     * @param vehicleId ID of the vehicle
     * @return Vehicle object or null if not found
     */
    public VehiclesData getVehicleById(int vehicleId) {
        Connection conn = mySql.openConnection();
        String sql = "SELECT * FROM vehicles WHERE vehicle_id = ? AND is_active = true";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractVehicleFromResultSet(rs);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting vehicle by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Get all active vehicles
     * @return List of Vehicle objects
     */
    public List<VehiclesData> getAllVehicles() {
        Connection conn = mySql.openConnection();
        List<VehiclesData> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE is_active = true ORDER BY vehicle_id DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                vehicles.add(extractVehicleFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all vehicles: " + e.getMessage());
            e.printStackTrace();
        }
        return vehicles;
    }
    
    /**
     * Get vehicles by type
     * @param vehicleType Type of vehicle to search for
     * @return List of Vehicle objects
     */
    public List<VehiclesData> getVehiclesByType(String vehicleType) {
        Connection conn = mySql.openConnection();
        List<VehiclesData> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE vehicle_type = ? AND is_active = true ORDER BY vehicle_id DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicleType);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(extractVehicleFromResultSet(rs));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting vehicles by type: " + e.getMessage());
            e.printStackTrace();
        }
        return vehicles;
    }
    
    /**
     * Check if vehicle number already exists
     * @param vehicleNumber Vehicle number to check
     * @return true if exists, false otherwise
     */
    public boolean isVehicleNumberExists(String vehicleNumber) {
        Connection conn = mySql.openConnection();
        String sql = "SELECT COUNT(*) FROM vehicles WHERE vehicle_number = ? AND is_active = true";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicleNumber);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error checking vehicle number: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Search vehicles by various criteria
     * @param searchTerm Search term to match against vehicle number, name, or color
     * @return List of matching Vehicle objects
     */
    public List<VehiclesData> searchVehicles(String searchTerm) {
        Connection conn = mySql.openConnection();
        List<VehiclesData> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE (vehicle_number LIKE ? OR vehicle_name LIKE ? OR " +
                    "vehicle_color LIKE ? OR travel_agency LIKE ?) AND is_active = true ORDER BY vehicle_id DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String searchPattern = "%" + searchTerm + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);
            stmt.setString(4, searchPattern);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(extractVehicleFromResultSet(rs));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error searching vehicles: " + e.getMessage());
            e.printStackTrace();
        }
        return vehicles;
    }
    
    /**
     * Extract Vehicle object from ResultSet
     * @param rs ResultSet containing vehicle data
     * @return Vehicle object
     * @throws SQLException if database error occurs
     */
    private VehiclesData extractVehicleFromResultSet(ResultSet rs) throws SQLException {
        return new VehiclesData(
            rs.getInt("vehicle_id"),
            rs.getString("vehicle_type"),
            rs.getString("vehicle_number"),
            rs.getInt("number_of_seats"),
            rs.getString("vehicle_name"),
            rs.getString("vehicle_color"),
            rs.getString("travel_agency"),
            rs.getBoolean("is_active"),
            rs.getBytes("vehicle_Image")    
        );
    }
    
    /**
     * Close database conn
     */
    public void closeConnection() {
        Connection conn = mySql.openConnection();
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing conn: " + e.getMessage());
        }
    }
}
    

