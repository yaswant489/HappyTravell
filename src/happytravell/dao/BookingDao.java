/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.dao;
import happytravell.database.MysqlConnection;
import happytravell.model.BookingData;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Acer
 */

public class BookingDao {
    
    MysqlConnection mySql = new MysqlConnection();
    Connection conn = mySql.openConnection();
    public boolean insertBooking(BookingData booking) {
        String sql = "INSERT INTO bookings (traveller_id, pickup_address, drop_address, " +
                    "departure_datetime, return_datetime, passenger_count, vehicle_type, " +
                    "payment_method) VALUES (?, ?, ?, ?, ?, ?, ?,? )";
        
        try (
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, booking.getTravellerId());
            stmt.setString(2, booking.getPickupAddress());
            stmt.setString(3, booking.getDropAddress());
            stmt.setString(4, booking.getDepartureDateTime());
            stmt.setString(5, booking.getReturnDateTime());
            stmt.setInt(6, booking.getPassengerCount());
            stmt.setString(7, booking.getVehicleType());
            stmt.setString(8, booking.getPaymentMethod());
            
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Get the generated booking ID
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        booking.setBookingId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error inserting booking: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    
    public List<BookingData> getBookingsByTravellerId(int travellerId) {
        List<BookingData> bookings = new ArrayList<>();
        String sql = "SELECT b.*, t.name as traveller_name, v.vehicle_number, d.name as driver_name " +
                    "FROM bookings b " +
                    "LEFT JOIN travellers t ON b.traveller_id = t.id " +
                    "LEFT JOIN vehicles v ON b.vehicle_id = v.id " +
                    "LEFT JOIN drivers d ON b.driver_id = d.id " +
                    "WHERE b.traveller_id = ? ORDER BY b.created_at DESC";
        
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, travellerId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                BookingData booking = new BookingData();
                booking.setBookingId(rs.getInt("id"));
                booking.setTravellerId(rs.getInt("traveller_id"));
                booking.setTravellerName(rs.getString("traveller_name"));
                booking.setPickupAddress(rs.getString("pickup_address"));
                booking.setDropAddress(rs.getString("drop_address"));
                booking.setDepartureDateTime(rs.getString("departure_datetime"));
                booking.setReturnDateTime(rs.getString("return_datetime"));
                booking.setPassengerCount(rs.getInt("passenger_count"));
                booking.setVehicleNumber(rs.getString("vehicle_number"));
                booking.setVehicleType(rs.getString("vehicle_type"));
                booking.setDriverName(rs.getString("driver_name"));
                booking.setPaymentMethod(rs.getString("payment_method"));
                
                bookings.add(booking);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving bookings: " + e.getMessage());
            e.printStackTrace();
        }
        
        return bookings;
    }
    
    
    public List<BookingData> getAllBookings() {
        List<BookingData> bookings = new ArrayList<>();
        String sql = "SELECT b.*, t.name as traveller_name, v.vehicle_number, d.name as driver_name " +
                    "FROM bookings b " +
                    "LEFT JOIN travellers t ON b.traveller_id = t.id " +
                    "LEFT JOIN vehicles v ON b.vehicle_id = v.id " +
                    "LEFT JOIN drivers d ON b.driver_id = d.id " +
                    "ORDER BY b.created_at DESC";
        
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                BookingData booking = new BookingData();
                booking.setBookingId(rs.getInt("id"));
                booking.setTravellerId(rs.getInt("traveller_id"));
                booking.setTravellerName(rs.getString("traveller_name"));
                booking.setPickupAddress(rs.getString("pickup_address"));
                booking.setDropAddress(rs.getString("drop_address"));
                booking.setDepartureDateTime(rs.getString("departure_datetime"));
                booking.setReturnDateTime(rs.getString("return_datetime"));
                booking.setPassengerCount(rs.getInt("passenger_count"));
                booking.setVehicleNumber(rs.getString("vehicle_number"));
                booking.setVehicleType(rs.getString("vehicle_type"));
                booking.setDriverName(rs.getString("driver_name"));
                booking.setPaymentMethod(rs.getString("payment_method"));
                
                bookings.add(booking);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving all bookings: " + e.getMessage());
            e.printStackTrace();
        }
        
        return bookings;
    }
    
    
    public boolean updateBookingStatus(int bookingId, String status) {
        String sql = "UPDATE bookings SET booking_status = ?, updated_at = NOW() WHERE id = ?";
        
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status);
            stmt.setInt(2, bookingId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating booking status: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    
    public boolean assignVehicleAndDriver(int bookingId, int vehicleId, int driverId) {
        String sql = "UPDATE bookings SET vehicle_id = ?, driver_id = ?, booking_status = 'CONFIRMED', updated_at = NOW() WHERE id = ?";
        
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, vehicleId);
            stmt.setInt(2, driverId);
            stmt.setInt(3, bookingId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error assigning vehicle and driver: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Get available vehicles by type
     * @param vehicleType Type of vehicle (Car, Jeep, Taxi, etc.)
     * @return List of available vehicle numbers
     */
    public List<String> getAvailableVehiclesByType(String vehicleType) {
        List<String> vehicles = new ArrayList<>();
        String sql = "SELECT vehicle_number FROM vehicle WHERE vehicle_type = ? ";
        
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, vehicleType);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                vehicles.add(rs.getString("vehicle_number"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving available vehicles: " + e.getMessage());
            e.printStackTrace();
        }
        
        return vehicles;
    }
    
    /**
     * Get available drivers
     * @return List of available driver names
     */
    public List<String> getAvailableDrivers() {
        List<String> drivers = new ArrayList<>();
        String sql = "SELECT name FROM drivers WHERE status = 'AVAILABLE'";
        
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                drivers.add(rs.getString("name"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving available drivers: " + e.getMessage());
            e.printStackTrace();
        }
        
        return drivers;
    }
}