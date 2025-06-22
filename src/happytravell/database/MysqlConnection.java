package happytravell.database;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this te
*/

import java.sql.*;

/**
 *
 * @author Acer
 */

public class MysqlConnection implements DbConnection{

    @Override
    public Connection openConnection() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ database,username,password);

            return conn;
           
        }
        catch(Exception e){
            return null;
        }
    }
    
    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn!=null && !conn.isClosed()){
                conn.close();
                System.out.println("Database connection closed successfully!");
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
        catch(Exception e){
            
        }
    }
}