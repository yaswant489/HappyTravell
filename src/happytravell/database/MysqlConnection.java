package happytravell.database;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import happytravell.database.DbConnection;
import java.sql.*;
import java.sql.Connection;

/**
 *
 * @author Acer
 */
public class MysqlConnection implements DbConnection{

    @Override
    public Connection openConnection() {
        try{
            String username="root";
            String password="Wj28@krhps";
            String database="happytravel";
            Class.forName("com.mysql.jdbc.Driver");
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306"+
                    database,username,password);
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
            }
        }
        catch(Exception e){
            
        }
    }
    
}
