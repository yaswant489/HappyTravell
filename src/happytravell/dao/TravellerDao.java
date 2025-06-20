/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.dao;
import happytravell.database.MysqlConnection;
import happytravell.model.TravellerData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import happytravell.model.LoginRequest;
import happytravell.model.ResetPasswordRequest;
/**
 *
 * @author User
 */
public class TravellerDao {
    MysqlConnection mysql = new MysqlConnection();
    public boolean Register(TravellerData traveller){
        Connection conn = mysql.openConnection();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS traveller("
                    + "traveller_ID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "first_name VARCHAR(100) NOT NULL,"
                    + "last_name VARCHAR(100) NOT NULL,"
                    + "username VARCHAR(100) NOT NULL,"
                    + "phone_number VARCHAR(15) NOT NULL,"
                    + "address VARCHAR(100) NOT NULL,"
                    + "email VARCHAR(100) UNIQUE NOT NULL,"
                    + "password VARCHAR(100) NOT NULL"
                + ")";
         String insertQuery = "INSERT INTO traveller (first_name, last_name, email, address, phone_number, username, password) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
         try {   
                PreparedStatement createTable= conn.prepareStatement(createTableSQL);
                createTable.executeUpdate();
                PreparedStatement stmt = conn.prepareStatement(insertQuery);
                createTable.executeUpdate();
                stmt.setString(1, traveller.getFirstName() != null ? traveller.getFirstName() : "");
                stmt.setString(2, traveller.getLastName() != null ? traveller.getLastName() : "");
                stmt.setString(3, traveller.getEmail() != null ? traveller.getEmail() : "");
                stmt.setString(4, traveller.getAddress() != null ? traveller.getAddress() : "");
                stmt.setString(5, traveller.getPhoneNumber() != null ? traveller.getPhoneNumber() : "");
                stmt.setString(6, traveller.getUsername() != null ? traveller.getUsername() : "");
                stmt.setString(7, traveller.getPassword() != null ? traveller.getPassword() : "");

                int result = stmt.executeUpdate();
                return result > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    } finally {
        mysql.closeConnection(conn);
    }
    }
    
     public TravellerData travellerLogin(LoginRequest travellerLoginData){
        String query= "SELECT * FROM traveller WHERE email=? and password=?";
        Connection conn= mysql.openConnection();
        try{
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1, travellerLoginData.getEmail());
            stmnt.setString(2, travellerLoginData.getPassword());
            
            // always use excuteQuery for select
            ResultSet result= stmnt.executeQuery();
            System.out.println("Result:"+result);
//            result returns row if email and password matches else it returns null;
            if (result.next()){
//                retrieving value from resultset
                String name = result.getString("first_name"); // use the name of column in database
                String id = result.getString("traveller_ID");
                String email = result.getString("email");
                String password = result.getString("password");
//                wrapping the data in model
                TravellerData traveller = new TravellerData(id,name,email,password);
                return traveller;
            } else{
                return null;
            }
        } catch(Exception e){
            System.out.println("Exception "+e);
            return null;
        } finally {
            mysql.closeConnection(conn);
        }   
        
    }
    public boolean checkEmail(String email) {
        String query = "SELECT * FROM traveller WHERE email=?";
        Connection conn = mysql.openConnection();
        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1, email);
            ResultSet result = stmnt.executeQuery();
            // return result.next();
            if (result.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean resetPassword(ResetPasswordRequest reset) {
        String query = "UPDATE traveller SET password=? WHERE email=?";
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
 
}
