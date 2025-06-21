package happytravell.dao;

import happytravell.database.MysqlConnection;
import happytravell.model.BookingData;
import happytravell.model.TravellerData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import happytravell.model.LoginRequest;
import happytravell.model.ResetPasswordRequest;
import java.util.ArrayList;
import java.util.List;

public class TravellerDao {
    private static final String TRAVELLER_TABLE = "traveller";
    private static final String BOOKING_TABLE = "bookingDetails";
    private final MysqlConnection mysql = new MysqlConnection();


    // SQL queries
    private static final String CREATE_TRAVELLER_TABLE = 
        "CREATE TABLE IF NOT EXISTS " + TRAVELLER_TABLE + "("
        + "traveller_ID INT AUTO_INCREMENT PRIMARY KEY,"
        + "first_name VARCHAR(100) NOT NULL,"
        + "last_name VARCHAR(100) NOT NULL,"
        + "username VARCHAR(100) NOT NULL,"
        + "phone_number VARCHAR(15) NOT NULL,"
        + "address VARCHAR(100) NOT NULL,"
        + "email VARCHAR(100) UNIQUE NOT NULL,"
        + "password VARCHAR(100) NOT NULL,"
        + "profile_picture MEDIUMBLOB"
        + ")";

    private static final String INSERT_TRAVELLER = 
        "INSERT INTO " + TRAVELLER_TABLE + " (first_name, last_name, username, phone_number, "
        + "address, email, password, profile_picture) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_BOOKINGS_WITH_IMAGES = 
        "SELECT b.booking_ID, b.pickup_address, b.drop_address, b.departure_date_time, "
        + "b.return_date_time, b.passenger_number, b.vehicle_number, b.driver_name, "
        + "t.traveller_ID, t.first_name, t.last_name, t.profile_picture "
        + "FROM " + BOOKING_TABLE + " b "
        + "JOIN " + TRAVELLER_TABLE + " t ON b.traveller_ID = t.traveller_ID "
        + "WHERE t.profile_picture IS NOT NULL";

    private static final String LOGIN_QUERY = 
        "SELECT traveller_ID, first_name, last_name, username, phone_number, "
        + "address, email, password, profile_picture "
        + "FROM " + TRAVELLER_TABLE + " WHERE email=? AND password=?";

    private static final String CHECK_EMAIL_QUERY = 
        "SELECT 1 FROM " + TRAVELLER_TABLE + " WHERE email=?";

    private static final String RESET_PASSWORD_QUERY = 
        "UPDATE " + TRAVELLER_TABLE + " SET password=? WHERE email=?";

    private static final String GET_TRAVELLER_BY_ID = 
        "SELECT traveller_ID, first_name, last_name, username, phone_number, "
        + "address, email, password, profile_picture "
        + "FROM " + TRAVELLER_TABLE + " WHERE traveller_ID=?";

    private static final String GET_BOOKINGS_BY_TRAVELLER = 
        "SELECT booking_ID, pickup_address, drop_address, departure_date_time, "
        + "return_date_time, passenger_number, vehicle_number, driver_name "
        + "FROM " + BOOKING_TABLE + " WHERE traveller_ID=?";

    public boolean register(TravellerData traveller) {
        Connection conn = null;
        PreparedStatement createTableStmt = null;
        PreparedStatement insertStmt = null;

        
        try {
            conn = mysql.openConnection();
            
            // Create table if not exists
            createTableStmt = conn.prepareStatement(CREATE_TRAVELLER_TABLE);
            createTableStmt.executeUpdate();
            
            // Insert traveller data
            insertStmt = conn.prepareStatement(INSERT_TRAVELLER);
            insertStmt.setString(1, traveller.getFirstName());
            insertStmt.setString(2, traveller.getLastName());
            insertStmt.setString(3, traveller.getUsername());
            insertStmt.setString(4, traveller.getPhoneNumber());
            insertStmt.setString(5, traveller.getAddress());
            insertStmt.setString(6, traveller.getEmail());
            insertStmt.setString(7, traveller.getPassword());
            insertStmt.setBytes(8, traveller.getProfilePicture());
            
            return insertStmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(createTableStmt, insertStmt);
            mysql.closeConnection(conn);
        }
    }

    public List<BookingData> getAllBookingDetailsWithImage() {
        List<BookingData> bookingList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_BOOKINGS_WITH_IMAGES);
            rs = stmt.executeQuery();
            

            while (rs.next()) {
                // Create TravellerData
                TravellerData traveller = new TravellerData();
                traveller.setTravellerID(rs.getInt("traveller_ID"));
                traveller.setFirstName(rs.getString("first_name"));
                traveller.setLastName(rs.getString("last_name"));
                traveller.setProfilePicture(rs.getBytes("profile_picture"));
                
                // Create BookingData
                BookingData booking = new BookingData();
                booking.setBookingId(rs.getInt("booking_ID"));
                booking.setPickupAddress(rs.getString("pickup_address"));
                booking.setDropAddress(rs.getString("drop_address"));
                booking.setDepartureDateTime(rs.getString("departure_date_time"));
                booking.setReturnDateTime(rs.getString("return_date_time"));
                booking.setPassengerCount(rs.getInt("passenger_number"));
                booking.setVehicleNumber(rs.getString("vehicle_number"));
                booking.setDriverName(rs.getString("driver_name"));
                booking.setTravellerId(traveller.getTravellerID());

                
                bookingList.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return bookingList;
    }
    
    public List<BookingData> getBookingsByTravellerId(int travellerId) {
        List<BookingData> bookings = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_BOOKINGS_BY_TRAVELLER);
            stmt.setInt(1, travellerId);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                BookingData booking = new BookingData();
                booking.setBookingId(rs.getInt("booking_ID"));
                booking.setPickupAddress(rs.getString("pickup_address"));
                booking.setDropAddress(rs.getString("drop_address"));
                booking.setDepartureDateTime(rs.getString("departure_date_time"));
                booking.setReturnDateTime(rs.getString("return_date_time"));
                booking.setPassengerCount(rs.getInt("passenger_number"));
                booking.setVehicleNumber(rs.getString("vehicle_number"));
                booking.setDriverName(rs.getString("driver_name"));
                booking.setTravellerId(travellerId);
                
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return bookings;
    }
    
    public TravellerData travellerLogin(LoginRequest loginRequest) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(LOGIN_QUERY);
            stmt.setString(1, loginRequest.getEmail());
            stmt.setString(2, loginRequest.getPassword());
            
            rs = stmt.executeQuery();
            

            if (rs.next()) {
                TravellerData traveller = new TravellerData();
                traveller.setTravellerID(rs.getInt("traveller_ID"));
                traveller.setFirstName(rs.getString("first_name"));
                traveller.setLastName(rs.getString("last_name"));
                traveller.setUsername(rs.getString("username"));
                traveller.setPhoneNumber(rs.getString("phone_number"));
                traveller.setAddress(rs.getString("address"));
                traveller.setEmail(rs.getString("email"));
                traveller.setPassword(rs.getString("password"));
                traveller.setProfilePicture(rs.getBytes("profile_picture"));
                

                return traveller;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return null;
    }
    
    public boolean checkEmail(String email) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(CHECK_EMAIL_QUERY);
            stmt.setString(1, email);
            
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
    }
    
    public boolean resetPassword(ResetPasswordRequest resetRequest) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(RESET_PASSWORD_QUERY);
            stmt.setString(1, resetRequest.getPassword());
            stmt.setString(2, resetRequest.getEmail());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }
    
    public TravellerData getTravellerById(int travellerId) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_TRAVELLER_BY_ID);
            stmt.setInt(1, travellerId);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                TravellerData traveller = new TravellerData();
                traveller.setTravellerID(rs.getInt("traveller_ID"));
                traveller.setFirstName(rs.getString("first_name"));
                traveller.setLastName(rs.getString("last_name"));
                traveller.setUsername(rs.getString("username"));
                traveller.setPhoneNumber(rs.getString("phone_number"));
                traveller.setAddress(rs.getString("address"));
                traveller.setEmail(rs.getString("email"));
                traveller.setPassword(rs.getString("password"));
                traveller.setProfilePicture(rs.getBytes("profile_picture"));
                
                return traveller;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return null;
    }
    
    private void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}