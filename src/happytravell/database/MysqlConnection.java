
package happytravell.database;


import java.sql.*;

public class MysqlConnection implements DbConnection {
    // Database configuration
    private static final String URL = "jdbc:mysql://localhost:3306/happytravel";
    private static final String USER = "root";
    private static final String PASSWORD = "admin123";

    
    @Override
    public Connection openConnection() {
        try{
            String username="root";
            String password="admin123";
            String database="happytravel";
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
            if (conn!=null && !conn.isClosed()){
                conn.close();
            }
        }
        catch(Exception e){
            
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
