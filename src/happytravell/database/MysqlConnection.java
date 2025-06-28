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
        try{
            String username="root";
            String password="yaswant";
            String database="happytravel";
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
            }
        }
        catch(Exception e){
            
        }
    }
    
}
