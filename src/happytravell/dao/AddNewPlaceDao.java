/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.dao;

import happytravell.database.MysqlConnection;
import happytravell.model.TravellerData;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Acer
 */
public class AddNewPlaceDao {
    MysqlConnection mySql = new MysqlConnection();
    public boolean Register(TravellerData user){
        String query = "INSERT INTO route ( place_name,description,route)"
                + "VALUES (?,?,?)";
        Connection conn = mySql.openConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getplace_name());
            stmt.setString(2, user.getdescription());
            stmt.setString(3, user.getroute());

            int result = stmt.executeUpdate();
            return result > 0;
    }   catch (Exception e) {
            return false;
    }   finally {
        mySql.closeConnection(conn);
    }
    }
}
