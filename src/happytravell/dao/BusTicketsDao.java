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

    // SQL queries
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
        + "FOREIGN KEY (seat_number) REFERENCES " + BUS_SEATS_TABLE + "(seat_number) ON DELETE CASCADE,"
        + "UNIQUE KEY unique_seat_booking (travel_date, seat_number)"
        + ")";

    private static final String CREATE_BUS_SEATS_TABLE = 
        "CREATE TABLE IF NOT EXISTS " + BUS_SEATS_TABLE + "("
        + "seat_number VARCHAR(50) PRIMARY KEY,"
        + "account_status ENUM('PENDING', 'ACTIVE', 'AVAILABLE') DEFAULT 'AVAILABLE'"
        + ")";

    private static final String INSERT_SEATS = 
        "INSERT IGNORE INTO " + BUS_SEATS_TABLE + " (seat_number) VALUES " +
        "('A1'), ('A2'), ('A3'), ('A4'), ('A5'), ('A6'), ('A7'), ('A8'), ('A9'), ('A10'), ('A11'), ('A12'), ('A13'), ('A14')," +
        "('B1'), ('B2'), ('B3'), ('B4'), ('B5'), ('B6'), ('B7'), ('B8'), ('B9'), ('B10'), ('B11'), ('B12'), ('B13'), ('B14')";

    private static final String ADD_BUS_TICKET = 
        "INSERT INTO " + BUS_TICKETS_TABLE + " (name, phone_number, bus_number, pickup_address, "
        + "drop_address, departure_date_time, return_date_time, travel_date, seat_number) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ALL_BUS_TICKETS = 
        "SELECT * FROM " + BUS_TICKETS_TABLE;

    private static final String GET_AVAILABLE_SEATS = 
        "SELECT seat_number FROM " + BUS_SEATS_TABLE + " WHERE seat_number NOT IN "
        + "(SELECT seat_number FROM " + BUS_TICKETS_TABLE + " WHERE travel_date = ?) "
        + "AND account_status = 'AVAILABLE'";

    private static final String GET_BOOKED_SEATS = 
        "SELECT seat_number FROM " + BUS_TICKETS_TABLE + " WHERE travel_date = ?";

    private static final String UPDATE_SEAT_STATUS = 
        "UPDATE " + BUS_SEATS_TABLE + " SET account_status = ? WHERE seat_number = ?";

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
            
            // Create bus_Tickets table
            createTicketsTableStmt = conn.prepareStatement(CREATE_BUS_TICKETS_TABLE);
            createTicketsTableStmt.executeUpdate();
            
            // Create bus_seats table
            createSeatsTableStmt = conn.prepareStatement(CREATE_BUS_SEATS_TABLE);
            createSeatsTableStmt.executeUpdate();
            
            // Insert default seats
            insertSeatsStmt = conn.prepareStatement(INSERT_SEATS);
            insertSeatsStmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(createTicketsTableStmt, createSeatsTableStmt, insertSeatsStmt);
            mysql.closeConnection(conn);
        }
    }

    // Add a new bus ticket
    public boolean addBusTicket(BusTicketsData ticket) {
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
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(stmt);
            mysql.closeConnection(conn);
        }
    }
    
    // Get all bus tickets
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
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
            mysql.closeConnection(conn);
        }
        
        return tickets;
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
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    // Add these methods to BusTicketsDao
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
        e.printStackTrace();
    } finally {
        closeResources(rs, stmt);
        mysql.closeConnection(conn);
    }
    
    return bookedSeats;
}

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
            ")");
        stmt.setString(1, vehicleNumber);
        stmt.setString(2, date);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            availableSeats.add(rs.getString("seat_number"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources(rs, stmt);
        mysql.closeConnection(conn);
    }
    
    return availableSeats;
}

public boolean updateSeatStatus(String vehicleNumber, String seatNumber, String status) {
    initializeTables(); // Ensure tables exist
    Connection conn = null;
    PreparedStatement stmt = null;
    
    try {
        conn = mysql.openConnection();
        stmt = conn.prepareStatement("UPDATE " + BUS_TICKETS_TABLE + 
                                    " SET status = ? WHERE bus_number = ? AND seat_number = ?");
        stmt.setString(1, status);
        stmt.setString(2, vehicleNumber);
        stmt.setString(3, seatNumber);
        
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        closeResources(stmt);
        mysql.closeConnection(conn);
    }
}
}