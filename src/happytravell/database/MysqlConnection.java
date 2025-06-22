/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class MysqlConnection implements DbConnection {
    
    // Database connection parameters
    // ===========================================
    // Using Environment Variables for security
    // Each developer should set their own environment variables
    // ===========================================
    private static final String URL = "jdbc:mysql://localhost:3306/happytravel";
    private static final String USERNAME = System.getenv("DB_USERNAME") != null ? System.getenv("DB_USERNAME") : "root";
    private static final String PASSWORD = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "";
    // ===========================================
    // Instructions for setting environment variables:
    // 1. Open Command Prompt as Administrator
    // 2. Run: setx DB_USERNAME "root"
    // 3. Run: setx DB_PASSWORD "your_mysql_password"
    // 4. Restart your IDE/Command Prompt
    // 5. Run the application
    // ===========================================
   
    
    @Override
    public Connection openConnection() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Create and return connection
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connected successfully!");
            return connection;
            
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Database connection closed successfully!");
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}