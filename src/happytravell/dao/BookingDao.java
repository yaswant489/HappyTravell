package happytravell.dao;

import happytravell.database.MysqlConnection;
import happytravell.model.BookingData;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Booking operations
 * Handles all database operations related to bookings
 * 
 * @author Acer
 */
public class BookingDao {
    
    private final MysqlConnection mySql;
    
    // SQL Query Constants
    private static final String INSERT_BOOKING = 
    "INSERT INTO bookings (traveller_id, pickup_address, drop_address, " +
    "departure_date_time, return_date_time, passenger_number, vehicles_number, " +
    "driver_name, vehicle_type, payment_method, vehicle_id, driver_id) " +
    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, NULL)";
    
    private static final String GET_BOOKINGS_BY_TRAVELLER = 
        "SELECT b.*, t.first_name, t.last_name, v.vehicle_number, d.name as driver_name " +
        "FROM bookings b " +
        "LEFT JOIN traveller t ON b.traveller_id = t.traveller_ID " +
        "LEFT JOIN vehicle v ON b.vehicle_id = v.vehicle_id " +
        "LEFT JOIN drivers d ON b.driver_id = d.id " +
        "WHERE b.traveller_id = ? ORDER BY b.departure_date_time DESC";
    
    private static final String GET_ALL_BOOKINGS = 
        "SELECT b.*, t.first_name, t.last_name, v.vehicle_number, d.name as driver_name " +
        "FROM bookings b " +
        "LEFT JOIN traveller t ON b.traveller_id = t.traveller_ID " +
        "LEFT JOIN vehicle v ON b.vehicle_id = v.vehicle_id " +
        "LEFT JOIN drivers d ON b.driver_id = d.id " +
        "ORDER BY b.departure_date_time DESC";
    
    private static final String UPDATE_BOOKING_STATUS = 
        "UPDATE bookings SET booking_status = ? WHERE booking_ID = ?";
    
    private static final String ASSIGN_VEHICLE_AND_DRIVER = 
        "UPDATE bookings SET vehicle_id = ?, driver_id = ?, booking_status = 'CONFIRMED' " +
        "WHERE booking_ID = ?";
    
    private static final String GET_AVAILABLE_VEHICLES_BY_TYPE = 
    "SELECT vehicle_id, vehicle_number, number_of_seats, travel_agency, vehicles_image " +
    "FROM vehicle WHERE vehicle_type = ? AND is_active = TRUE";

    private static final String GET_AVAILABLE_DRIVERS = 
        "SELECT id, name FROM drivers WHERE status = 'AVAILABLE'";
    
    private static final String GET_BOOKING_BY_ID = 
        "SELECT b.*, t.first_name, t.last_name, v.vehicle_number, d.name as driver_name " +
        "FROM bookings b " +
        "LEFT JOIN traveller t ON b.traveller_id = t.traveller_ID " +
        "LEFT JOIN vehicle v ON b.vehicle_id = v.vehicle_id " +
        "LEFT JOIN drivers d ON b.driver_id = d.id " +
        "WHERE b.booking_ID = ?";
    
    public BookingDao() {
        this.mySql = new MysqlConnection();
    }
    
    // ================== CRUD Operations ==================
    
    public boolean insertBooking(BookingData booking) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(INSERT_BOOKING, Statement.RETURN_GENERATED_KEYS);
            
            setBookingParameters(stmt, booking);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                setGeneratedBookingId(stmt, booking);
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error inserting booking: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        
        return false;
    }
    
    public List<BookingData> getBookingsByTravellerId(int travellerId) {
        List<BookingData> bookings = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(GET_BOOKINGS_BY_TRAVELLER);
            stmt.setInt(1, travellerId);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                bookings.add(createBookingFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving bookings for traveller " + travellerId + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        
        return bookings;
    }
    
    public List<BookingData> getAllBookings() {
        List<BookingData> bookings = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(GET_ALL_BOOKINGS);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                bookings.add(createBookingFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving all bookings: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        
        return bookings;
    }
    
    public BookingData getBookingById(int bookingId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(GET_BOOKING_BY_ID);
            stmt.setInt(1, bookingId);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return createBookingFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving booking " + bookingId + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        
        return null;
    }
    
    // ================== Update Operations ==================
    
    public boolean updateBookingStatus(int bookingId, String status) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(UPDATE_BOOKING_STATUS);
            stmt.setString(1, status);
            stmt.setInt(2, bookingId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating booking status: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        
        return false;
    }
    
    public boolean assignVehicleAndDriver(int bookingId, int vehicleId, int driverId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(ASSIGN_VEHICLE_AND_DRIVER);
            stmt.setInt(1, vehicleId);
            stmt.setInt(2, driverId);
            stmt.setInt(3, bookingId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error assigning vehicle and driver: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        
        return false;
    }
    
    public boolean updateBookingAmount(int bookingId, double totalAmount) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement("UPDATE bookings SET total_amount = ? WHERE booking_ID = ?");
            stmt.setDouble(1, totalAmount);
            stmt.setInt(2, bookingId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating booking amount: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        
        return false;
    }
    
    
    
    public List<BookingData.VehicleInfo> getAvailableVehiclesByType(String vehicleType) {
    List<BookingData.VehicleInfo> vehicles = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    try {
        conn = mySql.openConnection();
        stmt = conn.prepareStatement(GET_AVAILABLE_VEHICLES_BY_TYPE);
        stmt.setString(1, vehicleType);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            BookingData.VehicleInfo vehicle = new BookingData.VehicleInfo();
            vehicle.setVehicleId(rs.getInt("vehicle_id"));
            vehicle.setVehicleNumber(rs.getString("vehicle_number"));
            vehicle.setNumberOfSeats(rs.getInt("number_of_seats"));
            vehicle.setTravelAgency(rs.getString("travel_agency"));
            vehicle.setVehicleImage(rs.getBytes("vehicles_image"));
            vehicles.add(vehicle);
        }
        
    } catch (SQLException e) {
        System.err.println("Error retrieving available vehicles: " + e.getMessage());
        e.printStackTrace();
    } finally {
        closeResources(rs, stmt, conn);
    }
    
    return vehicles;
}
    
    public List<BookingData.DriverInfo> getAvailableDrivers() {
        List<BookingData.DriverInfo> drivers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mySql.openConnection();
            stmt = conn.prepareStatement(GET_AVAILABLE_DRIVERS);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                BookingData.DriverInfo driver = new BookingData.DriverInfo();
                driver.setDriverId(rs.getInt("id"));
                driver.setName(rs.getString("name"));
                drivers.add(driver);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving available drivers: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        
        return drivers;
    }
    
    // ================== Helper Methods ==================
    
    private void setBookingParameters(PreparedStatement stmt, BookingData booking) throws SQLException {
        stmt.setInt(1, booking.getTravellerId());
        stmt.setString(2, booking.getPickupAddress());
        stmt.setString(3, booking.getDropAddress());
        stmt.setTimestamp(4, Timestamp.valueOf(booking.getDepartureDateTime()));
        
        if (booking.getReturnDateTime() != null && !booking.getReturnDateTime().isEmpty()) {
            stmt.setTimestamp(5, Timestamp.valueOf(booking.getReturnDateTime()));
        } else {
            stmt.setNull(5, Types.TIMESTAMP);
        }
        
        stmt.setInt(6, booking.getPassengerCount());
        stmt.setString(7, booking.getVehicleNumber());
        stmt.setString(8, booking.getDriverName());
        stmt.setString(9, booking.getVehicleType());
        stmt.setString(10, booking.getPaymentMethod());
    }
    
    private void setGeneratedBookingId(PreparedStatement stmt, BookingData booking) throws SQLException {
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                booking.setBookingId(generatedKeys.getInt(1));
            }
        }
    }
    
    private BookingData createBookingFromResultSet(ResultSet rs) throws SQLException {
        BookingData booking = new BookingData();
        booking.setBookingId(rs.getInt("booking_ID"));
        booking.setTravellerId(rs.getInt("traveller_id"));
        
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String fullName = (firstName != null ? firstName : "") + 
                         (lastName != null ? " " + lastName : "");
        booking.setTravellerName(fullName.trim());
        
        booking.setPickupAddress(rs.getString("pickup_address"));
        booking.setDropAddress(rs.getString("drop_address"));
        
        Timestamp departureTs = rs.getTimestamp("departure_date_time");
        if (departureTs != null) {
            booking.setDepartureDateTime(departureTs.toString());
        }
        
        Timestamp returnTs = rs.getTimestamp("return_date_time");
        if (returnTs != null) {
            booking.setReturnDateTime(returnTs.toString());
        }
        
        booking.setPassengerCount(rs.getInt("passenger_number"));
        booking.setVehicleNumber(rs.getString("vehicles_number"));
        booking.setVehicleType(rs.getString("vehicle_type"));
        booking.setDriverName(rs.getString("driver_name"));
        booking.setPaymentMethod(rs.getString("payment_method"));
        
        // Set vehicle and driver IDs if they exist
        int vehicleId = rs.getInt("vehicle_id");
        if (!rs.wasNull()) {
            booking.setVehiclesId(vehicleId);
            booking.setVehicleNumber(rs.getString("vehicle_number"));
        }
        
        int driverId = rs.getInt("driver_id");
        if (!rs.wasNull()) {
            booking.setDriverId(driverId);
        }
        
        return booking;
    }
    
    private void closeResources(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) mySql.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}