
package happytravell.database;
//
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this te
//*/


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection implements DbConnection {
    // Database configuration
    private static final String URL = "jdbc:mysql://localhost:3306/happytravel";
    private static final String USER = "root";
    private static final String PASSWORD = "admin123";

    
    @Override
    public Connection openConnection() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            

            // Establish connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Failed to create database connection: " + e.getMessage());

            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void closeConnection(Connection conn) {

        try {
            if (conn != null && !conn.isClosed()) {

                conn.close();
                System.out.println("Database connection closed successfully!");
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.err.println("Failed to close database connection: " + e.getMessage());

        }
    }
}