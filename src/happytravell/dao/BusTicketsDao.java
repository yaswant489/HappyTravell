package happytravell.dao;

import happytravell.database.MysqlConnection;
import happytravell.model.BusTicketsData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BusTicketsDao {
    private static final String BUS_TICKETS_TABLE = "bus_Tickets";
    private static final String BUS_SEATS_TABLE = "bus_seats";
    
    private MysqlConnection mysql = new MysqlConnection();

    // FIXED: Added proper foreign key constraint for traveller_id
    private static final String CREATE_BUS_TICKETS_TABLE = 
        "CREATE TABLE IF NOT EXISTS " + BUS_TICKETS_TABLE + "("
        + "id INT AUTO_INCREMENT PRIMARY KEY,"
        + "name VARCHAR(100) NOT NULL,"
        + "phone_number VARCHAR(50) NOT NULL,"
        + "bus_number VARCHAR(20) NOT NULL,"
        + "pickup_address TEXT NOT NULL,"
        + "drop_address TEXT NOT NULL,"
        + "departure_date_time DATETIME NOT NULL,"
        + "return_date_time DATETIME,"
        + "travel_date DATE NOT NULL,"
        + "seat_number VARCHAR(50) NOT NULL,"
        + "traveller_id INT,"
        + "FOREIGN KEY (seat_number) REFERENCES " + BUS_SEATS_TABLE + "(seat_number) ON DELETE CASCADE,"
        + "FOREIGN KEY (traveller_id) REFERENCES traveller(traveller_ID) ON DELETE CASCADE,"
        + "UNIQUE KEY unique_seat_booking (travel_date, seat_number)"
        + ")";

    private static final String CREATE_BUS_SEATS_TABLE = 
        "CREATE TABLE IF NOT EXISTS " + BUS_SEATS_TABLE + "("
        + "seat_number VARCHAR(50) PRIMARY KEY,"
        + "status ENUM('PENDING', 'ACTIVE', 'AVAILABLE') DEFAULT 'AVAILABLE'"
        + ")";

    private static final String INSERT_SEATS = 
        "INSERT IGNORE INTO " + BUS_SEATS_TABLE + " (seat_number) VALUES " +
        "('A1'), ('A2'), ('A3'), ('A4'), ('A5'), ('A6'), ('A7'), ('A8'), ('A9'), ('A10'), ('A11'), ('A12'), ('A13'), ('A14')," +
        "('B1'), ('B2'), ('B3'), ('B4'), ('B5'), ('B6'), ('B7'), ('B8'), ('B9'), ('B10'), ('B11'), ('B12'), ('B13'), ('B14')";

    private static final String ADD_BUS_TICKET = 
        "INSERT INTO " + BUS_TICKETS_TABLE + " (name, phone_number, bus_number, pickup_address, "
        + "drop_address, departure_date_time, return_date_time, travel_date, seat_number, traveller_id) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ALL_BUS_TICKETS = 
        "SELECT * FROM " + BUS_TICKETS_TABLE;

    private static final String GET_AVAILABLE_SEATS = 
        "SELECT seat_number FROM " + BUS_SEATS_TABLE + " WHERE seat_number NOT IN "
        + "(SELECT seat_number FROM " + BUS_TICKETS_TABLE + " WHERE travel_date = ?) "
        + "AND status = 'AVAILABLE'";

    private static final String GET_BOOKED_SEATS = 
        "SELECT seat_number FROM " + BUS_TICKETS_TABLE + " WHERE travel_date = ?";

    private static final String UPDATE_SEAT_STATUS = 
        "UPDATE " + BUS_SEATS_TABLE + " SET status = ? WHERE seat_number = ?";

    private static final String GET_TICKET_BY_ID = 
        "SELECT * FROM " + BUS_TICKETS_TABLE + " WHERE id = ?";

    private static final String DELETE_TICKET = 
        "DELETE FROM " + BUS_TICKETS_TABLE + " WHERE id = ?";

    // Initialize database tables
    private void initializeTables() {
        Connection conn = null;
        PreparedStatement createTicketsTableStmt = null;
        PreparedStatement createSeatsTableStmt = null;
        PreparedStatement insertSeatsStmt = null;

        try {
            conn = mysql.openConnection();
            
            // Create bus_seats table first (referenced by bus_Tickets)
            createSeatsTableStmt = conn.prepareStatement(CREATE_BUS_SEATS_TABLE);
            createSeatsTableStmt.executeUpdate();
            
            // Insert default seats
            insertSeatsStmt = conn.prepareStatement(INSERT_SEATS);
            insertSeatsStmt.executeUpdate();
            
            // Create bus_Tickets table (requires traveller table to exist first)
            createTicketsTableStmt = conn.prepareStatement(CREATE_BUS_TICKETS_TABLE);
            createTicketsTableStmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error initializing tables: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(createTicketsTableStmt, createSeatsTableStmt, insertSeatsStmt);
            mysql.closeConnection(conn);
        }
    }

    // Add bus ticket with traveller_id validation
    public boolean addBusTicket(BusTicketsData ticket, int travellerId) {
        initializeTables(); // Ensure tables exist
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            
            
            
            stmt = conn.prepareStatement(ADD_BUS_TICKET);
            stmt.setString(1, ticket.getName());
            stmt.setString(2, ticket.getPhoneNumber());
            stmt.setString(3, ticket.getBusNumber());
            stmt.setString(4, ticket.getPickupAddress());
            stmt.setString(5, ticket.getDropAddress());
            stmt.setTimestamp(6, ticket.getDepartureDateTime());
            stmt.setTimestamp(7, ticket.getReturnDateTime());
            stmt.setString(8, ticket.getTravelDate());
            stmt.setString(9, ticket.getSeatNumber());
            
            // Handle null traveller_id properly
            if (travellerId > 0) {
                stmt.setInt(10, travellerId);
            } else {
                stmt.setNull(10, java.sql.Types.INTEGER);
            }
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding bus ticket: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }
    
    
    
    // Get available seats for a specific date
    public List<String> getAvailableSeats(String travelDate) {
        initializeTables(); // Ensure tables exist
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> availableSeats = new ArrayList<>();
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_AVAILABLE_SEATS);
            stmt.setString(1, travelDate);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                availableSeats.add(rs.getString("seat_number"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting available seats: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return availableSeats;
    }
    
    // Get booked seats for a specific date
    public List<String> getBookedSeats(String travelDate) {
        initializeTables(); // Ensure tables exist
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> bookedSeats = new ArrayList<>();
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_BOOKED_SEATS);
            stmt.setString(1, travelDate);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                bookedSeats.add(rs.getString("seat_number"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting booked seats: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return bookedSeats;
    }
    
    // Update seat status
    public boolean updateSeatStatus(String seatNumber, String status) {
        initializeTables(); // Ensure tables exist
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(UPDATE_SEAT_STATUS);
            stmt.setString(1, status);
            stmt.setString(2, seatNumber);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating seat status: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }
    
    // Get ticket by ID
    public BusTicketsData getTicketById(int id) {
        initializeTables(); // Ensure tables exist
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(GET_TICKET_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                BusTicketsData ticket = new BusTicketsData(
                    rs.getString("name"),
                    rs.getString("phone_number"),
                    rs.getString("bus_number"),
                    rs.getString("pickup_address"),
                    rs.getString("drop_address"),
                    rs.getTimestamp("departure_date_time"),
                    rs.getTimestamp("return_date_time"),
                    rs.getString("travel_date"),
                    rs.getString("seat_number")
                );
                ticket.setId(rs.getInt("id"));
                return ticket;
            }
        } catch (SQLException e) {
            System.err.println("Error getting ticket by ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return null;
    }
    
    // Delete ticket
    public boolean deleteTicket(int id) {
        initializeTables(); // Ensure tables exist
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(DELETE_TICKET);
            stmt.setInt(1, id);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting ticket: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }

    private void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    System.err.println("Error closing resource: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
    
    // Get booked seats for specific vehicle and date
    public List<String> getBookedSeatsForVehicle(String vehicleNumber, String date) {
        initializeTables(); // Ensure tables exist
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> bookedSeats = new ArrayList<>();
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement("SELECT seat_number FROM " + BUS_TICKETS_TABLE + 
                                        " WHERE bus_number = ? AND travel_date = ?");
            stmt.setString(1, vehicleNumber);
            stmt.setString(2, date);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                bookedSeats.add(rs.getString("seat_number"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting booked seats for vehicle: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return bookedSeats;
    }

    // Get available seats for specific vehicle and date
    public List<String> getAvailableSeatsForVehicle(String vehicleNumber, String date) {
        initializeTables(); // Ensure tables exist
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> availableSeats = new ArrayList<>();
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(
                "SELECT s.seat_number FROM " + BUS_SEATS_TABLE + " s " +
                "WHERE s.seat_number NOT IN (" +
                "    SELECT t.seat_number FROM " + BUS_TICKETS_TABLE + " t " +
                "    WHERE t.bus_number = ? AND t.travel_date = ?" +
                ") AND s.status = 'AVAILABLE'");
            stmt.setString(1, vehicleNumber);
            stmt.setString(2, date);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                availableSeats.add(rs.getString("seat_number"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting available seats for vehicle: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return availableSeats;
    }

    // Get tickets by traveller ID
    public List<BusTicketsData> getTicketsByTravellerId(int travellerId) {
        initializeTables();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<BusTicketsData> tickets = new ArrayList<>();
        
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement("SELECT * FROM " + BUS_TICKETS_TABLE + " WHERE traveller_id = ?");
            stmt.setInt(1, travellerId);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                BusTicketsData ticket = new BusTicketsData(
                    rs.getString("name"),
                    rs.getString("phone_number"),
                    rs.getString("bus_number"),
                    rs.getString("pickup_address"),
                    rs.getString("drop_address"),
                    rs.getTimestamp("departure_date_time"),
                    rs.getTimestamp("return_date_time"),
                    rs.getString("travel_date"),
                    rs.getString("seat_number")
                );
                ticket.setId(rs.getInt("id"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.err.println("Error getting tickets by traveller ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return tickets;
    }

 // Update seat status for a specific vehicle (alternative method)
    public boolean updateSeatStatusForVehicle(String vehicleNumber, String seatNumber, String status) {
        initializeTables();
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = mysql.openConnection();
            // Note: This updates the general seat status, not vehicle-specific
            // If you need vehicle-specific seat status, you'll need to modify your database schema
            stmt = conn.prepareStatement(UPDATE_SEAT_STATUS);
            stmt.setString(1, status);
            stmt.setString(2, seatNumber);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating seat status for vehicle: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }
    
    
    // Add this method to BusTicketsDao.java
public List<BusTicketsData> getAllBusTickets() {
    initializeTables(); // Ensure tables exist
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<BusTicketsData> tickets = new ArrayList<>();
    
    try {
        conn = mysql.openConnection();
        stmt = conn.prepareStatement(GET_ALL_BUS_TICKETS);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            BusTicketsData ticket = new BusTicketsData(
                rs.getString("name"),
                rs.getString("phone_number"),
                rs.getString("bus_number"),
                rs.getString("pickup_address"),
                rs.getString("drop_address"),
                rs.getTimestamp("departure_date_time"),
                rs.getTimestamp("return_date_time"),
                rs.getString("travel_date"),
                rs.getString("seat_number")
            );
            ticket.setId(rs.getInt("id"));
            tickets.add(ticket);
        }
    } catch (SQLException e) {
        System.err.println("Error getting all bus tickets: " + e.getMessage());
        e.printStackTrace();
    } finally {
        closeResources(rs, stmt);
        mysql.closeConnection(conn);
    }
    
    return tickets;
}
    
}