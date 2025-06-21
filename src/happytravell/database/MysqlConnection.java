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
    private static final String URL = "jdbc:mysql://localhost:3306/happytravell";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "yaswant";
    
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
            System.err.println("Please make sure mysql-connector-j-9.2.0.jar is in the lib directory and included in the classpath.");
            System.err.println("Run the application with: java -cp \"lib/*;build/classes\" happytravell.HappyTravell");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            System.err.println("Please check if MySQL server is running and the database 'happytravell' exists.");
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