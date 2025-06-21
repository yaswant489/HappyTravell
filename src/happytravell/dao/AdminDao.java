/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.dao;

import happytravell.database.MysqlConnection;
import happytravell.model.AdminData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import happytravell.model.LoginRequest;
import happytravell.model.ResetPasswordRequest;


/**
 *
 * @author Acer
 */
public class AdminDao {
    MysqlConnection mysql = new MysqlConnection();
    public boolean Register(AdminData admin){
        Connection conn = mysql.openConnection();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS admin("
                    + "admin_ID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "first_name VARCHAR(100) NOT NULL,"
                    + "last_name VARCHAR(100) NOT NULL,"
                    + "username VARCHAR(100) NOT NULL,"
                    + "phone_number VARCHAR(15) NOT NULL,"
                    + "address VARCHAR(100) NOT NULL,"
                    + "email VARCHAR(100) UNIQUE NOT NULL,"
                    + "password VARCHAR(100) NOT NULL"
                + ")";
         String insertQuery = "INSERT INTO admin (first_name, last_name, email, address, phone_number, username, password) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
         try {   
                PreparedStatement createTable= conn.prepareStatement(createTableSQL);
                createTable.executeUpdate();
                PreparedStatement stmt = conn.prepareStatement(insertQuery);
                createTable.executeUpdate();
                stmt.setString(1, admin.getFirstName() != null ? admin.getFirstName() : "");
                stmt.setString(2, admin.getLastName() != null ? admin.getLastName() : "");
                stmt.setString(3, admin.getEmail() != null ? admin.getEmail() : "");
                stmt.setString(4, admin.getAddress() != null ? admin.getAddress() : "");
                stmt.setString(5, admin.getPhoneNumber() != null ? admin.getPhoneNumber() : "");
                stmt.setString(6, admin.getUsername() != null ? admin.getUsername() : "");
                stmt.setString(7, admin.getPassword() != null ? admin.getPassword() : "");

                int result = stmt.executeUpdate();
                return result > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    } finally {
        mysql.closeConnection(conn);
    }
    }
    
    public AdminData adminLogin(LoginRequest adminLoginData){
    String query = "SELECT * FROM admin WHERE email=? AND password=?";
    Connection conn = mysql.openConnection();
    try{
        PreparedStatement stmnt = conn.prepareStatement(query);
        stmnt.setString(1, adminLoginData.getEmail());
        stmnt.setString(2, adminLoginData.getPassword());
        
        // Always use executeQuery for SELECT statements
        ResultSet result = stmnt.executeQuery();
        System.out.println("Query executed successfully");
        
        // Result returns row if email and password match, else it returns empty
        if (result.next()){
            int id = result.getInt("admin_ID");
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name"); // Fixed: was using "first_name" twice
            String email = result.getString("email");
            String password = result.getString("password");
            
            // Wrapping the data in model - using the corrected constructor
            AdminData admin = new AdminData(id, firstName, lastName, email, password);
            return admin;
        } else {
            return null;
        }
    } catch(Exception e){
        System.out.println("Exception in adminLogin: " + e.getMessage());
        e.printStackTrace(); // Added for better debugging
        return null;
    } finally {
        mysql.closeConnection(conn);
    }   
}

public boolean checkEmail(String email) {
    String query = "SELECT 1 FROM admin WHERE email=?"; // More efficient query
    Connection conn = mysql.openConnection();
    try {
        PreparedStatement stmnt = conn.prepareStatement(query);
        stmnt.setString(1, email);
        ResultSet result = stmnt.executeQuery();
        
        return result.next(); // Simplified return statement
    } catch (Exception e) {
        System.out.println("Exception in checkEmail: " + e.getMessage());
        e.printStackTrace();
        return false;
    } finally {
        mysql.closeConnection(conn);
    }
}

    public boolean resetPassword(ResetPasswordRequest reset) {
        String query = "UPDATE admin SET password=? WHERE email=?";
        Connection conn = mysql.openConnection();
        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1, reset.getPassword());
            stmnt.setString(2, reset.getEmail());
            int result = stmnt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    
    public AdminData getAdminById(int adminId) {
    String query = "SELECT * FROM admin WHERE admin_ID = ?";
    Connection conn = mysql.openConnection();
    try {
        PreparedStatement stmnt = conn.prepareStatement(query);
        stmnt.setInt(1, adminId);
        ResultSet result = stmnt.executeQuery();
        
        if (result.next()) {
            // Fixed: Get ID as String to match AdminData field type
            int id = result.getInt("admin_ID");
            
            // Fixed: Split full_name or use separate first_name and last_name columns
            String firstName = result.getString("first_name"); // Changed from "full_name"
            String lastName = result.getString("last_name");
            String phoneNumber = result.getString("phone_number");
            String address = result.getString("address");
            String email = result.getString("email");
            String username = result.getString("username");
            String password = result.getString("password");
            byte[] profilePicture = result.getBytes("profile_picture");
            
            // Fixed: Use correct constructor with proper parameter order
            AdminData admin = new AdminData(id, firstName, lastName, username, email, phoneNumber, address, password);
            admin.setProfilePicture(profilePicture);
            return admin;
        }
    } catch (Exception e) {
        System.out.println("Exception in getAdminById: " + e.getMessage());
        e.printStackTrace(); // Added for better debugging
        return null;
    } finally {
        mysql.closeConnection(conn);
    }
    return null;
}
    
     public boolean updateAdminProfile(int adminId, String firstName, String lastName, String username, String phoneNumber, String email, String address) {
        String query = "UPDATE admin SET first_name = ?, last_name = ?, username = ?, phone_number = ?, email = ?, address = ? ,profile_picture = ? WHERE admin_ID = ?";
        Connection conn = mysql.openConnection();
        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1, firstName);
            stmnt.setString(2, lastName);
            stmnt.setString(3, username);
            stmnt.setString(4, phoneNumber);
            stmnt.setString(5, email);
            stmnt.setString(6, address);
            stmnt.setInt(7, adminId);
            
    
            int result = stmnt.executeUpdate();
            
            System.out.println("Update Admin profile result: " + result + " for admin ID: " + adminId);
            return result > 0;
        } catch (Exception e) {
            System.out.println("Error updating admin profile: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
     
     
    public byte[] getProfilePicture(int adminId) {
        String query = "SELECT profile_picture FROM admin WHERE admin_ID = ?";
        Connection conn = mysql.openConnection();
        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setInt(1, adminId);
            ResultSet result = stmnt.executeQuery();
            if (result.next()) {
                return result.getBytes("profile_picture");
            }
        } catch (Exception e) {
            return null;
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    } 

    public boolean updateProfilePicture(int currentAdminId, byte[] imageData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     
}
