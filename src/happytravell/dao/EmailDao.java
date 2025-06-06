/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.dao;

import happytravell.database.MySqlConnection;
import java.sql.PreparedStatement;
import happytravell.model.TravellerData;
import java.sql.*;

/**
 *
 * @author Acer
 */
public class EmailDao {
    MySqlConnection mySql = new MySqlConnection();
    public boolean Register(TravellerData user){
        String query = "INSERT INTO login_email ( email,password)"
                + "VALUES (?,?)";
        Connection conn = mySql.openConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());

            int result = stmt.executeUpdate();
            return result > 0;
    }   catch (Exception e) {
            return false;
    }   finally {
        mySql.closeConnection(conn);
    }
    }
}
