
package happytravell.database;


import java.sql.*;

public class MysqlConnection implements DbConnection {
    
    // Database connection parameters
    // ===========================================
    // Using Environment Variables for security
    // Each developer should set their own environment variables
    // ===========================================
    private static final String URL = "jdbc:mysql://localhost:3306/happytravel";
    private static final String USERNAME = "root"; // Set username directly
    private static final String PASSWORD = "yaswant"; // Set password directly
    // ===========================================
    // The application will now use the password "yaswant" by default.
    // You no longer need to use the run_with_env_vars.bat file.
    // ===========================================
   
    
    @Override
    public Connection openConnection() {
        try{
            String username="root";
            String password="admin123";
            String database="happytravel";
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
        try {
            if (conn!=null && !conn.isClosed()){
                conn.close();
            }
        }
        catch(Exception e){
            
        }
    }
    
   
}
