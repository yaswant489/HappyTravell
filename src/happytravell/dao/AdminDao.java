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


/**
 *
 * @author Acer
 */
public class AdminDao {
    MysqlConnection mysql = new MysqlConnection();
    public boolean Register(AdminData admin){
        String query = "INSERT INTO admin (first_name, last_name, email, address, phone_number, username, password)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = mysql.openConnection();
         try {
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, admin.getFirstName());
        stmt.setString(2, admin.getLastName());
        stmt.setString(3, admin.getEmail());
        stmt.setString(4, admin.getAddress());
        stmt.setString(5, admin.getPhoneNumber());
        stmt.setString(6, admin.getUsername());
        stmt.setString(7, admin.getPassword());


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
        String query= "SELECT * FROM admin WHERE email=? and password=?";
        Connection conn= mysql.openConnection();
        try{
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1, adminLoginData.getEmail());
            stmnt.setString(2, adminLoginData.getPassword());
            
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
                AdminData admin = new AdminData(id,name,email,password);
                return admin;
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
}
